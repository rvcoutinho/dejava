package org.dejava.component.util.reflection;

import java.lang.reflect.Field;

import org.dejava.component.util.exception.localized.unchecked.EmptyParameterException;

/**
 * TODO
 */
public class FieldMirror {
	
	/**
	 * Field being reflected.
	 */
	private Field reflectedField;
	
	/**
	 * Gets the field being reflected.
	 * 
	 * @return The field being reflected.
	 */
	public Field getReflectedField() {
		return reflectedField;
	}
	
	/**
	 * Sets the field being reflected.
	 * 
	 * @param reflectedField
	 *            New field being reflected.
	 */
	public void setReflectedField(final Field reflectedField) {
		this.reflectedField = reflectedField;
	}
	
	/**
	 * Public constructor for the reflection class.
	 * 
	 * @param reflectedField
	 *            Field being reflected.
	 * @throws EmptyParameterException
	 *             If the reflected field is not given.
	 */
	public FieldMirror(final Field reflectedField) throws EmptyParameterException {
		// If the field is null.
		if (reflectedField == null) {
			// Throws an exception.
			throw new EmptyParameterException(1);
		}
		// Sets the main reflection fields.
		this.reflectedField = reflectedField;
	}
	
	/**
	 * The field getter prefix.
	 */
	public static final String GETTER_PREFIX = "get";
	
	/**
	 * Get the getter name for a field.
	 * 
	 * @return The getter name for a field.
	 */
	private String getGetterName() {
		// Append the "get" followed by the field name (with upper case first letter).
		return GETTER_PREFIX + getReflectedField().getName().substring(0, 1).toUpperCase()
				+ getReflectedField().getName().substring(1);
	}
	
	/**
	 * The boolean field getter prefix.
	 */
	public static final String BOOL_GETTER_PREFIX = "is";
	
	/**
	 * Get the getter name for a boolean field.
	 * 
	 * @param fieldName
	 *            Name of the field to get the getter name.
	 * @return The getter name for a boolean field.
	 */
	private static String getBooleanGetterName(final String fieldName) {
		// Append the "get" followed by the field name (with upper case first letter).
		return BOOL_GETTER_PREFIX + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
	
	/**
	 * The field setter prefix.
	 */
	public static final String SETTER_PREFIX = "set";
	
	/**
	 * Get the setter name for a field.
	 * 
	 * @return The setter name for a field.
	 */
	private String getSetterName() {
		// Append the "set" followed by the field name (with upper case first letter).
		return SETTER_PREFIX + getReflectedField().getName().substring(0, 1).toUpperCase()
				+ getReflectedField().getName().substring(1);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reflectedField == null) ? 0 : reflectedField.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FieldMirror other = (FieldMirror) obj;
		if (reflectedField == null) {
			if (other.reflectedField != null)
				return false;
		}
		else if (!reflectedField.equals(other.reflectedField))
			return false;
		return true;
	}
	
}
