package org.dejava.component.reflection;

import java.util.LinkedList;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.reflection.exception.InvocationException;

/**
 * TODO
 */
public class FieldPath {
	
	/**
	 * Field path.
	 */
	private LinkedList<FieldMirror> fieldPath;
	
	/**
	 * Gets the field path.
	 * 
	 * @return The field path.
	 */
	public LinkedList<FieldMirror> getFieldPath() {
		// If field path is null.
		if (fieldPath == null) {
			// Creates a new list for the field path.
			fieldPath = new LinkedList<>();
		}
		// Returns the field path.
		return fieldPath;
	}
	
	/**
	 * Sets the field path.
	 * 
	 * @param fieldPath
	 *            New field path.
	 */
	public void setFields(final LinkedList<FieldMirror> fieldPath) {
		this.fieldPath = fieldPath;
	}
	
	/**
	 * Public constructor for the reflection class.
	 * 
	 * @param fieldPath
	 *            Field path.
	 */
	public FieldPath(final LinkedList<FieldMirror> fieldPath) {
		this.fieldPath = fieldPath;
	}
	
	/**
	 * Gets the value of the last field value in the path.
	 * 
	 * @param targetObject
	 *            The target object to get the field value from. Might be null if it is a static field.
	 * @param fieldAccess
	 *            If each field must be accessed directly (or via getter).
	 * @param ignoreAccess
	 *            If the defined access (private or protected) must be ignored.
	 * @return Returns the field value.
	 * @throws EmptyParameterException
	 *             If the target object is null and a field/getter is not static.
	 * @throws InvalidParameterException
	 *             If a field/getter in the field path cannot be accessed or found.
	 * @throws InvocationException
	 *             If a getter in the field path throws an exception.
	 */
	public Object getValue(final Object targetObject, final Boolean fieldAccess, final Boolean ignoreAccess)
			throws EmptyParameterException, InvalidParameterException, InvocationException {
		// The current field value starts with the given object.
		Object currentFieldValue = targetObject;
		// For each field in the path.
		for (final FieldMirror currentField : getFieldPath()) {
			// Gets the value for the current field.
			currentFieldValue = currentField.getValue(currentFieldValue, fieldAccess, ignoreAccess);
		}
		// Returns the value of the last field.
		return currentFieldValue;
	}
	
	/**
	 * Sets the value of the last field in the path.
	 * 
	 * @param targetObject
	 *            The target object to set the field value. Might be null if it is a static field.
	 * @param newValue
	 *            The new value for the field.
	 * @param fieldAccess
	 *            If each field must be accessed directly (or via setter).
	 * @param ignoreAccess
	 *            If the defined access (private or protected) must be ignored.
	 * @throws EmptyParameterException
	 *             If the target object is null and a field/setter is not static.
	 * @throws InvalidParameterException
	 *             If a field/setter in the field path cannot be accessed or found. Or if the new value is
	 *             invalid.
	 * @throws InvocationException
	 *             If a setter in the field path for the field throws an exception.
	 */
	public void setValue(final Object targetObject, final Object newValue, final Boolean fieldAccess,
			final Boolean ignoreAccess) throws EmptyParameterException, InvalidParameterException,
			InvocationException {
		// The current field value starts with the given object.
		Object currentFieldValue = targetObject;
		// For each field in the path.
		for (final FieldMirror currentField : getFieldPath()) {
			// If it is not the last field.
			if (!currentField.equals(getFieldPath().peekLast())) {
				// Gets the value for the current field.
				currentFieldValue = currentField.getValue(currentFieldValue, fieldAccess, ignoreAccess);
			}
			// If it is.
			else {
				// Sets its value.
				currentField.setValue(currentFieldValue, newValue, fieldAccess, ignoreAccess);
			}
		}
	}
}
