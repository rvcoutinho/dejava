package org.dejava.component.test.runner.dataset;

import java.util.Collection;

import org.dejava.component.test.exception.parametric.InvalidParametricTestException;
import org.dejava.component.test.runner.JUnitParametricRunner;
import org.dejava.component.test.runner.statement.ParametricTestMethodInvoker;
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
	 * @throws InvalidParametricTestException
	 *             If the test data provider cannot be created.
	 */
	Collection<?> getTestData(final FrameworkMethod testMethod) throws InvalidParametricTestException;
}
