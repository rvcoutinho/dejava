package org.dejava.component.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.dejava.component.util.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.util.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.util.reflection.constant.ErrorKeys;

/**
 * TODO
 * 
 * @param <Reflected>
 *            The class to be reflected.
 */
public class ClassMirror<Reflected extends Object> {
	
	/**
	 * Class being reflected.
	 */
	private Class<? extends Reflected> reflectedClass;
	
	/**
	 * Gets the class being reflected.
	 * 
	 * @return The class being reflected.
	 */
	public Class<? extends Reflected> getReflectedClass() {
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
		// If the given class is null.
		if (reflectedClass == null) {
			// Throws an exception.
			throw new EmptyParameterException(1);
		}
		// Sets the main reflection fields.
		this.reflectedClass = reflectedClass;
	}
	
	/**
	 * Public constructor for the reflection class.
	 * 
	 * @param reflectedClassName
	 *            Name of the class being reflected.
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
			throw new InvalidParameterException(ErrorKeys.UNEXPECTED_CLASS, exception, 1, reflectedClassName);
		}
	}
	
	/**
	 * Public constructor for the reflection class.
	 * 
	 * @param reflectedClassName
	 *            Name of the class being reflected.
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
	 * @param classLoader
	 *            Class loader to be used with the reflected class.
	 * @param initialize
	 *            Whether the class must be initialized.
	 * @throws InvalidParameterException
	 *             If the class cannot be found for the name or if the depth is greater than the stack trace
	 *             size.
	 */
	public ClassMirror(final Integer depth, final ClassLoader classLoader, final Boolean initialize)
			throws InvalidParameterException {
		// Tries to set the reflected class.
		try {
			this.reflectedClass = (Class<Reflected>) getCurrentClass(depth, classLoader, initialize);
		}
		// If the class cannot be casted.
		catch (final ClassCastException exception) {
			// Throws an exception.
			throw new InvalidParameterException(ErrorKeys.UNEXPECTED_CLASS, exception, 1, depth);
		}
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
		this(depth, null, true);
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
	 * @throws InvalidParameterException
	 *             If the class cannot be found for the name.
	 */
	public static Class<?> getClass(final String className, final ClassLoader classLoader,
			final Boolean initialize) throws InvalidParameterException {
		// If the given class name is null.
		if ((className == null) || (className.isEmpty())) {
			// Throws an exception.
			throw new EmptyParameterException(1);
		}
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
			throw new InvalidParameterException(ErrorKeys.CLASS_NOT_FOUND, exception, 1, className);
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
			throw new InvalidParameterException(ErrorKeys.CLASS_NOT_FOUND, exception, 1, depth);
		}
	}
	
	/**
	 * Gets the current class (with the selected depth) being executed.
	 * 
	 * @param depth
	 *            Depth in the current stack for the desired class (0 is the first and so on).
	 * @param classLoader
	 *            Class loader that must be used in the class recovery. If no class loader is given, the
	 *            context class loader is used.
	 * @param initialize
	 *            Whether the class must be initialized.
	 * @return The current class (with the selected depth) being executed.
	 * @throws InvalidParameterException
	 *             If the class cannot be found for the name or if the depth is greater than the stack trace
	 *             size.
	 */
	public static Class<?> getCurrentClass(final Integer depth, final ClassLoader classLoader,
			final Boolean initialize) throws InvalidParameterException {
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
		return getClass(className, classLoader, initialize);
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
		// The context class loader is used to recover the current class.
		return getCurrentClass(actualDepth + 1, null, true);
	}
	
