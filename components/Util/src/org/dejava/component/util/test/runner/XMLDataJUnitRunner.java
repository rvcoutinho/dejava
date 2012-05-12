package org.dejava.component.util.test.runner;

import java.util.ArrayList;
import java.util.List;

import org.dejava.component.util.test.annotation.TestCaseConfig;
import org.dejava.component.util.test.exception.TestException;
import org.dejava.component.util.test.model.TestCase;
import org.dejava.component.util.test.runner.statement.InvokeMethodXMLConfig;
import org.junit.Test;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 * JUnit runner that uses XML as test data for the test methods annotated with @TestCaseConfig. It injects a
 * TestCase object as argument for the test methods.
 */
public class XMLDataJUnitRunner extends BlockJUnit4ClassRunner {
	
	/**
	 * @see BlockJUnit4ClassRunner#BlockJUnit4ClassRunner(java.lang.Class)
	 */
	public XMLDataJUnitRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}
	
	/**
	 * @see BlockJUnit4ClassRunner#computeTestMethods()
	 */
	@Override
	protected List<FrameworkMethod> computeTestMethods() {
		// List of test methods.
		List<FrameworkMethod> testMethods = new ArrayList<FrameworkMethod>();
		// Adds the methods annotated with @Test (as for default action).
		testMethods.addAll(getTestClass().getAnnotatedMethods(Test.class));
		// Removes the methods annotated with @TestCaseConfig (if method has both annotations).
		testMethods.removeAll(getTestClass().getAnnotatedMethods(TestCaseConfig.class));
		// Adds the methods annotated with @TestCaseConfig again.
		testMethods.addAll(getTestClass().getAnnotatedMethods(TestCaseConfig.class));
		// Returns the found test methods.
		return testMethods;
	}
	
	/**
	 * @see BlockJUnit4ClassRunner#methodInvoker(org.junit.runners.model.FrameworkMethod, java.lang.Object)
	 */
	@Override
	protected Statement methodInvoker(FrameworkMethod method, Object test) {
		// Uses the InvokeMethodXMLConfig instead.
		return new InvokeMethodXMLConfig(method, test);
	}
	
	/**
	 * Validate the passed methods (usually methods annotated with @Test but not @TestCaseConfig) as
	 * "public void no args".
	 * 
	 * @param methods
	 *            Methods to be validated.
	 * @param isStatic
	 *            If the method must or must not be static.
	 * @param errors
	 *            Errors list where new validation errors should be added.
	 */
	protected void validatePublicVoidNoArgs(List<FrameworkMethod> methods, boolean isStatic,
			List<Throwable> errors) {
		// For each method.
		for (FrameworkMethod currentTestMethod : methods) {
			// Performs "public void no args" validation.
			currentTestMethod.validatePublicVoidNoArg(isStatic, errors);
		}
	}
	
	/**
	 * Validate the passed methods (usually methods annotated with @TestCaseConfig) as
	 * "public void one test case arg".
	 * 
	 * @param methods
	 *            Methods to be validated.
	 * @param isStatic
	 *            If the method must or must not be static.
	 * @param errors
	 *            Errors list where new validation errors should be added.
	 */
	protected void validatePublicVoidOneTestCaseArg(List<FrameworkMethod> methods, boolean isStatic,
			List<Throwable> errors) {
		// For each method.
		for (FrameworkMethod currentTestMethod : methods) {
			// Performs "public void" validation.
			currentTestMethod.validatePublicVoid(isStatic, errors);
			// If there are not one and only one parameter.
			if (currentTestMethod.getMethod().getParameterTypes().length != 1) {
				// Adds an error. FIXME
				errors.add(new TestException("Method " + currentTestMethod.getName()
						+ " should have one and only one parameter", null, null));
			}
			// If there are.
			else {
				// If the parameter type is not in the TestCase hierarchy.
				if (!currentTestMethod.getMethod().getParameterTypes()[0].isAssignableFrom(TestCase.class)) {
					// Adds an error. FIXME
					errors.add(new TestException("Method " + currentTestMethod.getName()
							+ " should have one parameter class that is assignable from TestCase.class",
							null, null));
				}
			}
		}
	}
	
	/**
	 * @see BlockJUnit4ClassRunner#validateTestMethods(java.util.List)
	 */
	@Override
	protected void validateTestMethods(List<Throwable> errors) {
		// Gets the methods annotated with @Test.
		List<FrameworkMethod> testAnnotatedMethods = getTestClass().getAnnotatedMethods(Test.class);
		// Gets the methods annotated with @TestCaseConfig again.
		List<FrameworkMethod> testCaseConfigAnnotatedMethods = getTestClass().getAnnotatedMethods(
				TestCaseConfig.class);
		// Removes the methods with both annotations from the first list.
		testAnnotatedMethods.removeAll(testCaseConfigAnnotatedMethods);
		// Validate the just annotated with @Test as "public void no args".
		validatePublicVoidNoArgs(testAnnotatedMethods, false, errors);
		// Validate the just annotated with @Test as "public void one test case arg".
		validatePublicVoidOneTestCaseArg(testCaseConfigAnnotatedMethods, false, errors);
	}
}
