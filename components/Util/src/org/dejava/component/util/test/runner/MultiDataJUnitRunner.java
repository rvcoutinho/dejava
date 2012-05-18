package org.dejava.component.util.test.runner;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.dejava.component.util.test.annotation.MultiDataTest;
import org.dejava.component.util.test.runner.statement.InvokeMultiDataTestMethod;
import org.junit.Test;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 * JUnit runner that inject test data into tests. It runs the same test multiple times if multiple data is
 * given.
 * 
 * It uses a custom annotation named {@link MultiDataTest} to decide how test data will be retrieved.
 * 
 * It also runs regular JUnit test annotated with @Test. In this cases, there is no data injection.
 */
public class MultiDataJUnitRunner extends BlockJUnit4ClassRunner {
	
	/**
	 * @see BlockJUnit4ClassRunner#BlockJUnit4ClassRunner(java.lang.Class)
	 */
	public MultiDataJUnitRunner(Class<?> clazz) throws InitializationError {
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
		// Removes the methods annotated with {@link MultiDataTest} (if method has both annotations).
		testMethods.removeAll(getTestClass().getAnnotatedMethods(MultiDataTest.class));
		// Adds the methods annotated with {@link MultiDataTest} again.
		testMethods.addAll(getTestClass().getAnnotatedMethods(MultiDataTest.class));
		// Returns the found test methods.
		return testMethods;
	}
	
	/**
	 * @see BlockJUnit4ClassRunner#methodInvoker(org.junit.runners.model.FrameworkMethod, java.lang.Object)
	 */
	@Override
	protected Statement methodInvoker(FrameworkMethod method, Object test) {
		// Uses the InvokeMultiDataTestMethod instead.
		return new InvokeMultiDataTestMethod(method, test);
	}
	
	/**
	 * Validate the passed methods (usually methods annotated with @Test but not @MultiDataTest) as
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
	 * Validate the passed methods (usually methods annotated with @MultiDataTest) as
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
				errors.add(new Exception("Method " + currentTestMethod.getName()
						+ " should have one and only one parameter"));
			}
			// If there are.
			else {
				// If the parameter type is not in the TestCase hierarchy.
				if (!currentTestMethod.getMethod().getParameterTypes()[0].isAssignableFrom(TestCase.class)) {
					// Adds an error. FIXME
					errors.add(new Exception("Method " + currentTestMethod.getName()
							+ " should have one parameter class that is assignable from TestCase.class"));
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
		// Gets the methods annotated with @MultiDataTest again.
		List<FrameworkMethod> testCaseConfigAnnotatedMethods = getTestClass().getAnnotatedMethods(
				MultiDataTest.class);
		// Removes the methods with both annotations from the first list.
		testAnnotatedMethods.removeAll(testCaseConfigAnnotatedMethods);
		// Validate the just annotated with @Test as "public void no args".
		validatePublicVoidNoArgs(testAnnotatedMethods, false, errors);
		// Validate the just annotated with @MultiDataTest as "public void one test case arg".
		validatePublicVoidOneTestCaseArg(testCaseConfigAnnotatedMethods, false, errors);
	}
}
