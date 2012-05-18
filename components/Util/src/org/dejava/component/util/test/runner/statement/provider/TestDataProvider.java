package org.dejava.component.util.test.runner.statement.provider;

import java.util.List;

import org.dejava.component.util.test.exception.UnavailableTestDataException;
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
	 * @throws UnavailableTestDataException
	 *             If the test data provider cannot be created.
	 */
	public List<?> getTestData(final Object targetTest, final FrameworkMethod testMethod)
			throws UnavailableTestDataException;
}
