package org.dejava.component.util.test.test.auxiliary;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/**
 * TODO
 */
public class SuccessRunListener extends RunListener {

	/**
	 * Test method name that must run successfully.
	 */
	private String testMethodName;

	/**
	 * Gets the test method name that must run successfully.
	 * 
	 * @return The test method name that must run successfully.
	 */
	public String getTestMethodName() {
		return testMethodName;
	}

	/**
	 * Sets the test method name that must run successfully.
	 * 
	 * @param testMethodName
	 *            New test method name that must run successfully.
	 */
	public void setTestMethodName(String testMethodName) {
		this.testMethodName = testMethodName;
	}

	/**
	 * If the test for the given name finished as expected.
	 */
	private Boolean asExpected = true;

	/**
	 * Gets if the test for the given name finished as expected.
	 * 
	 * @return If the test for the given name finished as expected.
	 */
	public Boolean getAsExpected() {
		return asExpected;
	}

	/**
	 * Sets if the test for the given name finished as expected.
	 * 
	 * @param asExpected
	 *            If the test for the given name finished as expected.
	 */
	public void setAsExpected(Boolean asExpected) {
		this.asExpected = asExpected;
	}

	/**
	 * Default constructor.
	 * 
	 * @param testMethodName
	 *            The test method name that must run successfully.
	 */
	public SuccessRunListener(String testMethodName) {
		super();
		// Sets the main fields.
		this.testMethodName = testMethodName;
	}

	/**
	 * @see org.junit.runner.notification.RunListener#testIgnored(org.junit.runner.Description)
	 */
	@Override
	public void testIgnored(Description description) throws Exception {
		// If the ignored test has the same given test description.
		if (description.getMethodName().equals(getTestMethodName())) {
			// Sets the the test did not finished as expected.
			setAsExpected(false);
		}
	}

	/**
	 * @see org.junit.runner.notification.RunListener#testFailure(org.junit.runner.notification.Failure)
	 */
	@Override
	public void testFailure(Failure failure) throws Exception {
		// If the failed test has the same given test description.
		if (failure.getDescription().getMethodName()
				.equals(getTestMethodName())) {
			// Sets the the test did not finished as expected.
			setAsExpected(false);
		}
	}

	/**
	 * @see org.junit.runner.notification.RunListener#testAssumptionFailure(org.junit.runner.notification.Failure)
	 */
	@Override
	public void testAssumptionFailure(Failure failure) {
		// If the failed test has the same given test description.
		if (failure.getDescription().getMethodName()
				.equals(getTestMethodName())) {
			// Sets the the test did not finished as expected.
			setAsExpected(false);
		}
	}

}
