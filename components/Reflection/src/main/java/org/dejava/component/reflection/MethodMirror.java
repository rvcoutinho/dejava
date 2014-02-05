package org.dejava.component.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.reflection.constant.ErrorKeys;
import org.dejava.component.reflection.constant.MethodParamKeys;
import org.dejava.component.reflection.exception.InvocationException;
import org.dejava.component.reflection.util.MessageTypes;
import org.dejava.component.validation.method.PreConditions;

/**
 * Extends the reflection behavior of the Method type. It is part of an extended Java reflection API, that
 * intends to make reflection easier.
 */
public class MethodMirror {

	/**
	 * Method being reflected.
	 */
	private Method reflectedMethod;

	/**
	 * Gets the method being reflected.
	 * 
	 * @return The method being reflected.
	 */
	public Method getReflectedMethod() {
		return reflectedMethod;
	}

	/**
	 * Sets the method being reflected.
	 * 
	 * @param reflectedMethod
	 *            New method being reflected.
	 */
	public void setReflectedMethod(final Method reflectedMethod) {
		this.reflectedMethod = reflectedMethod;
	}

	/**
	 * Public constructor for the reflection class.
	 * 
	 * @param reflectedMethod
	 *            Method being reflected.
	 * @throws EmptyParameterException
	 *             If the reflected field is not given.
	 */
	public MethodMirror(final Method reflectedMethod) throws EmptyParameterException {
		// Asserts that the method is not null.
		PreConditions.assertParamNotNull(MethodParamKeys.METHOD, reflectedMethod);
		// Sets the main reflection fields.
		this.reflectedMethod = reflectedMethod;
	}

	/**
	 * Gets the name of the current method (with the selected depth) being executed.
	 * 
	 * @param depth
	 *            Depth in the current stack for the desired method (0 is the first and so on).
	 * @return The name of the current method (with the selected depth) being executed.
	 */
	public static String getCurrentMethodName(final Integer depth) {
		// Actual depth is the given one.
		Integer actualDepth = depth;
		// If the given depth is null.
		if (actualDepth == null) {
			// The depth is 0.
			actualDepth = 0;
		}
		// Gets the stack trace.
		final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		// Returns the name of the method in the selected depth.
		return stackTrace[2 + actualDepth].getMethodName();
	}

	/**
	 * Regular expression for getter methods.
	 */
	public static final String GETTER_REGEX = "(get|is)[A-Z0-9_\\$].*";

	/**
	 * Returns if the method name is from a getter.
	 * 
	 * @param methodName
	 *            The method name.
	 * @return If the method name is from a getter.
	 */
	public static Boolean isGetter(final String methodName) {
		// Returns if the method name matches the getter regular expression.
		return methodName.matches(GETTER_REGEX);
	}

	/**
	 * Returns if the method name is from a getter.
	 * 
	 * @return If the method name is from a getter.
	 */
	public Boolean isGetter() {
		// Returns if the method name matches the getter regular expression.
		return isGetter(getReflectedMethod().getName());
	}

	/**
	 * Regular expression for setter methods.
	 */
	public static final String SETTER_REGEX = "set[A-Z0-9_\\$].*";

	/**
	 * Returns if the method name is from a setter.
	 * 
	 * @param methodName
	 *            The method name.
	 * @return If the method name is from a setter.
	 */
	public static Boolean isSetter(final String methodName) {
		// Returns if the method name matches the setter regular expression.
		return methodName.matches(SETTER_REGEX);
	}

	/**
	 * Returns if the method name is from a setter.
	 * 
	 * @return If the method name is from a setter.
	 */
	public Boolean isSetter() {
		// Returns if the method name matches the setter regular expression.
		return isSetter(getReflectedMethod().getName());
	}

	/**
	 * Gets the field name from a getter/setter.
	 * 
	 * @param methodName
	 *            The method name.
	 * @return The field name from a getter/setter. Or null, if the method is not a getter/setter.
	 */
	public static String getFieldName(final String methodName) {
		// The field name.
		String fieldName = null;
		// If the method is a setter.
		if (isSetter(methodName)) {
			// The field name is the method name without "set".
			fieldName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
		}
		// Otherwise, if it is a getter.
		if (isGetter(methodName)) {
			// If the method starts with "get".
			if (methodName.startsWith(FieldMirror.GETTER_PREFIX)) {
				// The field name is the method name without "get".
				fieldName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
			}
			// If it does not ("is").
			else {
				// The field name is the method name without "is".
				fieldName = methodName.substring(2, 3).toLowerCase() + methodName.substring(3);
			}
		}
		// Returns the field name.
		return fieldName;
	}

