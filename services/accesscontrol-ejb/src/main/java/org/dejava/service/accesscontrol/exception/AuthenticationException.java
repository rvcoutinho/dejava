package org.dejava.service.accesscontrol.exception;


/**
 * Authentication related exception.
 */
public class AuthenticationException extends SecurityException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -6315458449725156298L;

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
	public AuthenticationException(Object type, String messageKey, Object[] parameters, Throwable cause) {
		super(type, messageKey, parameters, cause);
	}

	/**
	 * Basic constructor.
	 * 
	 * @param messageKey
	 *            Message key that describes the exception.
	 */
	public AuthenticationException(String messageKey) {
		super(messageKey);
	}

}
