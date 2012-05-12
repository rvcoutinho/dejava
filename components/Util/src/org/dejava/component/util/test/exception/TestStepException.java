package org.dejava.component.util.test.exception;

/**
 * Exception related to an execution of a test step.
 */
public class TestStepException extends TestException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -8848983161835559042L;
	
	/**
	 * @see TestException#TestException(String, Throwable, Object[])
	 */
	public TestStepException(final String message, final Throwable cause, final Object[] parameters) {
		super(message, cause, parameters);
	}
}
