package org.dejava.component.reflection.exception;

import org.dejava.component.exception.localized.checked.AbstractLocalizedException;
import org.dejava.component.reflection.constant.ErrorKeys;

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
	 * @param bundleInfo
	 *            The information regarding the message bundle to be used in the message retrieval
	 * @param parameters
	 *            Parameters for the exception (and message).
	 * @param cause
	 *            Exception cause.
	 */
	public InvocationException(final Object bundleInfo, final Object[] parameters, final Throwable cause) {
		super(bundleInfo, ErrorKeys.METHOD_EXCEPTION, parameters, cause);
	}
}
