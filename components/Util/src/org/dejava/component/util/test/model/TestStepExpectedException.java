package org.dejava.component.util.test.model;

/**
 * Test step expected exception.
 */
public class TestStepExpectedException {
	
	/**
	 * Name of the expected exception.
	 */
	private String name;
	
	/**
	 * Gets the name of the expected exception.
	 * 
	 * @return The name of the expected exception.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the expected exception.
	 * 
	 * @param name
	 *            New name of the expected exception.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Message of the expected exception.
	 */
	private String message;
	
	/**
	 * Gets the message of the expected exception.
	 * 
	 * @return The message of the expected exception.
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the message of the expected exception.
	 * 
	 * @param message
	 *            New message of the expected exception.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
