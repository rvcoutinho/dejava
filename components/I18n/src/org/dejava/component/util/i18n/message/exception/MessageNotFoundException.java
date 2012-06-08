package org.dejava.component.util.i18n.message.exception;

import org.dejava.component.util.exception.localized.AbstractLocalizedException;
import org.dejava.component.util.i18n.message.constant.ErrorKeys;

/**
 * Exception related to unaccessible messages.
 */
public class MessageNotFoundException extends AbstractLocalizedException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1539446247454625787L;
	
	/**
	 * Default constructor.
	 * 
	 * @param cause
	 *            Exception cause.
	 * @param parameters
	 *            Parameters for the exception (and message).
	 */
	public MessageNotFoundException(final Throwable cause, final Object[] parameters) {
		super(ErrorKeys.MISSING_KEY, cause, parameters);
	}
	
	/**
	 * @see AbstractLocalizedException#AbstractLocalizedException(String, Throwable, Object[])
	 */
	public MessageNotFoundException(final String message, final Throwable cause, final Object[] parameters) {
		super(message, cause, parameters);
	}
}
