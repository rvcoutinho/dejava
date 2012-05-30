package org.dejava.component.util.test.runner.rule;

import org.dejava.component.util.test.runner.statement.ParametricTestWrapper;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * This rule applies a parametric test wrapper to a test statement.
 */
public class ParametricTestWrapperRule implements TestRule {
	
	/**
	 * The target test object for the method invocation.
	 */
	private Object targetTest;
	
	/**
	 * Returns the target test object for the method invocation.
	 * 
	 * @return The target test object for the method invocation.
	 */
	public Object getTargetTest() {
		return targetTest;
	}
	
	/**
	 * Sets the target test object for the method invocation.
	 * 
	 * @param targetTest
	 *            New target test object for the method invocation.
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
	 * The parameters values for the test method.
	 */
	private Object[] paramsValues;
	
	/**
	 * Gets the parameters values for the test method.
	 * 
	 * @return The parameters values for the test method.
	 */
	public Object[] getParamsValues() {
		return paramsValues;
	}
	
	/**
	 * Sets the parameters values for the test method.
	 * 
	 * @param paramsValues
	 *            New parameters values for the test method.
	 */
	public void setParamsValues(final Object[] paramsValues) {
		this.paramsValues = paramsValues;
	}
	
	/**
	 * Default constructor.
	 * 
	 * @param targetTest
	 *            The target test object for the method invocation.
	 * @param testMethod
	 *            The JUnit framework method.
	 * @param paramsValues
	 *            The parameters values for the test method.
	 */
	public ParametricTestWrapperRule(final Object targetTest, final FrameworkMethod testMethod,
			final Object[] paramsValues) {
		super();
		// Sets the main fields.
		this.targetTest = targetTest;
		this.testMethod = testMethod;
		this.paramsValues = paramsValues;
	}
	
	/**
	 * @see org.junit.rules.TestRule#apply(org.junit.runners.model.Statement, org.junit.runner.Description)
	 */
	@Override
	public ParametricTestWrapper apply(final Statement base, final Description description) {
		// Wraps the base statement with a new parametric test wrapper.
		return new ParametricTestWrapper(base, getTargetTest(), getTestMethod(), getParamsValues());
	}
	
}