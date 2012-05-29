package org.dejava.component.util.reflection.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.dejava.component.util.exception.localized.EmptyParameterException;
import org.dejava.component.util.exception.localized.InvalidParameterException;
import org.dejava.component.util.reflection.constant.ErrorKeys;
import org.dejava.component.util.reflection.exception.InvocationException;

/**
 * Helps handling reflection with classes and methods.
 */
public final class MethodHandler {
	
	/**
	 * Private constructor.
	 */
	private MethodHandler() {
	}
	
	/**
	 * 
	 * Returns the methods with the given annotation. Only public methods are returned. The search includes
	 * inherited methods.
	 * 
	 * @param clazz
	 *            Class from which the methods are searched for.
	 * @param annotationClass
	 *            Class of the annotation that must be found for the wanted methods.
	 * @return The methods with the given annotation.
	 * @throws EmptyParameterException
	 *             If the parameters for this method are not valid (empty).
	 */
	public static Collection<Method> getMethods(final Class<?> clazz,
			final Class<? extends Annotation> annotationClass) throws EmptyParameterException {
		// If the passed class is null.
		if (clazz == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// If the annotation class is null.
		if (annotationClass == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// Methods with annotation.
		final Collection<Method> annotatedMethods = new ArrayList<Method>();
		// Gets the methods for the class.
		final Method[] methods = clazz.getMethods();
		// For each method found for the class.
		for (final Method currentMethod : methods) {
			// If the current class has the given annotation.
			if (currentMethod.isAnnotationPresent(annotationClass)) {
				// Adds the current method in the annotated methods list.
				annotatedMethods.add(currentMethod);
			}
		}
		// Returns the annotated methods.
		return annotatedMethods;
	}
	
	/**
	 * Gets the name of the current method (with the selected depth) being executed.
	 * 
	 * @param depth
	 *            Depth in the current stack for the desired method (0 is the first and so on).
	 * @return The name of the current method (with the selected depth) being executed.
	 */
	public static String getCurrentMethodName(Integer depth) {
		// If the given depth is null.
		if (depth == null) {
			// The depth is 0.
			depth = 0;
		}
		// Gets the stack trace.
		final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		// Returns the name of the method in the selected depth.
		return stackTrace[2 + depth].getMethodName();
	}
	
	/**
	 * Gets a method from a class with the passed parameters. The search includes the inherited classes and
	 * interfaces of each parameter class.
	 * 
	 * @param clazz
	 *            The class from which the method will be found.
	 * @param methodName
	 *            Name of the method to be found.
	 * @param paramsClasses
	 *            Parameters classes for the method to be found.
	 * @param varyingParamIndex
	 *            Index of the parameter class that will be varied with inherited classes/interfaces.
	 * @return A method from a class with the passed parameters.
	 * @throws InvalidParameterException
	 *             If the parameters for this method are not valid (empty or method could not be accessed).
	 */
	private static Method getMethod(final Class<?> clazz, final String methodName,
			final Class<?>[] paramsClasses, Integer varyingParamIndex) throws InvalidParameterException {
		// If the index of the parameter being varied is null.
		if (varyingParamIndex == null) {
			// The index is 0.
			varyingParamIndex = 0;
		}
		// Keeps the original value of the parameter that will be changed.
		final Class<?> initialParamClass = paramsClasses[varyingParamIndex];
		// For each class inherited by the current parameter being varied.
		for (final Class<?> currentParamClass : ClassHandler
				.getSuperclasses(paramsClasses[varyingParamIndex])) {
			// Changes the parameter class with the current
			// superclass/interface.
			paramsClasses[varyingParamIndex] = currentParamClass;
			// Tries to return the method with the exact parameters classes.
			try {
				return clazz.getMethod(methodName, paramsClasses);
			}
			// If it is not possible to get the method.
			catch (final Exception exception) {
				// If the current varying parameter class is not the last
				// parameter class.
				if (varyingParamIndex < (paramsClasses.length - 1)) {
					// Tries to get the method varying the next parameter.
					try {
						return getMethod(clazz, methodName, paramsClasses, varyingParamIndex + 1);
					}
					// If it is not possible to get the method.
					catch (final Exception exception2) {
						// Ignores and continues the loop.
					}
				}
			}
		}
		// At the end, returns the parameter class being varied to its original
		// value.
		paramsClasses[varyingParamIndex] = initialParamClass;
		// If no method was found, throws an exception.
		throw new InvalidParameterException(ErrorKeys.MISSING_METHOD, null, null);
	}
	
	/**
	 * Gets a method from a class with the passed parameters. The search includes the inherited classes and
	 * interfaces of each parameter class.
	 * 
	 * @param clazz
	 *            The class from which the method will be found.
	 * @param methodName
	 *            Name of the method to be found.
	 * @param paramsClasses
	 *            Parameters classes for the method to be found.
	 * @return A method from a class with the passed parameters.
	 * @throws InvalidParameterException
	 *             If the parameters for this method are not valid (empty or method could not be accessed).
	 */
	public static Method getMethod(final Class<?> clazz, final String methodName,
			final Class<?>[] paramsClasses) throws InvalidParameterException {
		// If the passed class is null.
		if (clazz == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// If the method name is empty.
		if ((methodName == null) || (methodName.isEmpty())) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// If there are no parameters for the method to be found.
		if ((paramsClasses == null) || (paramsClasses.length == 0)) {
			// Tries to get the method normally using the reflection API.
			try {
				return clazz.getMethod(methodName);
			}
			// If the method cannot be found or accessed.
			catch (final Exception exception) {
				// Throws an exception.
				throw new InvalidParameterException(ErrorKeys.MISSING_METHOD, exception, null);
			}
		}
		// If there are parameters.
		else {
			// Tries to get the method varying the first parameter class.
			return getMethod(clazz, methodName, paramsClasses, 0);
		}
	}
	
	/**
	 * Returns the classes of the parameters from its values.
	 * 
	 * @param paramsValues
	 *            Parameters values to be used during a invocation of a method.
	 * @return The classes of the parameters from its values.
	 * @throws InvalidParameterException
	 *             If the parameters for this method are not valid (empty).
	 */
	public static Class<?>[] getParametersClasses(final Object[] paramsValues)
			throws InvalidParameterException {
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
					throw new InvalidParameterException(ErrorKeys.EMPTY_PARAM_VALUE, null, null);
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
	 * Invokes a method from an object/class. The search includes the inherited classes and interfaces of each
	 * parameter class.
	 * 
	 * @param clazz
	 *            The class from which the method will be invoked.
	 * @param object
	 *            The object from which the method will be invoked.
	 * @param methodName
	 *            Name of the method to be found.
	 * @param paramsClasses
	 *            Parameters classes for the method to be invoked.
	 * @param paramsValues
	 *            Parameters values to be used during the invocation.
	 * @param ignoreAccess
	 *            If the defined access for the method must be ignored.
	 * @return Returns the method being invoked return value.
	 * @throws InvalidParameterException
	 *             If the parameters for this method are not valid (empty or method could not be accessed).
	 * @throws InvocationException
	 *             If the method throws an exception itself.
	 */
	private static Object invokeMethod(final Class<?> clazz, final Object object, final String methodName,
			Class<?>[] paramsClasses, final Object[] paramsValues, final Boolean ignoreAccess)
			throws InvalidParameterException, InvocationException {
		// If no classes are given for the parameters.
		if (paramsClasses == null) {
			// Gets the parameters classes from the parameters values.
			paramsClasses = getParametersClasses(paramsValues);
		}
		// Tries to invoke the method.
		try {
			// Tries to get the method.
			final Method method = getMethod(clazz, methodName, paramsClasses);
			// Redefines the access of the method.
			method.setAccessible(ignoreAccess);
			// Tries to invoke it.
			return method.invoke(object, paramsValues);
		}
		// If an exception is thrown by the method itself.
		catch (final InvocationTargetException exception) {
			// Throws an exception using the exception thrown as its cause.
			throw new InvocationException(ErrorKeys.METHOD_EXCEPTION, exception.getCause(), null);
		}
		// If the parameter values are illegal for the method.
		catch (final IllegalArgumentException exception) {
			// Throws an exception.
			throw new InvalidParameterException(ErrorKeys.ILLEGAL_PARAMS_VALUES, exception, null);
		}
		// If the method cannot be accessed.
		catch (final IllegalAccessException exception) {
			// Throws an exception.
			throw new InvalidParameterException(ErrorKeys.UNACCESSIBLE_METHOD, exception, null);
		}
	}
	
	/**
	 * Invokes a static method from a class. The search includes the inherited classes and interfaces of each
	 * parameter class.
	 * 
	 * @param clazz
	 *            The class from which the method will be invoked.
	 * @param methodName
	 *            Name of the method to be invoked.
	 * @param paramsClasses
	 *            Parameters classes for the method to be invoked.
	 * @param paramsValues
	 *            Parameters values to be used during the invocation.
	 * @param ignoreAccess
	 *            If the defined access for the method must be ignored.
	 * @return Returns the method being invoked return value.
	 * @throws InvalidParameterException
	 *             If the parameters for this method are not valid (empty or method could not be accessed).
	 * @throws InvocationException
	 *             If the method throws an exception itself.
	 */
	public static Object invokeStaticMethod(final Class<?> clazz, final String methodName,
			final Class<?>[] paramsClasses, final Object[] paramsValues, final Boolean ignoreAccess)
			throws InvalidParameterException, InvocationException {
		// Invoke the method.
		return invokeMethod(clazz, null, methodName, paramsClasses, paramsValues, ignoreAccess);
	}
	
	/**
	 * Invokes a method from an object. The search includes the inherited classes and interfaces of each
	 * parameter class.
	 * 
	 * @param object
	 *            The object from which the method will be invoked.
	 * @param methodName
	 *            Name of the method to be found.
	 * @param paramsClasses
	 *            Parameters classes for the method to be invoked.
	 * @param paramsValues
	 *            Parameters values to be used during the invocation.
	 * @param ignoreAccess
	 *            If the defined access for the method must be ignored.
	 * @return Returns the method being invoked return value.
	 * @throws InvalidParameterException
	 *             If the parameters for this method are not valid (empty or method could not be accessed).
	 * @throws InvocationException
	 *             If the method throws an exception itself.
	 */
	public static Object invokeMethod(final Object object, final String methodName,
			final Class<?>[] paramsClasses, final Object[] paramsValues, final Boolean ignoreAccess)
			throws InvalidParameterException, InvocationException {
		// Invokes the method.
		return invokeMethod(object.getClass(), object, methodName, paramsClasses, paramsValues, ignoreAccess);
	}
	
	/**
	 * Invokes a method from a JNDI object. The search includes the inherited classes and interfaces of each
	 * parameter class.
	 * 
	 * @param jndiPath
	 *            The JNDI path for the object from which the method will be invoked.
	 * @param methodName
	 *            Name of the method to be found.
	 * @param paramsClasses
	 *            Parameters classes for the method to be invoked.
	 * @param paramsValues
	 *            Parameters values to be used during the invocation.
	 * @param ignoreAccess
	 *            If the defined access for the method must be ignored.
	 * @return Returns the method being invoked return value.
	 * @throws InvalidParameterException
	 *             If the parameters for this method are not valid (empty or method could not be accessed).
	 * @throws InvocationException
	 *             If the method throws an exception itself.
	 */
	public static Object invokeMethod(final String jndiPath, final String methodName,
			final Class<?>[] paramsClasses, final Object[] paramsValues, final Boolean ignoreAccess)
			throws InvalidParameterException, InvocationException {
		// If the JNDI path is empty.
		if ((jndiPath == null) || (jndiPath.isEmpty())) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// Tries to invoke the method.
		try {
			// Tries to retrieve the object.
			final Object object = InitialContext.doLookup(jndiPath);
			// Invokes the method.
			return invokeMethod(object, methodName, paramsClasses, paramsValues, ignoreAccess);
		}
		// If the object cannot be found.
		catch (final NamingException exception) {
			// Throws an exception.
			throw new InvalidParameterException(ErrorKeys.INVALID_JNDI_PATH, exception, null);
		}
	}
}