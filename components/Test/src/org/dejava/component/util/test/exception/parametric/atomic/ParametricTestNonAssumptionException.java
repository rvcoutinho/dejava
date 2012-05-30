package org.dejava.component.util.test.exception.parametric.atomic;


/**
 * A single parametric test non-assumption exception.
 * 
 * @see AbstractParametricTestException
 */
public class ParametricTestNonAssumptionException extends AbstractParametricTestException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -4175427239547673796L;
	
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
	public ParametricTestNonAssumptionException(final Throwable cause, final String testName,
			final Object[] paramsValues) {
		// TODO
		super(null, cause, testName, paramsValues);
	}
}
