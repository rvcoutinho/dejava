package org.dejava.component.test.exception.parametric;

import javax.annotation.Resources;

import org.dejava.component.test.constant.ErrorKeys;
import org.dejava.component.test.exception.AbstractTestException;

/**
 * Exception related to an invalid parametric test method (that should be public void with one argument).
 */
public class InvalidParametricTestException extends AbstractTestException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -141551638307214890L;

	/**
	 * @see AbstractTestException#AbstractTestException(Object, String, String, Object[], Throwable)
	 */
	public InvalidParametricTestException(final Object type, final String messageKey,
			final String testName, final Object[] parameters, final Throwable cause) {
		super(type, messageKey, testName, parameters, cause);
	}

	/**
	 * Basic constructor.
	 * 
	 * @param testName
	 *            The test name.
	 * @param parameters
	 *            Parameters for the exception (and message).
	 */
	public InvalidParametricTestException(final String testName, final Object[] parameters) {
		super(Resources.class, ErrorKeys.INVALID_PARAM_TEST_METHOD, testName, parameters, null);
	}
}
