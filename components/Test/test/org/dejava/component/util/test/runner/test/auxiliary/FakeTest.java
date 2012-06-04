package org.dejava.component.util.test.runner.test.auxiliary;

import org.dejava.component.util.exception.localized.BusinessRuleException;
import org.dejava.component.util.test.runner.ParametricJUnitRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Fake test class to be used in the test framework tests.
 */
@RunWith(value = ParametricJUnitRunner.class)
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
}
