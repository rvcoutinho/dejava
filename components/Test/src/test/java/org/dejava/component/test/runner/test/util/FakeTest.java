package org.dejava.component.test.runner.test.util;

import java.util.Arrays;
import java.util.List;

import org.dejava.component.exception.localized.checked.BusinessRuleException;
import org.dejava.component.test.annotation.ParametricTest;
import org.dejava.component.test.runner.JUnitParametricRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Fake test class to be used in the test framework tests.
 */
@RunWith(value = JUnitParametricRunner.class)
public class FakeTest {
	
	/**
	 * Simulates a regular test success.
	 */
	@Test
	public void testRegularSuccess() {
		// Success...
	}
	
	/**
	 * Simulates a regular assumption failure test.
	 * 
	 */
	@Test
	public void testRegularAssumptionFailure() {
		Assert.fail("testRegularAssumptionFailure");
	}
	
	/**
	 * Simulates a regular test failure.
	 * 
	 * @throws BusinessRuleException
	 *             A business rule exception.
	 */
	@Test
	public void testRegularFailure() throws BusinessRuleException {
		throw new BusinessRuleException("testRegularFailure", null, null);
	}
	
	/**
	 * Simulates a regular timeout failure.
	 * 
	 * @throws InterruptedException
	 *             If the thread is interrupted while waiting.
	 */
	@Test(timeout = 100000)
	public void testRegularTimeoutFailure() throws InterruptedException {
		Thread.currentThread().wait(200000);
	}
	
	/**
	 * Simulates a expected exception test.
	 * 
	 * @throws BusinessRuleException
	 *             A business rule exception.
	 */
	@Test(expected = BusinessRuleException.class)
	public void testRegularExceptedException() throws BusinessRuleException {
		throw new BusinessRuleException("testRegularExceptedException", null, null);
	}
	
	/**
	 * Gets the parametric success test data.
	 * 
	 * @return The parametric success test data.
	 */
	public static List<Integer> testParametricSuccessData() {
		return Arrays.asList(new Integer[] { -1, -2, -3, -4, -5 });
	}
	
	/**
	 * Simulates a parametric test success.
	 * 
	 * @param number
	 *            Any number.
	 */
	@ParametricTest
	public void testParametricSuccess(final Integer number) {
		// If the number greater than 0.
		if (number > 0) {
			// Fails the test.
			Assert.fail("testParametricSuccess");
		}
	}
}
