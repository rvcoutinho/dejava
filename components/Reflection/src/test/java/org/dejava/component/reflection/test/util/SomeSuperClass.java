package org.dejava.component.reflection.test.util;

/**
 * Some super class (to be used in tests).
 */
public class SomeSuperClass {

	/**
	 * Fields names.
	 */
	public static final String[] FIELDS_NAMES = { "FIELDS_NAMES", "fieldA", "fieldB", "fieldD", "fieldX", "fieldY" };

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

}