	/**
	 * Gets the field name from a getter/setter.
	 * 
	 * @return The field name from a getter/setter. Or null, if the method is not a getter/setter.
	 */
	public String getFieldName() {
		// Gets the field name from a getter/setter.
		return getFieldName(getReflectedMethod().getName());
	}

	/**
	 * Invokes a method from an object/class.
	 * 
	 * @param targetObject
	 *            The target object from which the method will be invoked.
	 * @param ignoreAccess
	 *            If the defined access for the method must be ignored.
	 * @param paramsValues
	 *            Parameters values to be used during the invocation.
	 * @return Returns the object returned from the method being invoked.
	 * @throws EmptyParameterException
	 *             If the target object is null and the method is not static.
	 * @throws InvalidParameterException
	 *             If the given parameters are from the wrong type or if the method is unaccessible.
	 * @throws InvocationException
	 *             If the method throws an exception itself.
	 */
	public Object invokeMethod(final Object targetObject, final Boolean ignoreAccess,
			final Object[] paramsValues) throws EmptyParameterException, InvalidParameterException,
			InvocationException {
		// If the field is not static and the target object is null.
		if ((targetObject == null) && (!Modifier.isStatic(getReflectedMethod().getModifiers()))) {
			// Throws an exception.
			throw new EmptyParameterException(MethodParamKeys.TARGET_OBJECT);
		}
		// Tries to invoke the method.
		try {
			// Redefines the access of the method.
			getReflectedMethod().setAccessible(ignoreAccess);
			// Tries to invoke it.
			return getReflectedMethod().invoke(targetObject, paramsValues);
		}
		// If an exception is thrown by the method itself.
		catch (final InvocationTargetException exception) {
			exception.printStackTrace();
			// Throws an exception using the exception thrown as its cause.
			throw new InvocationException(new Object[] { targetObject, paramsValues, ignoreAccess },
					exception);
		}
		// If the parameter values are illegal for the method.
		catch (final IllegalArgumentException exception) {
			// Throws an exception.
			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.ILLEGAL_PARAMS_VALUES,
					new Object[] { getReflectedMethod(), targetObject, paramsValues, ignoreAccess },
					exception);
		}
		// If the method cannot be accessed.
		catch (final IllegalAccessException exception) {
			// Throws an exception.
			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.UNACCESSIBLE_METHOD,
					new Object[] { getReflectedMethod(), targetObject, paramsValues, ignoreAccess },
					exception);
		}
		// Finally.
		finally {
			// Reinforces the method accessibility.
			getReflectedMethod().setAccessible(false);
		}
	}

	/**
	 * Invokes a method from a JNDI object.
	 * 
	 * @param jndiPath
	 *            The JNDI path for the object from which the method will be invoked.
	 * @param ignoreAccess
	 *            If the defined access for the method must be ignored.
	 * @param paramsValues
	 *            Parameters values to be used during the invocation.
	 * @return Returns the object returned from the method being invoked.
	 * @throws InvalidParameterException
	 *             If the parameters for this method are not valid (empty or method could not be accessed).
	 * @throws InvocationException
	 *             If the method throws an exception itself.
	 */
	public Object invokeMethod(final String jndiPath, final Boolean ignoreAccess, final Object[] paramsValues)
			throws InvalidParameterException, InvocationException {
		// Asserts that the JNDI path is not empty.
		PreConditions.assertParamNotEmpty(MethodParamKeys.JNDI_PATH, jndiPath);
		// Tries to invoke the method.
		try {
			// Tries to retrieve the object.
			final Object object = InitialContext.doLookup(jndiPath);
			// Invokes the method.
			return invokeMethod(object, ignoreAccess, paramsValues);
		}
		// If the object cannot be found.
		catch (final NamingException exception) {
			// Throws an exception.
			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.INVALID_JNDI_PATH,
					new Object[] { getReflectedMethod() }, exception);
		}
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((reflectedMethod == null) ? 0 : reflectedMethod.hashCode());
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
		final MethodMirror other = (MethodMirror) obj;
		if (reflectedMethod == null) {
			if (other.reflectedMethod != null) {
				return false;
			}
		} else if (!reflectedMethod.equals(other.reflectedMethod)) {
			return false;
		}
		return true;
	}

}
