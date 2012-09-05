package org.dejava.component.test.runner.statement;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * Statement to invoke test methods.
 * 
 * Each test method might be invoked multiple times with the provided test data.
 */
public class ParametricTestMethodInvoker extends AbstractParametricTestStatement {
	
	/**
	 * Method name expression to be evaluated later.
	 */
	public static final String METHOD_NAME_EXPRESSION = "#{annotated_method_name}";
	
	/**
	 * @see AbstractParametricTestStatement#AbstractParametricTestStatement(Object, FrameworkMethod, Object[])
	 */
	public ParametricTestMethodInvoker(final Object targetTest, final FrameworkMethod testMethod,
			final Object[] paramsValues) {
		super(targetTest, testMethod, paramsValues);
	}
	
	/**
	 * @see Statement#evaluate()
	 */
	@Override
	public void evaluate() throws Throwable {
		// Invokes the test method with the given parameters values.
		getTestMethod().invokeExplosively(getTargetTest(), getParamsValues());
	}
}