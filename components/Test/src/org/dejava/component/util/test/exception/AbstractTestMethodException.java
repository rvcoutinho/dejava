package org.dejava.component.util.test.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dejava.component.util.exception.localized.AbstractLocalizedException;

/**
 * Exception related tests.
 */
public abstract class AbstractTestMethodException extends AbstractLocalizedException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -4585522559594211167L;
	
	/**
	 * Basic constructor.
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
	public AbstractTestMethodException(final String messageKey, final Throwable cause, final String testName,
			final Object[] paramsValues) {
		super(messageKey, cause, null);
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
