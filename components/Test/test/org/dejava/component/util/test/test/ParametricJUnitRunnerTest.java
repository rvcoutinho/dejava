package org.dejava.component.util.test.test;

import junit.framework.AssertionFailedError;

import org.dejava.component.util.test.LocalizedAssert;
import org.dejava.component.util.test.runner.ParametricJUnitRunner;
import org.dejava.component.util.test.test.auxiliary.FakeTest;
import org.dejava.component.util.test.test.auxiliary.RunListenerLog;
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
	 * Tests if a normal test method (annotated with @Test) runs normally.
	 * 
	 * @throws InitializationError
	 *             If the test runner cannot me initialized for the {@link FakeTest}.
	 */
	@Test
	public void testTestNormalSuccess() throws InitializationError {
		// Creates a new notifier.
		final RunNotifier runNotifier = new RunNotifier();
		// Creates a listener for the FakeTest.
		final RunListenerLog runListener = new RunListenerLog("testNormalSuccess");
		// Adds the listener to the notifier.
		runNotifier.addListener(runListener);
		// Tries to run the tests.
		getFakeTestRunner().run(runNotifier);
		// If the test is not finished without failures.
		if ((!runListener.getStarted()) || (!runListener.getFinished()) || (runListener.getFailure() != null)) {
			// The test fails. FIXME
			LocalizedAssert.fail();
		}
	}
	
	/**
	 * Tests if a assumption failure test method (annotated with @Test) runs normally.
	 * 
	 * @throws InitializationError
	 *             If the test runner cannot me initialized for the {@link FakeTest}.
	 */
	@Test
	public void testTestNormalAssumptionFailure() throws InitializationError {
		// Creates a new notifier.
		final RunNotifier runNotifier = new RunNotifier();
		// Creates a listener for the FakeTest.
		final RunListenerLog runListener = new RunListenerLog("testNormalAssumptionFailure");
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
	 * Tests if a failure test method (annotated with @Test) runs normally.
	 * 
	 * @throws InitializationError
	 *             If the test runner cannot me initialized for the {@link FakeTest}.
	 */
	@Test
	public void testTestNormalFailure() throws InitializationError {
		// Creates a new notifier.
		final RunNotifier runNotifier = new RunNotifier();
		// Creates a listener for the FakeTest.
		final RunListenerLog runListener = new RunListenerLog("testNormalFailure");
		// Adds the listener to the notifier.
		runNotifier.addListener(runListener);
		// Tries to run the tests.
		getFakeTestRunner().run(runNotifier);
		// If the test is not finished with expected failures.
		if ((!runListener.getStarted()) || (!runListener.getFinished()) || (runListener.getFailure() == null)
				|| ((runListener.getFailure().getException() instanceof AssertionFailedError))) {
			// The test fails. FIXME
			LocalizedAssert.fail();
		}
	}
}
