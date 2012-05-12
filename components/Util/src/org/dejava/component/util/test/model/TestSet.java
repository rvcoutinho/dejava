package org.dejava.component.util.test.model;

import org.dejava.component.util.exception.localized.InvalidParameterException;
import org.dejava.component.util.reflection.exception.InvocationException;
import org.dejava.component.util.reflection.handler.MethodHandler;
import org.dejava.component.util.test.exception.InvalidTestStepException;
import org.dejava.component.util.test.exception.TestStepException;

/**
 * Represents a set of test cases.
 */
public class TestSet extends junit.framework.TestCase {
	
	/**
	 * Run a step with the given test data object.
	 * 
	 * @param testStep
	 *            Test step to be ran.
	 * @param testDataObject
	 *            Test data object that the step should be run with.
	 * @throws InvalidTestStepException
	 *             If the test step is not valid (invalid method information).
	 * @throws TestStepException
	 *             If the test step method throws an exception.
	 */
	protected void runStep(final TestStep testStep, final Object testDataObject)
			throws InvalidTestStepException, TestStepException {
		// Tries to run the step.
		try {
			// Step return object.
			Object stepReturn;
			// Gets the class to call static method.
			Class<?> staticMethodClass = testStep.getMethod().getStaticMethodClass();
			// Gets the name of the test.
			String methodName = testStep.getMethod().getName();
			// Gets the parameters classes for the method.
			Class<?>[] parametersClasses = testStep.getMethod().getParametersClasses()
					.toArray(new Class<?>[0]);
			// Gets the actual parameter values for the method.
			Object[] parametersValues = testStep.getMethod().getActualParametersValues(testDataObject)
					.toArray();
			// If it is a static method call.
			if (staticMethodClass != null) {
				// Tries to invoke the static method from the defined class.
				stepReturn = MethodHandler.invokeStaticMethod(staticMethodClass, methodName,
						parametersClasses, parametersValues);
			}
			// If not.
			else {
				// Tries to invoke the method from the test object.
				stepReturn = MethodHandler
						.invokeMethod(this, methodName, parametersClasses, parametersValues);
			}
			// If there is an expected exception for the step.
			if (testStep.getMethod().getExpectedException() != null) {
				// Throws an exception.
				throw new TestStepException("test.step.method.exception.expected", null, null);
			}
			// If the step return is different than expected.
			if ((!((stepReturn == null) && (testStep.getMethod().getActualExpectedReturn(testDataObject) == null)))
					&& (((stepReturn == null) && (testStep.getMethod()
							.getActualExpectedReturn(testDataObject) != null)) || (!stepReturn
							.equals(testStep.getMethod().getActualExpectedReturn(testDataObject))))) {
				// Throws a test method exception.
				throw new TestStepException("test.step.method.return.unexpected", null, null);
			}
		}
		// If a parameter is not valid for invoking the step.
		catch (InvalidParameterException exception) {
			// Throws an invalid test step exception.
			throw new InvalidTestStepException("test.step.invalid", exception, null);
		}
		// If an exception is thrown by the test step method.
		catch (InvocationException exception) {
			
			// If the exception thrown by the step method has the expected name
			// and
			// message.
			if ((testStep.getMethod().getExpectedException() != null)
					&& (exception.getCause().getCause().getClass().getSimpleName().equalsIgnoreCase(testStep
							.getMethod().getExpectedException().getName()))
					&& (exception.getCause().getCause().getMessage().equalsIgnoreCase(testStep.getMethod()
							.getExpectedException().getMessage()))) {
				// The exception is supressed.
			}
			// If the exception is not the expected.
			else {
				// Throws an exception.
				throw new TestStepException("test.step.method.exception.unexpected", exception, null);
			}
		}
	}
	
	/**
	 * Run the steps of a test case.
	 * 
	 * @param testCase
	 *            Test case to run the steps.
	 * @throws InvalidTestStepException
	 *             If the test step is not valid (invalid method information).
	 * @throws TestStepException
	 *             If the test step method throws an exception.
	 */
	protected void runSteps(final TestCase testCase) throws InvalidTestStepException, TestStepException {
		// If there are test steps.
		if ((testCase != null) && (!testCase.getSteps().isEmpty())) {
			// If there is test data defined in the configuration.
			if ((testCase.getData() != null) && (!testCase.getData().isEmpty())) {
				// For each object in the test data.
				for (Object currentTestDataObject : testCase.getData()) {
					// For each test step.
					for (TestStep currentTestStep : testCase.getSteps()) {
						// Run the current step with the current test data
						// object.
						runStep(currentTestStep, currentTestDataObject);
					}
				}
			}
			// If there are not (it is a single data test).
			else {
				// For each test step.
				for (TestStep currentTestStep : testCase.getSteps()) {
					// Run the current step.
					runStep(currentTestStep, null);
				}
			}
		}
	}
}
