package org.dejava.component.validation.method;

import java.util.Collection;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;

/**
 * Some pre-conditions for methods to run: helps checking invalid parameters.
 */
public final class PreConditions {

	/**
	 * Private constructor.
	 */
	private PreConditions() {
	}

	/**
	 * Asserts that a parameter is valid.
	 * 
	 * @param expression
	 *            Expression to check if the parameter is valid.
	 * @param messageType
	 *            The type of the message for the invalid parameter.
	 * @param messageKey
	 *            The key of the message for the invalid parameter.
	 * @param messageParameters
	 *            The parameters of the message for the invalid parameter.
	 */
	public static void assertParamValid(final Boolean expression, final Object messageType,
			final String messageKey, final Object[] messageParameters) {
		// If the given expression is not true.
		if (!expression) {
			// Throws a new invalid parameter exception.
			throw new InvalidParameterException(messageType, messageKey, messageParameters, null);
		}
	}

	/**
	 * Asserts that a parameter is valid.
	 * 
	 * @param expression
	 *            Expression to check if the parameter is valid.
	 * @param param
	 *            Parameter being checked.
	 * @param paramValue
	 *            Parameter value.
	 */
	public static void assertParamValid(final Boolean expression, final String param, final Object paramValue) {
		// If the given expression is not true.
		if (!expression) {
			// Throws a new invalid parameter exception.
			throw new InvalidParameterException(param, paramValue, null);
		}
	}

	/**
	 * Asserts that a parameter is not null.
	 * 
	 * @param paramValue
	 *            The parameter value.
	 * @param messageType
	 *            The type of the message for the invalid parameter.
	 * @param messageKey
	 *            The key of the message for the invalid parameter.
	 * @param messageParameters
	 *            The parameters of the message for the invalid parameter.
	 */
	public static void assertParamNotNull(final Object paramValue, final Object messageType,
			final String messageKey, final Object[] messageParameters) {
		// If the given parameter value is null.
		if (paramValue == null) {
			// Throws a new empty parameter exception.
			throw new EmptyParameterException(messageType, messageKey, messageParameters, null);
		}
	}

	/**
	 * Asserts that a parameter is not null.
	 * 
	 * @param param
	 *            Parameter being checked.
	 * @param paramValue
	 *            Parameter value.
	 */
	public static void assertParamNotNull(final String param, final Object paramValue) {
		// If the given parameter value is null.
		if (paramValue == null) {
			// Throws a new empty parameter exception.
			throw new EmptyParameterException(param);
		}
	}

	/**
	 * Asserts that a parameter is not empty.
	 * 
	 * @param paramValue
	 *            The parameter value.
	 * @param messageType
	 *            The type of the message for the invalid parameter.
	 * @param messageKey
	 *            The key of the message for the invalid parameter.
	 * @param messageParameters
	 *            The parameters of the message for the invalid parameter.
	 */
	public static void assertParamNotEmpty(final String paramValue, final Object messageType,
			final String messageKey, final Object[] messageParameters) {
		// If the given parameter value is empty.
		if ((paramValue == null) || (paramValue.isEmpty())) {
			// Throws a new empty parameter exception.
			throw new EmptyParameterException(messageType, messageKey, messageParameters, null);
		}
	}

	/**
	 * Asserts that a parameter is not empty.
	 * 
	 * @param param
	 *            Parameter being checked.
	 * @param paramValue
	 *            Parameter value.
	 */
	public static void assertParamNotEmpty(final String param, final String paramValue) {
		// If the given parameter value is empty.
		if ((paramValue == null) || (paramValue.isEmpty())) {
			// Throws a new empty parameter exception.
			throw new EmptyParameterException(param);
		}
	}

	/**
	 * Asserts that a parameter is not empty.
	 * 
	 * @param paramValue
	 *            The parameter value.
	 * @param messageType
	 *            The type of the message for the invalid parameter.
	 * @param messageKey
	 *            The key of the message for the invalid parameter.
	 * @param messageParameters
	 *            The parameters of the message for the invalid parameter.
	 */
	public static void assertParamNotEmpty(final Collection<?> paramValue, final Object messageType,
			final String messageKey, final Object[] messageParameters) {
		// If the given parameter value is empty.
		if ((paramValue == null) || (paramValue.isEmpty())) {
			// Throws a new empty parameter exception.
			throw new EmptyParameterException(messageType, messageKey, messageParameters, null);
		}
	}

	/**
	 * Asserts that a parameter is not empty.
	 * 
	 * @param param
	 *            Parameter being checked.
	 * @param paramValue
	 *            Parameter value.
	 */
	public static void assertParamNotEmpty(final String param, final Collection<?> paramValue) {
		// If the given parameter value is empty.
		if ((paramValue == null) || (paramValue.isEmpty())) {
			// Throws a new empty parameter exception.
			throw new EmptyParameterException(param);
		}
	}

	/**
	 * Asserts that a parameter is not empty.
	 * 
	 * @param <Param>
	 *            Any parameter type.
	 * @param paramValue
	 *            The parameter value.
	 * @param messageType
	 *            The type of the message for the invalid parameter.
	 * @param messageKey
	 *            The key of the message for the invalid parameter.
	 * @param messageParameters
	 *            The parameters of the message for the invalid parameter.
	 */
	public static <Param> void assertParamNotEmpty(final Param[] paramValue, final Object messageType,
			final String messageKey, final Object[] messageParameters) {
		// If the given parameter value is empty.
		if ((paramValue == null) || (paramValue.length == 0)) {
			// Throws a new empty parameter exception.
			throw new EmptyParameterException(messageType, messageKey, messageParameters, null);
		}
	}

	/**
	 * Asserts that a parameter is not empty.
	 * 
	 * @param <Param>
	 *            Any parameter type.
	 * @param param
	 *            Parameter being checked.
	 * @param paramValue
	 *            Parameter value.
	 */
	public static <Param> void assertParamNotEmpty(final String param, final Param[] paramValue) {
		// If the given parameter value is empty.
		if ((paramValue == null) || (paramValue.length == 0)) {
			// Throws a new empty parameter exception.
			throw new EmptyParameterException(param);
		}
	}

}
