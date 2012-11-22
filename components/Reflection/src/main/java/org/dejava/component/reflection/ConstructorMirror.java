package org.dejava.component.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.reflection.constant.ErrorKeys;
import org.dejava.component.reflection.exception.InvocationException;
import org.dejava.component.reflection.util.MessageTypes;

/**
 * TODO
 * 
 * @param <Reflected>
 *            The class for the reflected constructor.
 */
public class ConstructorMirror<Reflected> {

	/**
	 * Constructor being reflected.
	 */
	private Constructor<? extends Reflected> reflectedConstructor;

	/**
	 * Gets the constructor being reflected.
	 * 
	 * @return The constructor being reflected.
	 */
	public Constructor<? extends Reflected> getReflectedConstructor() {
		return reflectedConstructor;
	}

	/**
	 * Sets the constructor being reflected.
	 * 
	 * @param reflectedConstructor
	 *            New constructor being reflected.
	 */
	public void setReflectedConstructor(final Constructor<? extends Reflected> reflectedConstructor) {
		this.reflectedConstructor = reflectedConstructor;
	}

	/**
	 * Public constructor for the reflection class.
	 * 
	 * @param reflectedConstructor
	 *            Constructor being reflected.
	 */
	public ConstructorMirror(final Constructor<? extends Reflected> reflectedConstructor) {
		this.reflectedConstructor = reflectedConstructor;
	}

	/**
	 * Invokes a constructor for a class.
	 * 
	 * @param paramsValues
	 *            Parameters values to be used during the invocation.
	 * @param ignoreAccess
	 *            If the defined access for the constructor must be ignored.
	 * @return Returns the object returned from the constructor being invoked.
	 * @throws InvalidParameterException
	 *             If the given parameters are from the wrong type, if the constructor is unaccessible or if
	 *             the class is abstract.
	 * @throws InvocationException
	 *             If the constructor throws an exception itself.
	 */
	public Reflected newInstance(final Object[] paramsValues, final Boolean ignoreAccess)
			throws InvalidParameterException, InvocationException {
		// Tries to invoke the constructor.
		try {
			// Redefines the access of the constructor.
			getReflectedConstructor().setAccessible(ignoreAccess);
			// Tries to invoke it.
			return getReflectedConstructor().newInstance(paramsValues);
		}
		// If an exception is thrown by the constructor itself.
		catch (final InvocationTargetException exception) {
			// Throws an exception using the exception thrown as its cause.
			throw new InvocationException(new Object[] { getReflectedConstructor(), paramsValues,
					ignoreAccess }, exception);
		}
		// If the parameter values are illegal for the constructor.
		catch (final IllegalArgumentException exception) {
			// Throws an exception.
			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.ILLEGAL_PARAMS_VALUES,
					new Object[] { getReflectedConstructor(), paramsValues, ignoreAccess }, exception);
		}
		// If the constructor cannot be accessed.
		catch (final IllegalAccessException exception) {
			// Throws an exception.
			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.UNACCESSIBLE_CONSTRUCTOR,
					new Object[] { getReflectedConstructor(), paramsValues, ignoreAccess }, exception);
		}
		// If the class is abstract.
		catch (final InstantiationException exception) {
			// Throws an exception.
			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.ABSTRACT_CLASS, new Object[] {
					getReflectedConstructor(), paramsValues, ignoreAccess }, exception);
		}
		// Finally.
		finally {
			// Reinforces the constructor accessibility.
			getReflectedConstructor().setAccessible(false);
		}
	}

}
