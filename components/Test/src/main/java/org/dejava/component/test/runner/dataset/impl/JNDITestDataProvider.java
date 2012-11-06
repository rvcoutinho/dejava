package org.dejava.component.test.runner.dataset.impl;

import java.util.Collection;

import org.dejava.component.test.constant.ErrorKeys;
import org.dejava.component.test.exception.parametric.InvalidParametricTestException;
import org.dejava.component.test.runner.dataset.TestDataProvider;
import org.dejava.component.test.util.Resources;
import org.junit.runners.model.FrameworkMethod;

/**
 * Provides access to XML test data.
 */
public class JNDITestDataProvider implements TestDataProvider {

	/**
	 * The JNDI path for the object from which the test data provider method will be invoked.
	 */
	private String objectPath;

	/**
	 * Gets the JNDI path for the object from which the test data provider method will be invoked.
	 * 
	 * @return The JNDI path for the object from which the test data provider method will be invoked.
	 */
	public String getObjectPath() {
		return objectPath;
	}

	/**
	 * Sets the JNDI path for the object from which the test data provider method will be invoked.
	 * 
	 * @param objectPath
	 *            New JNDI path for the object from which the test data provider method will be invoked.
	 */
	public void setObjectPath(final String objectPath) {
		this.objectPath = objectPath;
	}

	/**
	 * The method name for the test data provider.
	 */
	private String methodName;

	/**
	 * Gets the method name for the test data provider.
	 * 
	 * @return The method name for the test data provider.
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * Sets the method name for the test data provider.
	 * 
	 * @param methodName
	 *            New method name for the test data provider.
	 */
	public void setMethodName(final String methodName) {
		this.methodName = methodName;
	}

	/**
	 * Default constructor.
	 * 
	 * @param objectPath
	 *            The JNDI path for the object from which the test data provider method will be invoked.
	 * @param methodName
	 *            The method name for the test data provider.
	 */
	public JNDITestDataProvider(final String objectPath, final String methodName) {
		super();
		this.objectPath = objectPath;
		this.methodName = methodName;
	}

	/**
	 * @see org.dejava.component.test.runner.dataset.TestDataProvider#getTestData(org.junit.runners.model.FrameworkMethod)
	 */
	@Override
	public Collection<?> getTestData(final FrameworkMethod testMethod) throws InvalidParametricTestException {
		// Tries to get the data.
		try {
			// The test data is the return of the method invocation. FIXME
			// return (Collection<?>) MethodHandler.invokeMethod(getObjectPath(), getMethodName(), null,
			// null,false);
			return null;
		}
		// If the test data cannot be retrieved.
		catch (final Exception exception) {
			// Throws an exception.
			throw new InvalidParametricTestException(Resources.class, ErrorKeys.UNAVAILABLE_TEST_DATA,
					testMethod.getName(), null, exception);
		}
	}
}
