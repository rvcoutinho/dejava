package org.dejava.component.util.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.dejava.component.util.test.runner.ParametricJUnitRunner;
import org.dejava.component.util.test.runner.dataset.TestDataProvider;
import org.dejava.component.util.test.runner.dataset.impl.XMLTestDataProvider;
import org.junit.Test.None;

/**
 * Parametric data test. This annotation tells the JUnit (with the proper Runner -
 * {@link ParametricJUnitRunner}) to run the annotated test multiple times by injecting each of the items in
 * the Collection returned by the test data provider.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface ParametricTest {
	
	/**
	 * The test data provider for multiple data test.
	 */
	Class<? extends TestDataProvider> dataProvider() default XMLTestDataProvider.class;
	
	/**
	 * The parameters to instantiate the test data provider.
	 * 
	 * The data provider must have a constructor with the same number of String arguments given.
	 */
	String[] paramsValues() default {};
	
	/**
	 * The maximum number of test data objects that must be used in the test.
	 * 
	 * If the provided data quantity is larger than the given number, only a subset of the test data (having
	 * the exact defined size) will be used. This subset will be random.
	 * 
	 * If the maximum is 0, all data will be used.
	 */
	int maxTestData() default 0;
	
	/**
	 * Exception class that is expected to be raised during the test method execution.
	 */
	Class<? extends Throwable> expectedExceptionClass() default None.class;
	
	/**
	 * Exception message that is expected during the test method execution. Will only be used if an expected
	 * exception class is given.
	 */
	String expectedExceptionMessage() default "";
	
	/**
	 * Timeout in milliseconds to cause a test method to fail if it takes longer than that number of
	 * milliseconds.
	 */
	int timeout() default 0;
}
