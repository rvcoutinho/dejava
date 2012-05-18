package org.dejava.component.util.test.runner.statement.provider;

import org.dejava.component.util.test.runner.MultiDataJUnitRunner;
import org.dejava.component.util.test.runner.statement.InvokeMultiDataTestMethod;
import org.junit.runners.model.FrameworkMethod;

/**
 * Defines how test data must be provided for the {@link MultiDataJUnitRunner} and
 * {@link InvokeMultiDataTestMethod}.
 */
public interface TestDataProvider {
	
	/**
	 * Gets the test data from the appropriate source.
	 * 
	 * @param targetTest
	 *            The target test object for the method invocation.
	 * @param testMethod
	 *            The JUnit framework method.
	 * @return The test data from the appropriate source.
	 */
	public Iterable<?> getTestData(final Object targetTest, final FrameworkMethod testMethod);
}
