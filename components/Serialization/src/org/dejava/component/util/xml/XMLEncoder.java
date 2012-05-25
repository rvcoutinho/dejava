package org.dejava.component.util.xml;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dejava.component.util.exception.localized.EmptyParameterException;
import org.dejava.component.util.exception.localized.InvalidParameterException;
import org.dejava.component.util.reflection.exception.InvocationException;
import org.dejava.component.util.reflection.handler.ConstructorHandler;
import org.dejava.component.util.reflection.handler.FieldHandler;
import org.dejava.component.util.xml.annotation.XMLComplexNode;
import org.dejava.component.util.xml.constant.ErrorKeys;
import org.dejava.component.util.xml.exception.XMLConversionException;
import org.dejava.component.util.xml.exception.XMLCreationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * Helps handling XML creation/conversion.
 */
public final class XMLEncoder {
	
	/**
	 * Private constructor.
	 */
	private XMLEncoder() {
	}
	
	/**
	 * Sets the class of a node.
	 * 
	 * @param node
	 *            Node to set the class.
	 * @param object
	 *            Object that represents the node.
	 */
	private static void setNodeClass(final Element node, final Object object) {
		// Sets the node class. TODO Think about.
		node.setAttribute(XMLDecoder.CLASS_ATTR, object.getClass().getName());
	}
	
	/**
	 * Sets the id of a node.
	 * 
	 * @param node
	 *            Node to set the id.
	 * @param object
	 *            Object that represents the node.
	 */
	private static void setNodeId(final Element node, final Object object) {
		// Sets the node id. TODO Think about.
		node.setAttribute(XMLDecoder.ID_ATTR,
				object.getClass().getName() + '@' + Integer.toHexString(object.hashCode()));
	}
	
	/**
	 * Returns if a object is already under conversion.
	 * 
	 * @param object
	 *            Object to be checked.
	 * @param objectsUnderConversion
	 *            Map of objects that are under conversion. Used to prevent cycles and match object references
	 *            (id).
	 * @return If a object is already under conversion.
	 */
	private static Boolean isObjectUnderConversion(final Object object,
			final Collection<Object> objectsUnderConversion) {
		// For each object under conversion.
		for (Object objectAtual : objectsUnderConversion) {
			// If the object is the same.
			if (object == objectAtual) {
				// Returns true.
				return true;
			}
		}
		// If the object was not found in the collection, returns false.
		return false;
	}
	
	/**
	 * Creates XML nodes that represents fields of a complex object.
	 * 
	 * @param xmlDocument
	 *            XML document that the new nodes will be nested.
	 * @param object
	 *            Object that holds the fields to be created as nodes.
	 * @param forceAllNodeClasses
	 *            Forces all nodes to create the class attribute. Usually the attribute is only used when the
	 *            object is not from the expected class (usually when its a subclass).
	 * @param objectsUnderConversion
	 *            Map of objects that are under conversion. Used to prevent cycles and match object references
	 *            (id).
	 * @return The XML fields nodes created from the complex object.
	 * @throws XMLConversionException
	 *             If the conversion is not possible.
	 */
	private static List<Element> createComplexObjectFieldNodes(final Document xmlDocument,
			final Object object, final Boolean forceAllNodeClasses,
			final Collection<Object> objectsUnderConversion) throws XMLConversionException {
		// Tries to create a complex node.
		try { // Fields node list.
			List<Element> nodeList = new ArrayList<Element>();
			// For each field of the complex object.
			for (Field currentField : FieldHandler.getAllFields(object.getClass())) {
				// Gets the field value.
				Object currentFieldValue = FieldHandler.getFieldValue(object, currentField.getName(), false,
						false);
				// If the field value is not null.
				if (currentFieldValue != null) {
					// If it should force the node class (false by default).
					Boolean forceThisNodeClass = false;
					// If the current field value class is different than the field class.
					if (currentField.getType().equals(currentFieldValue.getClass())) {
						// Set to force this node class.
						forceThisNodeClass = true;
					}
					// Creates the node for the field.
					Element currentFieldNode = createNode(xmlDocument, currentFieldValue,
							currentField.getName(), forceAllNodeClasses, forceThisNodeClass,
							objectsUnderConversion);
					// Adds the current node to the list.
					nodeList.add(currentFieldNode);
				}
			}
			// Returns the node list.
			return nodeList;
		}
		// If invalid parameters are given to get the field (or fields).
		catch (InvalidParameterException exception) {
			// Throws an exception.
			throw new XMLConversionException(ErrorKeys.INVALID_GETTER_PARAMS, exception, null);
		}
		// If the getter for the field throws an exception.
		catch (InvocationException exception) {
			// Throws an exception.
			throw new XMLConversionException(ErrorKeys.GETTER_EXCEPTION, exception, null);
		}
	}
	
