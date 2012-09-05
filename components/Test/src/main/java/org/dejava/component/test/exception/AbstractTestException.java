package org.dejava.component.test.exception;

import org.dejava.component.exception.localized.checked.AbstractLocalizedException;

/**
 * Test related exceptions.
 */
public abstract class AbstractTestException extends AbstractLocalizedException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 5069520548110477099L;
	
	/**
	 * Basic constructor.
	 * 
	 * @param messageKey
	 *            Message key that describes the exception.
	 * @param cause
	 *            Exception cause.
	 * @param testName
	 *            The name of the test for the exception.
	 */
	public AbstractTestException(final String messageKey, final Throwable cause, final String testName) {
		super(messageKey, cause, new Object[] { testName });
	}
}
