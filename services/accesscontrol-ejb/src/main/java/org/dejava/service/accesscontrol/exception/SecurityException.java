package org.dejava.service.accesscontrol.exception;

import org.dejava.component.ejb.exception.BusinessRuleException;
import org.dejava.service.accesscontrol.util.MessageTypes;

/**
 * Security related exception.
 */
public class SecurityException extends BusinessRuleException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -8910295035901970452L;

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
	public SecurityException(Object type, String messageKey, Object[] parameters, Throwable cause) {
		super(type, messageKey, parameters, cause);
	}

	/**
	 * Basic constructor.
	 * 
	 * @param messageKey
	 *            Message key that describes the exception.
	 */
	public SecurityException(String messageKey) {
		super(MessageTypes.Error.class, messageKey, null, null);
	}

}