	/**
	 * Gets all the classes and interfaces inherited by the reflected class (including this one).
	 * 
	 * @return All the classes and interfaces inherited by the reflected class (including this one).
	 * @throws EmptyParameterException
	 *             If the reflected class is null (should never be).
	 */
	public Set<ClassMirror<?>> getSuperclasses() throws EmptyParameterException {
		// All classes to be returned.
		final Set<ClassMirror<?>> classes = new LinkedHashSet<ClassMirror<?>>();
		// Adds the class itself to the list.
		classes.add(this);
		// If the class has any superclass.
		if (getReflectedClass().getSuperclass() != null) {
			// Calls the same method recursively with its superclass (adding the classes to the list).
			classes.addAll(new ClassMirror<Object>(getReflectedClass().getSuperclass()).getSuperclasses());
		}
		// If the class has any interfaces.
		if (getReflectedClass().getInterfaces() != null) {
			// For each interface.
			for (final Class<?> currentInterface : getReflectedClass().getInterfaces()) {
				// Calls the same method recursively with the current interface (adding the classes to the
				// list).
				classes.addAll(new ClassMirror<Object>(currentInterface).getSuperclasses());
			}
		}
		// Adds the Object class.
		classes.add(new ClassMirror<Object>(Object.class));
		// Returns all found classes.
		return classes;
	}
	
	/**
	 * Returns a field from the reflected class. The search includes the inherited classes. The first field
	 * found for the name is returned.
	 * 
	 * @param fieldName
	 *            Name of the field to be found.
	 * @return A field from the desired class.
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty or not accessible).
	 */
	public FieldMirror getField(final String fieldName) throws InvalidParameterException {
		// If the field name is empty.
		if ((fieldName == null) || (fieldName.isEmpty())) {
			// Throws an exception.
			throw new EmptyParameterException(1);
		}
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
		throw new InvalidParameterException(ErrorKeys.FIELD_NOT_FOUND, null, 1, fieldName);
	}
	
