package org.dejava.component.exception.localized.unchecked;

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
	 * @param messageKey
	 *            Message key that describes the exception.
	 * @param cause
	 *            Exception cause.
	 * @param invalidParameter
	 *            Parameter that is invalid.
	 * @param InvalidParamValue
	 *            Invalid parameter value.
	 */
	public InvalidParameterException(final String messageKey, final Throwable cause,
			final Integer invalidParameter, final Object InvalidParamValue) {
		super(messageKey, cause, new Object[] { invalidParameter, InvalidParamValue });
	}
	
	/**
	 * @see AbstractLocalizedRuntimeException#AbstractLocalizedRuntimeException(String, Throwable, Object[])
	 */
	public InvalidParameterException(final String messageKey, final Throwable cause, final Object[] parameters) {
		super(messageKey, cause, parameters);
	}
}
