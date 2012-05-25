package org.dejava.component.util.reflection.handler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import org.dejava.component.util.exception.localized.EmptyParameterException;
import org.dejava.component.util.exception.localized.InvalidParameterException;
import org.dejava.component.util.reflection.constant.ErrorKeys;
import org.dejava.component.util.reflection.exception.InvocationException;

/**
 * Helps handling reflection with fields.
 */
public final class FieldHandler {
	
	/**
	 * Private constructor.
	 */
	private FieldHandler() {
	}
	
	/**
	 * Returns a field from the desired class. The search includes the inherited classes. The first field
	 * found for the name is returned.
	 * 
	 * @param clazz
	 *            Class to look for the field.
	 * @param fieldPath
	 *            Path of the field to be found.
	 * @return A field from the desired class.
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty or not accessible).
	 */
	public static Field getField(Class<?> clazz, final String fieldPath) throws InvalidParameterException {
		// If the passed class is null.
		if (clazz == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// If the field name is empty.
		if ((fieldPath == null) || (fieldPath.isEmpty())) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// While the current class is not null.
		while (clazz != null) {
			// Tries to return the desired field.
			try {
				return clazz.getDeclaredField(fieldPath);
			}
			// If an exception is thrown.
			catch (final Exception exception) {
				// It will be ignored.
			}
			// The current class is now its superclass.
			clazz = clazz.getSuperclass();
		}
		// If no field was found for this name, throws an exception.
		throw new InvalidParameterException(ErrorKeys.MISSING_FIELD, null, null);
	}
	
	/**
	 * Returns all fields from a class (including inherited ones).
	 * 
	 * @param clazz
	 *            Class to get the fields from.
	 * @return All fields from a class (including inherited ones).
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty).
	 */
	public static Collection<Field> getAllFields(Class<?> clazz) throws InvalidParameterException {
		// If the passed class is null.
		if (clazz == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// Collection to keep found fields.
		final Collection<Field> fields = new ArrayList<Field>();
		// While the current class is not null.
		while (clazz != null) {
			// For each field in the current class.
			for (final Field currentField : clazz.getDeclaredFields()) {
				// Add it to the collection with all fields.
				fields.add(currentField);
			}
			// The current class is now its superclass.
			clazz = clazz.getSuperclass();
		}
		// Return all the fields found in the search.
		return fields;
	}
	
	/**
	 * Regular expression for getter methods.
	 */
	public static final String GETTER_REGEX = "get[A-Z0-9_\\$].*";
	
	/**
	 * Returns if the method name is from a getter.
	 * 
	 * @param methodName
	 *            Name of the method to be checked.
	 * @return If the method name is from a getter.
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty).
	 */
	public static Boolean isGetter(final String methodName) throws InvalidParameterException {
		// If the method name is null or empty.
		if (methodName == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// Returns if the method name matches the getter regular expression.
		return methodName.matches(GETTER_REGEX);
	}
	
	/**
	 * Regular expression for setter methods.
	 */
	public static final String SETTER_REGEX = "set[A-Z0-9_\\$].*";
	
	/**
	 * Returns if the method name is from a setter.
	 * 
	 * @param methodName
	 *            Name of the method to be checked.
	 * @return If the method name is from a setter.
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty).
	 */
	public static Boolean isSetter(final String methodName) throws InvalidParameterException {
		// If the method name is null or empty.
		if (methodName == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// Returns if the method name matches the setter regular expression.
		return methodName.matches(SETTER_REGEX);
	}
	
	/**
	 * Returns the field name from a getter/setter method name.
	 * 
	 * @param methodName
	 *            Name of the getter/setter.
	 * @return The field name from a getter/setter method name.
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty).
	 */
	public static String getFieldName(final String methodName) throws InvalidParameterException {
		// If the method name is null or empty.
		if (methodName == null) {
			// Throws an exception.
			// throw new EmptyParameterException(ErrorKeys.EMPTY_CLASS, null, null);
		}
		// If it is a getter or a setter.
		if ((isGetter(methodName)) || (isSetter(methodName))) {
			// Returns the method name without the get/set and with the first letter with lower case.
			return methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
		}
		else {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
	}
	
	/**
	 * Get the getter name for a field.
	 * 
	 * @param fieldName
	 *            Name of the field to get the getter name.
	 * @return The getter name for a field.
	 */
	private static String getGetterName(final String fieldName) {
		// Append the "get" followed by the field name (with upper case first letter).
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
	
	/**
	 * Get the setter name for a field.
	 * 
	 * @param fieldName
	 *            Name of the field to get the setter name.
	 * @return The setter name for a field.
	 */
	private static String getSetterName(final String fieldName) {
		// Append the "set" followed by the field name (with upper case first letter).
		return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
	
	/**
	 * Gets a field value directly (field access).
	 * 
	 * @param object
	 *            Object to get the field values.
	 * @param fieldPath
	 *            Field path from object.
	 * @param exceptionOnNull
	 *            If an exception must be thrown when getting a null value before going through all path. If
	 *            false, a null is returned is this case.
	 * @param ignoreAccess
	 *            If the defined accessibility for the field must be ignored.
	 * @return A field value through invoking getters.
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty or field is not accessible).
	 */
	public static Object getFieldValueDirectly(Object object, final String fieldPath,
			final Boolean ignoreAccess, final Boolean exceptionOnNull) throws InvalidParameterException {
		// If the passed object is null.
		if (object == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// Split the fields from the passed path.
		final String[] fieldNames = fieldPath.split("\\.");
		// For each field name.
		for (final String currentFieldName : fieldNames) {
			// If the object is null.
			if (object == null) {
				// If an exception must be thrown when getting a null value before going through all path.
				if (exceptionOnNull) {
					// Throws an exception.
					throw new InvalidParameterException(ErrorKeys.UNACCESSIBLE_FIELD_PATH, null, null);
				}
				// If not.
				else {
					// Returns null.
					return null;
				}
			}
			// Gets the field for the current name.
			final Field currentField = getField(object.getClass(), currentFieldName);
			// Defines the accessibility of the field.
			currentField.setAccessible(ignoreAccess);
			// Tries to get the next field value in the path.
			try {
				object = currentField.get(object);
			}
			// If the parameter values are illegal for the method.
			catch (final IllegalArgumentException exception) {
				// Throws an exception.
				throw new InvalidParameterException(ErrorKeys.ILLEGAL_PARAMS_VALUES, null, null);
			}
			// If the field is not accessible.
			catch (final IllegalAccessException exception) {
				// Throws an exception.
				throw new InvalidParameterException(ErrorKeys.UNACCESSIBLE_FIELD, null, null);
			}
		}
		// Returns the field value.
		return object;
	}
	
	/**
	 * Gets a field value through invoking getters.
	 * 
	 * @param object
	 *            Object to get the field values.
	 * @param fieldPath
	 *            Field path from object.
	 * @param ignoreAccess
	 *            If the defined accessibility for the field getter must be ignored.
	 * @param exceptionOnNull
	 *            If an exception must be thrown when getting a null value before going through all path. If
	 *            false, a null is returned is this case.
	 * @return A field value through invoking getters.
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty or getter could not be accessed).
	 * @throws InvocationException
	 *             If an exception is thrown by a getter.
	 */
	public static Object getFieldValue(Object object, final String fieldPath, final Boolean ignoreAccess,
			final Boolean exceptionOnNull) throws InvalidParameterException, InvocationException {
		// If the passed object is null.
		if (object == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// Split the fields from the passed path.
		final String[] fieldNames = fieldPath.split("\\.");
		// For each field name.
		for (final String currentFieldName : fieldNames) {
			// If the object is null.
			if (object == null) {
				// If an exception must be thrown when getting a null value before going through all path.
				if (exceptionOnNull) {
					// Throws an exception.
					throw new InvalidParameterException(ErrorKeys.UNACCESSIBLE_FIELD_PATH, null, null);
				}
				// If not.
				else {
					// Returns null.
					return null;
				}
			}
			// Tries to get the next field value in the path.
			object = MethodHandler.invokeMethod(object, getGetterName(currentFieldName), null, ignoreAccess);
		}
		// Returns the field value.
		return object;
	}
	
	/**
	 * Sets a field value directly (field access).
	 * 
	 * @param object
	 *            Object to get the field values.
	 * @param fieldPath
	 *            Field path from object.
	 * @param fieldValue
	 *            New value for the field.
	 * @param exceptionOnNull
	 *            If an exception must be thrown when getting a null value before going through all path. If
	 *            false, a null is returned is this case.
	 * @param ignoreAccess
	 *            If the defined accessibility for the field must be ignored.
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty or field is not accessible).
	 */
	public static void setFieldValueDirectly(Object object, final String fieldPath, final Object fieldValue,
			final Boolean ignoreAccess, final Boolean exceptionOnNull) throws InvalidParameterException {
		// If the passed object is null.
		if (object == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// Split the fields from the passed path.
		final String[] fieldNames = fieldPath.split("\\.");
		// For each field name.
		for (Integer currentFieldIndex = 0; currentFieldIndex < fieldNames.length; currentFieldIndex++) {
			// If the object is null.
			if (object == null) {
				// If an exception must be thrown when getting a null value before going through all path.
				if (exceptionOnNull) {
					// Throws an exception.
					throw new InvalidParameterException(ErrorKeys.UNACCESSIBLE_FIELD_PATH, null, null);
				}
				// If not.
				else {
					// Finishes the method.
					return;
				}
			}
			// Gets the field for the current name.
			final Field currentField = getField(object.getClass(), fieldNames[currentFieldIndex]);
			// Defines the accessibility of the field.
			currentField.setAccessible(ignoreAccess);
			// Tries to get or set the current field value.
			try {
				// If it is not the last field.
				if (currentFieldIndex != (fieldNames.length - 1)) {
					// Tries to get the next field value in the path.
					object = currentField.get(object);
				}
				// If it is the last field.
				else {
					// Tries to set the final field value in the path.
					currentField.set(object, fieldValue);
				}
			}
			// If the parameter values are illegal for the method.
			catch (final IllegalArgumentException exception) {
				// Throws an exception.
				throw new InvalidParameterException(ErrorKeys.ILLEGAL_PARAMS_VALUES, exception, null);
			}
			// If the field is not accessible.
			catch (final IllegalAccessException exception) {
				// Throws an exception.
				throw new InvalidParameterException(ErrorKeys.UNACCESSIBLE_FIELD, exception, null);
			}
		}
	}
	
	/**
	 * Sets a field value through invoking getters/setters.
	 * 
	 * @param object
	 *            Object to get the field values.
	 * @param fieldPath
	 *            Field path from object.
	 * @param fieldClass
	 *            Class of the field to be set.
	 * @param fieldValue
	 *            New value for the field.
	 * @param ignoreAccess
	 *            If the defined accessibility for the field getter/setter must be ignored.
	 * @param exceptionOnNull
	 *            If an exception must be thrown when getting a null value before going through all path. If
	 *            false, a null is returned is this case.
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty or getter could not be accessed).
	 * @throws InvocationException
	 *             If an exception is thrown by a getter/setter.
	 */
	public static void setFieldValue(Object object, final String fieldPath, final Class<?> fieldClass,
			final Object fieldValue, final Boolean ignoreAccess, final Boolean exceptionOnNull)
			throws InvalidParameterException, InvocationException {
		// If the passed object is null.
		if (object == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// Split the fields from the passed path.
		final String[] fieldNames = fieldPath.split("\\.");
		// For each field name.
		for (Integer currentFieldIndex = 0; currentFieldIndex < fieldNames.length; currentFieldIndex++) {
			// If the object is null.
			if (object == null) {
				// If an exception must be thrown when getting a null value before going through all path.
				if (exceptionOnNull) {
					// Throws an exception.
					throw new InvalidParameterException(ErrorKeys.UNACCESSIBLE_FIELD_PATH, null, null);
				}
				// If not.
				else {
					// Finishes the method.
					return;
				}
			}
			// If it is not the last field.
			if (currentFieldIndex != (fieldNames.length - 1)) {
				// Tries to get the next field value in the path.
				object = MethodHandler.invokeMethod(object, getGetterName(fieldNames[currentFieldIndex]),
						null, ignoreAccess);
			}
			// If it is the last field.
			else {
				// Tries to set the final field value in the path.
				MethodHandler.invokeMethod(object, getSetterName(fieldNames[currentFieldIndex]),
						new Class<?>[] { fieldClass }, new Object[] { fieldValue }, ignoreAccess);
			}
		}
	}
	
	/**
	 * Sets a field value through invoking getters/setters.
	 * 
	 * @param object
	 *            Object to get the field values.
	 * @param fieldPath
	 *            Field path from object.
	 * @param fieldValue
	 *            New value for the field.
	 * @param ignoreAccess
	 *            If the defined accessibility for the field getter/setter must be ignored.
	 * @param exceptionOnNull
	 *            If an exception must be thrown when getting a null value before going through all path. If
	 *            false, a null is returned is this case.
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty or getter could not be accessed).
	 * @throws InvocationException
	 *             If an exception is thrown by a getter/setter.
	 */
	public static void setFieldValue(final Object object, final String fieldPath, final Object fieldValue,
			final Boolean ignoreAccess, final Boolean exceptionOnNull) throws InvalidParameterException,
			InvocationException {
		// Gets the parameter class for the field.
		final Class<?>[] parametersClasses = MethodHandler.getParametersClasses(new Object[] { fieldValue });
		// Tries to set the field using the class.
		setFieldValue(object, fieldPath, parametersClasses[0], fieldValue, ignoreAccess, exceptionOnNull);
	}
}
