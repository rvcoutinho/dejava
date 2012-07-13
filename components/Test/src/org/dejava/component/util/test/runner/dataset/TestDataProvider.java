package org.dejava.component.util.test.runner.dataset;

import java.util.Collection;

import org.dejava.component.util.test.exception.UnavailableTestDataException;
import org.dejava.component.util.test.runner.JUnitParametricRunner;
import org.dejava.component.util.test.runner.statement.ParametricTestMethodInvoker;
import org.junit.runners.model.FrameworkMethod;

/**
 * Defines how test data must be provided for the {@link JUnitParametricRunner} and
 * {@link ParametricTestMethodInvoker}.
 */
public interface TestDataProvider {
	
	/**
	 * Gets the test data from the appropriate source.
	 * 
	 * @param testMethod
	 *            The JUnit framework method.
	 * @return The test data from the appropriate source.
	 * @throws UnavailableTestDataException
	 *             If the test data provider cannot be created.
	 */
	Collection<?> getTestData(final FrameworkMethod testMethod) throws UnavailableTestDataException;
}
