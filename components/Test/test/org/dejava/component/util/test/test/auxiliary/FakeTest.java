package org.dejava.component.util.test.test.auxiliary;

import junit.framework.TestCase;

import org.dejava.component.util.exception.localized.BusinessRuleException;
import org.dejava.component.util.test.runner.ParametricJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Fake org.dejava.component.util.test.test class to be used in the org.dejava.component.util.test.test framework tests.
 */
@RunWith(value = ParametricJUnitRunner.class)
public class FakeTest extends TestCase {
	
	/**
	 * Simulates a normal org.dejava.component.util.test.test success.
	 */
	@Test
	public void testNormalSuccess() {
		// Success...
	}
	
	/**
	 * Simulates a normal assumption failure org.dejava.component.util.test.test.
	 * 
	 */
	@Test
	public void testNormalAssumptionFailure() {
		fail("testNormalAssumptionFailure");
	}
	
	/**
	 * Simulates a org.dejava.component.util.test.test failure.
	 * 
	 * @throws BusinessRuleException
	 *             A business rule exception.
	 */
	@Test
	public void testNormalFailure() throws BusinessRuleException {
		throw new BusinessRuleException("testNormalFailure", null, null);
	}
	
}
