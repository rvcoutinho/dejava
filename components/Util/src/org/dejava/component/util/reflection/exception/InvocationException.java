package org.dejava.component.util.reflection.exception;

import org.dejava.component.util.exception.localized.AbstractLocalizedException;

/**
 * Exception related to exceptions thrown by a target method.
 */
public class InvocationException extends AbstractLocalizedException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -315210606548717238L;
	
	/**
	 * @see AbstractLocalizedException#AbstractLocalizedException(String, Throwable, Object[])
	 */
	public InvocationException(final String message, final Throwable cause, final Object[] parameters) {
		super(message, cause, parameters);
	}
}
