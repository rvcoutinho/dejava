package org.dejava.component.exception.localized.unchecked;

import org.dejava.component.exception.constant.ErrorKeys;

/**
 * Exception related invalid parameter use.
 */
public class InvalidParameterException extends AbstractLocalizedRuntimeException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -4405267486703105707L;

	/**
	 * Basic constructor.
	 * 
	 * @param bundleInfo
	 *            The information regarding the message bundle to be used in the message retrieval
	 * @param invalidParameter
	 *            Parameter that is invalid.
	 * @param InvalidParamValue
	 *            Invalid parameter value.
	 * @param cause
	 *            Exception cause.
	 */
	public InvalidParameterException(final Object bundleInfo, final String invalidParameter,
			final Object InvalidParamValue, final Throwable cause) {
		super(bundleInfo, ErrorKeys.INVALID_PARAM, new Object[] { invalidParameter, InvalidParamValue },
				cause);
	}

	/**
	 * @see AbstractLocalizedRuntimeException#AbstractLocalizedRuntimeException(Object, String, Object[],
	 *      Throwable)
	 */
	public InvalidParameterException(final Object bundleInfo, final String messageKey,
			final Object[] parameters, final Throwable cause) {
		super(bundleInfo, messageKey, parameters, cause);
	}
}
