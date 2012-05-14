package org.dejava.component.util.reflection.test.annotation;

import org.dejava.component.util.exception.localized.EmptyParameterException;
import org.dejava.component.util.reflection.handler.AnnotationHandler;
import org.dejava.component.util.reflection.test.annotation.auxiliary.AnnotatedClass;
import org.dejava.component.util.reflection.test.annotation.auxiliary.AnnotationK;
import org.dejava.component.util.test.annotation.MultiDataTest;
import org.dejava.component.util.test.exception.InvalidTestStepException;
import org.dejava.component.util.test.exception.TestStepException;
import org.dejava.component.util.test.model.TestCase;
import org.dejava.component.util.test.model.TestSet;
import org.dejava.component.util.test.runner.MultiDataJUnitRunner;
import org.junit.runner.RunWith;

/**
 * Test set for AnnotationHandler methods.
 */
@RunWith(MultiDataJUnitRunner.class)
public class AnnotationHandlerTestSet extends TestSet {
	
	/**
	 * Tests the method getAnnotation with a null class as parameter value.
	 * 
	 * @param testCase
	 *            Test case configuration information.
	 * @throws TestStepException
	 *             TODO
	 * @throws InvalidTestStepException
	 *             TODO
	 */
	@MultiDataTest
	public void testGetAnnotationWithNullClass(final TestCase testCase) throws InvalidTestStepException,
			TestStepException {
		// Run steps of this test case.
		runSteps(testCase);
	}
	
	/**
	 * Tests the method getAnnotation with a null annotation as parameter value.
	 * 
	 * @param testCase
	 *            Test case configuration information.
	 * @throws TestStepException
	 *             TODO
	 * @throws InvalidTestStepException
	 *             TODO
	 */
	@MultiDataTest
	public void testGetAnnotationWithNullAnnotation(TestCase testCase) throws InvalidTestStepException,
			TestStepException {
		// Run steps of this test case.
		runSteps(testCase);
	}
	
	/**
	 * Compare if the returned annotation has the expected value.
	 * 
	 * @param annotatedClass
	 *            Class with the annotation to be tested.
	 * @throws EmptyParameterException
	 *             If the parameters for the test are invalid.
	 * @throws TestStepException
	 *             TODO
	 */
	public void runStepTestGetAnnotationWithValidClassAndAnnotation(AnnotatedClass annotatedClass)
			throws EmptyParameterException, TestStepException {
		// Calls the method to test.
		AnnotationK annotation = AnnotationHandler.getAnnotation(annotatedClass.getAnnotatedClass(),
				AnnotationK.class);
		// If the annotation was not found or does not have the expected value.
		if ((annotation == null) || (!annotation.anything().equals(annotatedClass.getAnnotationValue()))) {
			// Throws a test method exception.
			throw new TestStepException("test.step.method.return.unexpected", null, null);
		}
	}
	
	/**
	 * Tests the method getAnnotation with a valid class and annotation.
	 * 
	 * @param testCase
	 *            Test case configuration information.
	 * @throws TestStepException
	 *             TODO
	 * @throws InvalidTestStepException
	 *             TODO
	 */
	@MultiDataTest
	public void testGetAnnotationWithValidClassAndAnnotation(TestCase testCase)
			throws InvalidTestStepException, TestStepException {
		// Run steps of this test case.
		runSteps(testCase);
	}
}