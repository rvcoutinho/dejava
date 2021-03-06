package org.dejava.component.serialization.xml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.reflection.ClassMirror;
import org.dejava.component.reflection.exception.InvocationException;
import org.dejava.component.serialization.xml.annotation.XMLComplexNode;
import org.dejava.component.serialization.xml.constant.ErrorKeys;
import org.dejava.component.serialization.xml.exception.XMLConversionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.xml.internal.ws.client.sei.MethodHandler;

/**
 * Helps handling XML creation/conversion.
 * 
 * There are to basic routines: fromXML() e toXML(). The conversions are made to be the simpler they can be.
 * 
 * In the first place, it is useful to understand a simple concept: complex and text objects. Complex objects
 * are the ones that cannot be evaluated from a single text. So, text objects are represented by a simple tag,
 * with a simple text value. Complex objects are represented by a tag that has child tags.
 * 
 * In addition, complex objects can be of two types (for now): regular and collection types. In regular types,
 * every field of the object is represented by a child tag (the tag name is the same as the field name). In
 * collection types, each child tag represents an object of the array/Collection. TODO Arrays are not
 * supported yet.
 * 
 * Then, there are a few special (XML) attributes used in the conversions: {@link #CLASS_ATTR},
 * {@link #ID_ATTR} and the ones related to JNDI objects.
 * 
 * The first one is the defined at {@link #CLASS_ATTR}. This attribute specifies which class represents any
 * given node. In fromXML, the class will be used to find the right constructor for the object. In toXML,
 * class attribute will only be set if the object class is different than the expected one (if it is a field).
 * In toXML, the first node class might be given in the method.
 * 
 * The second is the one defined at {@link #ID_ATTR}. It is used to prevent infinity cycles. So, only the
 * first occurrence of an object is filled with its value (for text or complex objects). The next occurrences
 * will just have the same id attribute value (and will represent the same instance in memory). In fromXML,
 * all objects stored in a map; so, when the id attribute is found, the object value is picked from there and
 * not created again.
 * 
 * The other attributes are related to JNDI objects. See {@link #getNodeJNDIObjectValue} for more information
 * about them. They are only used in fromXML.
 * 
 * In toXML, all classes that have a one string parameter constructor are considered simple objects (unless
 * they are annotated with {@link XMLComplexNode}).
 */
public final class XMLDecoder {
	
	/**
	 * Private constructor.
	 */
	private XMLDecoder() {
	}
	
	/**
	 * Represents the node id attribute name.
	 */
	public static final String ID_ATTR = "id";
	
	/**
	 * Gets the node id.
	 * 
	 * @param node
	 *            Node to get the id from.
	 * @return The node id.
	 */
	private static String getNodeId(final Node node) {
		// Tries to return the value of attribute ID_ATTR from the given node.
		try {
			return node.getAttributes().getNamedItem(ID_ATTR).getTextContent();
		}
		// If it is not possible to get the id.
		catch (final Exception exception) {
			// Returns null.
			return null;
		}
	}
	
	/**
	 * Represents the node class attribute name.
	 */
	public static final String CLASS_ATTR = "class";
	
	/**
	 * Gets the node class.
	 * 
	 * @param node
	 *            Node to get the class from.
	 * @param probableClass
	 *            Probable class to be used for a node. It is used when no class attribute is given.
	 * @return The node class.
	 */
	private static ClassMirror<?> getNodeClass(final Node node, final Class<?> probableClass) {
		// Tries to get the node class.
		try {
			// Gets the value of attribute "id" from the given node.
			final String nodeClassName = node.getAttributes().getNamedItem(CLASS_ATTR).getTextContent();
			// Tries to get the class for the name.
			final ClassMirror<?> classeNo = new ClassMirror<>(nodeClassName);
			// Returns the class.
			return classeNo;
		}
		// If an exception is thrown.
		catch (final Exception exception) {
			// Returns the probable class.
			return new ClassMirror<>(probableClass);
		}
	}
	
