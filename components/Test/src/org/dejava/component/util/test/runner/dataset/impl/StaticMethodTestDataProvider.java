package org.dejava.component.util.test.runner.dataset.impl;

import java.util.Collection;

import org.dejava.component.util.reflection.ClassMirror;
import org.dejava.component.util.test.exception.UnavailableTestDataException;
import org.dejava.component.util.test.runner.dataset.TestDataProvider;
import org.dejava.component.util.test.runner.statement.ParametricTestMethodInvoker;
import org.junit.runners.model.FrameworkMethod;

/**
 * Provides access to XML test data.
 */
public class StaticMethodTestDataProvider implements TestDataProvider {
	
	/**
	 * Default name for the test data objects method.
	 */
	public static final String DEFAULT_METHOD_NAME = ParametricTestMethodInvoker.METHOD_NAME_EXPRESSION
			+ "Data";
	
	/**
	 * The method name for the test data provider.
	 */
	private String methodName;
	
	/**
	 * Gets the method name for the test data provider.
	 * 
	 * @param testMethod
	 *            The JUnit framework method.
	 * @return The method name for the test data provider.
	 */
	public String getMethodName(final FrameworkMethod testMethod) {
		// If the method name is not given.
		if (methodName == null) {
			// The default method name is used.
			methodName = DEFAULT_METHOD_NAME;
			// Replaces the method name in the path.
			methodName = methodName.replace(ParametricTestMethodInvoker.METHOD_NAME_EXPRESSION,
					testMethod.getName());
		}
		// Returns the method name.
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
	 * The method
	 */
	public StaticMethodTestDataProvider() {
		super();
	}
	
	/**
	 * Default constructor.
	 * 
	 * @param methodName
	 *            The method name for the test data provider.
	 */
	public StaticMethodTestDataProvider(final String methodName) {
		super();
		this.methodName = methodName;
	}
	
	/**
	 * @see org.dejava.component.util.test.runner.dataset.TestDataProvider#getTestData(org.junit.runners.model.FrameworkMethod)
	 */
	@Override
	public Collection<?> getTestData(final FrameworkMethod testMethod) throws UnavailableTestDataException {
		// Tries to get the data.
		try {
			// Gets the test class mirror.
			final ClassMirror<?> testClass = new ClassMirror<>(testMethod.getMethod().getDeclaringClass());
			// The test data is the return of the method invocation.
			return (Collection<?>) testClass.getMethod(getMethodName(testMethod), null).invokeMethod(
					(Object) null, null, true);
		}
		// If the test data cannot be retrieved.
		catch (final Exception exception) {
			// Throws an exception.
			throw new UnavailableTestDataException(exception, testMethod.getName());
		}
	}
}
