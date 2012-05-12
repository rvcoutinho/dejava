package org.dejava.component.util.test.model;

/**
 * Test step in a test case.
 */
public class TestStep {
	
	/**
	 * Name of the test step.
	 */
	private String name;
	
	/**
	 * Gets the name of the test step.
	 * 
	 * @return The name of the test step.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the test step.
	 * 
	 * @param name
	 *            New name of the test step.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Description of the test step.
	 */
	private String description;
	
	/**
	 * Gets the description of the test step.
	 * 
	 * @return The description of the test step.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description of the test step.
	 * 
	 * @param description
	 *            New description of the test step.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Method information for the step.
	 */
	private TestStepMethod method;
	
	/**
	 * Gets the method information for the step.
	 * 
	 * @return The method information for the step.
	 */
	public TestStepMethod getMethod() {
		return method;
	}
	
	/**
	 * Sets the method information for the step.
	 * 
	 * @param method
	 *            New method information for the step.
	 */
	public void setMethod(TestStepMethod method) {
		this.method = method;
	}
}
