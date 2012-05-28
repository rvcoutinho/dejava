package org.dejava.component.util.test.test.auxiliary;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/**
 * Logs info about a org.dejava.component.util.test.test run.
 */
public class RunListenerLog extends RunListener {
	
	/**
	 * Test method name that must run successfully.
	 */
	private String testMethodName;
	
	/**
	 * Gets the org.dejava.component.util.test.test method name that must run successfully.
	 * 
	 * @return The org.dejava.component.util.test.test method name that must run successfully.
	 */
	public String getTestMethodName() {
		return testMethodName;
	}
	
	/**
	 * Sets the org.dejava.component.util.test.test method name that must run successfully.
	 * 
	 * @param testMethodName
	 *            New org.dejava.component.util.test.test method name that must run successfully.
	 */
	public void setTestMethodName(final String testMethodName) {
		this.testMethodName = testMethodName;
	}
	
	/**
	 * If the org.dejava.component.util.test.test for the given name has started.
	 */
	private Boolean started = false;
	
	/**
	 * Gets if the org.dejava.component.util.test.test for the given name has started.
	 * 
	 * @return If the org.dejava.component.util.test.test for the given name has started.
	 */
	public Boolean getStarted() {
		return started;
	}
	
	/**
	 * Sets if the org.dejava.component.util.test.test for the given name has started.
	 * 
	 * @param started
	 *            If the org.dejava.component.util.test.test for the given name has started.
	 */
	public void setStarted(final Boolean started) {
		this.started = started;
	}
	
	/**
	 * If the org.dejava.component.util.test.test has finished (correctly or not).
	 */
	private Boolean finished = false;
	
	/**
	 * Gets if the org.dejava.component.util.test.test has finished (correctly or not).
	 * 
	 * @return If the org.dejava.component.util.test.test has finished (correctly or not).
	 */
	public Boolean getFinished() {
		return finished;
	}
	
	/**
	 * Sets if the org.dejava.component.util.test.test has finished (correctly or not).
	 * 
	 * @param finished
	 *            If the org.dejava.component.util.test.test has finished (correctly or not).
	 */
	public void setFinished(final Boolean finished) {
		this.finished = finished;
	}
	
	/**
	 * Failure information for the org.dejava.component.util.test.test (if any).
	 */
	private Failure failure;
	
	/**
	 * Gets the failure information for the org.dejava.component.util.test.test (if any).
	 * 
	 * @return The failure information for the org.dejava.component.util.test.test (if any).
	 */
	public Failure getFailure() {
		return failure;
	}
	
	/**
	 * Sets the failure information for the org.dejava.component.util.test.test (if any).
	 * 
	 * @param failure
	 *            New failure information for the org.dejava.component.util.test.test (if any).
	 */
	public void setFailure(final Failure failure) {
		this.failure = failure;
	}
	
	/**
	 * Default constructor.
	 * 
	 * @param testMethodName
	 *            The org.dejava.component.util.test.test method name that must run successfully.
	 */
	public RunListenerLog(final String testMethodName) {
		super();
		// Sets the main fields.
		this.testMethodName = testMethodName;
	}
	
	/**
	 * @see org.junit.runner.notification.RunListener#testStarted(org.junit.runner.Description)
	 */
	@Override
	public void testStarted(final Description description) throws Exception {
		// If the started org.dejava.component.util.test.test has the same given org.dejava.component.util.test.test description.
		if (description.getMethodName().equals(getTestMethodName())) {
			// Sets that the org.dejava.component.util.test.test has started.
			setStarted(true);
		}
	}
	
	/**
	 * @see org.junit.runner.notification.RunListener#testFailure(org.junit.runner.notification.Failure)
	 */
	@Override
	public void testFailure(final Failure failure) throws Exception {
		// If the failed org.dejava.component.util.test.test has the same given org.dejava.component.util.test.test description.
		if (failure.getDescription().getMethodName().equals(getTestMethodName())) {
			// Logs the failure for the org.dejava.component.util.test.test.
			setFailure(failure);
		}
	}
	
	/**
	 * @see org.junit.runner.notification.RunListener#testAssumptionFailure(org.junit.runner.notification.Failure)
	 */
	@Override
	public void testAssumptionFailure(final Failure failure) {
		// If the failed org.dejava.component.util.test.test has the same given org.dejava.component.util.test.test description.
		if (failure.getDescription().getMethodName().equals(getTestMethodName())) {
			// Logs the failure for the org.dejava.component.util.test.test.
			setFailure(failure);
		}
	}
	
	/**
	 * @see org.junit.runner.notification.RunListener#testFinished(org.junit.runner.Description)
	 */
	@Override
	public void testFinished(final Description description) throws Exception {
		// If the finished org.dejava.component.util.test.test has the same given org.dejava.component.util.test.test description.
		if (description.getMethodName().equals(getTestMethodName())) {
			// Sets that the org.dejava.component.util.test.test has finished.
			setFinished(true);
		}
	}
}
