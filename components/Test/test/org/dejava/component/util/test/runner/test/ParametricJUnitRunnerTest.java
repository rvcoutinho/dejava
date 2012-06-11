package org.dejava.component.util.test.runner.test;

import junit.framework.AssertionFailedError;

import org.dejava.component.util.test.LocalizedAssert;
import org.dejava.component.util.test.runner.ParametricJUnitRunner;
import org.dejava.component.util.test.runner.test.auxiliary.FakeTest;
import org.dejava.component.util.test.runner.test.auxiliary.RunListenerLog;
import org.junit.Test;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

/**
 * Test cases for the ParametricJUnitRunner.
 */
public class ParametricJUnitRunnerTest {
	
	/**
	 * Returns an instance of the {@link ParametricJUnitRunner} for the {@link FakeTest} that will be used to
	 * test the TestRunner.
	 * 
	 * @return An instance of the {@link ParametricJUnitRunner} for the {@link FakeTest} that will be used to
	 *         test the TestRunner.
	 * @throws InitializationError
	 *             If the test runner cannot me initialized for the {@link FakeTest}.
	 */
	private ParametricJUnitRunner getFakeTestRunner() throws InitializationError {
		// Returns a new instance of the runner for the FakeTest.
		return new ParametricJUnitRunner(FakeTest.class);
	}
	
	/**
	 * Tests if a regular test method (annotated with @Test) runs regularly.
	 * 
	 * @throws InitializationError
	 *             If the test runner cannot me initialized for the {@link FakeTest}.
	 */
	@Test
	public void testTestRegularSuccess() throws InitializationError {
		// Creates a new notifier.
		final RunNotifier runNotifier = new RunNotifier();
		// Creates a listener for the FakeTest.
		final RunListenerLog runListener = new RunListenerLog("testRegularSuccess");
		// Adds the listener to the notifier.
		runNotifier.addListener(runListener);
		// Tries to run the tests.
		getFakeTestRunner().run(runNotifier);
		// If the test is finished with failures.
		if ((!runListener.getStarted()) || (!runListener.getFinished()) || (runListener.getFailure() != null)) {
			// The test fails. FIXME
			LocalizedAssert.fail();
		}
	}
	
	/**
	 * Tests if a assumption failure test method (annotated with @Test) runs regularly.
	 * 
	 * @throws InitializationError
	 *             If the test runner cannot me initialized for the {@link FakeTest}.
	 */
	@Test
	public void testTestRegularAssumptionFailure() throws InitializationError {
		// Creates a new notifier.
		final RunNotifier runNotifier = new RunNotifier();
		// Creates a listener for the FakeTest.
		final RunListenerLog runListener = new RunListenerLog("testRegularAssumptionFailure");
		// Adds the listener to the notifier.
		runNotifier.addListener(runListener);
		// Tries to run the tests.
		getFakeTestRunner().run(runNotifier);
		// If the test is not finished with expected failures.
		if ((!runListener.getStarted()) || (!runListener.getFinished()) || (runListener.getFailure() == null)
				|| (!(runListener.getFailure().getException() instanceof AssertionFailedError))) {
			// The test fails. FIXME
			LocalizedAssert.fail();
		}
	}
	
	/**
	 * Tests if a failure test method (annotated with @Test) runs regularly.
	 * 
	 * @throws InitializationError
	 *             If the test runner cannot me initialized for the {@link FakeTest}.
	 */
	@Test
	public void testTestRegularFailure() throws InitializationError {
		// Creates a new notifier.
		final RunNotifier runNotifier = new RunNotifier();
		// Creates a listener for the FakeTest.
		final RunListenerLog runListener = new RunListenerLog("testRegularFailure");
		// Adds the listener to the notifier.
		runNotifier.addListener(runListener);
		// Tries to run the tests.
		getFakeTestRunner().run(runNotifier);
		// If the test is not finished with failures.
		if ((!runListener.getStarted()) || (!runListener.getFinished()) || (runListener.getFailure() == null)) {
			// The test fails. FIXME
			LocalizedAssert.fail();
		}
	}
	
	/**
	 * TODO
	 * 
	 * @throws InitializationError
	 *             If the test runner cannot me initialized for the {@link FakeTest}.
	 */
	@Test
	public void testTestRegularTimeoutFailure() throws InitializationError {
		// Creates a new notifier.
		final RunNotifier runNotifier = new RunNotifier();
		// Creates a listener for the FakeTest.
		final RunListenerLog runListener = new RunListenerLog("testRegularTimeoutFailure");
		// Adds the listener to the notifier.
		runNotifier.addListener(runListener);
		// Tries to run the tests.
		getFakeTestRunner().run(runNotifier);
		// If the test is not finished with failures.
		if ((!runListener.getStarted()) || (!runListener.getFinished()) || (runListener.getFailure() == null)) {
			// The test fails. FIXME
			LocalizedAssert.fail();
		}
	}
	
	/**
	 * TODO
	 * 
	 * @throws InitializationError
	 *             If the test runner cannot me initialized for the {@link FakeTest}.
	 */
	@Test
	public void testTestRegularExceptedException() throws InitializationError {
		// Creates a new notifier.
		final RunNotifier runNotifier = new RunNotifier();
		// Creates a listener for the FakeTest.
		final RunListenerLog runListener = new RunListenerLog("testRegularExceptedException");
		// Adds the listener to the notifier.
		runNotifier.addListener(runListener);
		// Tries to run the tests.
		getFakeTestRunner().run(runNotifier);
		// If the test is finished with failures.
		if ((!runListener.getStarted()) || (!runListener.getFinished()) || (runListener.getFailure() != null)) {
			// The test fails. FIXME
			LocalizedAssert.fail();
		}
	}
}