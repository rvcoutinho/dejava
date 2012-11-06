package org.dejava.component.test.exception.parametric.atomic;

import org.dejava.component.test.exception.AbstractTestAssertionError;

/**
 * A single parametric test assertion error.
 */
public class AtomParametricTestAssertionError extends AbstractTestAssertionError {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 5433554643543299211L;

	/**
	 * Default constructor.
	 * @param testName The test name.
	 * @param parameters
	 *            Parameters for the exception (and message).
	 * @param cause
	 *            Exception cause.
	 */
	public AtomParametricTestAssertionError(final String testName, final Object[] parameters,
			final Throwable cause) {
		super(null, null, testName, parameters, cause);
	}
}
