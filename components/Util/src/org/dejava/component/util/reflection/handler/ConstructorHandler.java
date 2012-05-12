package org.dejava.component.util.reflection.handler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.dejava.component.util.exception.localized.EmptyParameterException;
import org.dejava.component.util.exception.localized.InvalidParameterException;
import org.dejava.component.util.reflection.constant.ErrorKeys;
import org.dejava.component.util.reflection.exception.InvocationException;

/**
 * Helps handling reflection with constructors.
 */
public final class ConstructorHandler {
	
	/**
	 * Private constructor.
	 */
	private ConstructorHandler() {
	}
	
	/**
	 * Gets a constructor from a class with the passed parameters. The search includes the inherited classes
	 * and interfaces of each parameter class.
	 * 
	 * @param <Type>
	 *            Any type.
	 * @param clazz
	 *            The class from which the constructor will be found.
	 * @param paramsClasses
	 *            Parameters classes for the constructor to be found.
	 * @param varyingParamIndex
	 *            Index of the parameter class that will be varied with inherited classes/interfaces.
	 * @return A constructor from a class with the passed parameters.
	 * @throws InvalidParameterException
	 *             If the parameters for this constructor are not valid (empty or constructor could not be
	 *             accessed).
	 */
	private static <Type extends Object> Constructor<Type> getConstructor(final Class<Type> clazz,
			final Class<?>[] paramsClasses, Integer varyingParamIndex) throws InvalidParameterException {
		// If the index of the parameter being varied is null.
		if (varyingParamIndex == null) {
			// The index is 0.
			varyingParamIndex = 0;
		}
		// Keeps the original value of the parameter that will be changed.
		final Class<?> originalParameterClass = paramsClasses[varyingParamIndex];
		// For each class inherited by the current parameter being varied.
		for (Class<?> currentParamClass : ClassHandler.getSuperclasses(paramsClasses[varyingParamIndex])) {
			// Changes the parameter class with the current
			// superclass/interface.
			paramsClasses[varyingParamIndex] = currentParamClass;
			// Tries to return the constructor with the exact parameters
			// classes.
			try {
				return clazz.getConstructor(paramsClasses);
			}
			// If it is not possible to get the constructor.
			catch (Exception exception) {
				// If the current varying parameter class is not the last
				// parameter class.
				if (varyingParamIndex < (paramsClasses.length - 1)) {
					// Tries to get the constructor varying the next parameter.
					try {
						return getConstructor(clazz, paramsClasses, varyingParamIndex + 1);
					}
					// If it is not possible to get the constructor.
					catch (Exception exception2) {
						// Ignores and continues the loop.
					}
				}
			}
		}
		// At the end, returns the parameter class being varied to its original
		// value.
		paramsClasses[varyingParamIndex] = originalParameterClass;
		// If no constructor was found, throws an exception.
		throw new InvalidParameterException(ErrorKeys.MISSING_CONSTRUCTOR, null, null);
	}
	
	/**
	 * Gets a constructor from a class with the passed parameters. The search includes the inherited classes
	 * and interfaces of each parameter class.
	 * 
	 * @param <Type>
	 *            Any type.
	 * @param clazz
	 *            The class from which the constructor will be found.
	 * @param paramsClasses
	 *            Parameters classes for the constructor to be found.
	 * @return A constructor from a class with the passed parameters.
	 * @throws InvalidParameterException
	 *             If the parameters for this constructor are not valid (empty or constructor could not be
	 *             accessed).
	 */
	public static <Type extends Object> Constructor<Type> getConstructor(final Class<Type> clazz,
			final Class<?>[] paramsClasses) throws InvalidParameterException {
		// If the passed class is null.
		if (clazz == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// If there are any parameters for the constructor to be found.
		if ((paramsClasses != null) && (paramsClasses.length != 0)) {
			// Tries to get the constructor varying the first parameter class.
			return getConstructor(clazz, paramsClasses, 0);
		}
		// If there are not.
		else {
			// Tries to get the constructor normally using the reflection API.
			try {
				return clazz.getConstructor();
			}
			// If the constructor cannot be found or accessed.
			catch (Exception exception) {
				// Throws an exception.
				throw new InvalidParameterException(ErrorKeys.MISSING_CONSTRUCTOR, exception, null);
			}
		}
	}
	
	/**
	 * Invoke a constructor from a class. The search includes the inherited classes and interfaces of each
	 * parameter class.
	 * 
	 * @param <Type>
	 *            Any type.
	 * @param clazz
	 *            The class from which the constructor will be invoked.
	 * @param paramsClasses
	 *            Parameters classes for the constructor to be invoked.
	 * @param paramsValues
	 *            Parameters values to be used during the invocation.
	 * @return Returns the constructor being invoked return value.
	 * @throws InvalidParameterException
	 *             If the parameters for this constructor are not valid (empty or constructor could not be
	 *             accessed).
	 * @throws InvocationException
	 *             If the constructor throws an exception itself.
	 */
	public static <Type extends Object> Type invokeConstructor(final Class<Type> clazz,
			final Class<?>[] paramsClasses, final Object[] paramsValues) throws InvalidParameterException,
			InvocationException {
		// Tries to invoke the constructor.
		try {
			// Tries to get the constructor.
			final Constructor<Type> constructor = getConstructor(clazz, paramsClasses);
			// Tries to invoke it.
			return constructor.newInstance(paramsValues);
		}
		// If the class is abstract.
		catch (InstantiationException exception) {
			// Throws an exception.
			throw new InvalidParameterException(ErrorKeys.ABSTRACT_CLASS, exception, null);
		}
		// If an exception is thrown by the constructor itself.
		catch (InvocationTargetException exception) {
			// Throws an exception using the exception thrown as its cause.
			throw new InvocationException(ErrorKeys.CONSTRUCTOR_EXCEPTION, exception, null);
		}
		// If the parameter values are illegal for the constructor.
		catch (IllegalArgumentException exception) {
			// Throws an exception.
			throw new InvalidParameterException(ErrorKeys.ILLEGAL_PARAMS_VALUES, exception, null);
		}
		// If the constructor cannot be accessed.
		catch (IllegalAccessException exception) {
			// Throws an exception.
			throw new InvalidParameterException(ErrorKeys.UNACCESSIBLE_CONSTRUCTOR, exception, null);
		}
	}
	
	/**
	 * Invoke a constructor from a class. The search includes the inherited classes and interfaces of each
	 * parameter class.
	 * 
	 * @param <Type>
	 *            Any type.
	 * @param clazz
	 *            The class from which the constructor will be invoked.
	 * @param paramsValues
	 *            Parameters values to be used during the invocation.
	 * @return Returns the constructor being invoked return value.
	 * @throws InvalidParameterException
	 *             If the parameters for this constructor are not valid (empty or constructor could not be
	 *             accessed).
	 * @throws InvocationException
	 *             If the constructor throws an exception itself.
	 */
	public static <Type extends Object> Type invokeConstructor(final Class<Type> clazz,
			final Object[] paramsValues) throws InvalidParameterException, InvocationException {
		// Invoke the constructor.
		return invokeConstructor(clazz, MethodHandler.getParametersClasses(paramsValues), paramsValues);
	}
}
