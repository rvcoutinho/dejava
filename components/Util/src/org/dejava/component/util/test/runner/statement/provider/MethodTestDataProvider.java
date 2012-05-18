package org.dejava.component.util.test.runner.statement.provider;

import org.dejava.component.util.reflection.handler.MethodHandler;
import org.dejava.component.util.test.runner.statement.InvokeMultiDataTestMethod;
import org.junit.runners.model.FrameworkMethod;

/**
 * Provides access to XML test data.
 */
public class MethodTestDataProvider implements TestDataProvider {
	
	/**
	 * Default name for the test data objects method.
	 */
	public static final String DEFAULT_METHOD_NAME = InvokeMultiDataTestMethod.METHOD_NAME_EXPRESSION
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
			methodName = methodName.replace(InvokeMultiDataTestMethod.METHOD_NAME_EXPRESSION,
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
	public MethodTestDataProvider() {
		super();
	}
	
	/**
	 * Default constructor.
	 * 
	 * @param methodName
	 *            The method name for the test data provider.
	 */
	public MethodTestDataProvider(final String methodName) {
		super();
		this.methodName = methodName;
	}
	
	/**
	 * @see org.dejava.component.util.test.runner.statement.provider.TestDataProvider#getTestData(java.lang.Object,
	 *      org.junit.runners.model.FrameworkMethod)
	 */
	@Override
	public Iterable<?> getTestData(final Object targetTest, final FrameworkMethod testMethod) {
		// The test data is the return of the method invocation.
		return (Iterable<?>) MethodHandler.invokeMethod(targetTest, getMethodName(testMethod), null);
	}
}
