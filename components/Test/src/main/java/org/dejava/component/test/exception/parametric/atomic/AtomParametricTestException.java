package org.dejava.component.test.exception.parametric.atomic;

import org.dejava.component.test.exception.AbstractTestException;

/**
 * A single parametric test exception.
 */
public class AtomParametricTestException extends AbstractTestException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -4175427239547673796L;

	/**
	 * Default constructor.
	 * 
	 * @param testName
	 *            The test name.
	 * @param parameters
	 *            Parameters for the exception (and message).
	 * @param cause
	 *            Exception cause.
	 */
	public AtomParametricTestException(final String testName, final Object[] parameters, final Throwable cause) {
		super(null, null, testName, parameters, cause);
	}
}
