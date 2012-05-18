package org.dejava.component.util.test.runner.statement;

import org.dejava.component.util.reflection.handler.ConstructorHandler;
import org.dejava.component.util.test.annotation.MultiDataTest;
import org.dejava.component.util.test.runner.statement.provider.TestDataProvider;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * Statement to invoke test methods.
 * 
 * Each test method might be invoked multiple times with the provided test data.
 */
public class InvokeMultiDataTestMethod extends Statement {
	
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
	 * Default constructor.
	 * 
	 * @param testMethod
	 *            The JUnit framework method.
	 * @param targetTest
	 *            The target test object for the method invocation.
	 */
	public InvokeMultiDataTestMethod(final FrameworkMethod testMethod, final Object targetTest) {
		super();
		// Sets the main fields.
		this.testMethod = testMethod;
		this.targetTest = targetTest;
	}
	
	/**
	 * Gets the test data provider instance from the given annotation.
	 * 
	 * @return The test data provider instance from the given annotation.
	 */
	public TestDataProvider getTestDataProvider() {
		// Gets the annotation with the test data information.
		MultiDataTest multiDataTest = getTestMethod().getAnnotation(MultiDataTest.class);
		// Returns the test data provider.
		return ConstructorHandler.invokeConstructor(multiDataTest.dataProvider(),
				multiDataTest.paramsValues());
	}
	
	/**
	 * @see Statement#evaluate()
	 */
	@Override
	public void evaluate() throws Throwable {
		// If the @MultiDataTest annotation exists.
		if (getTestMethod().getAnnotation(MultiDataTest.class) != null) {
			// Tries to get the test data objects.
			Iterable<?> testData = getTestDataProvider().getTestData(getTargetTest(), getTestMethod());
			// Keeps the current test data object index.
			Integer currentTestDataObjIdx = 0;
			// List of failed tests. TODO
			// For each test data object.
			for (Object currentTestDataObj : testData) {
				// Tries to invoke the test with the current test data.
				try {
					getTestMethod().invokeExplosively(getTargetTest(), new Object[] { currentTestDataObj });
				}
				// If the test raises an exception.
				catch (Exception exception) {
					// Adds the failed test information to the list. TODO
				}
				// The current object index is incremented.
				currentTestDataObjIdx++;
			}
		}
		// If it does not exist.
		else {
			// Invokes the method with no parameters.
			getTestMethod().invokeExplosively(getTargetTest(), new Object[0]);
		}
	}
}
