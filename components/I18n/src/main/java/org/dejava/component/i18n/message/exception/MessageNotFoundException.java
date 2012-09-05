package org.dejava.component.i18n.message.exception;

import org.dejava.component.exception.localized.unchecked.AbstractLocalizedRuntimeException;
import org.dejava.component.i18n.message.constant.ErrorKeys;

/**
 * Exception related to unaccessible messages.
 */
public class MessageNotFoundException extends AbstractLocalizedRuntimeException {
	
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
	 * @see AbstractLocalizedRuntimeException#AbstractLocalizedRuntimeException(String, Throwable, Object[])
	 */
	public MessageNotFoundException(final String messageKey, final Throwable cause, final Object[] parameters) {
		super(messageKey, cause, parameters);
	}
}
