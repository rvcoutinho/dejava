package org.dejava.component.test.exception.parametric;

import java.util.ArrayList;
import java.util.Collection;

import org.dejava.component.test.exception.AbstractTestException;
import org.dejava.component.test.exception.parametric.atomic.AtomParametricTestAssertionError;
import org.dejava.component.test.exception.parametric.atomic.AtomParametricTestException;

/**
 * Exception related to a parametric test exceptions (and/or assertion errors).
 */
public class ParametricTestException extends AbstractTestException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 2854622415815735145L;
	
	/**
	 * Exceptions thrown during a parametric test.
	 */
	private Collection<AtomParametricTestException> testExceptions;
	
	/**
	 * Gets the exceptions thrown during a parametric test.
	 * 
	 * @return The exceptions thrown during a parametric test.
	 */
	public Collection<AtomParametricTestException> getTestExceptions() {
		// If the collection is null.
		if (testExceptions == null) {
			// It is now a new list.
			testExceptions = new ArrayList<AtomParametricTestException>();
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
	public void setTestExceptions(final Collection<AtomParametricTestException> testExceptions) {
		this.testExceptions = testExceptions;
	}
	
	/**
	 * Assumption violated exceptions thrown during a parametric test.
	 */
	private Collection<AtomParametricTestAssertionError> testAssumptionViolations;
	
	/**
	 * Gets the assumption violated exceptions thrown during a parametric test.
	 * 
	 * @return The assumption violated exceptions thrown during a parametric test.
	 */
	public Collection<AtomParametricTestAssertionError> getTestAssumptionViolations() {
		// If the collection is null.
		if (testAssumptionViolations == null) {
			// It is now a new list.
			testAssumptionViolations = new ArrayList<AtomParametricTestAssertionError>();
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
			final Collection<AtomParametricTestAssertionError> testAssumptionViolations) {
		this.testAssumptionViolations = testAssumptionViolations;
	}
	
	/**
	 * Default constructor.
	 * 
	 * @param testExceptions
	 *            Exceptions thrown during a parametric test.
	 * @param testAssumptionViolations
	 *            Assumption violated exceptions thrown during a parametric test.
	 * @param testName
	 *            The name of the test for the exception.
	 */
	public ParametricTestException(final Collection<AtomParametricTestException> testExceptions,
			final Collection<AtomParametricTestAssertionError> testAssumptionViolations,
			final String testName) {
		super(null, null, testName);
		// Sets the basic fields.
		this.testExceptions = testExceptions;
		this.testAssumptionViolations = testAssumptionViolations;
	}
}