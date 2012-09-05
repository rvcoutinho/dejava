package org.dejava.component.util.test.exception.parametric.atomic;

/**
 * A single parametric test assumption exception.
 * 
 * @see AbstractParametricTestException
 */
public class ParametricTestAssumptionException extends AbstractParametricTestException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 5433554643543299211L;
	
	/**
	 * Default constructor.
	 * 
	 * @param cause
	 *            Exception cause.
	 * @param testName
	 *            The name of the test for the exception.
	 * @param paramsValues
	 *            The parameters values for the test method.
	 */
	public ParametricTestAssumptionException(final Throwable cause, final String testName,
			final Object[] paramsValues) {
		// TODO
		super(null, cause, testName, paramsValues);
	}
}
