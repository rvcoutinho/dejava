package org.dejava.component.util.reflection.exception;

import org.dejava.component.exception.localized.checked.AbstractLocalizedException;
import org.dejava.component.util.reflection.constant.ErrorKeys;

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
	 * @param cause
	 *            Exception cause.
	 * @param parameters
	 *            Parameters for the exception (and message).
	 */
	public InvocationException(final Throwable cause, final Object[] parameters) {
		super(ErrorKeys.METHOD_EXCEPTION, cause, parameters);
	}
}
