package org.dejava.component.util.test.runner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.dejava.component.util.reflection.handler.ConstructorHandler;
import org.dejava.component.util.test.annotation.ParametricTest;
import org.dejava.component.util.test.exception.UnavailableTestDataException;
import org.dejava.component.util.test.runner.dataset.TestDataProvider;
import org.dejava.component.util.test.runner.statement.ParametricTestMethodInvoker;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Test.None;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.internal.runners.model.ReflectiveCallable;
import org.junit.internal.runners.statements.Fail;
import org.junit.rules.ExpectedException;
import org.junit.rules.RunRules;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.notification.RunNotifier;
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
	 * @param testMethod
	 *            Test method that will be executed.
	 * @return The test data provider instance from the given annotation.
	 * @throws UnavailableTestDataException
	 *             If the test data provider cannot be created.
	 */
	protected TestDataProvider getTestDataProvider(final FrameworkMethod testMethod)
			throws UnavailableTestDataException {
		// Tries to get the provider.
		try {
			// Gets the annotation with the test data information.
			final ParametricTest parametricTest = testMethod.getAnnotation(ParametricTest.class);
			// Returns the test data provider.
			return ConstructorHandler.invokeConstructor(parametricTest.dataProvider(), null,
					parametricTest.paramsValues(), false);
		}
		// If the provider cannot be instantiated.
		catch (final Exception exception) {
			// Throws an exception.
			throw new UnavailableTestDataException(exception, testMethod.getName());
		}
	}
	
	/**
	 * Gets a new expected exception rule for the current test method.
	 * 
	 * @param testMethod
	 *            Test method that will be executed.
	 * @return A new expected exception rule for the current test method.
	 */
	protected TestRule getExpectedExceptionRule(final FrameworkMethod testMethod) {
		// Creates a new expected exception rule.
		final ExpectedException expectedException = ExpectedException.none();
		// Tries to get the @ParametricTest annotation.
		final ParametricTest paramTestAnnotation = testMethod.getAnnotation(ParametricTest.class);
		// If the @ParametricTest annotation is not present for the method.
		if (paramTestAnnotation == null) {
			// Tries to get the @Test annotation.
			final Test testAnnotation = testMethod.getAnnotation(Test.class);
			// If the @Test annotation is present for the method, and the excepted exception is not None.
			if ((testAnnotation != null) && (!None.class.equals(testAnnotation.expected()))) {
				// Adds the expected exception class to the Rule.
				expectedException.expect(testAnnotation.expected());
			}
		}
		// If it is present.
		else {
			// If the excepted exception class is not None.
			if (!None.class.equals(paramTestAnnotation.expectedExceptionClass())) {
				// Adds the expected exception class to the Rule.
				expectedException.expect(paramTestAnnotation.expectedExceptionClass());
				// If there is an excepted exception message.
				if ("".equals(paramTestAnnotation.expectedExceptionMessage())) {
					// Adds the expected exception message to the Rule.
					expectedException.expectMessage(paramTestAnnotation.expectedExceptionMessage());
				}
			}
		}
		// Returns the expected exception rule.
		return expectedException;
	}
	
	/**
	 * Gets a new timeout rule for the current test method.
	 * 
	 * @param testMethod
	 *            Test method that will be executed.
	 * @return A new timeout rule for the current test method.
	 */
	protected TestRule getTimeoutRule(final FrameworkMethod testMethod) {
		// The default timeout is 0 (wait forever).
		Integer timeout = 0;
		// Tries to get the @ParametricTest annotation.
		final ParametricTest paramTestAnnotation = testMethod.getAnnotation(ParametricTest.class);
		// If the @ParametricTest annotation is not present for the method.
		if (paramTestAnnotation == null) {
			// Tries to get the @Test annotation.
			final Test testAnnotation = testMethod.getAnnotation(Test.class);
			// If the @Test annotation is present for the method.
			if (testAnnotation != null) {
				// Uses the @Test timeout.
				timeout = new Float(testAnnotation.timeout()).intValue();
			}
		}
		// If it is present.
		else {
			// Uses the @ParametricTest timeout.
			timeout = paramTestAnnotation.timeout();
		}
		// Returns the timeout rule.
		return new Timeout(timeout);
	}
	
	/**
	 * Gets the rules for the given test.
	 * 
	 * @param testMethod
	 *            The test method for the statement.
	 * @param test
	 *            Test that the rules must be retrieved for.
	 * @return The rules for the given test.
	 */
	protected Collection<TestRule> getRules(final FrameworkMethod testMethod, final Object test) {
		// Creates the rules list.
		final List<TestRule> rules = new ArrayList<TestRule>();
		// For each field (rule) from the test class annotated with @Rule.
		for (final TestRule currentRule : super.getTestRules(test)) {
			// Adds the current rule to the list,
			rules.add(currentRule);
		}
		// Adds the expected exception rule.
		rules.add(0, getExpectedExceptionRule(testMethod));
		// Adds the timeout rule to the list.
		rules.add(1, getTimeoutRule(testMethod));
		// TODO Adds other default rules.
		// Returns the rules.
		return rules;
	}
	
	/**
	 * Gets the statement for a parametric test method.
	 * 
	 * @param testMethod
	 *            The test method for the statement.
	 * @param testParamsValues
	 *            The values of the parameters to be used in the test.
	 * @return the statement for a parametric test method.
	 */
	protected Statement getTestMethodStatement(final FrameworkMethod testMethod,
			final Object[] testParamsValues) {
		// Test instance.
		Object test = null;
		// Tries to get a new test instance.
		try {
			// Creates a new reflective call.
			final ReflectiveCallable createTestCall = new ReflectiveCallable() {
				
				@Override
				protected Object runReflectiveCall() throws Throwable {
					return createTest();
				}
			};
			// Creates the test instance.
			test = createTestCall.run();
		}
		// If anything is raised by the test creation.
		catch (final Throwable throwable) {
			// Returns a fail statement.
			return new Fail(throwable);
		}
		// Creates the ParametricTestMethodInvoker statement.
		Statement testMethodStatement = new ParametricTestMethodInvoker(test, testMethod, testParamsValues);
		// Wraps the statement with the given rules.
		testMethodStatement = new RunRules(testMethodStatement, getRules(testMethod, testMethodStatement),
				describeChild(testMethod));
		// Returns the test statement.
		return testMethodStatement;
	}
	
	/**
	 * Gets the statements for a parametric test method.
	 * 
	 * @param testMethod
	 *            The test method for the statements.
	 * @return The statements for a parametric test method.
	 * @throws UnavailableTestDataException
	 *             If the test data is not available.
	 */
	protected Collection<Statement> getTestMethodStatements(final FrameworkMethod testMethod)
			throws UnavailableTestDataException {
		// Gets the annotation with the test data information.
		final ParametricTest parametricTest = testMethod.getAnnotation(ParametricTest.class);
		// Creates the list with the statements for the current test.
		final Collection<Statement> testMethodStatements = new ArrayList<Statement>();
		// If the @ParametricTest annotation does not exist for the method.
		if (parametricTest == null) {
			// Gets only one statement for the test.
			final Statement testMethodStatement = getTestMethodStatement(testMethod, new Object[] {});
			// Adds the test statement to the collection.
			testMethodStatements.add(testMethodStatement);
		}
		// If it does exist.
		else {
			// Gets the test data for the method.
			final List<?> testMethodParamsValues = new ArrayList<Object>(getTestDataProvider(testMethod)
					.getTestData(testMethod));
			// Shuffles the parameters values.
			Collections.shuffle(testMethodParamsValues);
			// Gets the maximum number of test data objects to be used.
			Integer maxTestParams = parametricTest.maxTestData();
			// If the number is 0.
			if (maxTestParams == 0) {
				// The maximum number of test data objects is the list size.
				maxTestParams = testMethodParamsValues.size();
			}
			// For each test data object (until the maximum given).
			for (Integer currentDataObjIndex = 0; currentDataObjIndex < maxTestParams; currentDataObjIndex++) {
				// Gets the current parameter value.
				final Object currentTestParamValue = testMethodParamsValues.get(currentDataObjIndex);
				// Gets the test statement for the current parameter value.
				final Statement testMethodStatement = getTestMethodStatement(testMethod,
						new Object[] { currentTestParamValue });
				// Adds the test statement to the collection.
				testMethodStatements.add(testMethodStatement);
			}
		}
		// Returns the statements for the test.
		return testMethodStatements;
	}
	
	/**
	 * Runs a parametric test.
	 * 
	 * @param testMethod
	 *            The test method to run.
	 * @throws UnavailableTestDataException
	 *             If the test data is not available.
	 */
	protected void runParametricTest(final FrameworkMethod testMethod) throws UnavailableTestDataException {
		// Gets the statements for the test.
		final Collection<Statement> testMethodStatements = getTestMethodStatements(testMethod);
		// Creates a list for the failed tests.
		final Collection<Throwable> testExceptions = new ArrayList<Throwable>();
		// Creates a list for the assumption violated tests.
		final Collection<AssumptionViolatedException> testAssumptionViolations = new ArrayList<AssumptionViolatedException>();
		// For each statement.
		for (final Statement currentStatement : testMethodStatements) {
			// Tries to evaluate the current statement.
			try {
				currentStatement.evaluate();
			}
			// If an assumption is violated for the current statement.
			catch (final AssumptionViolatedException exception) {
				// Adds the exception to the proper list.
				testAssumptionViolations.add(exception);
			}
			// If any other exception is raised by the current test statement.
			catch (final Throwable throwable) {
				// Adds the exception to the proper list.
				testExceptions.add(throwable);
			}
		}
		// If the test statement evaluation has raised any exceptions.
	}
	
	/**
	 * @see org.junit.runners.BlockJUnit4ClassRunner#runChild(org.junit.runners.model.FrameworkMethod,
	 *      org.junit.runner.notification.RunNotifier)
	 */
	@Override
	protected void runChild(final FrameworkMethod testMethod, final RunNotifier notifier) {
		// Creates a new notifier for the test.
		final EachTestNotifier testNotifier = new EachTestNotifier(notifier, describeChild(testMethod));
		// If the @Ignore annotation is not present.
		if (testMethod.getAnnotation(Ignore.class) == null) {
			// Notifies that the test has been started.
			testNotifier.fireTestStarted();
			// Tries to run the test.
			try {
				runParametricTest(testMethod);
			}
			// If an assumption was violated.
			catch (final AssumptionViolatedException exception) {
				// Notifies that a test assumption has been violated.
				testNotifier.addFailedAssumption(exception);
			}
			// If any other exception is raised.
			catch (final Throwable throwable) {
				// Notifies that a test has failed.
				testNotifier.addFailure(throwable);
			}
			// Finally.
			finally {
				// Notifies that a test has finished.
				testNotifier.fireTestFinished();
			}
		}
		// If it is present.
		else {
			// Notifies that the test has been ignored.
			testNotifier.fireTestIgnored();
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
	 * Validate the passed methods (usually methods annotated with @ParametricTest) as "public void one arg".
	 * 
	 * @param methods
	 *            Methods to be validated.
	 * @param isStatic
	 *            If the method must or must not be static.
	 * @param errors
	 *            Errors list where new validation errors should be added.
	 */
	protected void validatePublicVoidOneArg(final List<FrameworkMethod> methods, final boolean isStatic,
			final List<Throwable> errors) {
		// For each method.
		for (final FrameworkMethod currentTestMethod : methods) {
			// Performs "public void" validation.
			currentTestMethod.validatePublicVoid(isStatic, errors);
			// If there is not one and only one parameter.
			if (currentTestMethod.getMethod().getParameterTypes().length != 1) {
				// Adds an error. FIXME
				errors.add(new Exception("Method " + currentTestMethod.getName()
						+ " should have one and only one parameter"));
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
		// Validate the just annotated with @ParametricTest as "public void one arg".
		validatePublicVoidOneArg(parametricTestMethods, false, errors);
	}
}
