package org.dejava.component.util.test.runner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import org.dejava.component.util.reflection.handler.ConstructorHandler;
import org.dejava.component.util.test.annotation.ParametricTest;
import org.dejava.component.util.test.exception.UnavailableTestDataException;
import org.dejava.component.util.test.runner.dataset.TestDataProvider;
import org.dejava.component.util.test.runner.statement.ParametricTestMethodInvoker;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.internal.runners.model.ReflectiveCallable;
import org.junit.internal.runners.statements.Fail;
import org.junit.rules.MethodRule;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkField;
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
	 *            Test method that will be executed.
	 * @return The test data provider instance from the given annotation.
	 * @throws UnavailableTestDataException
	 *             If the test data provider cannot be created.
	 */
	protected TestDataProvider getTestDataProvider(final FrameworkMethod method)
			throws UnavailableTestDataException {
		// Tries to get the provider.
		try {
			// Gets the annotation with the test data information.
			final ParametricTest parametricTest = method.getAnnotation(ParametricTest.class);
			// Returns the test data provider.
			return ConstructorHandler.invokeConstructor(parametricTest.dataProvider(), null,
					parametricTest.paramsValues(), false);
		}
		// If the provider cannot be instantiated.
		catch (final Exception exception) {
			// Throws an exception. FIXME
			throw new UnavailableTestDataException(null, exception, new Object[] { method.getName() });
		}
	}
	
	/**
	 * Gets the rules for the given test.
	 * 
	 * @param test
	 *            Test that the rules must be retrieved for.
	 * @return The rules for the given test.
	 */
	protected Collection<MethodRule> getRules(final Object test) {
		// Creates the rules list.
		final Collection<MethodRule> rules = new ArrayList<MethodRule>();
		// For each field (rule) from the test class annotated with @Rule.
		for (final FrameworkField currentRule : getTestClass().getAnnotatedFields(Rule.class)) {
			// Tries to add the current rule to the list,
			try {
				rules.add((MethodRule) currentRule.get(test));
			}
			// If it is not possible to get the current rule.
			catch (final Exception exception) {
				// Throws an RuntimeException as it should not be possible to get a not accessible field.
				// FIXME
				throw new RuntimeException();
			}
		}
		// Returns the rules.
		return rules;
	}
	
	/**
	 * TODO
	 * @param method
	 * @param target
	 * @param statement
	 * @return
	 */
	private Statement withRules(final FrameworkMethod method, final Object test, final Statement statement) {
		Statement result = statement;
		for (final MethodRule each : rules(target)) {
			result = each.apply(result, method, target);
		}
		return result;
	}
	
	/**
	 * TODO
	 * 
	 * @param method
	 * @param testParamsValues
	 * @return
	 */
	protected Statement getTestMethodStatement(final FrameworkMethod method, final Object[] testParamsValues) {
		// Test instance.
		Object test;
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
		Statement testMethodStatement = new ParametricTestMethodInvoker(test, method, testParamsValues);
		// Wraps the statement
		testMethodStatement = withRules(testMethodStatement);
	}
	
	/**
	 * TODO
	 * 
	 * @param method
	 * @param testParamValue
	 * @return
	 * @throws UnavailableTestDataException
	 */
	protected Collection<Statement> getTestMethodStatements(final FrameworkMethod method)
			throws UnavailableTestDataException {
		// Gets the annotation with the test data information.
		final ParametricTest parametricTest = method.getAnnotation(ParametricTest.class);
		// Creates the list with the statements for the current test.
		final Collection<Statement> testMethodStatements = new ArrayList<Statement>();
		// If the @ParametricTest annotation does not exist for the method.
		if (parametricTest == null) {
			// Gets only one statement for the test.
			final Statement testMethodStatement = getTestMethodStatement(method, new Object[] {});
			// Adds the test statement to the collection.
			testMethodStatements.add(testMethodStatement);
		}
		// If it does exist.
		else {
			// Gets the test data for the method.
			final List<?> testMethodParamsValues = new ArrayList<Object>(getTestDataProvider(method)
					.getTestData(method));
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
				final Statement testMethodStatement = getTestMethodStatement(method,
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
	 * @param method
	 *            The test method to run.
	 */
	protected void runParametricTest(final FrameworkMethod method) {
		
	}
	
	/**
	 * @see org.junit.runners.BlockJUnit4ClassRunner#runChild(org.junit.runners.model.FrameworkMethod,
	 *      org.junit.runner.notification.RunNotifier)
	 */
	@Override
	protected void runChild(final FrameworkMethod method, final RunNotifier notifier) {
		// Creates a new notifier for the test.
		final EachTestNotifier eachNotifier = new EachTestNotifier(notifier, describeChild(method));
		// If the @Ignore annotation is not present.
		if (method.getAnnotation(Ignore.class) == null) {
			// Notifies that the test has been started.
			eachNotifier.fireTestStarted();
			// Tries to run the test.
			try {
				runParametricTest(method);
			}
			// If an assumption was violated.
			catch (final AssumptionViolatedException exception) {
				// Notifies that a test assumption has been violated.
				eachNotifier.addFailedAssumption(exception);
			}
			// If any other exception is raised.
			catch (final Throwable throwable) {
				// Notifies that a test has failed.
				eachNotifier.addFailure(throwable);
			}
			// Finally.
			finally {
				// Notifies that a test has finished.
				eachNotifier.fireTestFinished();
			}
		}
		// If it is present.
		else {
			// Notifies that the test has been ignored.
			eachNotifier.fireTestIgnored();
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
			// If there is one and only one parameter.
			if (currentTestMethod.getMethod().getParameterTypes().length == 1) {
				// If the parameter type is not in the TestCase hierarchy.
				if (!currentTestMethod.getMethod().getParameterTypes()[0].isAssignableFrom(TestCase.class)) {
					// Adds an error. FIXME
					errors.add(new Exception("Method " + currentTestMethod.getName()
							+ " should have one parameter class that is assignable from TestCase.class"));
				}
			}
			// If there are not.
			else {
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
		// Validate the just annotated with @ParametricTest as "public void one test case arg".
		validatePublicVoidOneTestCaseArg(parametricTestMethods, false, errors);
	}
}
