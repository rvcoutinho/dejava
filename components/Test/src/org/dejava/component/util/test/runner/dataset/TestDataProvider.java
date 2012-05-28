package org.dejava.component.util.test.runner.dataset;

import java.util.Collection;

import org.dejava.component.util.test.exception.UnavailableTestDataException;
import org.dejava.component.util.test.runner.ParametricJUnitRunner;
import org.dejava.component.util.test.runner.statement.ParametricTestMethodInvoker;
import org.junit.runners.model.FrameworkMethod;

/**
 * Defines how org.dejava.component.util.test.test data must be provided for the {@link ParametricJUnitRunner} and
 * {@link ParametricTestMethodInvoker}.
 */
public interface TestDataProvider {
	
	/**
	 * Gets the org.dejava.component.util.test.test data from the appropriate source.
	 * 
	 * @param testMethod
	 *            The JUnit framework method.
	 * @return The org.dejava.component.util.test.test data from the appropriate source.
	 * @throws UnavailableTestDataException
	 *             If the org.dejava.component.util.test.test data provider cannot be created.
	 */
	Collection<?> getTestData(final FrameworkMethod testMethod) throws UnavailableTestDataException;
}
