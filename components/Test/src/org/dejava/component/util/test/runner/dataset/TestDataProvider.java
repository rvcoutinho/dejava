package org.dejava.component.util.test.runner.dataset;

import java.util.Collection;

import org.dejava.component.util.test.exception.UnavailableTestDataException;
import org.dejava.component.util.test.runner.ParametricJUnitRunner;
import org.dejava.component.util.test.runner.statement.ParametricTestMethodInvoker;
import org.junit.runners.model.FrameworkMethod;

/**
 * Defines how test data must be provided for the {@link ParametricJUnitRunner} and
 * {@link ParametricTestMethodInvoker}.
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
	Collection<?> getTestData(final Object targetTest, final FrameworkMethod testMethod)
			throws UnavailableTestDataException;
}