	/**
	 * Creates a XML node that represents a complex object.
	 * 
	 * @param xmlDocument
	 *            XML document that the new node will be nested.
	 * @param object
	 *            Object to be created as a node.
	 * @param node
	 *            Current object node.
	 * @param forceAllNodeClasses
	 *            Forces all nodes to create the class attribute. Usually the attribute is only used when the
	 *            object is not from the expected class (usually when its a subclass).
	 * @param objectsUnderConversion
	 *            Map of objects that are under conversion. Used to prevent cycles and match object references
	 *            (id).
	 * @return The XML node created from the complex object.
	 * @throws XMLConversionException
	 *             If the conversion is not possible.
	 */
	private static Element createComplexNode(final Document xmlDocument, final Object object,
			final Element node, final Boolean forceAllNodeClasses,
			final Collection<Object> objectsUnderConversion) throws XMLConversionException {
		// For each field node for the complex object.
		for (Node noAtual : createComplexObjectFieldNodes(xmlDocument, object, forceAllNodeClasses,
				objectsUnderConversion)) {
			// Appends it to the complex object node.
			node.appendChild(noAtual);
		}
		// Returns the object node.
		return node;
	}
	
	/**
	 * Name of the nodes for collection items.
	 */
	public static final String COLLECTION_NODE = "item";
	
	/**
	 * Creates a XML node that represents a collection.
	 * 
	 * @param xmlDocument
	 *            XML document that the new node will be nested.
	 * @param object
	 *            Object to be created as a node.
	 * @param node
	 *            Current object node.
	 * @param forceAllNodeClasses
	 *            Forces all nodes to create the class attribute. Usually the attribute is only used when the
	 *            object is not from the expected class (usually when its a subclass).
	 * @param objectsUnderConversion
	 *            Map of objects that are under conversion. Used to prevent cycles and match object references
	 *            (id).
	 * @return The XML node created from the collection.
	 * @throws XMLConversionException
	 *             If the conversion is not possible.
	 * @throws EmptyParameterException
	 *             If the object is null.
	 */
	private static Element createCollectionNode(final Document xmlDocument, final Object object,
			final Element node, final Boolean forceAllNodeClasses,
			final Collection<Object> objectsUnderConversion) throws EmptyParameterException,
			XMLConversionException {
		// For each object in the collection.
		for (Object currentCollectionItem : (Collection<?>) object) {
			// Creates a new object node.
			Element noItemAtualColecao = createNode(xmlDocument, currentCollectionItem, COLLECTION_NODE,
					forceAllNodeClasses, true, objectsUnderConversion);
			// Appends the new node to the collection node.
			node.appendChild(noItemAtualColecao);
		}
		// Returns the object node.
		return node;
	}
	
	/**
	 * Creates a XML node with text value.
	 * 
	 * @param xmlDocument
	 *            XML document that the new node will be nested.
	 * @param object
	 *            Object to be created as a node.
	 * @param node
	 *            Current object node.
	 * @return The XML node created from the text object.
	 */
	private static Element createTextNode(final Document xmlDocument, final Object object, final Element node) {
		// Gets the object as a String.
		String nodeVale = object.toString();
		// Creates a new text node within the current object node.
		Text textnode = xmlDocument.createTextNode(nodeVale);
		// Adds the text node object as a current object node child.
		node.appendChild(textnode);
		// Returns the object node.
		return node;
	}
	
