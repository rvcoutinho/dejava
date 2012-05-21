package org.dejava.component.util.test.test;

import junit.framework.TestCase;

import org.dejava.component.util.test.runner.MultiDataJUnitRunner;
import org.dejava.component.util.test.test.auxiliary.FakeTest;
import org.dejava.component.util.test.test.auxiliary.SuccessRunListener;
import org.junit.Test;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

/**
 * Test cases for the MultiDataJUnitRunner.
 */
public class MultiDataJUnitRunnerTest extends TestCase {

	/**
	 * Returns an instance of the {@link MultiDataJUnitRunner} for the
	 * {@link FakeTest} that will be used to test the TestRunner.
	 * 
	 * @return An instance of the {@link MultiDataJUnitRunner} for the
	 *         {@link FakeTest} that will be used to test the TestRunner.
	 * @throws InitializationError
	 *             If the test runner cannot me initialized for the
	 *             {@link FakeTest}.
	 */
	private MultiDataJUnitRunner getFakeTestRunner() throws InitializationError {
		// Returns a new instance of the runner for the FakeTest.
		return new MultiDataJUnitRunner(FakeTest.class);
	}

	/**
	 * TODO
	 * 
	 * @throws InitializationError
	 *             TODO
	 */
	@Test
	public void testTestNormalFailure() throws InitializationError {
		// Creates a new notifier.
		RunNotifier runNotifier = new RunNotifier();
		// Creates a listener for the FakeTest.
		SuccessRunListener runListener = new SuccessRunListener(
				"testNormalSuccess");
		// Adds the listener to the notifier.
		runNotifier.addListener(runListener);
		// Tries to run the tests.
		getFakeTestRunner().run(runNotifier);
		// If the test did not finished as expected.
		if (!runListener.getAsExpected()) {
			// The test fails. FIXME
			fail();
		}
	}
}
