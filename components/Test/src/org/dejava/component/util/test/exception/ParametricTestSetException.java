package org.dejava.component.util.test.exception;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.internal.AssumptionViolatedException;

/**
 * Exception related to unavailable test data.
 */
public class ParametricTestSetException extends AbstractTestException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -7501622556068793489L;
	
	/**
	 * Exceptions thrown during a parametric test.
	 */
	private Collection<Throwable> testExceptions;
	
	/**
	 * Gets the exceptions thrown during a parametric test.
	 * 
	 * @return The exceptions thrown during a parametric test.
	 */
	public Collection<Throwable> getTestExceptions() {
		// If the collection is null.
		if (testExceptions == null) {
			// It is now a new list.
			testExceptions = new ArrayList<Throwable>();
		}
		// Returns the exceptions.
		return testExceptions;
	}
	
	/**
	 * Sets the exceptions thrown during a parametric test.
	 * 
	 * @param testExceptions
	 *            New exceptions thrown during a parametric test.
	 */
	public void setTestExceptions(final Collection<Throwable> testExceptions) {
		this.testExceptions = testExceptions;
	}
	
	/**
	 * Assumption violated exceptions thrown during a parametric test.
	 */
	private Collection<AssumptionViolatedException> testAssumptionViolations;
	
	/**
	 * Gets the assumption violated exceptions thrown during a parametric test.
	 * 
	 * @return The assumption violated exceptions thrown during a parametric test.
	 */
	public Collection<AssumptionViolatedException> getTestAssumptionViolations() {
		// If the collection is null.
		if (testAssumptionViolations == null) {
			// It is now a new list.
			testAssumptionViolations = new ArrayList<AssumptionViolatedException>();
		}
		// Returns the exceptions.
		return testAssumptionViolations;
	}
	
	/**
	 * Sets the assumption violated exceptions thrown during a parametric test.
	 * 
	 * @param testAssumptionViolations
	 *            New assumption violated exceptions thrown during a parametric test.
	 */
	public void setTestAssumptionViolations(
			final Collection<AssumptionViolatedException> testAssumptionViolations) {
		this.testAssumptionViolations = testAssumptionViolations;
	}
	
	/**
	 * Default constructor.
	 * 
	 * @param messageKey
	 *            Message key that describes the exception.
	 * @param testExceptions
	 *            Exceptions thrown during a parametric test.
	 * @param testAssumptionViolations
	 *            Assumption violated exceptions thrown during a parametric test.
	 * @param testName
	 *            The name of the test for the exception.
	 */
	public ParametricTestSetException(final String messageKey, final Collection<Throwable> testExceptions,
			final Collection<AssumptionViolatedException> testAssumptionViolations, final String testName) {
		super(messageKey, null, null);
		// Sets the basic fields.
		this.testExceptions = testExceptions;
		this.testAssumptionViolations = testAssumptionViolations;
	}
}
