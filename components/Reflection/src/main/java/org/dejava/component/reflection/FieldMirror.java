package org.dejava.component.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.reflection.constant.ErrorKeys;
import org.dejava.component.reflection.constant.FieldParamKeys;
import org.dejava.component.reflection.exception.InvocationException;
import org.dejava.component.reflection.util.MessageTypes;
import org.dejava.component.validation.method.PreConditions;

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
	 * Type (class) of the reflected field.
	 */
	private ClassMirror<?> type;

	/**
	 * Gets the type (class) of the reflected field.
	 * 
	 * @return The type (class) of the reflected field.
	 */
	public ClassMirror<?> getType() {
		// If the type is null.
		if (type == null) {
			// Gets the field type.
			type = new ClassMirror<>(getReflectedField().getType());
		}
		// Returns the type.
		return type;
	}

	/**
	 * Sets the type (class) of the reflected field.
	 * 
	 * @param type
	 *            New type (class) of the reflected field.
	 */
	public void setType(final ClassMirror<?> type) {
		this.type = type;
	}

	/**
	 * Declaring class of the field.
	 */
	private ClassMirror<?> declaringClass;

	/**
	 * Gets the declaring class of the field.
	 * 
	 * @return The declaring class of the field.
	 */
	public ClassMirror<?> getDeclaringClass() {
		// If the declaring class is null.
		if (declaringClass == null) {
			// Gets the declaring class of the field.
			declaringClass = new ClassMirror<>(getReflectedField().getDeclaringClass());
		}
		// Returns the class.
		return declaringClass;
	}

	/**
	 * The field getter prefix.
	 */
	public static final String GETTER_PREFIX = "get";

	/**
	 * Get the getter name for a field.
	 * 
	 * @param fieldName
	 *            The field name.
	 * 
	 * @return The getter name for a field.
	 */
	public static String getGetterName(final String fieldName) {
		// Append the "get" followed by the field name (with upper case first letter).
		return GETTER_PREFIX + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	/**
	 * The boolean field getter prefix.
	 */
	public static final String BOOL_GETTER_PREFIX = "is";

	/**
	 * Get the getter name for a boolean field.
	 * 
	 * @param fieldName
	 *            The field name.
	 * 
	 * @return The getter name for a boolean field.
	 */
	public static String getBooleanGetterName(final String fieldName) {
		// Append the "get" followed by the field name (with upper case first letter).
		return BOOL_GETTER_PREFIX + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	/**
	 * Getter for the reflected field.
	 */
	private MethodMirror getter;

	/**
	 * Gets the getter for the reflected field.
	 * 
	 * @return The getter for the reflected field.
	 * @throws InvalidParameterException
	 *             If the getter cannot be found.
	 */
	public MethodMirror getGetter() throws InvalidParameterException {
		// If the getter is null.
		if (getter == null) {
			// Tries to get the getter for the field.
			getter = getDeclaringClass().getMethod(getGetterName(getReflectedField().getName()), null);
		}
		// If the method cannot be found.
		if (getter == null) {
			// If field type is boolean.
			if ((getReflectedField().getType().isAssignableFrom(Boolean.class))
					|| (getReflectedField().getType().isAssignableFrom(boolean.class))) {
				// Tries to get the boolean field getter.
				getter = getDeclaringClass().getMethod(getBooleanGetterName(getReflectedField().getName()),
						null);
			}
		}
		// If the method cannot be found.
		if (getter == null) {
			// If the getter cannot be found, throws an exception.
			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.GETTER_NOT_FOUND,
					new Object[] { getReflectedField() }, null);
		}
		// Returns the getter.
		return getter;
	}

	/**
	 * The field setter prefix.
	 */
	public static final String SETTER_PREFIX = "set";

	/**
	 * Get the setter name for a field.
	 * 
	 * @param fieldName
	 *            The field name.
	 * 
	 * @return The setter name for a field.
	 */
	public static String getSetterName(String fieldName) {
		// Append the "set" followed by the field name (with upper case first letter).
		return SETTER_PREFIX + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	/**
	 * Setter for the reflected field.
	 */
	private MethodMirror setter;

	/**
	 * Gets the setter for the reflected field.
	 * 
	 * @return The setter for the reflected field.
	 * @throws InvalidParameterException
	 *             If the setter cannot be found.
	 */
	public MethodMirror getSetter() throws InvalidParameterException {
		// If the setter is null.
		if (setter == null) {
			// Tries to get the setter for the field.
			try {
				setter = getDeclaringClass().getMethod(getSetterName(getReflectedField().getName()),
						new Class[] { getReflectedField().getType() });
			}
			// If the setter cannot be found.
			catch (final Exception exception) {
				// Throws an exception.
				throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.SETTER_NOT_FOUND,
						new Object[] { getReflectedField() }, null);
			}
		}
		// Returns the setter.
		return setter;
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
		// Asserts that the field is not null.
		PreConditions.assertParamNotNull(FieldParamKeys.FIELD, reflectedField);
		// Sets the main reflection fields.
		this.reflectedField = reflectedField;
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
	 *             If the field/getter cannot be accessed or found.
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
			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.WRONG_TARGET_OBJ,
					new Object[] { getReflectedField(), targetObject, ignoreAccess }, null);
		}
		// If the field cannot be accessed (modifiers).
		catch (final IllegalAccessException exception) {
			// Throws an exception.
			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.UNACCESSIBLE_FIELD,
					new Object[] { getReflectedField(), targetObject, ignoreAccess }, null);
		}
		// Finally.
		finally {
			// Reinforces the field accessibility.
			getReflectedField().setAccessible(false);
		}
	}

	/**
	 * Gets the value of a field accessed through its getter.
	 * 
	 * @param targetObject
	 *            The target object to get the field value from. Might be null if it is a static field.
	 * @param ignoreAccess
	 *            If the defined access (private or protected) must be ignored.
	 * @return The value of a field.
	 * @throws EmptyParameterException
	 *             If the target object is null and the getter is not static.
	 * @throws InvalidParameterException
	 *             If the getter cannot be accessed or found.
	 * @throws InvocationException
	 *             If the getter for the field throws an exception.
	 */
	private Object getValue(final Object targetObject, final Boolean ignoreAccess)
			throws EmptyParameterException, InvalidParameterException, InvocationException {
		// Tries to return the field value.
		return getGetter().invokeMethod(targetObject, ignoreAccess, null);
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
	 *             If the target object is null and the field/getter is not static.
	 * @throws InvalidParameterException
	 *             If the field/getter cannot be accessed or found.
	 * @throws InvocationException
	 *             If the getter for the field throws an exception.
	 */
	public Object getValue(final Object targetObject, final Boolean fieldAccess, final Boolean ignoreAccess)
			throws EmptyParameterException, InvalidParameterException, InvocationException {
		// If the field is not static and the target object is null.
		if ((targetObject == null) && (!Modifier.isStatic(getReflectedField().getModifiers()))) {
			// Throws an exception.
			throw new EmptyParameterException(FieldParamKeys.TARGET_OBJECT);
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
	 * Sets the value of a field accessed directly (field access).
	 * 
	 * @param targetObject
	 *            The target object to set the field value. Might be null if it is a static field.
	 * @param newValue
	 *            The new value for the field.
	 * @param ignoreAccess
	 *            If the defined access (private or protected) must be ignored.
	 * @throws InvalidParameterException
	 *             If the field/setter cannot be accessed or found.
	 */
	private void setValueDirectly(final Object targetObject, final Object newValue, final Boolean ignoreAccess)
			throws InvalidParameterException {
		// Defines the accessibility of the field.
		getReflectedField().setAccessible(ignoreAccess);
		// Tries to set the field value.
		try {
			getReflectedField().set(targetObject, newValue);
		}
		// If the target object is not from the declaring class type of the reflected field.
		catch (final IllegalArgumentException exception) {
			// Throws an exception.
			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.WRONG_TARGET_OBJ,
					new Object[] { getReflectedField(), targetObject, newValue, ignoreAccess }, exception);
		}
		// If the field cannot be accessed (modifiers).
		catch (final IllegalAccessException exception) {
			// Throws an exception.
			throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.UNACCESSIBLE_FIELD,
					new Object[] { getReflectedField(), targetObject, newValue, ignoreAccess }, exception);
		}
		// Finally.
		finally {
			// Reinforces the field accessibility.
			getReflectedField().setAccessible(false);
		}
	}

	/**
	 * Sets the value of a field accessed through its setter.
	 * 
	 * @param targetObject
	 *            The target object to set the field value. Might be null if it is a static field.
	 * @param newValue
	 *            The new value for the field.
	 * @param ignoreAccess
	 *            If the defined access (private or protected) must be ignored.
	 * @throws EmptyParameterException
	 *             If the target object is null and the setter is not static.
	 * @throws InvalidParameterException
	 *             If the setter cannot be accessed or found. Or if the new value is invalid.
	 * @throws InvocationException
	 *             If the setter for the field throws an exception.
	 */
	private void setValue(final Object targetObject, final Object newValue, final Boolean ignoreAccess)
			throws EmptyParameterException, InvalidParameterException, InvocationException {
		// Tries to set the field value.
		getSetter().invokeMethod(targetObject, ignoreAccess, new Object[] { newValue });
	}

	/**
	 * Sets the value of a field.
	 * 
	 * @param targetObject
	 *            The target object to set the field value. Might be null if it is a static field.
	 * @param newValue
	 *            The new value for the field.
	 * @param fieldAccess
	 *            If the field must be accessed directly (or via setter).
	 * @param ignoreAccess
	 *            If the defined access (private or protected) must be ignored.
	 * @throws EmptyParameterException
	 *             If the target object is null and the field/setter is not static.
	 * @throws InvalidParameterException
	 *             If the field/setter cannot be accessed or found. Or if the new value is invalid.
	 * @throws InvocationException
	 *             If the setter for the field throws an exception.
	 */
	public void setValue(final Object targetObject, final Object newValue, final Boolean fieldAccess,
			final Boolean ignoreAccess) throws EmptyParameterException, InvalidParameterException,
			InvocationException {
		// If the field is not static and the target object is null.
		if ((targetObject == null) && (!Modifier.isStatic(getReflectedField().getModifiers()))) {
			// Throws an exception.
			throw new EmptyParameterException(FieldParamKeys.TARGET_OBJECT);
		}
		// If the value must be accessed directly.
		if (fieldAccess) {
			// Sets the field value directly.
			setValueDirectly(targetObject, newValue, ignoreAccess);
		}
		// If the value must be accessed through its setter.
		else {
			// Sets the field value through its setter.
			setValue(targetObject, newValue, ignoreAccess);
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
		} else if (!reflectedField.equals(other.reflectedField)) {
			return false;
		}
		return true;
	}

}
