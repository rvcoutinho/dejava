package org.dejava.component.util.test.runner.statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dejava.component.util.test.annotation.ParametricTest;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * Statement to invoke test methods.
 * 
 * Each test method might be invoked multiple times with the provided test data.
 */
public class InvokeParametricTestMethod extends Statement {
	
	/**
	 * Method name expression to be evaluated later.
	 */
	public static final String METHOD_NAME_EXPRESSION = "#{annotated_method_name}";
	
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
	 * @param testMethod
	 *            The JUnit framework method.
	 * @param targetTest
	 *            The target test object for the method invocation.
	 * @param paramsValues
	 *            The parameters values for the test method.
	 */
	public InvokeParametricTestMethod(final FrameworkMethod testMethod, final Object targetTest,
			final Object[] paramsValues) {
		super();
		// Sets the main fields.
		this.testMethod = testMethod;
		this.targetTest = targetTest;
		this.paramsValues = paramsValues;
	}
	
	/**
	 * @see Statement#evaluate()
	 */
	@Override
	public void evaluate() throws Throwable {
		// Invokes the test method with the given parameters values.
		getTestMethod().invokeExplosively(getTargetTest(), new Object[] { getParamsValues() });
		
		// Gets the annotation with the test data information.
		final ParametricTest parametricTest = getTestMethod().getAnnotation(ParametricTest.class);
		// If the @ParametricTest annotation exists.
		if (parametricTest != null) {
			// Tries to get the test data objects.
			final List<?> testData = new ArrayList<Object>(getTestDataProvider().getTestData(getTargetTest(),
					getTestMethod()));
			// Shuffles the list (important when {@link ParametricTest#maxTestData()} is given).
			Collections.shuffle(testData);
			// Gets the maximum number of test data objects to be used.
			Integer maxTestData = parametricTest.maxTestData();
			// If the number is 0.
			if (maxTestData == 0) {
				// The maximum number of test data objects is the list size.
				maxTestData = testData.size();
			}
			// List of failed tests. TODO
			// For each test data object (until the maximum given).
			for (Integer currentDataObjTdx = 0; currentDataObjTdx < maxTestData; currentDataObjTdx++) {
				// Gets the current object.
				final Object currentTestDataObj = testData.get(currentDataObjTdx);
				// Tries to invoke the test with the current test data.
				try {
					getTestMethod().invokeExplosively(getTargetTest(), new Object[] { currentTestDataObj });
				}
				// If the test raises an exception.
				catch (final Exception exception) {
					// Adds the failed test information to the list. TODO
				}
			}
		}
		// If it does not exist.
		else {
			// Invokes the method with no parameters.
			getTestMethod().invokeExplosively(getTargetTest(), new Object[0]);
		}
	}
}