package org.dejava.component.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.reflection.constant.ClassParamKeys;
import org.dejava.component.reflection.constant.ErrorKeys;
import org.dejava.component.reflection.util.MessageTypes;
import org.dejava.component.validation.method.PreConditions;

/**
 * Extends the reflection behavior of the Class type. It is part of an extended Java reflection API, that
 * intends to make reflection easier.
 * 
 * @param <Reflected>
 *            The class to be reflected.
 */
public class ClassMirror<Reflected> {

	/**
	 * Class being reflected.
	 */
	private Class<? extends Reflected> reflectedClass;

	/**
	 * Gets the class being reflected.
	 * 
	 * @return The class being reflected.
	 */
	public final Class<? extends Reflected> getReflectedClass() {
		return reflectedClass;
	}

	/**
	 * Sets the class being reflected.
	 * 
	 * @param reflectedClass
	 *            New class being reflected.
	 */
	protected void setReflectedClass(final Class<? extends Reflected> reflectedClass) {
		this.reflectedClass = reflectedClass;
	}

	/**
	 * Public constructor for the reflection class.
	 * 
	 * @param reflectedClass
	 *            Class being reflected.
	 * @throws EmptyParameterException
	 *             If the reflected class is not given.
	 */
	public ClassMirror(final Class<? extends Reflected> reflectedClass) throws EmptyParameterException {
		// Asserts that the class is not null.
		PreConditions.assertParamNotNull(ClassParamKeys.CLASS, reflectedClass);
		// Sets the main reflection fields.
		this.reflectedClass = reflectedClass;
	}

