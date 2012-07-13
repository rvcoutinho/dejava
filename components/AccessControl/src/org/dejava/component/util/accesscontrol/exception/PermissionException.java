package org.dejava.component.util.accesscontrol.exception;

import org.dejava.component.util.accesscontrol.constant.ErrorKeys;

/**
 * Exception related to a permission exception.
 */
public class PermissionException extends AbstractAccessControlException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 2422721632674984903L;

	/**
	 * Default constructor.
	 * 
	 * @param cause
	 *            Exception cause.
	 */
	public PermissionException(final Throwable cause) {
		super(ErrorKeys.PERMISSION, cause, null);
	}
}
