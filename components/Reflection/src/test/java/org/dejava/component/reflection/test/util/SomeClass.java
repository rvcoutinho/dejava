package org.dejava.component.reflection.test.util;

/**
 * Some class (to be used in tests).
 */
public class SomeClass extends SomeSuperClass {

	/**
	 * Fields names.
	 */
	public static final String[] FIELDS_NAMES = { "FIELDS_NAMES", "fieldA", "fieldC", "fieldD", "fieldE",
			"fieldF" };

	/**
	 * Some field.
	 */
	private Object fieldA;

	/**
	 * Some field.
	 */
	private Object fieldC;

	/**
	 * Gets the fieldC.
	 * 
	 * @return The fieldC.
	 */
	public Object getFieldC() {
		return fieldC;
	}

	/**
	 * Sets the fieldC.
	 * 
	 * @param fieldC
	 *            New fieldC.
	 */
	public void setFieldC(final Object fieldC) {
		this.fieldC = fieldC;
	}

	/**
	 * Some field.
	 */
	@SomeAnnotation
	private Object fieldD;

	/**
	 * Some field.
	 */
	@SomeAnnotation
	private Object fieldE;

	/**
	 * Some field.
	 */
	@SomeOtherAnnotation
	private Object fieldF;

}
