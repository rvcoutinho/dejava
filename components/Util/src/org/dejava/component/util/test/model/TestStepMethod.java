package org.dejava.component.util.test.model;

import java.util.ArrayList;
import java.util.List;

import org.dejava.component.util.exception.localized.InvalidParameterException;
import org.dejava.component.util.reflection.exception.InvocationException;
import org.dejava.component.util.reflection.handler.ConstructorHandler;
import org.dejava.component.util.reflection.handler.FieldHandler;

/**
 * Holds information about the method for a test step.
 */
public class TestStepMethod {
	
	/**
	 * Class that holds the static method for the test step. If null, the test object will be used to run the
	 * step. If not, the class will be used with a static method with the provided name.
	 */
	private Class<?> staticMethodClass;
	
	/**
	 * Gets the qualified name for a class that holds the static method for the test step.
	 * 
	 * @return The qualified name for a class that holds the static method for the test step.
	 */
	public Class<?> getStaticMethodClass() {
		return staticMethodClass;
	}
	
	/**
	 * Sets the qualified name for a class that holds the static method for the test step. If null, the test
	 * object will be used to run the step. If not, the class will be used with a static method with the
	 * provided name.
	 * 
	 * @param staticMethodClass
	 *            New qualified name for a class that holds the static method for the test step.
	 */
	public void setStaticMethodClass(Class<?> staticMethodClass) {
		this.staticMethodClass = staticMethodClass;
	}
	
	/**
	 * Name of the method for test step.
	 */
	private String name;
	
	/**
	 * Gets the name of the method for test step.
	 * 
	 * @return The name of the method for test step.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the method for test step.
	 * 
	 * @param name
	 *            New name of the method for test step.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Qualified class names for the parameters of the method.
	 */
	private List<Class<?>> parametersClasses;
	
	/**
	 * Gets the qualified class names for the parameters of the method.
	 * 
	 * @return The qualified class names for the parameters of the method.
	 */
	public List<Class<?>> getParametersClasses() {
		// If the list is null.
		if (parametersClasses == null) {
			// It is a new list.
			parametersClasses = new ArrayList<Class<?>>();
		}
		// Returns the list.
		return parametersClasses;
	}
	
	/**
	 * Sets the qualified class names for the parameters of the method.
	 * 
	 * @param parametersClasses
	 *            New qualified class names for the parameters of the method.
	 */
	public void setParametersClasses(List<Class<?>> parametersClasses) {
		this.parametersClasses = parametersClasses;
	}
	
	/**
	 * Values for the parameters of the method.
	 */
	private List<Object> parametersValues;
	
	/**
	 * Gets the values for the parameters of the method.
	 * 
	 * @return The values for the parameters of the method.
	 */
	public List<Object> getParametersValues() {
		// If the list is null.
		if (parametersValues == null) {
			// It is a new list.
			parametersValues = new ArrayList<Object>();
		}
		// Returns the list.
		return parametersValues;
	}
	
	/**
	 * Returns if a value represents an expression.
	 * 
	 * @param value
	 *            Value to be checked.
	 * @return If a value represents an expression.
	 */
	private Boolean isExpression(Object value) {
		// If the value is a String.
		if (value instanceof String) {
			String stringValue = (String) value;
			// If the value is not empty.
			if ((stringValue != null) && (!stringValue.isEmpty())) {
				// If the value starts with "#{" and ands with "}".
				if ((stringValue.startsWith("#{")) && (stringValue.endsWith("}"))) {
					// Returns true.
					return true;
				}
			}
		}
		// As default, return false.
		return false;
	}
	
	/**
	 * Expression that must be evaluated as null.
	 */
	public static final String NULL_EXPRESSION = "null";
	
	/**
	 * Expression that must be evaluated as the test data object.
	 */
	public static final String TEST_DATA_OBJECT_EXPRESSION = "object";
	
	/**
	 * Evaluates a given expression and test data object.
	 * 
	 * @param expression
	 *            Expression to be evaluated.
	 * @param testDataObject
	 *            Test data object to be used in the evaluation.
	 * @return Returns the result of the evaluated expression.
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty or getter could not be accessed).
	 */
	public Object evaluateExpression(String expression, Object testDataObject)
			throws InvalidParameterException {
		// Removes the expression wrapper.
		expression = expression.substring(2, expression.length() - 1);
		// If it is an expression for a null value.
		if (NULL_EXPRESSION.equals(expression)) {
			// Returns null.
			return null;
		}
		// If the expression starts with the test data object expression.
		else if (expression.startsWith(TEST_DATA_OBJECT_EXPRESSION)) {
			// If is exactly the test data object expression.
			if (TEST_DATA_OBJECT_EXPRESSION.equalsIgnoreCase(expression)) {
				// Returns the object.
				return testDataObject;
			}
			// If not.
			else {
				// Tries to remove the beginning of the expression to resolve it as a test data object field.
				expression = expression.substring(TEST_DATA_OBJECT_EXPRESSION.length() + 1);
				// Tries to get the field value using the expression as a path to the field.
				try {
					return FieldHandler.getFieldValue(testDataObject, expression, true);
				}
				// If an exception is thrown by a getter of the test data object.
				catch (InvocationException exception) {
					// Throws an invalid parameter exception.
					throw new InvalidParameterException("test.data.object.expression.invalid", exception,
							null);
				}
			}
		}
		// Else.
		else {
			// Returns null.
			return null;
		}
	}
	
