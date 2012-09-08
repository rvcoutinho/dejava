package org.dejava.component.test.exception.parametric;

import org.dejava.component.test.constant.ErrorKeys;
import org.dejava.component.test.exception.AbstractTestException;

/**
 * Exception related to an invalid parametric test method (that should be public
 * void with one argument).
 */
public class InvalidParametricTestException extends AbstractTestException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -141551638307214890L;

	/**
	 * @see AbstractTestException#AbstractTestException(String, Throwable, String)
	 */
	public InvalidParametricTestException(final String messageKey,
			final Throwable cause, final String testName) {
		super(messageKey, cause, testName);
	}

	/**
	 * Basic constructor.
	 * 
	 * @param testName
	 *            The name of the test for the exception.
	 */
	public InvalidParametricTestException(final String testName) {
		super(ErrorKeys.INVALID_PARAM_TEST_METHOD, null, testName);
	}
}