	/**
	 * Returns if a class should be represent as a complex node (has children).
	 * 
	 * @param <Type>
	 *            Any type.
	 * @param clazz
	 *            Class to be checked.
	 * @return If a class should be represent as a complex node (has children).
	 */
	private static <Type extends Object> Boolean isComplexNode(final Class<Type> clazz) {
		// One string parameter constructor.
		Constructor<Type> oneStringConstructor = null;
		// Tries to get a constructor with a String parameter for the given class.
		try {
			oneStringConstructor = ConstructorHandler.getConstructor(clazz, new Class<?>[] { String.class });
		}
		// If an exception is thrown.
		catch (Exception exception) {
			// It is ignored.
		}
		// If there is not a one parameter string constructor or if the class is annotated with
		// @XMLComplexNode.
		if ((oneStringConstructor == null) || (clazz.isAnnotationPresent(XMLComplexNode.class))) {
			// Returns true.
			return true;
		}
		// Otherwise.
		else {
			// Returns false.
			return false;
		}
	}
	
	/**
	 * Creates a XML node from an object.
	 * 
	 * @param xmlDocument
	 *            XML document that the new node will be nested.
	 * @param object
	 *            Object to be created as a node.
	 * @param nodeName
	 *            Name of the node to be created.
	 * @param forceAllNodeClasses
	 *            Forces all nodes to create the class attribute. Usually the attribute is only used when the
	 *            object is not from the expected class (usually when its a subclass).
	 * @param forceThisNodeClass
	 *            Forces this node to create the class attribute. Usually the attribute is only used when the
	 *            object is not from the expected class (usually when its a subclass).
	 * @param objectsUnderConversion
	 *            Map of objects that are under conversion. Used to prevent cycles and match object references
	 *            (id).
	 * @return The XML node created from the given object.
	 * @throws XMLConversionException
	 *             If the conversion is not possible.
	 * @throws EmptyParameterException
	 *             If the object is null.
	 */
	private static Element createNode(final Document xmlDocument, final Object object, final String nodeName,
			final Boolean forceAllNodeClasses, final Boolean forceThisNodeClass,
			Collection<Object> objectsUnderConversion) throws XMLConversionException, EmptyParameterException {
		// If the given object is null.
		if (object == null) {
			// Throws an exception.
			throw new EmptyParameterException(""); // TODO
		}
		// Tries to create the node.
		try {
			// If the collection of objects under conversion is null.
			if (objectsUnderConversion == null) {
				// Creates a new list.
				objectsUnderConversion = new ArrayList<Object>();
			}
			// Creates the node for the object from the XML document.
			Element node = xmlDocument.createElement(nodeName);
			// If it is told to force node class.
			if ((forceAllNodeClasses) || (forceThisNodeClass)) {
				// Sets the node class.
				setNodeClass(node, object);
			}
			// Sets the node id.
			setNodeId(node, object);
			// If the object is not yet under conversion.
			if (!isObjectUnderConversion(object, objectsUnderConversion)) {
				// Adds it to the collection of objects under conversion. This is done to prevent infinity
				// cycles.
				objectsUnderConversion.add(object);
			}
			// If it is already under conversion.
			else {
				// Returns the object node as it is now (just with the class and id attributes).
				return node;
			}
			// Gets the class of the object.
			Class<?> objectClass = object.getClass();
			// If the object is a collection.
			if (Collection.class.isAssignableFrom(objectClass)) {
				// Tries to create the node as a collection.
				return createCollectionNode(xmlDocument, object, node, forceAllNodeClasses,
						objectsUnderConversion);
			}
			// If the class must be represented as a complex node (has children).
			else if (isComplexNode(objectClass)) {
				// Tries to create the node as a complex node.
				return createComplexNode(xmlDocument, object, node, forceAllNodeClasses,
						objectsUnderConversion);
			}
			// Otherwise.
			else {
				// Tries to create the node as a text node.
				return createTextNode(xmlDocument, object, node);
			}
		}
		// If an conversion exception is thrown.
		catch (XMLConversionException exception) {
			// If no object was used as a exception parameter.
			if (exception.getParameters().length == 0) {
				// Adds the node name as a parameter.
				exception.setParameters(new Object[] { object.toString() });
			}
			// Throws the exception again.
			throw exception;
		}
	}
	
