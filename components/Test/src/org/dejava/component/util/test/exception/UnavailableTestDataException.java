package org.dejava.component.util.test.exception;

import org.dejava.component.util.test.constant.ErrorKeys;

/**
 * Exception related to unavailable test data.
 */
public class UnavailableTestDataException extends AbstractTestException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -141551638307214890L;
	
	/**
	 * @see AbstractTestException#AbstractTestException(String, Throwable, String)
	 */
	public UnavailableTestDataException(final String messageKey, final Throwable cause, final String testName) {
		super(messageKey, cause, testName);
	}
	
	/**
	 * Basic constructor.
	 * 
	 * @param cause
	 *            Exception cause.
	 * @param testName
	 *            The name of the test for the exception.
	 */
	public UnavailableTestDataException(final Throwable cause, final String testName) {
		this(ErrorKeys.UNAVAILABLE_TEST_DATA, cause, testName);
	}
}
