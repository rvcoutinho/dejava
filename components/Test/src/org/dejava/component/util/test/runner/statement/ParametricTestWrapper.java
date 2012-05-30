package org.dejava.component.util.test.runner.statement;

import junit.framework.AssertionFailedError;

import org.dejava.component.util.test.exception.parametric.atomic.ParametricTestAssumptionException;
import org.dejava.component.util.test.exception.parametric.atomic.ParametricTestNonAssumptionException;
import org.junit.internal.AssumptionViolatedException;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * Wrapper for parametric test statements. Its goal is to keep information on test argument values when
 * raising exceptions.
 */
public class ParametricTestWrapper extends AbstractParametricTestStatement {
	
	/**
	 * Nested test statement for the parametric test.
	 */
	private Statement testStatement;
	
	/**
	 * Gets the nested test statement for the parametric test.
	 * 
	 * @return The nested test statement for the parametric test.
	 */
	public Statement getTestStatement() {
		return testStatement;
	}
	
	/**
	 * Sets the nested test statement for the parametric test.
	 * 
	 * @param testStatement
	 *            New nested test statement for the parametric test.
	 */
	public void setTestStatement(final Statement testStatement) {
		this.testStatement = testStatement;
	}
	
	/**
	 * Default constructor.
	 * 
	 * @param testStatement
	 *            Nested test statement for the parametric test.
	 * @param targetTest
	 *            The target test object for the method invocation.
	 * @param testMethod
	 *            The JUnit framework method.
	 * @param paramsValues
	 *            The parameters values for the test method.
	 */
	public ParametricTestWrapper(final Statement testStatement, final Object targetTest,
			final FrameworkMethod testMethod, final Object[] paramsValues) {
		super(targetTest, testMethod, paramsValues);
		// Sets the basic fields values.
		this.testStatement = testStatement;
	}
	
	/**
	 * @see Statement#evaluate()
	 */
	@Override
	public void evaluate() throws ParametricTestAssumptionException, ParametricTestNonAssumptionException {
		// Tries to evaluate the current statement.
		try {
			getTestStatement().evaluate();
		}
		// If an assertion has failed for the current statement.
		catch (final AssertionError exception) {
			// Wraps the exception with a single parametric test assumption exception.
			throw new ParametricTestAssumptionException(exception, getTestMethod().getName(),
					getParamsValues());
		}
		// If an assumption is violated for the current statement.
		catch (final AssumptionViolatedException exception) {
			// Wraps the exception with a single parametric test assumption exception.
			throw new ParametricTestAssumptionException(exception, getTestMethod().getName(),
					getParamsValues());
		}
		// If any other exception is raised by the current test statement.
		catch (final Throwable throwable) {
			// Wraps the exception with a single parametric test exception.
			throw new ParametricTestNonAssumptionException(throwable, getTestMethod().getName(),
					getParamsValues());
		}
	}
}