	/**
	 * Returns if the node represents a collection.
	 * 
	 * @param node
	 *            node to be tested.
	 * @param probableClass
	 *            Probable class to be used for a node. It is used when no class attribute is given.
	 * @return If the node represents a collection.
	 */
	private static Boolean isCollection(final Node node, final Class<?> probableClass) {
		// Tries to return if the node is a collection.
		try {
			return Collection.class.isAssignableFrom(getNodeClass(node, probableClass).getReflectedClass());
		}
		// If an exception is thrown.
		catch (final Exception exception) {
			// Return false.
			return false;
		}
	}
	
	/**
	 * Adds a node object to a collection.
	 * 
	 * @param collection
	 *            Collection where the object must be added.
	 * @param node
	 *            Node that represents the object to be added in the collection.
	 * @param object
	 *            Initial value for the object that represents the node.
	 * @param probableClass
	 *            Probable class to be used for a node. It is used when no class attribute is given.
	 * @param objectsUnderConversion
	 *            Map of objects that are under conversion. Used to prevent cycles and match object references
	 *            (id).
	 * @throws XMLConversionException
	 *             If the conversion is not possible.
	 * @throws EmptyParameterException
	 *             If the node is null.
	 */
	private static void addNodeObjectToCollection(final Collection<Object> collection, final Node node,
			final Object object, final Class<?> probableClass,
			final Map<String, Object> objectsUnderConversion) throws XMLConversionException,
			EmptyParameterException {
		// Gets the value of the node.
		final Object nodeValue = fromXML(node, object, probableClass, objectsUnderConversion);
		// If the object value is not null.
		if (nodeValue != null) {
			// Adds it to the collection.
			collection.add(nodeValue);
		}
	}
	
	/**
	 * Name of the node attribute that represents the JNDI object to get the initial value of a node object.
	 */
	public static final String JNDI_OBJ_ATTR = "jndiObject";
	
	/**
	 * Name of the node attribute that represents the JNDI object method to get the initial value of a node
	 * object.
	 */
	public static final String JNDI_OBJ_METHOD_ATTR = "jndiMethod";
	
	/**
	 * Name of the node attribute that represents the class of JNDI object method parameter to get the initial
	 * value of a node object.
	 */
	public static final String JNDI_OBJ_METHOD_PARAM_CLASS_ATTR = "jndiParameterClass";
	
	/**
	 * Name of the node attribute that represents the value of JNDI object method parameter to get the initial
	 * value of a node object.
	 */
	public static final String JNDI_OBJ_METHOD_PARAM_VALUE_ATTR = "jndiParameterValue";
	