	/**
	 * Gets the real parameters values for the method. Expressions are evaluated and conversions made.
	 * 
	 * @param testDataObject
	 *            Test data object to be used in the evaluation of expressions.
	 * @return The real parameters values after evaluating expression.
	 * @throws InvalidParameterException
	 *             If the parameter is not valid (empty), or a class is not found.
	 */
	public List<Object> getActualParametersValues(Object testDataObject) throws InvalidParameterException {
		// List with actual parameters values.
		List<Object> actualParametersValues = new ArrayList<Object>();
		// For each parameter class name.
		for (Integer currentParameterIndex = 0; currentParameterIndex < getParametersClasses().size(); currentParameterIndex++) {
			// Gets the raw parameter value.
			Object actualCurrentParameterValue = getParametersValues().get(currentParameterIndex);
			// If the current parameter value is an expression.
			if (isExpression(actualCurrentParameterValue)) {
				// Evaluates the expression.
				actualCurrentParameterValue = evaluateExpression((String) actualCurrentParameterValue,
						testDataObject);
			}
			// Tries to get the current parameter class.
			Class<?> currentParameterClass = getParametersClasses().get(currentParameterIndex);
			// Tries to cast the actual current parameter value to the defined
			// class.
			try {
				actualCurrentParameterValue = currentParameterClass.cast(actualCurrentParameterValue);
			}
			// If the cast is not possible.
			catch (ClassCastException castException) {
				// Tries to get a new instance of the class using the current
				// value as parameter.
				try {
					actualCurrentParameterValue = ConstructorHandler.invokeConstructor(currentParameterClass,
							new Object[] { actualCurrentParameterValue });
				}
				// If an exception is thrown by the constructor of class.
				catch (InvocationException exception) {
					// Throws an invalid parameter exception.
					throw new InvalidParameterException("test.step.method.parameter.invalid", exception, null);
				}
			}
			// Adds the actual current parameter value to the list.
			actualParametersValues.add(actualCurrentParameterValue);
		}
		// Returns the actual parameters values.
		return actualParametersValues;
	}
	
	/**
	 * Sets the values for the parameters of the method.
	 * 
	 * @param parametersValues
	 *            New values for the parameters of the method.
	 */
	public void setParametersValues(List<Object> parametersValues) {
		this.parametersValues = parametersValues;
	}
	
	/**
	 * Expected return for the method.
	 */
	private String expectedReturn;
	
	/**
	 * Gets the expected return for the method.
	 * 
	 * @return The expected return for the method.
	 */
	public String getExpectedReturn() {
		return expectedReturn;
	}
	
	/**
	 * Gets the actual expected return for the method.
	 * 
	 * @param testDataObject
	 *            Test data object to be used in the evaluation of expressions.
	 * @return The actual expected return for the method.
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty or getter could not be accessed).
	 */
	public Object getActualExpectedReturn(Object testDataObject) throws InvalidParameterException {
		// If the current parameter value is an expression.
		if (isExpression(getExpectedReturn())) {
			// Evaluates the expression.
			return evaluateExpression(getExpectedReturn(), testDataObject);
		}
		// If it is not an expression.
		else {
			// Returns the raw value.
			return getExpectedReturn();
		}
	}
	
	/**
	 * Sets the expected return for the method.
	 * 
	 * @param expectedReturn
	 *            New expected return for the method.
	 */
	public void setExpectedReturn(String expectedReturn) {
		this.expectedReturn = expectedReturn;
	}
	
	/**
	 * Expected exception for this test step.
	 */
	private TestStepExpectedException expectedException;
	
	/**
	 * Gets the expected exception for this test step.
	 * 
	 * @return The expected exception for this test step.
	 */
	public TestStepExpectedException getExpectedException() {
		return expectedException;
	}
	
	/**
	 * Sets the expected exception for this test step.
	 * 
	 * @param expectedException
	 *            new expected exception for this test step.
	 */
	public void setExpectedException(TestStepExpectedException expectedException) {
		this.expectedException = expectedException;
	}
}
