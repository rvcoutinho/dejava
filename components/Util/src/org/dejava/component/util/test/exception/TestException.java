package org.dejava.component.util.test.exception;

import org.dejava.component.util.exception.localized.AbstractLocalizedException;

/**
 * Exception related to an execution of a test.
 */
public class TestException extends AbstractLocalizedException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -8848983161835559042L;
	
	/**
	 * @see AbstractLocalizedException#AbstractLocalizedException(String, Throwable, Object[])
	 */
	public TestException(final String message, final Throwable cause, final Object[] parameters) {
		super(message, cause, parameters);
	}
}