	/**
	 * Gets the value of a node object via JNDI.
	 * 
	 * If a JNDI object attribute is given but not a method name, the JNDI object itself is returned.
	 * 
	 * If a JNDI method name is given, each attribute that starts with the
	 * {@link #JNDI_OBJ_METHOD_PARAM_CLASS_ATTR} and has a sequential number (starting with 1) will be used as
	 * a parameter class. For instance.: jndiParameterClassX='classX' (where X would be 1, 2, 3...).
	 * 
	 * For each given parameter class, there should be a parameter value in the attribute
	 * {@link #JNDI_OBJ_METHOD_PARAM_VALUE_ATTR} followed by sequential numbers too. For instance:
	 * jndiParameterValueX='valueX' (where X would be 1, 2, 3...). If the value is not given but the class is,
	 * the null is used. The value is converted to the parameter class through a constructor that takes one
	 * string parameter.
	 * 
	 * @param node
	 *            Object node to get the object via JNDI.
	 * @return The value of a node object via JNDI.
	 * @throws XMLConversionException
	 *             If the conversion is not possible.
	 */
	private static Object getNodeJNDIObjectValue(final Node node) throws XMLConversionException {
		// Tries to get the value of a node object via JNDI.
		try {
			// Gets the name of the JNDI object.
			final String jndiObjectName = node.getAttributes().getNamedItem(JNDI_OBJ_ATTR).getTextContent();
			// Tries to get the JNDI object for the name.
			final Object jndiObject = InitialContext.doLookup(jndiObjectName);
			// If there is a method defined for getting the object via JNDI.
			if ((node.getAttributes() != null)
					&& (node.getAttributes().getNamedItem(JNDI_OBJ_METHOD_ATTR) != null)) {
				// Classes of JNDI method parameters.
				final List<Class<?>> jndiParamsClasses = new ArrayList<Class<?>>();
				// For each given parameter class.
				for (Integer currentParamIndex = 1;; currentParamIndex++) {
					// Tries to get the current parameter class.
					try {
						// Gets the current parameter class name.
						final String currentJNDIParameterClass = node.getAttributes()
								.getNamedItem(JNDI_OBJ_METHOD_PARAM_CLASS_ATTR + currentParamIndex)
								.getTextContent();
						// Tries to get the class for the name and add it to the
						// list.
						jndiParamsClasses.add(ClassHandler.getClass(currentJNDIParameterClass));
					}
					// If the current parameter class is not given as attribute.
					catch (final NullPointerException exception) {
						// Stops searching for parameters.
						break;
					}
				}
				// Values of JNDI method parameters.
				final List<Object> jndiParamsValues = new ArrayList<Object>();
				// For each JNDI parameter class.
				for (Integer currentParamIndex = 1; currentParamIndex <= jndiParamsClasses.size(); currentParamIndex++) {
					// If the current parameter value is given.
					if ((node.getAttributes() != null)
							&& (node.getAttributes().getNamedItem(
									JNDI_OBJ_METHOD_PARAM_VALUE_ATTR + currentParamIndex) != null)) {
						// Gets the current parameter text value.
						Object currentParamValue = node.getAttributes()
								.getNamedItem(JNDI_OBJ_METHOD_PARAM_VALUE_ATTR + currentParamIndex)
								.getTextContent();
						// Tries to get the current parameter value by invoking
						// the constructor that takes one string parameter.
						currentParamValue = ConstructorHandler.invokeConstructor(
								jndiParamsClasses.get(currentParamIndex - 1), null,
								new Object[] { currentParamValue }, false);
						// Adds the value to the list.
						jndiParamsValues.add(currentParamValue);
					}
					// If it is not given.
					else {
						// Adds null as the parameter value.
						jndiParamsValues.add(null);
					}
				}
				// Gets the JNDI object method name.
				final String jndiMethodName = node.getAttributes().getNamedItem(JNDI_OBJ_METHOD_ATTR)
						.getTextContent();
				// Invoke the method for the given parameters.
				return MethodHandler.invokeMethod(jndiObject, jndiMethodName,
						jndiParamsClasses.toArray(new Class<?>[jndiParamsClasses.size()]),
						jndiParamsValues.toArray(), false);
			}
			// If there is not.
			else {
				// Returns the JNDI object itself.
				return jndiObject;
			}
		}
		// If the JNDI object cannot be found.
		catch (final NamingException exception) {
			// Throws an exception.
			throw new XMLConversionException(ErrorKeys.UNACCESSIBLE_JNDI_OBJ, exception, null);
		}
		// If the constructor for the parameter value or the JNDI method
		// parameters are invalid (or class cannot be found).
		catch (final InvalidParameterException exception) {
			// Throws an exception.
			throw new XMLConversionException(ErrorKeys.INVALID_JNDI_METHOD_PARAMS, exception, null);
		}
		// If the constructor for the parameter value or the JNDI method throws
		// an exception.
		catch (final InvocationException exception) {
			// Throws an exception.
			throw new XMLConversionException(ErrorKeys.JNDI_METHOD_EXCEPTION, exception, null);
		}
	}
	
