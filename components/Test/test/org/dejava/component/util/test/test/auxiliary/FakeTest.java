package org.dejava.component.util.test.test.auxiliary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.dejava.component.util.exception.localized.BusinessRuleException;
import org.dejava.component.util.test.annotation.ParametricTest;
import org.dejava.component.util.test.runner.ParametricJUnitRunner;
import org.dejava.component.util.test.runner.dataset.impl.StaticMethodTestDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Fake org.dejava.component.util.test.test class to be used in the org.dejava.component.util.test.test
 * framework tests.
 */
@RunWith(value = ParametricJUnitRunner.class)
public class FakeTest {
	
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
		Assert.fail("testNormalAssumptionFailure");
	}
	
	/**
	 * TODO
	 * 
	 * @return d
	 */
	public static Collection<Integer> testNormalFailureData() {
		return Arrays.asList(new Integer[] { 1, 2, 3 });
	}
	
	/**
	 * Simulates a org.dejava.component.util.test.test failure.
	 * 
	 * @param number
	 *            eee
	 * 
	 * @throws BusinessRuleException
	 *             A business rule exception.
	 */
	@ParametricTest(dataProvider = StaticMethodTestDataProvider.class, maxTestData = 5)
	public void testNormalFailure(final Integer number) throws BusinessRuleException {
		throw new BusinessRuleException("testNormalFailure", null, null);
	}
	
}
