package org.dejava.component.test.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dejava.component.exception.localized.checked.AbstractLocalizedException;

/**
 * Test related exceptions.
 */
public abstract class AbstractTestException extends AbstractLocalizedException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 5069520548110477099L;

	/**
	 * Basic constructor.
	 * 
	 * @param bundleInfo
	 *            The information regarding the message bundle to be used in the message retrieval
	 * @param messageKey
	 *            Message key that describes the exception.
	 * @param testName
	 *            The test name.
	 * @param parameters
	 *            Parameters (other than the test name) for the exception (and message).
	 * @param cause
	 *            Exception cause.
	 */
	public AbstractTestException(final Object bundleInfo, final String messageKey, final String testName,
			final Object[] parameters, final Throwable cause) {
		super(bundleInfo, messageKey, null, cause);
		// List of exception parameters (including test name).
		final List<Object> allParams = new ArrayList<Object>();
		// Adds the test name as the first parameter.
		allParams.add(testName);
		// If there are any parameters.
		if (parameters != null) {
			// Adds all the parameters values for the test method.
			allParams.addAll(Arrays.asList(parameters));
		}
		// Sets the parameters of the exception.
		getMessageCommand().setParameters(allParams.toArray());
	}
}
