package org.dejava.component.util.accesscontrol.exception;

import org.dejava.component.util.accesscontrol.constant.ErrorKeys;

/**
 * Exception related to a permission exception.
 */
public class AuthenticationException extends AbstractAccessControlException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 997127359549517132L;

	/**
	 * Default constructor.
	 * 
	 * @param cause
	 *            Exception cause.
	 */
	public AuthenticationException(final Throwable cause) {
		super(ErrorKeys.AUTHENTICATION, cause, null);
	}
}
