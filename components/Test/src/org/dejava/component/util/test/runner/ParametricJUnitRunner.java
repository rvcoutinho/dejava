package org.dejava.component.util.test.runner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import org.dejava.component.util.reflection.handler.ConstructorHandler;
import org.dejava.component.util.test.annotation.ParametricTest;
import org.dejava.component.util.test.exception.UnavailableTestDataException;
import org.dejava.component.util.test.runner.dataset.TestDataProvider;
import org.junit.Test;
import org.junit.internal.runners.model.ReflectiveCallable;
import org.junit.internal.runners.statements.Fail;
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
			throw new UnavailableTestDataException(null, exception, null);
		}
	}
	
	
	
	/**
	 * @see org.junit.runners.BlockJUnit4ClassRunner#methodBlock(org.junit.runners.model.FrameworkMethod)
	 */
	@Override
	protected Statement methodBlock(final FrameworkMethod method) {
		// Test instance.
		Object test;
		// Tries to get a new test instance.
		try {
			// Creates a new reflective call.
			ReflectiveCallable createTestCall = new ReflectiveCallable() {
				
				@Override
				protected Object runReflectiveCall() throws Throwable {
					return createTest();
				}
			};
			// Creates the test instance.
			test = createTestCall.run();
		}
		// If anything is raised by the test creation.
		catch (Throwable throwable) {
			// Returns a fail statement.
			return new Fail(throwable);
		}
		
		
		
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
		final List<FrameworkMethod> testMethods = getTestClass().getAnnotatedMethods(Test.class);
		// Gets the methods annotated with @ParametricTest again.
		final List<FrameworkMethod> parametricTestMethods = getTestClass().getAnnotatedMethods(
				ParametricTest.class);
		// Removes the methods with both annotations from the first list.
		testMethods.removeAll(parametricTestMethods);
		// Validate the just annotated with @Test as "public void no args".
		validatePublicVoidNoArgs(testMethods, false, errors);
		// Validate the just annotated with @ParametricTest as "public void one test case arg".
		validatePublicVoidOneTestCaseArg(parametricTestMethods, false, errors);
	}
}
