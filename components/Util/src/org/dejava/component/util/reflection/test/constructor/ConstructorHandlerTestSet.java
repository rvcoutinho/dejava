package org.dejava.component.util.reflection.test.constructor;

import org.dejava.component.util.test.exception.InvalidTestStepException;
import org.dejava.component.util.test.exception.TestStepException;
import org.dejava.component.util.test.model.TestCase;
import org.dejava.component.util.test.model.TestSet;
import org.dejava.component.util.test.runner.MultiDataJUnitRunner;
import org.junit.runner.RunWith;

/**
 * Test set for ConstructorHandler methods.
 */
@RunWith(MultiDataJUnitRunner.class)
public class ConstructorHandlerTestSet extends TestSet {
	
	/**
	 * TODO
	 * 
	 * @param testCase
	 *            Test case configuration information.
	 * @throws TestStepException TODO
	 * @throws InvalidTestStepException TODO
	 */
	public void testGetConstructorWithNullClass(TestCase testCase) throws InvalidTestStepException, TestStepException {
		// Run steps of this test case.
		runSteps(testCase);
	}
	
	/**
	 * TODO
	 * 
	 * @param testCase
	 *            Test case configuration information.
	 * @throws TestStepException TODO
	 * @throws InvalidTestStepException TODO
	 */
	public void testInvokeConstructorWithNullClassAndValidParametersClasses(TestCase testCase) throws InvalidTestStepException, TestStepException {
		// Run steps of this test case.
		runSteps(testCase);
	}
	
	/**
	 * TODO
	 * 
	 * @param testCase
	 *            Test case configuration information.
	 * @throws TestStepException TODO
	 * @throws InvalidTestStepException TODO
	 */
	public void testInvokeConstructorWithNullClassAndValidParametersValues(TestCase testCase) throws InvalidTestStepException, TestStepException {
		// Run steps of this test case.
		runSteps(testCase);
	}
}
