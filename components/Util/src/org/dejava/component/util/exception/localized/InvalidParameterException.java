package org.dejava.component.util.exception.localized;

/**
 * Exception related invalid parameter use.
 */
public class InvalidParameterException extends AbstractLocalizedException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -4405267486703105707L;
	
	/**
	 * @see AbstractLocalizedException#AbstractLocalizedException(String, Throwable, Object[])
	 */
	public InvalidParameterException(final String message, final Throwable cause, final Object[] parameters) {
		super(message, cause, parameters);
	}
}
