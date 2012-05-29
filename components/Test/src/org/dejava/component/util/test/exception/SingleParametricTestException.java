package org.dejava.component.util.test.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO
 */
public class SingleParametricTestException extends AbstractTestException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 975981736423667079L;
	
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
	public SingleParametricTestException(final Throwable cause, final String testName,
			final Object[] paramsValues) {
		// TODO
		super(null, cause, null);
		// List of exception parameters.
		final List<Object> parameters = new ArrayList<Object>();
		// Adds the test name as the first parameter.
		parameters.add(testName);
		// Adds all the parameters values for the test method.
		parameters.addAll(Arrays.asList(paramsValues));
		// Sets the parameters of the exception.
		setParameters(parameters.toArray());
	}
}
