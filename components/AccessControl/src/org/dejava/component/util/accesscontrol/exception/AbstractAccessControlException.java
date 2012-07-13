package org.dejava.component.util.accesscontrol.exception;

import org.dejava.component.util.exception.localized.checked.AbstractLocalizedException;

/**
 * Exception related to access control.
 */
public class AbstractAccessControlException extends AbstractLocalizedException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1913693014082150580L;

	/**
	 * @see AbstractLocalizedException#AbstractLocalizedException(String,
	 *      Throwable, Object[])
	 */
	public AbstractAccessControlException(final String message, final Throwable cause,
			final Object[] parameters) {
		super(message, cause, parameters);
	}
}
