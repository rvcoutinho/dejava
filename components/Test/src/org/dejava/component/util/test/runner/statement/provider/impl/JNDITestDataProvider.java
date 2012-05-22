package org.dejava.component.util.test.runner.statement.provider.impl;

import java.util.Collection;

import org.dejava.component.util.reflection.handler.MethodHandler;
import org.dejava.component.util.test.exception.UnavailableTestDataException;
import org.dejava.component.util.test.runner.statement.provider.TestDataProvider;
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
	public void setObjectPath(String objectPath) {
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
	public JNDITestDataProvider(String objectPath, String methodName) {
		super();
		this.objectPath = objectPath;
		this.methodName = methodName;
	}
	
	/**
	 * @see org.dejava.component.util.test.runner.statement.provider.TestDataProvider#getTestData(java.lang.Object,
	 *      org.junit.runners.model.FrameworkMethod)
	 */
	@Override
	public Collection<?> getTestData(final Object targetTest, final FrameworkMethod testMethod)
			throws UnavailableTestDataException {
		// Tries to get the data.
		try {
			// The test data is the return of the method invocation.
			return (Collection<?>) MethodHandler.invokeMethod(getObjectPath(), getMethodName(), null);
		}
		// If the test data cannot be retrieved.
		catch (Exception exception) {
			// Throws an exception. FIXME
			throw new UnavailableTestDataException(null, null, null);
		}
	}
}