	/**
	 * Creates an object from a text node.
	 * 
	 * @param node
	 *            Node that represents the object to be created.
	 * @param probableClass
	 *            Probable class to be used for a node. It is used when no class attribute is given.
	 * @param objectsUnderConversion
	 *            Map of objects that are under conversion. Used to prevent cycles and match object references
	 *            (id).
	 * @return The object created from the node.
	 * @throws XMLConversionException
	 *             If the conversion is not possible.
	 */
	private static Object createTextNodeObject(final Node node, final Class<?> probableClass,
			final Map<String, Object> objectsUnderConversion) throws XMLConversionException {
		// Tries to create the text node object.
		try {
			// If the JNDI object is not defined for the node.
			if ((node.getAttributes() == null) || (node.getAttributes().getNamedItem(JNDI_OBJ_ATTR) == null)) {
				// The default value for the object is an empty String.
				String nodeTextValue = "";
				// If there are child nodes.
				if (node.getChildNodes().getLength() != 0) {
					// Gets the actual node value.
					nodeTextValue = node.getChildNodes().item(0).getNodeValue();
				}
				// Removes the blank spaces from the begin and end of the
				// string.
				nodeTextValue = nodeTextValue.trim();
				// Tries to get the node class.
				Class<?> nodeClass = getNodeClass(node, probableClass);
				// If no class was found.
				if (nodeClass == null) {
					// Tries to use String.class.
					nodeClass = String.class;
				}
				// Object that represents the node.
				Object object;
				// If the node class is Class.
				if (Class.class.equals(nodeClass)) {
					// Tries to get the class for the name.
					object = ClassHandler.getClass(nodeTextValue);
				}
				// If not.
				else {
					// Creates a new instance of the object (from the node
					// class)
					// with the node text value.
					object = ConstructorHandler.invokeConstructor(nodeClass, null,
							new Object[] { nodeTextValue }, false);
				}
				// Gets the node id.
				final String nodeId = getNodeId(node);
				// If the node has an id.
				if (nodeId != null) {
					// Puts it in the map with the current object.
					objectsUnderConversion.put(nodeId, object);
				}
				// Returns the object for the node.
				return object;
			}
			// If it is defined.
			else {
				// Gets the initial value of the object via JNDI.
				return getNodeJNDIObjectValue(node);
			}
		}
		// If invalid parameters are given to the constructor of the node
		// object.
		catch (final InvalidParameterException exception) {
			// Throws an exception.
			throw new XMLConversionException(ErrorKeys.INVALID_CONSTRUCTOR_PARAMS, exception, null);
		}
		// If the constructor for the object throws an exception.
		catch (final InvocationException exception) {
			// Throws an exception.
			throw new XMLConversionException(ErrorKeys.CONSTRUCTOR_EXCEPTION, exception, null);
		}
	}
	
	/**
	 * Creates a object from a node and gives it an initial value. If the node has JNDI access attributes, the
	 * object initial value comes from JNDI. If not, a new instance of the object is created.
	 * 
	 * @param node
	 *            Node that represents the object to be created.
	 * @param object
	 *            Initial value for the object that represents the node.
	 * @param probableClass
	 *            Probable class to be used for a node. It is used when no class attribute is given.
	 * @return The initial value for an object that represents the given node.
	 * @throws XMLConversionException
	 *             If the conversion is not possible.
	 */
	private static Object createComplexNodeObjectInitialValue(final Node node, final Object object,
			final Class<?> probableClass) throws XMLConversionException {
		// Tries to create the initial value of the object.
		try {
			// If the JNDI object is not defined for the node.
			if ((node.getAttributes() == null) || (node.getAttributes().getNamedItem(JNDI_OBJ_ATTR) == null)) {
				// If the initial value of the object is null.
				if (object == null) {
					// Tries to get a new instance of the node object.
					return ConstructorHandler.invokeConstructor(getNodeClass(node, probableClass), null,
							null, false);
				}
				// If it is not null.
				else {
					// Returns it.
					return object;
				}
			}
			// If it is defined.
			else {
				// Gets the initial value of the object via JNDI.
				return getNodeJNDIObjectValue(node);
			}
		}
		// If invalid parameters are given to the constructor of the node object.
		catch (final InvalidParameterException exception) {
			// Throws an exception.
			throw new XMLConversionException(ErrorKeys.INVALID_CONSTRUCTOR_PARAMS, exception, null);
		}
		// If the constructor for the object throws an exception.
		catch (final InvocationException exception) {
			// Throws an exception.
			throw new XMLConversionException(ErrorKeys.CONSTRUCTOR_EXCEPTION, exception, null);
		}
	}
	
