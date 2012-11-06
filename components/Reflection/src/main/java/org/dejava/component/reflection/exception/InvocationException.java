package org.dejava.component.reflection.exception;

import org.dejava.component.exception.localized.checked.AbstractLocalizedException;
import org.dejava.component.reflection.constant.ErrorKeys;
import org.dejava.component.reflection.util.Resources;

/**
 * Exception related to exceptions thrown by a target method.
 */
public class InvocationException extends AbstractLocalizedException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -315210606548717238L;

	/**
	 * Basic constructor.
	 * 
	 * @param parameters
	 *            Parameters for the exception (and message).
	 * @param cause
	 *            Exception cause.
	 */
	public InvocationException(final Object[] parameters, final Throwable cause) {
		super(Resources.class, ErrorKeys.METHOD_EXCEPTION, parameters, cause);
	}

	/**
	 * @see AbstractLocalizedException#AbstractLocalizedException(Object, String, Object[], Throwable)
	 */
	public InvocationException(final Object bundleInfo, final String messageKey, final Object[] parameters,
			final Throwable cause) {
		super(bundleInfo, messageKey, parameters, cause);
	}

}
