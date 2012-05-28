package org.dejava.component.util.test.exception;

import org.dejava.component.util.exception.localized.AbstractLocalizedException;

/**
 * Exception related tests.
 */
public abstract class AbstractTestException extends AbstractLocalizedException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -4585522559594211167L;
	
	/**
	 * @see AbstractLocalizedException#AbstractLocalizedException(String, Throwable, Object[])
	 */
	public AbstractTestException(final String messageKey, final Throwable cause, final Object[] parameters) {
		super(messageKey, cause, parameters);
	}
}
