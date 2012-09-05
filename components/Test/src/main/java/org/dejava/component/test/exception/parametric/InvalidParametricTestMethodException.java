package org.dejava.component.test.exception.parametric;

import org.dejava.component.test.constant.ErrorKeys;
import org.dejava.component.test.exception.AbstractTestException;

/**
 * Exception related to an invalid parametric test method (that should be public void with one argument).
 */
public class InvalidParametricTestMethodException extends AbstractTestException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -141551638307214890L;
	
	/**
	 * Basic constructor.
	 * 
	 * @param testName
	 *            The name of the test for the exception.
	 */
	public InvalidParametricTestMethodException(final String testName) {
		super(ErrorKeys.INVALID_PARAM_TEST_METHOD, null, testName);
	}
}
