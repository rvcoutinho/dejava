package org.dejava.component.util.test.runner.notifier;

import java.util.Collection;

import org.dejava.component.util.test.exception.parametric.atomic.ParametricTestAssumptionException;
import org.dejava.component.util.test.exception.parametric.atomic.ParametricTestNonAssumptionException;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

/**
 * TODO
 */
public class ParametricTestNotifier {
	
	/**
	 * Test run notifier.
	 */
	private RunNotifier notifier;
	
	/**
	 * Gets the test run notifier.
	 * 
	 * @return The test run notifier.
	 */
	public RunNotifier getNotifier() {
		return notifier;
	}
	
	/**
	 * Sets the test run notifier.
	 * 
	 * @param notifier
	 *            New test run notifier.
	 */
	public void setNotifier(final RunNotifier notifier) {
		this.notifier = notifier;
	}
	
	/**
	 * Test description.
	 */
	private Description description;
	
	/**
	 * Gets the test description.
	 * 
	 * @return The test description.
	 */
	public Description getDescription() {
		return description;
	}
	
	/**
	 * Sets the test description.
	 * 
	 * @param description
	 *            New test description.
	 */
	public void setDescription(final Description description) {
		this.description = description;
	}
	
	/**
	 * Default constructor.
	 * 
	 * @param notifier
	 *            Test run notifier.
	 * @param description
	 *            Test description.
	 */
	public ParametricTestNotifier(final RunNotifier notifier, final Description description) {
		this.notifier = notifier;
		this.description = description;
	}
	
	/**
	 * Add a failure to a test.
	 * 
	 * @param testException
	 *            The exception thrown during the test run.
	 */
	public void addFailure(final Throwable testException) {
		// Fire a new failure for the given test exception.
		getNotifier().fireTestFailure(new Failure(getDescription(), testException));
	}
	
	/**
	 * Adds any number of failures to a test.
	 * 
	 * @param testExceptions
	 *            The exceptions thrown during the test run.
	 */
	public void addFailures(final Collection<ParametricTestNonAssumptionException> testExceptions) {
		// For each test exception.
		for (final ParametricTestNonAssumptionException currentTestException : testExceptions) {
			// Fire a new failure for the current test exception.
			addFailure(currentTestException);
		}
	}
	
	/**
	 * Adds aa failed assumption to a test.
	 * 
	 * @param testFailedAssumption
	 *            The failed assumption that occurred during the test run.
	 */
	public void addFailedAssumption(final Throwable testFailedAssumption) {
		// Fire a new fail assumption for the given test exception.
		getNotifier().fireTestAssumptionFailed(new Failure(getDescription(), testFailedAssumption));
	}
	
	/**
	 * Adds any number of failed assumptions to a test.
	 * 
	 * @param testFailedAssumptions
	 *            The failed assumptions that occurred during the test run.
	 */
	public void addFailedAssumptions(final Collection<ParametricTestAssumptionException> testFailedAssumptions) {
		// For each test exception.
		for (final ParametricTestAssumptionException currentTestException : testFailedAssumptions) {
			// Fire a new fail assumption for the current test exception.
			addFailedAssumption(currentTestException);
		}
	}
	
	/**
	 * @see RunNotifier#fireTestFinished(Description)
	 */
	public void fireTestFinished() {
		getNotifier().fireTestFinished(getDescription());
	}
	
	/**
	 * @see RunNotifier#fireTestStarted(Description)
	 */
	public void fireTestStarted() {
		getNotifier().fireTestStarted(getDescription());
	}
	
	/**
	 * @see RunNotifier#fireTestIgnored(Description)
	 */
	public void fireTestIgnored() {
		getNotifier().fireTestIgnored(getDescription());
	}
}