	/**
	 * Converts an object into a XML document.
	 * 
	 * @param object
	 *            Object to be converted into a XML document.
	 * @param nodeName
	 *            Name for the object (initial) node.
	 * @param forceAllNodeClasses
	 *            Forces all nodes to create the class attribute. Usually the attribute is only used when the
	 *            object is not from the expected class (usually when its a subclass).
	 * @param objectsUnderConversion
	 *            Map of objects that are under conversion. Used to prevent cycles and match object references
	 *            (id).
	 * @return The object converted into a XML document.
	 * @throws XMLConversionException
	 *             If the conversion is not possible.
	 * @throws EmptyParameterException
	 *             If the object is null.
	 * @throws XMLCreationException
	 *             If the XML document cannot be created.
	 */
	public static Document toXML(final Object object, final String nodeName,
			final Boolean forceAllNodeClasses, final Collection<Object> objectsUnderConversion)
			throws XMLConversionException, EmptyParameterException, XMLCreationException {
		// Creates an empty XML document.
		Document xmlDocument = XMLCreator.createXMLDocument();
		// Creates the first node.
		Element firstNode = createNode(xmlDocument, object, nodeName, forceAllNodeClasses, true,
				objectsUnderConversion);
		// Appends the first node to the XML.
		xmlDocument.appendChild(firstNode);
		// Returns the new XML document.
		return xmlDocument;
	}
	
	/**
	 * Converts a collection of objects into a XML document.
	 * 
	 * @param objects
	 *            Objects to be converted into a XML document.
	 * @param collectionNodeName
	 *            Name of the node that holds the collection of objects.
	 * @param collectionItemNodeName
	 *            Name of the node for each object in the collection.
	 * @param forceAllNodeClasses
	 *            Forces all nodes to create the class attribute. Usually the attribute is only used when the
	 *            object is not from the expected class (usually when its a subclass).
	 * @param objectsUnderConversion
	 *            Map of objects that are under conversion. Used to prevent cycles and match object references
	 *            (id).
	 * @return The collection of objects converted into a XML document.
	 * @throws XMLConversionException
	 *             If the conversion is not possible.
	 * @throws EmptyParameterException
	 *             If the object is null.
	 * @throws XMLCreationException
	 *             If the XML document cannot be created.
	 */
	public static Document toXML(final Collection<?> objects, final String collectionNodeName,
			final String collectionItemNodeName, final Boolean forceAllNodeClasses,
			final Collection<Object> objectsUnderConversion) throws XMLConversionException,
			EmptyParameterException, XMLCreationException {
		// Creates an empty XML document.
		Document xmlDocument = XMLCreator.createXMLDocument();
		// Creates the first node.
		Element firstNode = xmlDocument.createElement(collectionNodeName);
		// Appends the first node to the XML.
		xmlDocument.appendChild(firstNode);
		// For each object.
		for (Object currentObject : objects) {
			// Creates the node for the object.
			Element noPrincipalAtual = createNode(xmlDocument, currentObject, collectionItemNodeName,
					forceAllNodeClasses, true, objectsUnderConversion);
			// Appends the current object to the first node.
			firstNode.appendChild(noPrincipalAtual);
		}
		// Returns the new XML document.
		return xmlDocument;
	}
}