	/**
	 * Creates a object from a complex node.
	 * 
	 * @param node
	 *            Node that represents the object to be created.
	 * @param object
	 *            Initial value for the object that represents the node.
	 * @param probableClass
	 *            Probable class to be used for a node. It is used when no class attribute is given.
	 * @param objectsUnderConversion
	 *            Map of objects that are under conversion. Used to prevent cycles and match object references
	 *            (id).
	 * @return The object created from the node.
	 * @throws XMLConversionException
	 *             If the conversion is not possible.
	 */
	@SuppressWarnings("unchecked")
	private static Object createComplexNodeObject(final Node node, Object object,
			final Class<?> probableClass, final Map<String, Object> objectsUnderConversion)
			throws XMLConversionException {
		// Tries to create a complex node object.
		try {
			// Creates the object node and gets its initial value.
			object = createComplexNodeObjectInitialValue(node, object, probableClass);
			// Gets the node id.
			final String nodeId = getNodeId(node);
			// If the node has an id.
			if (nodeId != null) {
				// Puts it in the map with the current object.
				objectsUnderConversion.put(nodeId, object);
			}
			// Gets the field (child) nodes from the given node.
			final NodeList fieldNodes = node.getChildNodes();
			// For each field (child) node.
			for (Integer currentFieldNodeIndex = 0; currentFieldNodeIndex < fieldNodes.getLength(); currentFieldNodeIndex++) {
				// Gets the current child node.
				final Node currentFieldNode = fieldNodes.item(currentFieldNodeIndex);
				// If the current field node is not a text node.
				if (currentFieldNode.getNodeType() != Node.TEXT_NODE) {
					// If the node represents a collection.
					if (isCollection(node, probableClass)) {
						// Adds the current object to the collection that represents the node.
						// TODO Try to get the generic type for the collection
						// and pass as probable class.
						addNodeObjectToCollection((Collection<Object>) object, currentFieldNode, null, null,
								objectsUnderConversion);
					}
					// If it is not a collection.
					else {
						// Gets the field class with the same name of the current field node.
						final Class<?> currentFieldClass = FieldHandler.getField(object.getClass(),
								currentFieldNode.getNodeName()).getType();
						// Initial field value.
						Object initialFieldValue = null;
						// Tries to use the default value of the field as its initial value.
						try {
							initialFieldValue = FieldHandler.getFieldValue(object,
									currentFieldNode.getNodeName(), false, false);
						}
						// If an exception is thrown.
						catch (final Exception exception) {
							// Ignores it.
						}
						// Sets the current field value of the object with the result of the conversion for
						// the current field node.
						FieldHandler.setFieldValue(
								object,
								currentFieldNode.getNodeName(),
								fromXML(currentFieldNode, initialFieldValue, currentFieldClass,
										objectsUnderConversion), false, false);
					}
				}
			}
			// Returns the object created for the node.
			return object;
		}
		// If invalid parameters are given to set the field.
		catch (final InvalidParameterException exception) {
			// Throws an exception.
			throw new XMLConversionException(ErrorKeys.INVALID_SETTER_PARAMS, exception, null);
		}
		// If the setter for the field throws an exception.
		catch (final InvocationException exception) {
			// Throws an exception.
			throw new XMLConversionException(ErrorKeys.SETTER_EXCEPTION, exception, null);
		}
	}
	
