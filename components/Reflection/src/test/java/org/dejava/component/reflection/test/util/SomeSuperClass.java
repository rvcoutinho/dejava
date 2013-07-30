package org.dejava.component.reflection.test.util;

/**
 * Some super class (to be used in tests).
 */
public class SomeSuperClass {

	/**
	 * Fields names.
	 */
	public static final String[] FIELDS_NAMES = { "FIELDS_NAMES", "fieldA", "fieldB", "fieldD", "fieldX",
			"fieldY" };

	/**
	 * Some field.
	 */
	private Object fieldA;

	/**
	 * Some field.
	 */
	private Object fieldB;

	/**
	 * Some field.
	 */
	@SomeAnnotation
	private Object fieldD;

	/**
	 * Some field.
	 */
	@SomeAnnotation
	private Object fieldX;

	/**
	 * Some field.
	 */
	@SomeOtherAnnotation
	private Object fieldY;

	/**
	 * Some method.
	 * 
	 * @return Returns "A".
	 */
	public String methodA() {
		return "A";
	}

	/**
	 * Some method.
	 * 
	 * @return Returns "B".
	 */
	protected static String methodB() {
		return "B";
	}

	/**
	 * Some method.
	 * 
	 * @return Returns "C".
	 */
	@SuppressWarnings("unused")
	private final String methodC() {
		return "C";
	}

	/**
	 * Some method.
	 * 
	 * @return Returns "D".
	 */
	String methodD() {
		return "D";
	}

	/**
	 * Some method.
	 * 
	 * @return Returns "I".
	 */
	public String methodI() {
		return "I";
	}

	/**
	 * Some method.
	 * 
	 * @return Returns "J".
	 */
	public String methodJ() {
		return "J";
	}

}
