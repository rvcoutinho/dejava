package org.dejava.component.util.test.exception;

import org.dejava.component.util.test.constant.ErrorKeys;

/**
 * Exception related to unavailable org.dejava.component.util.test.test data.
 */
public class ParametricTestException extends AbstractTestException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -7501622556068793489L;
	
	/**
	 * Basic constructor.
	 * 
	 * @param cause
	 *            Exception cause.
	 * @param parameters
	 *            Parameters for the exception (and message).
	 */
	public ParametricTestException(final Throwable cause, final Object[] parameters) {
		super(ErrorKeys.UNAVAILABLE_TEST_DATA, cause, parameters);
	}
	
}
