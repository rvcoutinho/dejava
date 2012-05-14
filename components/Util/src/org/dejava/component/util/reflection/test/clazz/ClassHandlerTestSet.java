package org.dejava.component.util.reflection.test.clazz;

import org.dejava.component.util.test.annotation.MultiDataTest;
import org.dejava.component.util.test.exception.InvalidTestStepException;
import org.dejava.component.util.test.exception.TestStepException;
import org.dejava.component.util.test.model.TestCase;
import org.dejava.component.util.test.model.TestSet;
import org.dejava.component.util.test.runner.MultiDataJUnitRunner;
import org.junit.runner.RunWith;

/**
 * Test set for ClassHandler methods.
 */
@RunWith(MultiDataJUnitRunner.class)
public class ClassHandlerTestSet extends TestSet {
	
	/**
	 * Tests the method getSuperclass with a null class as parameter value.
	 * 
	 * @param testCase
	 *            Test case configuration information.
	 * @throws TestStepException  TODO
	 * @throws InvalidTestStepException  TODO
	 */
	@MultiDataTest
	public void testGetSuperclassesWithNullClass(TestCase testCase) throws InvalidTestStepException, TestStepException {
		// Run steps of this test case.
		runSteps(testCase);
	}
	
	/**
	 * Tests the method getSuperclass with valid parameters.
	 * 
	 * @param testCase
	 *            Test case configuration information.
	 * @throws TestStepException  TODO
	 * @throws InvalidTestStepException  TODO
	 */
	@MultiDataTest
	public void testGetSuperclassesWithValidClass(TestCase testCase) throws InvalidTestStepException, TestStepException {
		// Run steps of this test case.
		runSteps(testCase);
	}
	
	/**
	 * Tests the method getCurrentClassName.
	 * 
	 * @param testCase
	 *            Test case configuration information.
	 * @throws TestStepException TODO
	 * @throws InvalidTestStepException TODO
	 */
	@MultiDataTest
	public void testGetCurrentClassNameWithValidDepth(TestCase testCase) throws InvalidTestStepException, TestStepException {
		// Run steps of this test case.
		runSteps(testCase);
	}
	
	/**
	 * Tests the method getCurrentClass.
	 * 
	 * @param testCase
	 *            Test case configuration information.
	 * @throws TestStepException TODO
	 * @throws InvalidTestStepException TODO
	 */
	@MultiDataTest
	public void testGetCurrentClassWithValidDepth(TestCase testCase) throws InvalidTestStepException, TestStepException {
		// Run steps of this test case.
		runSteps(testCase);
	}
	
	/**
	 * Tests the method getCurrentClass giving a class loader.
	 * 
	 * @param testCase
	 *            Test case configuration information.
	 * @throws TestStepException TODO
	 * @throws InvalidTestStepException TODO
	 */
	@MultiDataTest
	public void testGetCurrentClassWithValidDepthAndClassLoader(TestCase testCase) throws InvalidTestStepException, TestStepException {
		// Run steps of this test case.
		runSteps(testCase);
	}
	
	/**
	 * Tests the method getClass with a null name.
	 * 
	 * @param testCase
	 *            Test case configuration information.
	 * @throws TestStepException TODO
	 * @throws InvalidTestStepException TODO
	 */
	@MultiDataTest
	public void testGetClassWithNullName(TestCase testCase) throws InvalidTestStepException, TestStepException {
		// Run steps of this test case.
		runSteps(testCase);
	}
	
	/**
	 * Tests the method getClass with invalid name.
	 * 
	 * @param testCase
	 *            Test case configuration information.
	 * @throws TestStepException TODO
	 * @throws InvalidTestStepException TODO
	 */
	@MultiDataTest
	public void testGetClassWithInvalidName(TestCase testCase) throws InvalidTestStepException, TestStepException {
		// Run steps of this test case.
		runSteps(testCase);
	}
	
	/**
	 * Tests the method getClass with valid name.
	 * 
	 * @param testCase
	 *            Test case configuration information.
	 * @throws TestStepException TODO
	 * @throws InvalidTestStepException TODO
	 */
	@MultiDataTest
	public void testGetClassWithValidName(TestCase testCase) throws InvalidTestStepException, TestStepException {
		// Run steps of this test case.
		runSteps(testCase);
	}
	
	/**
	 * Tests the method getClass with valid name and class loader.
	 * 
	 * @param testCase
	 *            Test case configuration information.
	 * @throws TestStepException TODO
	 * @throws InvalidTestStepException TODO
	 */
	@MultiDataTest
	public void testGetClassWithValidNameAndClassLoader(TestCase testCase) throws InvalidTestStepException, TestStepException {
		// Run steps of this test case.
		runSteps(testCase);
	}
}