	/**
	 * Returns all fields from the reflected class (including inherited ones).
	 * 
	 * @return All fields from a class (including inherited ones).
	 */
	public Collection<FieldMirror> getFields() {
		// Collection to keep found fields.
		final Collection<FieldMirror> fields = new ArrayList<FieldMirror>();
		// While there are super classes.
		for (Class<?> currentClass = getReflectedClass(); currentClass != null; currentClass = currentClass
				.getSuperclass()) {
			// For each field in the current class.
			for (final Field currentField : currentClass.getDeclaredFields()) {
				// Tries to add the current field to the collection.
				try {
					fields.add(new FieldMirror(currentField));
				}
				// If an invalid parameter exception is thrown.
				catch (final InvalidParameterException exception) {
					// Ignores it, as the current field should never be null.
				}
			}
		}
		// Return all the fields found in the search.
		return fields;
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
	 * Gets a method from the reflected class for the given parameters. The search includes the inherited
	 * classes and interfaces of each parameter class.
	 * 
	 * @param methodName
	 *            Name of the method to be found.
	 * @param paramsClasses
	 *            Parameters classes for the method to be found.
	 * @param varyingParamIndex
	 *            Index of the parameter class that will be varied with inherited classes/interfaces.
	 * @return A method from the reflected class for the given parameters.
	 * @throws InvalidParameterException
	 *             If the method cannot be found.
	 */
	private MethodMirror getMethod(final String methodName, final Class<?>[] paramsClasses,
			final Integer varyingParamIndex) throws InvalidParameterException {
		// Keeps the original value of the parameter that will be changed.
		final Class<?> initialParamClass = paramsClasses[varyingParamIndex];
		// Tries to get the method.
		try {
			// For each class inherited by the current parameter being varied.
			for (final ClassMirror<?> currentParamClass : new ClassMirror<Object>(
					paramsClasses[varyingParamIndex]).getSuperclasses()) {
				// Changes the parameter class with the current superclass/interface.
				paramsClasses[varyingParamIndex] = currentParamClass.getReflectedClass();
				// Tries to return the method with the exact parameters classes.
				try {
					return new MethodMirror(getReflectedClass().getMethod(methodName, paramsClasses));
				}
				// If it is not possible to get the method.
				catch (final Exception exception) {
					// If the current varying parameter class is not the last parameter class.
					if (varyingParamIndex < (paramsClasses.length - 1)) {
						// Tries to get the method varying the next parameter.
						try {
							return getMethod(methodName, paramsClasses, varyingParamIndex + 1);
						}
						// If it is not possible to get the method.
						catch (final Exception exception2) {
							// Ignores and continues the loop.
						}
					}
				}
			}
			// If no method was found, throws an exception.
			throw new InvalidParameterException(ErrorKeys.METHOD_NOT_FOUND, null, new Object[] { methodName,
					Arrays.asList(paramsClasses) });
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
	 *             If the parameters for this method are not valid (empty or method could not be accessed).
	 * @throws EmptyParameterException
	 *             If the method name is empty.
	 * @throws InvalidParameterException
	 *             If the method cannot be found or one of the parameters classes is empty.
	 */
	public MethodMirror getMethod(final String methodName, final Class<?>[] paramsClasses)
			throws InvalidParameterException, EmptyParameterException {
		// If the method name is empty.
		if ((methodName == null) || (methodName.isEmpty())) {
			// Throws an exception.
			throw new EmptyParameterException(1);
		}
		// If there are no parameters for the method to be found.
		if ((paramsClasses == null) || (paramsClasses.length == 0)) {
			// Tries to get the method normally using the reflection API.
			try {
				return new MethodMirror(getReflectedClass().getMethod(methodName));
			}
			// If the method cannot be found or accessed.
			catch (final Exception exception) {
				// Throws an exception.
				throw new InvalidParameterException(ErrorKeys.METHOD_NOT_FOUND, null, new Object[] {
						methodName, Arrays.asList(paramsClasses) });
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
					throw new InvalidParameterException(ErrorKeys.EMPTY_PARAM_CLASS, null,
							new Object[] { currentParamIndex + 1 });
				}
			}
			// Tries to get the method varying the first parameter class.
			return getMethod(methodName, paramsClasses, 0);
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
					throw new InvalidParameterException(ErrorKeys.EMPTY_PARAM_VALUE, null,
							new Object[] { currentParamIndex + 1 });
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
	 * Gets all annotations form a class. The search includes super classes and interfaces (but only the first
	 * annotation found for a type is kept).
	 * 
	 * @return All annotations form a class.
	 */
	public Set<AnnotationMirror<?>> getAnnotations() {
		// Creates the set for the annotations.
		final Set<AnnotationMirror<?>> annotations = new LinkedHashSet<>();
		// For each super class of the reflected class.
		for (final ClassMirror<?> currentClass : getSuperclasses()) {
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
	 * Returns an annotation from the passed class (and its inherited classes and interfaces).
	 * 
	 * @param <AnyAnnotation>
	 *            Any annotation being searched.
	 * @param clazz
	 *            Class from which the annotation will be searched for.
	 * @param annotationClass
	 *            Type of the annotation to be searched for.
	 * @return An annotation from the passed class (and its inherited classes and interfaces).
	 * @throws EmptyParameterException
	 *             If the passed class is null.
	 */
	public static <AnyAnnotation extends Annotation> AnyAnnotation getAnnotation(final Class<?> clazz,
			final Class<AnyAnnotation> annotationClass) throws EmptyParameterException {
		// If the passed class is null.
		if (clazz == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// If the passed annotation class is null.
		if (annotationClass == null) {
			// Throws an exception.
			throw new EmptyParameterException("annotation class");
		}
		// For each superclass/interface.
		for (final Class<?> currentClass : ClassHandler.getSuperclasses(clazz)) {
			// Tries to get the desired annotation.
			final AnyAnnotation annotation = currentClass.getAnnotation(annotationClass);
			// If the annotation was correctly recovered.
			if (annotation != null) {
				// Returns it.
				return annotation;
			}
		}
		// If no annotation was found, returns null.
		return null;
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
		final ClassMirror other = (ClassMirror) obj;
		if (reflectedClass == null) {
			if (other.reflectedClass != null) {
				return false;
			}
		}
		else if (!reflectedClass.equals(other.reflectedClass)) {
			return false;
		}
		return true;
	}
	
}
