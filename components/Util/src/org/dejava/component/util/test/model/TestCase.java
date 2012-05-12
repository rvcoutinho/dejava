package org.dejava.component.util.test.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents a test case.
 */
public class TestCase {
	
	/**
	 * Name of the test case.
	 */
	private String name;
	
	/**
	 * Returns the name of the test case.
	 * 
	 * @return The name of the test case.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the test case.
	 * 
	 * @param name
	 *            New name of the test case.
	 */
	public void setName(final String name) {
		this.name = name;
	}
	
	/**
	 * Description of the test case.
	 */
	private String description;
	
	/**
	 * Returns the description of the test case.
	 * 
	 * @return The description of the test case.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description of the test case.
	 * 
	 * @param description
	 *            New description of the test case.
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
	
	/**
	 * Steps of the test case.
	 */
	private List<TestStep> steps;
	
	/**
	 * Returns the steps of the test case.
	 * 
	 * @return The steps of the test case.
	 */
	public List<TestStep> getSteps() {
		// If steps is null.
		if (steps == null) {
			// Steps is an empty list.
			steps = new ArrayList<TestStep>();
		}
		// Returns steps.
		return steps;
	}
	
	/**
	 * Sets the steps of the test case.
	 * 
	 * @param steps
	 *            New steps of the test case.
	 */
	public void setSteps(final List<TestStep> steps) {
		this.steps = steps;
	}
	
	/**
	 * Data to be used in the test case.
	 */
	private Collection<Object> data;
	
	/**
	 * Returns the data to be used in the test case.
	 * 
	 * @return The data to be used in the test case.
	 */
	public Collection<Object> getData() {
		// If data is null.
		if (data == null) {
			// Data is an empty list.
			data = new ArrayList<Object>();
		}
		// Returns data.
		return data;
	}
	
	/**
	 * Sets the data to be used in the test case.
	 * 
	 * @param data
	 *            New data to be used in the test case.
	 */
	public void setData(final Collection<Object> data) {
		this.data = data;
	}
}
