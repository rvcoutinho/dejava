package org.dejava.component.util.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.dejava.component.util.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.util.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.util.reflection.constant.ErrorKeys;

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
	 * Gets the declaring class of the field.
	 * 
	 * @return The declaring class of the field.
	 */
	public ClassMirror<?> getDeclaringClass() {
		// Gets the declaring class of the field.
		return new ClassMirror<>(getReflectedField().getDeclaringClass());
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
	 * @return The getter name for a boolean field.
	 */
	private String getBooleanGetterName() {
		// Append the "get" followed by the field name (with upper case first letter).
		return BOOL_GETTER_PREFIX + getReflectedField().getName().substring(0, 1).toUpperCase()
				+ getReflectedField().getName().substring(1);
	}
	
	/**
	 * Gets the getter for the reflected field. TODO Think about creating field.
	 * 
	 * @return The getter for the reflected field.
	 */
	public MethodMirror getGetter() {
		// Tries to get the getter for the field.
		try {
			return getDeclaringClass().getMethod(getGetterName(), null);
		}
		// If the method was not found.
		catch (final Exception exception) {
			// If field type is boolean.
			if ((getReflectedField().getType().isAssignableFrom(Boolean.class))
					|| (getReflectedField().getType().isAssignableFrom(boolean.class))) {
				// Tries to get the boolean field getter. TODO Think
				return getDeclaringClass().getMethod(getBooleanGetterName(), null);
			}
		}
		// If the getter cannot be found, throws an exception. TODO
		
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
	 * Gets the value of a field accessed directly (field access).
	 * 
	 * @param targetObject
	 *            The target object to get the field value from. Might be null if it is a static field.
	 * @param ignoreAccess
	 *            If the defined access (private or protected) must be ignored.
	 * @return The value of a field.
	 * @throws InvalidParameterException 
	 */
	private Object getValueDirectly(final Object targetObject, final Boolean ignoreAccess)
			throws InvalidParameterException {
		// Defines the accessibility of the field.
		getReflectedField().setAccessible(ignoreAccess);
		// Tries to return the field value.
		try {
			return getReflectedField().get(targetObject);
		}
		// If the target object is not from the declaring class type of the reflected field.
		catch (final IllegalArgumentException exception) {
			// Throws an exception.
			throw new InvalidParameterException(ErrorKeys.WRONG_TARGET_OBJ, exception, new Object[] {
					getReflectedField(), targetObject });
		}
		// If the field cannot be accessed (modifiers).
		catch (final IllegalAccessException exception) {
			// Throws an exception.
			throw new InvalidParameterException(ErrorKeys.UNACCESSIBLE_FIELD, exception,
					new Object[] { getReflectedField() });
		}
		// Finally.
		finally {
			// Reinforces the field accessibility.
			getReflectedField().setAccessible(false);
		}
	}
	
	/**
	 * Gets the value of a field accessed by its getter.
	 * 
	 * @param targetObject
	 *            The target object to get the field value from. Might be null if it is a static field.
	 * @param ignoreAccess
	 *            If the defined access (private or protected) must be ignored.
	 * @return The value of a field.
	 */
	private Object getValue(final Object targetObject, final Boolean ignoreAccess) {
		// Tries to return the field value.
		return getGetter().invokeMethod(targetObject, null, ignoreAccess);
	}
	
	/**
	 * Gets the value of a field.
	 * 
	 * @param targetObject
	 *            The target object to get the field value from. Might be null if it is a static field.
	 * @param fieldAccess
	 *            If the field must be accessed directly (or via getter).
	 * @param ignoreAccess
	 *            If the defined access (private or protected) must be ignored.
	 * @return Returns the field value.
	 * @throws EmptyParameterException
	 *             If the target object is null and the field is not static.
	 */
	public Object getValue(final Object targetObject, final Boolean fieldAccess, final Boolean ignoreAccess)
			throws EmptyParameterException {
		// If the field is not static and the target object is null.
		if ((targetObject == null) && (Modifier.isStatic(getReflectedField().getModifiers()))) {
			// Throws an exception.
			throw new EmptyParameterException(1);
		}
		// If the value must be accessed directly.
		if (fieldAccess) {
			// Gets the field value directly.
			return getValueDirectly(targetObject, ignoreAccess);
		}
		// If the value must be accessed through its getter.
		else {
			// Gets the field value through its getter.
			return getValue(targetObject, ignoreAccess);
		}
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((reflectedField == null) ? 0 : reflectedField.hashCode());
		return result;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final FieldMirror other = (FieldMirror) obj;
		if (reflectedField == null) {
			if (other.reflectedField != null) {
				return false;
			}
		}
		else if (!reflectedField.equals(other.reflectedField)) {
			return false;
		}
		return true;
	}
	
}
