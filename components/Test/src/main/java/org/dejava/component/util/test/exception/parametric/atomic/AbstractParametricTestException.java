package org.dejava.component.util.test.exception.parametric.atomic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dejava.component.util.test.exception.AbstractTestException;

/**
 * An atomic parametric test exception. By atomic, it means a test execution for only one of the possible
 * parameters values for a test.
 */
public abstract class AbstractParametricTestException extends AbstractTestException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -8001637603404931093L;
	
	/**
	 * Default constructor.
	 * 
	 * @param messageKey
	 *            Message key that describes the exception.
	 * @param cause
	 *            Exception cause.
	 * @param testName
	 *            The name of the test for the exception.
	 * @param paramsValues
	 *            The parameters values for the test method.
	 */
	public AbstractParametricTestException(final String messageKey, final Throwable cause,
			final String testName, final Object[] paramsValues) {
		super(messageKey, cause, null);
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
