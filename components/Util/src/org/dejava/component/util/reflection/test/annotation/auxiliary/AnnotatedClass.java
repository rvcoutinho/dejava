package org.dejava.component.util.reflection.test.annotation.auxiliary;

/**
 * Auxiliary class.
 */
public class AnnotatedClass {
	
	/**
	 * Annotated class.
	 */
	private Class<?> annotatedClass;
	
	/**
	 * Gets the annotated class.
	 * 
	 * @return The annotated class.
	 */
	public Class<?> getAnnotatedClass() {
		return annotatedClass;
	}
	
	/**
	 * Sets the annotated class.
	 * 
	 * @param annotatedClass
	 *            New annotated class.
	 */
	public void setAnnotatedClass(Class<?> annotatedClass) {
		this.annotatedClass = annotatedClass;
	}
	
	/**
	 * Annotation value.
	 */
	private String annotationValue;
	
	/**
	 * Gets the annotation value.
	 * 
	 * @return The annotation value.
	 */
	public String getAnnotationValue() {
		return annotationValue;
	}
	
	/**
	 * Sets the annotation value.
	 * 
	 * @param annotationValue
	 *            New annotation value.
	 */
	public void setAnnotationValue(String annotationValue) {
		this.annotationValue = annotationValue;
	}
}
