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

	/**
	 * Some method.
	 * 
	 * @return Returns "E".
	 */
	public String methodE() {
		return "E";
	}

	/**
	 * Some method.
	 * 
	 * @return Returns "F".
	 */
	protected static String methodF() {
		return "F";
	}

	/**
	 * Some method.
	 * 
	 * @return Returns "G".
	 */
	@SuppressWarnings("unused")
	private final String methodG() {
		return "G";
	}

	/**
	 * Some method.
	 * 
	 * @return Returns "H".
	 */
	String methodH() {
		return "H";
	}

	/**
	 * Some method.
	 * 
	 * @return Returns "I".
	 */
	@Override
	public String methodI() {
		return "I";
	}

	/**
	 * Some method.
	 * 
	 * @param append
	 *            Append the string to the return string.
	 * @return Returns "J".
	 */
	public String methodJ(final String append) {
		return "J" + append;
	}

}
