package org.dejava.component.util.test.runner.statement;

import org.dejava.component.util.test.exception.SingleParametricTestException;
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
	}
	
	/**
	 * @see Statement#evaluate()
	 */
	@Override
	public void evaluate() throws Throwable {
		// Tries to evaluate the current statement.
		try {
			getTestStatement().evaluate();
		}
		// If any exception is raised by the current test statement.
		catch (final Throwable throwable) {
			// Wraps the exception with a single parametric test exception.
			throw new SingleParametricTestException(throwable, getTestMethod().getName(), getParamsValues());
		}
	}
}