	/**
	 * Converts a XML node into an object.
	 * 
	 * @param node
	 *            Node that represents the object to be created.
	 * @param object
	 *            Initial value for the object that represents the node.
	 * @param probableClass
	 *            Probable class to be used for a node. It is used when no class attribute is given.
	 * @param objectsUnderConversion
	 *            Map of objects that are under conversion. Used to prevent cycles and match object references
	 *            (id).
	 * @return The object for the given node.
	 * @throws XMLConversionException
	 *             If the conversion is not possible.
	 * @throws EmptyParameterException
	 *             If the node is null.
	 */
	public static Object fromXML(final Node node, Object object, final Class<?> probableClass,
			Map<String, Object> objectsUnderConversion) throws XMLConversionException,
			EmptyParameterException {
		// If the object node is null.
		if (node == null) {
			// Throws an exception.
			throw new EmptyParameterException(1); // TODO
		}
		// Tries to get the object for the node.
		try {
			// If the map of objects under conversion is null.
			if (objectsUnderConversion == null) {
				// Creates a new map.
				objectsUnderConversion = new HashMap<String, Object>();
			}
			// Tries to get the current node form the objects under conversion map.
			final Object objectInMap = objectsUnderConversion.get(getNodeId(node));
			// If the object is in the map.
			if (objectInMap != null) {
				// Returns the object in the map.
				return objectInMap;
			}
			// If it is not in the map.
			else {
				// Normalize the object node.
				node.normalize();
				// Gets the child nodes from the given node.
				final NodeList childNodes = node.getChildNodes();
				// If the there are 1 (or 0) child nodes, and the first child is
				// a text node.
				if ((childNodes.getLength() == 0)
						|| ((childNodes.getLength() == 1) && (childNodes.item(0).getNodeType() == Node.TEXT_NODE))) {
					// Tries to create the object from the text in the node.
					object = createTextNodeObject(node, probableClass, objectsUnderConversion);
				}
				// Otherwise.
				else {
					// Tries to create the object from a complex node.
					object = createComplexNodeObject(node, object, probableClass, objectsUnderConversion);
				}
				// Returns the object.
				return object;
			}
		}
		// If an conversion exception is thrown.
		catch (final XMLConversionException exception) {
			// If no node was used as a exception parameter.
			if (exception.getParameters().length == 0) {
				// Adds the node name as a parameter.
				exception.setParameters(new Object[] { node.getNodeName() });
			}
			// Throws the exception again.
			throw exception;
		}
	}
	
	/**
	 * Converts a XML node list into a collection of objects.
	 * 
	 * @param nodes
	 *            Nodes that represents the objects to be created.
	 * @param probableClass
	 *            Probable class to be used for each node. It is used when no class attribute is given.
	 * @param objectsUnderConversion
	 *            Map of objects that are under conversion. Used to prevent cycles and match object references
	 *            (id).
	 * @return The objects for the given node list.
	 * @throws XMLConversionException
	 *             If the conversion is not possible.
	 * @throws EmptyParameterException
	 *             If the node is null.
	 */
	public static Collection<? extends Object> fromXML(final NodeList nodes, final Class<?> probableClass,
			final Map<String, Object> objectsUnderConversion) throws EmptyParameterException,
			XMLConversionException {
		// If the object node list is null.
		if (nodes == null) {
			// Throws an exception.
			throw new EmptyParameterException(""); // TODO
		}
		// List of objects that represents the node list.
		final Collection<Object> objects = new ArrayList<Object>();
		// For each node in the given node list.
		for (Integer currentNodeIndex = 0; currentNodeIndex < nodes.getLength(); currentNodeIndex++) {
			// Gets the current node.
			final Node currentNode = nodes.item(currentNodeIndex);
			// Create the current object.
			final Object currentObject = fromXML(currentNode, null, probableClass, objectsUnderConversion);
			// Adds the current object to the list.
			objects.add(currentObject);
		}
		// Returns the collection created.
		return objects;
	}
	
	/**
	 * Converts a XML document into an object.
	 * 
	 * @param xmlDocument
	 *            XML document to create the object.
	 * @param object
	 *            Initial value for the object that represents the node.
	 * @param probableClass
	 *            Probable class to be used for the node(s). It is used when no class attribute is given.
	 * @param objectsUnderConversion
	 *            Map of objects that are under conversion. Used to prevent cycles and match object references
	 *            (id).
	 * @return The objects for the given node list.
	 * @throws XMLConversionException
	 *             If the conversion is not possible.
	 * @throws EmptyParameterException
	 *             If the node is null.
	 */
	public static Object fromXML(final Document xmlDocument, final Object object,
			final Class<?> probableClass, final Map<String, Object> objectsUnderConversion)
			throws EmptyParameterException, XMLConversionException {
		// Normalize the document.
		xmlDocument.normalizeDocument();
		// If there are no child nodes.
		if (xmlDocument.getChildNodes().getLength() == 0) {
			// Returns null.
			return null;
		}
		// If there is only one child node.
		if (xmlDocument.getChildNodes().getLength() == 1) {
			// Return one object.
			return fromXML(xmlDocument.getFirstChild(), object, probableClass, objectsUnderConversion);
		}
		// If there is more than child node.
		else {
			// Return a collection with the objects.
			return fromXML(xmlDocument.getChildNodes(), probableClass, objectsUnderConversion);
		}
	}
}