	/**
	 * Public constructor for the reflection class.
	 * 
	 * @param reflectedClassName
	 *            Full qualified name of the class being reflected.
	 * @param classLoader
	 *            Class loader to be used with the reflected class. If no class loader is given, the context
	 *            class loader is used.
	 * @param initialize
	 *            Whether the class must be initialized. Default is true.
	 * @throws InvalidParameterException
	 *             If the class cannot be found for the name.
	 */
	public ClassMirror(final String reflectedClassName, final ClassLoader classLoader,
			final Boolean initialize) throws InvalidParameterException {
		// Tries to set the reflected class.
		try {
			this.reflectedClass = (Class<Reflected>) getClass(reflectedClassName, classLoader, initialize);
		}
		// If the class cannot be casted.
		catch (final ClassCastException exception) {
			// Throws an exception.
			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.UNEXPECTED_CLASS,
					new Object[] { getReflectedClass(), ClassParamKeys.CLASS, reflectedClassName }, exception);
		}
	}

	/**
	 * Public constructor for the reflection class.
	 * 
	 * @param reflectedClassName
	 *            Full qualified name of the class being reflected.
	 * @throws InvalidParameterException
	 *             If the class cannot be found for the name.
	 */
	public ClassMirror(final String reflectedClassName) throws InvalidParameterException {
		this(reflectedClassName, null, true);
	}

	/**
	 * Public constructor for the reflection class.
	 * 
	 * @param depth
	 *            Depth in the current stack for the desired class (0 is the first and so on).
	 * @throws InvalidParameterException
	 *             If the class cannot be found for the name or if the depth is greater than the stack trace
	 *             size.
	 */
	public ClassMirror(final Integer depth) throws InvalidParameterException {
		// Actual depth is the given one.
		Integer actualDepth = depth;
		// If the given depth is null.
		if (actualDepth == null) {
			// The depth is 0.
			actualDepth = 0;
		}
		// Tries to set the reflected class.
		try {
			this.reflectedClass = (Class<Reflected>) getCurrentClass(actualDepth + 1);
		}
		// If the class cannot be casted.
		catch (final ClassCastException exception) {
			// Throws an exception.
			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.CLASS_NOT_FOUND,
					new Object[] { getReflectedClass(), ClassParamKeys.CLASS_DEPTH, depth }, exception);

		}
	}

	/**
	 * Gets a class with the given name from a given class loader.
	 * 
	 * @param className
	 *            Name of the class to be found.
	 * @param classLoader
	 *            Class loader to load the class. If no class loader is given, the context class loader is
	 *            used.
	 * @param initialize
	 *            Whether the class must be initialized.
	 * @return Returns the class with the name.
	 * @throws EmptyParameterException
	 *             If the class name is not given.
	 * @throws InvalidParameterException
	 *             If the class cannot be found for the name.
	 */
	public static Class<?> getClass(final String className, final ClassLoader classLoader,
			final Boolean initialize) throws InvalidParameterException, EmptyParameterException {
		// Asserts that the class name is not empty.
		PreConditions.assertParamNotEmpty(ClassParamKeys.CLASS_NAME, className);
		// Actual class loader default value is the current class loader.
		ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
		// If the given class loader is not null.
		if (classLoader != null) {
			// Updates the actual class loader.
			actualClassLoader = classLoader;
		}
		// Tries to return class.
		try {
			return Class.forName(className, initialize, actualClassLoader);
		}
		// If the class cannot be found.
		catch (final ClassNotFoundException exception) {
			// Throws an exception.
			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.CLASS_NOT_FOUND,
					new Object[] { ClassParamKeys.CLASS_NAME, className }, exception);
		}
	}

	/**
	 * Gets the name of the current class (with the selected depth) being executed.
	 * 
	 * @param depth
	 *            Depth in the current stack for the desired class (0 is the first and so on).
	 * @return The name of the current class (with the selected depth) being executed.
	 * @throws InvalidParameterException
	 *             If the depth is greater than the stack trace size.
	 */
	public static String getCurrentClassName(final Integer depth) throws InvalidParameterException {
		// Actual depth is the given one.
		Integer actualDepth = depth;
		// If the given depth is null.
		if (actualDepth == null) {
			// The depth is 0.
			actualDepth = 0;
		}
		// Tries to return the class.
		try {
			// Gets the stack trace.
			final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
			// Returns the name of the method in the selected depth.
			return stackTrace[2 + actualDepth].getClassName();
		}
		// If the depth is deeper than the stack.
		catch (final IndexOutOfBoundsException exception) {
			// Throws an exception.
			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.CLASS_NOT_FOUND,
					new Object[] { ClassParamKeys.CLASS_DEPTH, depth }, exception);
		}
	}

	/**
	 * Gets the current class (with the selected depth) being executed.
	 * 
	 * @param depth
	 *            Depth in the current stack for the desired class (0 is the first and so on).
	 * @return The current class (with the selected depth) being executed.
	 * @throws InvalidParameterException
	 *             If the class cannot be found for the name or if the depth is greater than the stack trace
	 *             size.
	 */
	public static Class<?> getCurrentClass(final Integer depth) throws InvalidParameterException {
		// Actual depth is the given one.
		Integer actualDepth = depth;
		// If the given depth is null.
		if (actualDepth == null) {
			// The depth is 0.
			actualDepth = 0;
		}
		// Gets the current class name for the given depth.
		final String className = getCurrentClassName(actualDepth + 1);
		// Returns the class for the name.
		return getClass(className, null, true);
	}

	/**
	 * Gets all the classes and interfaces inherited by the reflected class (including this one).
	 * 
	 * @param includeInterfaces
	 *            If the interfaces of the class should be included.
	 * @return All the classes and interfaces inherited by the reflected class (including this one).
	 */
	public Set<ClassMirror<?>> getSuperClasses(final Boolean includeInterfaces) {
		// All classes to be returned.
		final Set<ClassMirror<?>> classes = new LinkedHashSet<ClassMirror<?>>();
		// Adds the class itself to the list.
		classes.add(this);
		// If the interfaces should be included.
		if (includeInterfaces) {
			// If the class has any interfaces.
			if (getReflectedClass().getInterfaces() != null) {
				// For each interface.
				for (final Class<?> currentInterface : getReflectedClass().getInterfaces()) {
					// Calls the same method recursively with the current interface (adding the classes to the
					// list).
					classes.addAll(new ClassMirror<>(currentInterface).getSuperClasses(includeInterfaces));
				}
			}
		}
		// If the class has any superclass.
		if (getReflectedClass().getSuperclass() != null) {
			// Calls the same method recursively with its superclass (adding the classes to the list).
			classes.addAll(new ClassMirror<>(getReflectedClass().getSuperclass())
					.getSuperClasses(includeInterfaces));
		}
		// Adds the Object class.
		classes.add(new ClassMirror<Object>(Object.class));
		// Returns all found classes.
		return classes;
	}

	/**
	 * Returns all fields (public or not) from the reflected class (including inherited ones).
	 * 
	 * @return All fields from a class (including inherited ones).
	 */
	public Collection<FieldMirror> getFields() {
		// Collection to keep fields.
		final Collection<FieldMirror> fields = new ArrayList<FieldMirror>();
		// While there are super classes.
		for (Class<?> currentClass = getReflectedClass(); currentClass != null; currentClass = currentClass
				.getSuperclass()) {
			// For each field in the current class.
			for (final Field currentField : currentClass.getDeclaredFields()) {
				// Tries to add the current field to the collection.
				fields.add(new FieldMirror(currentField));
			}
		}
		// Returns all the fields found in the search.
		return fields;
	}

	/**
	 * Returns the fields annotated with the given annotation.
	 * 
	 * @param annotation
	 *            The annotations that the fields should be annotated with.
	 * @return The fields annotated with the given annotation.
	 */
	public Collection<FieldMirror> getFields(final Class<? extends Annotation> annotation) {
		// Asserts that the annotation class is not null.
		PreConditions.assertParamNotNull(ClassParamKeys.ANNOTATION_CLASS, annotation);
		// Collection to keep fields.
		final Collection<FieldMirror> fields = new ArrayList<FieldMirror>();
		// While there are super classes.
		for (Class<?> currentClass = getReflectedClass(); currentClass != null; currentClass = currentClass
				.getSuperclass()) {
			// For each field in the current class.
			for (final Field currentField : currentClass.getDeclaredFields()) {
				// If the field is annotated with the given annotation.
				if (currentField.isAnnotationPresent(annotation)) {
					// Tries to add the current field to the collection.
					fields.add(new FieldMirror(currentField));
				}
			}
		}
		// Returns all the fields found in the search.
		return fields;
	}

	/**
	 * Returns a field from the reflected class. The search includes the inherited classes. The first field
	 * found for the name is returned.
	 * 
	 * @param fieldName
	 *            Name of the field to be found.
	 * @return A field from the desired class.
	 * @throws EmptyParameterException
	 *             If the name is not given.
	 * @throws InvalidParameterException
	 *             If the field cannot be found for the name.
	 */
	public FieldMirror getField(final String fieldName) throws InvalidParameterException,
			EmptyParameterException {
		// Asserts that the field name is not empty.
		PreConditions.assertParamNotEmpty(ClassParamKeys.FIELD_NAME, fieldName);
		// While there are super classes.
		for (Class<?> currentClass = getReflectedClass(); currentClass != null; currentClass = currentClass
				.getSuperclass()) {
			// Tries to return the desired field.
			try {
				return new FieldMirror(currentClass.getDeclaredField(fieldName));
			}
			// If an exception is thrown.
			catch (final Exception exception) {
				// It will be ignored.
			}
		}
		// If no field was found for this name, throws an exception.
		throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.FIELD_NOT_FOUND,
				new Object[] { getReflectedClass(), ClassParamKeys.FIELD_NAME, fieldName }, null);

	}

	/**
	 * Gets a field path for the reflected class.
	 * 
	 * @param fieldNamePath
	 *            Field path using the desired field names. Using the format: "field1.field2.*.*".
	 * @return field path for the reflected class.
	 */
	public FieldPath getFieldPath(final String fieldNamePath) {
		// Asserts that the field path is not empty.
		PreConditions.assertParamNotEmpty(ClassParamKeys.FIELD_NAME_PATH, fieldNamePath);
		// Split the fields from the given path.
		final String[] fieldsNames = fieldNamePath.split("\\.");
		// Creates the field path.
		final LinkedList<FieldMirror> fieldPath = new LinkedList<>();
		// The current field declaring class is this one.
		ClassMirror<?> currentFieldDeclaringClass = this;
		// For each field name.
		for (final String currentFieldName : fieldsNames) {
			// Gets the field for the current name.
			final FieldMirror currentField = currentFieldDeclaringClass.getField(currentFieldName);
			// Adds the current field to the field path.
			fieldPath.add(currentField);
			// The next field declaring class is the field class.
			currentFieldDeclaringClass = currentField.getType();
		}
		// Returns the field path.
		return new FieldPath(fieldPath);
	}

	/**
	 * Gets the class package as a directory path.
	 * 
	 * @return The class package as a directory path.
	 */
	public String getPackageAsDirPath() {
		// Gets the directory path for the class.
		String dirPath = getReflectedClass().getPackage().toString().replace(".", "/");
		// Removes the "package " from the beginning.
		dirPath = dirPath.substring(8);
		// Returns the directory path.
		return dirPath;
	}

	/**
	 * Gets any method (despite of its modifiers) in the current class. The first method found with the
	 * signature is returned. Super classes are also part of the search.
	 * 
	 * @param methodName
	 *            Name of the method to be found.
	 * @param paramsClasses
	 *            The exact parameters classes for the method to be found.
	 * @return A method from the reflected class for the exact given parameters.
	 */
	public MethodMirror getAnyMethod(final String methodName, final Class<?>[] paramsClasses) {
		// Asserts that the method name is not empty.
		PreConditions.assertParamNotEmpty(ClassParamKeys.METHOD_NAME, methodName);
		// For each class or super class.
		for (final ClassMirror<?> currentClass : getSuperClasses(false)) {
			// Tries to get the method from the current class.
			try {
				return new MethodMirror(currentClass.getReflectedClass().getDeclaredMethod(methodName,
						paramsClasses));
			}
			// If the method cannot be found.
			catch (final Exception exception) {
				// Ignores and keeps looking.
			}
		}
		// If no method has been found, throws an exception.
		throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.METHOD_NOT_FOUND,
				new Object[] { getReflectedClass(), methodName, paramsClasses }, null);
	}

	/**
	 * Gets a method from the reflected class for the given parameters. The search includes the inherited
	 * classes and interfaces of each parameter class.
	 * 
	 * @param methodName
	 *            Name of the method to be found.
	 * @param varyingParamIndex
	 *            Index of the parameter class that will be varied with inherited classes/interfaces.
	 * @param paramsClasses
	 *            Parameters classes for the method to be found.
	 * @return A method from the reflected class for the given parameters.
	 * @throws InvalidParameterException
	 *             If the method cannot be found.
	 */
	private MethodMirror getMethod(final String methodName, final Integer varyingParamIndex,
			final Class<?>[] paramsClasses) throws InvalidParameterException {
		// Keeps the original value of the parameter that will be changed.
		final Class<?> initialParamClass = paramsClasses[varyingParamIndex];
		// Tries to get the method.
		try {
			// Gets the current varying class.
			final ClassMirror<?> currentVaryingClass = new ClassMirror<>(paramsClasses[varyingParamIndex]);
			// For each class inherited by the current parameter being varied.
			for (final ClassMirror<?> currentParamClass : currentVaryingClass.getSuperClasses(true)) {
				// Changes the parameter class with the current superclass/interface.
				paramsClasses[varyingParamIndex] = currentParamClass.getReflectedClass();
				// Tries to return the method with the exact parameters classes.
				try {
					return getAnyMethod(methodName, paramsClasses);
				}
				// If it is not possible to get the method.
				catch (final Exception exception) {
					// If the current varying parameter class is not the last parameter class.
					if (varyingParamIndex < (paramsClasses.length - 1)) {
						// Tries to get the method varying the next parameter.
						try {
							return getMethod(methodName, varyingParamIndex + 1, paramsClasses);
						}
						// If it is not possible to get the method.
						catch (final Exception exception2) {
							// Ignores and continues the loop.
						}
					}
				}
			}
			// If no method has been found, throws an exception.
			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.METHOD_NOT_FOUND,
					new Object[] { getReflectedClass(), methodName, paramsClasses, varyingParamIndex }, null);
		}
		// In all cases.
		finally {
			// At the end, returns the parameter class being varied to its original value.
			paramsClasses[varyingParamIndex] = initialParamClass;
		}
	}

	/**
	 * Gets a method from the reflected class for the given parameters. The search includes the inherited
	 * classes and interfaces of each parameter class.
	 * 
	 * @param methodName
	 *            Name of the method to be found.
	 * @param paramsClasses
	 *            Parameters classes for the method to be found.
	 * @return A method from the reflected class for the given parameters.
	 * @throws EmptyParameterException
	 *             If the method name is empty.
	 * @throws InvalidParameterException
	 *             If the method cannot be found or one of the parameters classes is empty.
	 */
	public MethodMirror getMethod(final String methodName, final Class<?>[] paramsClasses)
			throws InvalidParameterException, EmptyParameterException {
		// Asserts that the method name is not empty. TODO Re-think if it is needed here.
		PreConditions.assertParamNotEmpty(ClassParamKeys.METHOD_NAME, methodName);
		// If there are no parameters for the method to be found.
		if ((paramsClasses == null) || (paramsClasses.length == 0)) {
			// Tries to get the method normally using the reflection API.
			try {
				return getAnyMethod(methodName, null);
			}
			// If the method cannot be found or accessed.
			catch (final Exception exception) {
				// Throws an exception.
				throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.METHOD_NOT_FOUND,
						new Object[] { getReflectedClass(), methodName, paramsClasses }, null);
			}
		}
		// If there are parameters.
		else {
			// For each parameters class.
			for (Integer currentParamIndex = 0; currentParamIndex < paramsClasses.length; currentParamIndex++) {
				// Gets the current parameter class.
				final Class<?> currentParamClass = paramsClasses[currentParamIndex];
				// If the current parameter class is empty.
				if (currentParamClass == null) {
					// Throws an exception.
					throw new InvalidParameterException(MessageTypes.Error.class,
							ErrorKeys.EMPTY_PARAM_CLASS, new Object[] { currentParamIndex + 1 }, null);
				}
			}
			// Tries to get the method varying the first parameter class.
			return getMethod(methodName, 0, paramsClasses);
		}
	}

	/**
	 * Returns the classes of the parameters from its values.
	 * 
	 * @param paramsValues
	 *            Parameters values to be used during a invocation of a method.
	 * @return The classes of the parameters from its values.
	 * @throws InvalidParameterException
	 *             If one of the parameters values is empty.
	 */
	private Class<?>[] getParamsClasses(final Object[] paramsValues) throws InvalidParameterException {
		// Parameters classes.
		Class<?>[] paramsClasses = null;
		// If there are values for the parameters.
		if ((paramsValues != null) && (paramsValues.length != 0)) {
			// Creates an array for the parameters classes with the same length
			// as the parameters values one.
			paramsClasses = new Class<?>[paramsValues.length];
			// For each parameter value.
			for (Integer currentParamIndex = 0; currentParamIndex < paramsValues.length; currentParamIndex++) {
				// If the value for the parameter is null.
				if (paramsValues[currentParamIndex] == null) {
					// Throws an exception.
					throw new InvalidParameterException(MessageTypes.Error.class,
							ErrorKeys.EMPTY_PARAM_VALUE, new Object[] { currentParamIndex + 1 }, null);
				}
				// Gets the class for the current parameter and puts it in the
				// correspondent position inside array.
				paramsClasses[currentParamIndex] = paramsValues[currentParamIndex].getClass();
			}
		}
		// Returns the classes of the parameters.
		return paramsClasses;
	}

	/**
	 * Gets a method from the reflected class for the given parameters. The search includes the inherited
	 * classes and interfaces of each parameter class.
	 * 
	 * @param methodName
	 *            Name of the method to be found.
	 * @param paramsValues
	 *            Parameters values to be used during the method invocation.
	 * @return A method from the reflected class for the given parameters.
	 * @throws EmptyParameterException
	 *             If the method name is empty.
	 * @throws InvalidParameterException
	 *             If the method cannot be found or one of the parameters values is empty.
	 */
	public MethodMirror getMethod(final String methodName, final Object[] paramsValues)
			throws InvalidParameterException, EmptyParameterException {
		// Tries to get the method.
		return getMethod(methodName, getParamsClasses(paramsValues));
	}

	/**
	 * Gets all annotations from a class. The search includes super classes and interfaces (but only the first
	 * annotation found for a type is kept).
	 * 
	 * @return All annotations from a class.
	 */
	public Set<AnnotationMirror<?>> getAnnotations() {
		// Creates the set for the annotations.
		final Set<AnnotationMirror<?>> annotations = new LinkedHashSet<>();
		// For each super class of the reflected class.
		for (final ClassMirror<?> currentClass : getSuperClasses(true)) {
			// For each annotation in the current class.
			for (final Annotation currentAnnotation : currentClass.getReflectedClass().getAnnotations()) {
				// Adds the annotation to the set.
				annotations.add(new AnnotationMirror<>(currentAnnotation));
			}
		}
		// Returns the annotations found.
		return annotations;
	}

	/**
	 * Gets all annotations with a given type. The search includes super classes and interfaces.
	 * 
	 * @param <AnyAnnotation>
	 *            Any annotation being searched.
	 * @param annotationClass
	 *            Type of the annotation to be searched for.
	 * @return All annotations with the given type.
	 */
	public <AnyAnnotation extends Annotation> Collection<AnnotationMirror<AnyAnnotation>> getAnnotations(
			final Class<AnyAnnotation> annotationClass) {
		// Creates the set for the annotations.
		final Collection<AnnotationMirror<AnyAnnotation>> annotations = new ArrayList<>();
		// For each super class of the reflected class.
		for (final ClassMirror<?> currentClass : getSuperClasses(true)) {
			// Tries to get the annotation for the current class.
			final AnyAnnotation annotation = currentClass.getReflectedClass().getAnnotation(annotationClass);
			// If the annotation is found.
			if (annotation != null) {
				// Adds the annotation to the list.
				annotations.add(new AnnotationMirror<>(annotation));
			}
		}
		// Returns the annotations found.
		return annotations;
	}

	/**
	 * Returns an annotation from the reflected class (or its inherited classes and interfaces). The first
	 * annotation found for the given class is returned.
	 * 
	 * @param <AnyAnnotation>
	 *            Any annotation being searched.
	 * @param annotationClass
	 *            Type of the annotation to be searched for.
	 * @return An annotation from the reflected class (or its inherited classes and interfaces).
	 * @throws EmptyParameterException
	 *             If the passed class is null.
	 */
	public <AnyAnnotation extends Annotation> AnnotationMirror<AnyAnnotation> getAnnotation(
			final Class<AnyAnnotation> annotationClass) throws EmptyParameterException {
		// Asserts that the class is not null.
		PreConditions.assertParamNotNull(ClassParamKeys.ANNOTATION_CLASS, annotationClass);
		// For each annotation in the reflected class.
		for (final AnnotationMirror<?> currentAnnotation : getAnnotations()) {
			// If the current annotation is an instance of the given class.
			if (currentAnnotation.getReflectedAnnotation().annotationType().equals(annotationClass)) {
				// Returns the current annotation.
				return (AnnotationMirror<AnyAnnotation>) currentAnnotation;
			}
		}
		// If the annotation was not found, return null.
		return null;
	}

	/**
	 * Gets a constructor from the reflected class for the given parameters. The search includes the inherited
	 * classes and interfaces of each parameter class.
	 * 
	 * @param varyingParamIndex
	 *            Index of the parameter class that will be varied with inherited classes/interfaces.
	 * @param paramsClasses
	 *            Parameters classes for the constructor to be found.
	 * 
	 * @return A constructor from the reflected class for the given parameters.
	 * @throws InvalidParameterException
	 *             If the constructor cannot be found.
	 */
	private ConstructorMirror<Reflected> getConstructor(final Integer varyingParamIndex,
			final Class<?>[] paramsClasses) throws InvalidParameterException {
		// Keeps the original value of the parameter that will be changed.
		final Class<?> initialParamClass = paramsClasses[varyingParamIndex];
		// Tries to get the constructor.
		try {
			// For each class inherited by the current parameter being varied.
			for (final ClassMirror<?> currentParamClass : new ClassMirror<>(paramsClasses[varyingParamIndex])
					.getSuperClasses(true)) {
				// Changes the parameter class with the current superclass/interface.
				paramsClasses[varyingParamIndex] = currentParamClass.getReflectedClass();
				// Tries to return the constructor with the exact parameters classes.
				try {
					return new ConstructorMirror<>(getReflectedClass().getConstructor(paramsClasses));
				}
				// If it is not possible to get the constructor.
				catch (final Exception exception) {
					// If the current varying parameter class is not the last parameter class.
					if (varyingParamIndex < (paramsClasses.length - 1)) {
						// Tries to get the constructor varying the next parameter.
						try {
							return getConstructor(varyingParamIndex + 1, paramsClasses);
						}
						// If it is not possible to get the constructor.
						catch (final Exception exception2) {
							// Ignores and continues the loop.
						}
					}
				}
			}
			// If no constructor was found, throws an exception.

			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.CONSTRUCTOR_NOT_FOUND,
					new Object[] { getReflectedClass(), paramsClasses, varyingParamIndex }, null);
		}
		// In all cases.
		finally {
			// At the end, returns the parameter class being varied to its original value.
			paramsClasses[varyingParamIndex] = initialParamClass;
		}
	}

	/**
	 * Gets a constructor from the reflected class for the given parameters. The search includes the inherited
	 * classes and interfaces of each parameter class.
	 * 
	 * @param paramsClasses
	 *            Parameters classes for the constructor to be found.
	 * @return A constructor from the reflected class for the given parameters.
	 * @throws InvalidParameterException
	 *             If the constructor cannot be found or one of the parameters classes is empty.
	 */
	public ConstructorMirror<Reflected> getConstructor(final Class<?>[] paramsClasses)
			throws InvalidParameterException {
		// If there are no parameters for the constructor to be found.
		if ((paramsClasses == null) || (paramsClasses.length == 0)) {
			// Tries to get the constructor normally using the reflection API.
			try {
				return new ConstructorMirror<>(getReflectedClass().getConstructor());
			}
			// If the constructor cannot be found or accessed.
			catch (final Exception exception) {
				// Throws an exception.
				throw new InvalidParameterException(MessageTypes.Error.class,
						ErrorKeys.CONSTRUCTOR_NOT_FOUND, new Object[] { getReflectedClass(), paramsClasses },
						null);
			}
		}
		// If there are parameters.
		else {
			// For each parameters classes.
			for (Integer currentParamIndex = 0; currentParamIndex < paramsClasses.length; currentParamIndex++) {
				// Gets the current parameter class.
				final Class<?> currentParamClass = paramsClasses[currentParamIndex];
				// If the current parameter class is empty.
				if (currentParamClass == null) {
					// Throws an exception.
					throw new InvalidParameterException(MessageTypes.Error.class,
							ErrorKeys.EMPTY_PARAM_CLASS, new Object[] { currentParamIndex + 1 }, null);
				}
			}
			// Tries to get the constructor varying the first parameter class.
			return getConstructor(0, paramsClasses);
		}
	}

	/**
	 * Gets a constructor from the reflected class for the given parameters. The search includes the inherited
	 * classes and interfaces of each parameter class.
	 * 
	 * @param paramsValues
	 *            Parameters values to be used during the constructor invocation.
	 * @return A constructor from the reflected class for the given parameters.
	 * @throws InvalidParameterException
	 *             If the constructor cannot be found or one of the parameters values is empty.
	 */
	public ConstructorMirror<Reflected> getConstructor(final Object[] paramsValues)
			throws InvalidParameterException {
		// Tries to get the constructor.
		return getConstructor(getParamsClasses(paramsValues));
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((reflectedClass == null) ? 0 : reflectedClass.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ClassMirror<?> other = (ClassMirror<?>) obj;
		if (reflectedClass == null) {
			if (other.reflectedClass != null) {
				return false;
			}
		} else if (!reflectedClass.equals(other.reflectedClass)) {
			return false;
		}
		return true;
	}
}
