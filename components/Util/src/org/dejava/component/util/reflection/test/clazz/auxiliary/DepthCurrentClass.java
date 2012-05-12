package org.dejava.component.util.reflection.test.clazz.auxiliary;

/**
 * Object that holds information about current class and depth.
 */
public class DepthCurrentClass {
	
	/**
	 * Depth of the current class.
	 */
	private Integer depth;
	
	/**
	 * Gets the depth of the current class.
	 * 
	 * @return The depth of the current class.
	 */
	public Integer getDepth() {
		return depth;
	}
	
	/**
	 * Sets the depth of the current class.
	 * 
	 * @param depth
	 *            New depth of the current class.
	 */
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	
	/**
	 * Current class name.
	 */
	private String currentClassName;
	
	/**
	 * Gets the current class name.
	 * 
	 * @return The current class name.
	 */
	public String getCurrentClassName() {
		return currentClassName;
	}
	
	/**
	 * Sets the current class name.
	 * 
	 * @param currentClassName
	 *            new current class name.
	 */
	public void setCurrentClassName(String currentClassName) {
		this.currentClassName = currentClassName;
	}
	
	/**
	 * Current class.
	 */
	private Class<?> currentClass;
	
	/**
	 * Gets the current class.
	 * 
	 * @return The current class.
	 */
	public Class<?> getCurrentClass() {
		return currentClass;
	}
	
	/**
	 * Sets the current class.
	 * 
	 * @param currentClass
	 *            new current class.
	 */
	public void setCurrentClass(Class<?> currentClass) {
		this.currentClass = currentClass;
	}
}
