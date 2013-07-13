package org.dejava.component.exception.test.util;

import org.dejava.component.exception.localized.unchecked.AbstractLocalizedRuntimeException;

/**
 * Localized runtime exception.
 */
public class LocalizedRuntimeException extends AbstractLocalizedRuntimeException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -5031681554691854022L;

	/**
	 * Basic constructor.
	 * 
	 * @param type
	 *            The type for the message.
	 * @param messageKey
	 *            Message key that describes the exception.
	 * @param parameters
	 *            Parameters for the exception (and message).
	 * @param cause
	 *            Exception cause.
	 */
	public LocalizedRuntimeException(final Object type, final String messageKey, final Object[] parameters,
			final Throwable cause) {
		super(type, messageKey, parameters, cause);
	}

}
