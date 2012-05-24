package org.dejava.component.util.test.runner;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.dejava.component.util.reflection.handler.ConstructorHandler;
import org.dejava.component.util.test.annotation.ParametricTest;
import org.dejava.component.util.test.exception.UnavailableTestDataException;
import org.dejava.component.util.test.runner.statement.InvokeParametricTestMethod;
import org.dejava.component.util.test.runner.statement.provider.TestDataProvider;
import org.junit.Test;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 * JUnit runner that injects test data into tests. It runs the same test multiple times (if multiple data is
 * given).
 * 
 * It uses a custom annotation named {@link ParametricTest} to decide how test data will be retrieved.
 * 
 * It also runs regular JUnit test annotated with @Test. In this cases, there is no data injection.
 */
public class ParametricJUnitRunner extends BlockJUnit4ClassRunner {
	
	/**
	 * @see BlockJUnit4ClassRunner#BlockJUnit4ClassRunner(java.lang.Class)
	 */
	public ParametricJUnitRunner(final Class<?> clazz) throws InitializationError {
		super(clazz);
	}
	
	/**
	 * @see BlockJUnit4ClassRunner#computeTestMethods()
	 */
	@Override
	protected List<FrameworkMethod> computeTestMethods() {
		// List of test methods.
		final List<FrameworkMethod> testMethods = new ArrayList<FrameworkMethod>();
		// Adds the methods annotated with @Test (as for default action).
		testMethods.addAll(getTestClass().getAnnotatedMethods(Test.class));
		// Removes the methods annotated with {@link ParametricTest} (if method has both annotations).
		testMethods.removeAll(getTestClass().getAnnotatedMethods(ParametricTest.class));
		// Adds the methods annotated with {@link ParametricTest} again.
		testMethods.addAll(getTestClass().getAnnotatedMethods(ParametricTest.class));
		// Returns the found test methods.
		return testMethods;
	}
	
	/**
	 * @see BlockJUnit4ClassRunner#methodInvoker(org.junit.runners.model.FrameworkMethod, java.lang.Object)
	 */
	@Override
	protected Statement methodInvoker(final FrameworkMethod method, final Object test) {
		// Uses the InvokeParametricTestMethod instead.
		return new InvokeParametricTestMethod(method, test);
	}
	
	/**
	 * Gets the test data provider instance from the given annotation.
	 * 
	 * @param method
	 *            Test method that will be executed.f
	 * @return The test data provider instance from the given annotation.
	 * @throws UnavailableTestDataException
	 *             If the test data provider cannot be created.
	 */
	public TestDataProvider getTestDataProvider(final FrameworkMethod method)
			throws UnavailableTestDataException {
		// Tries to get the provider.
		try {
			// Gets the annotation with the test data information.
			final ParametricTest parametricTest = method.getAnnotation(ParametricTest.class);
			// Returns the test data provider.
			return ConstructorHandler.invokeConstructor(parametricTest.dataProvider(),
					parametricTest.paramsValues());
		}
		// If the provider cannot be instantiated.
		catch (final Exception exception) {
			// Throws an exception. FIXME
			throw new UnavailableTestDataException(null, null, null);
		}
	}
	
	/**
	 * @see org.junit.runners.BlockJUnit4ClassRunner#methodBlock(org.junit.runners.model.FrameworkMethod)
	 */
	@Override
	protected Statement methodBlock(final FrameworkMethod method) {
		
	}
	
	/**
	 * Validate the passed methods (usually methods annotated with @Test but not @ParametricTest) as
	 * "public void no args".
	 * 
	 * @param methods
	 *            Methods to be validated.
	 * @param isStatic
	 *            If the method must or must not be static.
	 * @param errors
	 *            Errors list where new validation errors should be added.
	 */
	protected void validatePublicVoidNoArgs(final List<FrameworkMethod> methods, final boolean isStatic,
			final List<Throwable> errors) {
		// For each method.
		for (final FrameworkMethod currentTestMethod : methods) {
			// Performs "public void no args" validation.
			currentTestMethod.validatePublicVoidNoArg(isStatic, errors);
		}
	}
	
	/**
	 * Validate the passed methods (usually methods annotated with @ParametricTest) as
	 * "public void one test case arg".
	 * 
	 * @param methods
	 *            Methods to be validated.
	 * @param isStatic
	 *            If the method must or must not be static.
	 * @param errors
	 *            Errors list where new validation errors should be added.
	 */
	protected void validatePublicVoidOneTestCaseArg(final List<FrameworkMethod> methods,
			final boolean isStatic, final List<Throwable> errors) {
		// For each method.
		for (final FrameworkMethod currentTestMethod : methods) {
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
	protected void validateTestMethods(final List<Throwable> errors) {
		// Gets the methods annotated with @Test.
		final List<FrameworkMethod> testAnnotatedMethods = getTestClass().getAnnotatedMethods(Test.class);
		// Gets the methods annotated with @ParametricTest again.
		final List<FrameworkMethod> testCaseConfigAnnotatedMethods = getTestClass().getAnnotatedMethods(
				ParametricTest.class);
		// Removes the methods with both annotations from the first list.
		testAnnotatedMethods.removeAll(testCaseConfigAnnotatedMethods);
		// Validate the just annotated with @Test as "public void no args".
		validatePublicVoidNoArgs(testAnnotatedMethods, false, errors);
		// Validate the just annotated with @ParametricTest as "public void one test case arg".
		validatePublicVoidOneTestCaseArg(testCaseConfigAnnotatedMethods, false, errors);
	}
}
