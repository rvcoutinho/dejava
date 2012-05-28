package org.dejava.component.util.test.exception;

/**
 * Exception related to unavailable test data.
 */
public class ParametricTestException extends AbstractTestMethodException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -7501622556068793489L;
	
	/**
	 * @see AbstractTestMethodException#AbstractTestMethodException(String, Throwable, String, Object[])
	 */
	public ParametricTestException(final String messageKey, final Throwable cause, final String testName,
			final Object[] paramsValues) {
		super(messageKey, cause, testName, paramsValues);
	}
}
