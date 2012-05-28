package org.dejava.component.util.test.runner.statement;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * Statement to invoke org.dejava.component.util.test.test methods.
 * 
 * Each org.dejava.component.util.test.test method might be invoked multiple times with the provided org.dejava.component.util.test.test data.
 */
public class ParametricTestMethodInvoker extends Statement {
	
	/**
	 * Method name expression to be evaluated later.
	 */
	public static final String METHOD_NAME_EXPRESSION = "#{annotated_method_name}";
	
	/**
	 * The target org.dejava.component.util.test.test object for the method invocation.
	 */
	private Object targetTest;
	
	/**
	 * Returns the target org.dejava.component.util.test.test object for the method invocation.
	 * 
	 * @return The target org.dejava.component.util.test.test object for the method invocation.
	 */
	public Object getTargetTest() {
		return targetTest;
	}
	
	/**
	 * Sets the target org.dejava.component.util.test.test object for the method invocation.
	 * 
	 * @param targetTest
	 *            New target org.dejava.component.util.test.test object for the method invocation.
	 */
	public void setTargetTest(final Object targetTest) {
		this.targetTest = targetTest;
	}
	
	/**
	 * The JUnit framework method.
	 */
	private FrameworkMethod testMethod;
	
	/**
	 * Returns the JUnit framework method.
	 * 
	 * @return The JUnit framework method.
	 */
	public FrameworkMethod getTestMethod() {
		return testMethod;
	}
	
	/**
	 * Sets the JUnit framework method.
	 * 
	 * @param testMethod
	 *            New JUnit framework method.
	 */
	public void setTestMethod(final FrameworkMethod testMethod) {
		this.testMethod = testMethod;
	}
	
	/**
	 * The parameters values for the org.dejava.component.util.test.test method.
	 */
	private Object[] paramsValues;
	
	/**
	 * Gets the parameters values for the org.dejava.component.util.test.test method.
	 * 
	 * @return The parameters values for the org.dejava.component.util.test.test method.
	 */
	public Object[] getParamsValues() {
		return paramsValues;
	}
	
	/**
	 * Sets the parameters values for the org.dejava.component.util.test.test method.
	 * 
	 * @param paramsValues
	 *            New parameters values for the org.dejava.component.util.test.test method.
	 */
	public void setParamsValues(final Object[] paramsValues) {
		this.paramsValues = paramsValues;
	}
	
	/**
	 * Default constructor.
	 * 
	 * @param targetTest
	 *            The target org.dejava.component.util.test.test object for the method invocation.
	 * @param testMethod
	 *            The JUnit framework method.
	 * @param paramsValues
	 *            The parameters values for the org.dejava.component.util.test.test method.
	 */
	public ParametricTestMethodInvoker(final Object targetTest, final FrameworkMethod testMethod,
			final Object[] paramsValues) {
		super();
		// Sets the main fields.
		this.targetTest = targetTest;
		this.testMethod = testMethod;
		this.paramsValues = paramsValues;
	}
	
	/**
	 * @see Statement#evaluate()
	 */
	@Override
	public void evaluate() throws Throwable {
		// Invokes the org.dejava.component.util.test.test method with the given parameters values.
		getTestMethod().invokeExplosively(getTargetTest(), new Object[] { getParamsValues() });
	}
}