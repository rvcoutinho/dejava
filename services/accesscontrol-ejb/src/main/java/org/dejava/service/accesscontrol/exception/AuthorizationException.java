package org.dejava.service.accesscontrol.exception;

import org.dejava.service.accesscontrol.util.MessageTypes;

/**
 * Authorization related exception.
 */
public class AuthorizationException extends SecurityException {

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
	public AuthorizationException(Object type, String messageKey, Object[] parameters, Throwable cause) {
		super(type, messageKey, parameters, cause);
	}

	/**
	 * Basic constructor.
	 * 
	 * @param messageKey
	 *            Message key that describes the exception.
	 */
	public AuthorizationException(String messageKey) {
		super(MessageTypes.Error.class, messageKey, null, null);
	}

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4776009257193620477L;

}
