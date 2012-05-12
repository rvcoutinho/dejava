package org.dejava.component.util.test.exception;

/**
 * Exception related to an invalid test step.
 */
public class InvalidTestStepException extends TestStepException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 7389924242160615803L;
	
	/**
	 * @see TestStepException#TestStepException(String, Throwable, Object[])
	 */
	public InvalidTestStepException(final String message, final Throwable cause, final Object[] parameters) {
		super(message, cause, parameters);
	}
}
