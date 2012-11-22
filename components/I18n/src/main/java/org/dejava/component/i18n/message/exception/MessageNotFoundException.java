package org.dejava.component.i18n.message.exception;

import org.dejava.component.exception.localized.unchecked.AbstractLocalizedRuntimeException;
import org.dejava.component.i18n.message.constant.ErrorKeys;
import org.dejava.component.i18n.message.util.MessageTypes;

/**
 * Exception related to unaccessible messages.
 */
public class MessageNotFoundException extends AbstractLocalizedRuntimeException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1539446247454625787L;

	/**
	 * @see AbstractLocalizedRuntimeException#AbstractLocalizedRuntimeException(Object, String, Object[],
	 *      Throwable)
	 */
	public MessageNotFoundException(final Object type, final String messageKey,
			final Object[] parameters, final Throwable cause) {
		super(type, messageKey, parameters, cause);
	}

	/**
	 * Default constructor.
	 * 
	 * @param parameters
	 *            Parameters for the exception (and message).
	 * @param cause
	 *            Exception cause.
	 */
	public MessageNotFoundException(final Object[] parameters, final Throwable cause) {
		super(MessageTypes.Error.class, ErrorKeys.MISSING_KEY, parameters, cause);
	}

}
