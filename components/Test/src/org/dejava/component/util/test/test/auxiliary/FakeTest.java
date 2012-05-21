package org.dejava.component.util.test.test.auxiliary;

import junit.framework.TestCase;

import org.dejava.component.util.test.runner.MultiDataJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Fake test class to be used in the test framework tests.
 */
@RunWith(value = MultiDataJUnitRunner.class)
public class FakeTest extends TestCase {

	/**
	 * Simulates a normal test failure.
	 */
	@Test
	public void testNormalFailure() {
		fail("testNormalFailure");
	}

	/**
	 * Simulates a normal test success.
	 */
	@Test
	public void testNormalSuccess() {
	}

	/**
	 * Simulates a normal test error.
	 * 
	 * @throws Exception
	 *             A exception.
	 */
	@Test
	public void testNormalError() throws Exception {
		throw new Exception("testNormalError");
	}
}
