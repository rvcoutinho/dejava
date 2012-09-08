package org.dejava.component.test.exception.parametric.atomic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	 * @param cause
	 *            Exception cause.
	 * @param testName
	 *            The name of the test for the exception.
	 * @param paramsValues
	 *            The parameters values for the test method.
	 */
	public AtomParametricTestException(final Throwable cause, final String testName,
			final Object[] paramsValues) {
		// TODO
		super(null, cause, testName);
		// List of exception parameters.
		final List<Object> parameters = new ArrayList<Object>();
		// Adds the test name as the first parameter.
		parameters.add(testName);
		// Adds all the parameters values for the test method.
		parameters.addAll(Arrays.asList(paramsValues));
		// Sets the parameters of the exception.
		getMessageCommand().setParameters(parameters.toArray());

	}
}
