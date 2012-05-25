package org.dejava.component.util.validation.processor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.dejava.component.util.exception.localized.EmptyParameterException;
import org.dejava.component.util.exception.localized.InvalidParameterException;
import org.dejava.component.util.reflection.exception.InvocationException;
import org.dejava.component.util.reflection.handler.ConstructorHandler;
import org.dejava.component.util.reflection.handler.FieldHandler;
import org.dejava.component.util.reflection.handler.MethodHandler;
import org.dejava.component.util.validation.annotation.ValidationMethod;
import org.dejava.component.util.validation.annotation.Validations;
import org.dejava.component.util.validation.constant.ErrorKeys;
import org.dejava.component.util.validation.exception.FailedValidationException;
import org.dejava.component.util.validation.exception.FailedValidationsException;
import org.dejava.component.util.validation.exception.ImpossibleValidationException;

/**
 * Processes validation on fields or objects.
 * 
 * The rules for the validations are the ones defined at {@link ValidationMethod}. Use {@link Validations} and
 * {@link ValidationMethod} annotations at your object fields at your will.
 * 
 * You can either validate a single annotated field (
 * {@link #validateField(java.lang.Object, java.lang.reflect.Field)} or
 * {@link #validateField(java.lang.Object, java.lang.String)}) of an object or the whole object (
 * {@link #validateObject}).
 * 
 * Have fun!
 */
public final class ValidationProcessor {
	
	/**
	 * Private constructor.
	 */
	private ValidationProcessor() {
	}
	
	/**
	 * Expression for the class name in the failed validation key.
	 */
	public static final String CLASS_EXPRESSION = "#{class}";
	
	/**
	 * Expression for the field name in the failed validation key.
	 */
	public static final String FIELD_EXPRESSION = "#{field}";
	
	/**
	 * Expression for the validation method name in the failed validation key.
	 */
	public static final String METHOD_EXPRESSION = "#{method}";
	
	/**
	 * Failed validation key expression template.
	 */
	public static final String FAILED_VALIDATION_KEY_TEMPLATE = CLASS_EXPRESSION + '.' + FIELD_EXPRESSION
			+ '.' + METHOD_EXPRESSION;
	
	/**
	 * Gets the key for the field validation error.
	 * 
	 * @param field
	 *            Field that cannot be validated.
	 * @param validationMethod
	 *            Validation method that cannot be verified.
	 * @return The key that represents the validation error.
	 */
	private static String getFailedValidationKey(final Field field, final ValidationMethod validationMethod) {
		// The initial validation key is retrieved from the template.
		String validationKey = FAILED_VALIDATION_KEY_TEMPLATE;
		// Updates the class name in the template.
		validationKey = validationKey.replace(CLASS_EXPRESSION, field.getDeclaringClass().getSimpleName());
		// Updates the field name in the template.
		validationKey = validationKey.replace(FIELD_EXPRESSION, field.getName());
		// If no method is given.
		if (validationMethod == null) {
			// Removes the validation method name in the template.
			validationKey = validationKey.replace(METHOD_EXPRESSION + '.', "");
		}
		// Otherwise.
		else {
			// Updates the validation method name in the template.
			validationKey = validationKey.replace(METHOD_EXPRESSION, validationMethod.methodName());
		}
		// Returns the failed validation key.
		return validationKey.toLowerCase();
	}
	
	/**
	 * Gets the classes of validation method parameters.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param field
	 *            Field to be validated.
	 * @param validationMethod
	 *            Validation method to be verified.
	 * @return The classes of validation method parameters.
	 * @throws ImpossibleValidationException
	 *             If it is not possible to retrieve the field value for the validation.
	 */
	private static Class<?>[] getValidationMethodParametersClasses(final Object object, final Field field,
			final ValidationMethod validationMethod) throws ImpossibleValidationException {
		// Tries to get the parameters.
		try {
			// List with the classes of the parameters for the method.
			final List<Class<?>> parametersClasses = new ArrayList<Class<?>>();
			// Puts the field type in the first position.
			parametersClasses.add(field.getType());
			// For each complementary field.
			for (String currentComplementaryFieldPath : validationMethod.complementaryFieldsPaths()) {
				// Gets the current complementary field.
				final Field currentField = FieldHandler.getField(object.getClass(),
						currentComplementaryFieldPath);
				// Adds the current field type in the list.
				parametersClasses.add(currentField.getType());
			}
			// For each other parameter class.
			for (Class<?> currentParameterClass : validationMethod.otherParametersClasses()) {
				// Adds the current parameter class in the list.
				parametersClasses.add(currentParameterClass);
			}
			// Returns the list in a array.
			return parametersClasses.toArray(new Class<?>[parametersClasses.size()]);
		}
		// If the field for the given information cannot be found.
		catch (InvalidParameterException exception) {
			// Throws an exception.
			throw new ImpossibleValidationException(getFailedValidationKey(field, validationMethod),
					exception, null);
		}
	}
	
	/**
	 * TODO
	 */
	private static final Class<?>[] ONE_STRING = { String.class };
	
	/**
	 * Gets the values of validation method parameters.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param field
	 *            Field to be validated.
	 * @param fieldValue
	 *            Field value to be validated.
	 * @param validationMethod
	 *            Validation method to be verified.
	 * @return The values of validation method parameters.
	 * @throws ImpossibleValidationException
	 *             If it is not possible to retrieve the complementary parameters value for the validation.
	 */
	private static Object[] getValidationMethodParametersValues(final Object object, final Field field,
			final Object fieldValue, final ValidationMethod validationMethod)
			throws ImpossibleValidationException {
		// Tries to get the parameters values.
		try {
			// List with the values of the parameters for the method.
			final List<Object> parametersValues = new ArrayList<Object>();
			// Adds the field value in the first position.
			parametersValues.add(fieldValue);
			// For each complementary field.
			for (String currentFieldPath : validationMethod.complementaryFieldsPaths()) {
				// Gets the current complementary field value.
				final Object currentFieldValue = FieldHandler.getFieldValue(object, currentFieldPath, false);
				// Adds the current field value in the list.
				parametersValues.add(currentFieldValue);
			}
			// For each other parameter class.
			for (Integer currentParamIndex = 0; currentParamIndex < validationMethod.otherParametersClasses().length; currentParamIndex++) {
				// Gets the current string value for the parameter.
				Object currentParamValue = validationMethod.otherParametersValues()[currentParamIndex];
				// If the parameter is a null value expression.
				if ((ValidationMethod.NULL_EXPRESSION.equals(currentParamValue))
						|| (ValidationMethod.NULL_EXPRESSION.equals(currentParamValue))) {
					// Null is used for current parameter value.
					parametersValues.add(null);
				}
				// Otherwise.
				else {
					// Tries to get the parameter actual value (using the given class).
					currentParamValue = ConstructorHandler.invokeConstructor(
							validationMethod.otherParametersClasses()[currentParamIndex],
							new Class<?>[] { String.class }, new Object[] { currentParamValue });
					// Adds the current parameter actual value in the list.
					parametersValues.add(currentParamValue);
				}
			}
			// Returns the list in a array.
			return parametersValues.toArray();
		}
		// If the value for one of the parameters cannot be retrieved caused to wrong values given to its
		// constructor/getter.
		catch (InvalidParameterException exception) {
			// Throws an exception.
			throw new ImpossibleValidationException(getFailedValidationKey(field, validationMethod),
					exception, null);
		}
		// If the value for one of the parameters cannot be retrieved caused to an exception in its
		// constructor/getter.
		catch (InvocationException exception) {
			// Throws an exception.
			throw new ImpossibleValidationException(getFailedValidationKey(field, validationMethod),
					exception, null);
		}
	}
	
	/**
	 * Validates a field value with a given validation method information.
	 * 
	 * @param field
	 *            Field to be validated.
	 * @param validationMethod
	 *            Validation method to be verified.
	 * @param methodClass
	 *            The class from which the validation method must be invoked.
	 * @param methodObject
	 *            The object from which the validation method must be invoked.
	 * @param parametersClasses
	 *            Parameters classes for the method to be invoked.
	 * @param parametersValues
	 *            Parameters values to be used during the invocation.
	 * @return If the field value cannot be validated for this field and validation method.
	 * @throws ImpossibleValidationException
	 *             If it is not possible to execute the validation (due to invalid information or an exception
	 *             during the validation).
	 */
	private static Boolean validateField(final Field field, final ValidationMethod validationMethod,
			final Class<?> methodClass, final Object methodObject, final Class<?>[] parametersClasses,
			final Object[] parametersValues) throws ImpossibleValidationException {
		// Tries to validate the field.
		try {
			// If the object is null.
			if (methodObject == null) {
				// Tries to perform the validation as a static method.
				return (Boolean) MethodHandler.invokeStaticMethod(methodClass, validationMethod.methodName(),
						parametersClasses, parametersValues);
			}
			// If there is a validation method object.
			else {
				// Tries to perform the validation as an object method.
				return (Boolean) MethodHandler.invokeMethod(methodObject, validationMethod.methodName(),
						parametersClasses, parametersValues);
			}
		}
		// If the parameters for this validation method are not valid (empty or method could not be accessed).
		catch (InvalidParameterException exception) {
			// Throws an exception.
			throw new ImpossibleValidationException(getFailedValidationKey(field, validationMethod),
					exception, null);
		}
		// If the validation method throws an exception itself.
		catch (InvocationException exception) {
			// Throws an exception.
			throw new ImpossibleValidationException(getFailedValidationKey(field, validationMethod),
					exception, null);
		}
	}
	
	/**
	 * Validates a field value with a given validation method information.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param field
	 *            Field to be validated.
	 * @param validationMethod
	 *            Validation method to be verified.
	 * @throws ImpossibleValidationException
	 *             If it is not possible to run the validation.
	 * @throws FailedValidationException
	 *             If the validation has failed.
	 */
	private static void validateField(final Object object, final Field field,
			final ValidationMethod validationMethod) throws ImpossibleValidationException,
			FailedValidationException {
		// Tries to validate the field.
		try {
			// Gets the field value.
			final Object fieldValue = FieldHandler.getFieldValue(object, field.getName(), false);
			// Gets the parameters classes to invoke the method.
			Class<?>[] parametersClasses = getValidationMethodParametersClasses(object, field,
					validationMethod);
			// Gets the parameters values to invoke the method.
			Object[] parametersValues = getValidationMethodParametersValues(object, field, fieldValue,
					validationMethod);
			// By default, the validation method class is the one in the validation
			// method annotation.
			final Class<?> methodClass = validationMethod.clazz();
			// By default, the object from which to invoke the validation method is null.
			Object methodObject = null;
			// If there is a JNDI validation object.
			if ((validationMethod.jndiObjectName() != null) && (!validationMethod.jndiObjectName().isEmpty())) {
				// Tries to get it.
				methodObject = InitialContext.doLookup(validationMethod.jndiObjectName());
			}
			// If it is supposed to validate every item in the array/iterable (not
			// the value itself).
			if (validationMethod.validateItems()) {
				// If the field value is not null.
				if (fieldValue != null) {
					// If the field value is an array.
					if (fieldValue.getClass().isArray()) {
						// For each object in the array.
						for (Object currentFieldValueItem : (Object[]) fieldValue) {
							// Resets the first parameter class.
							parametersClasses[0] = currentFieldValueItem.getClass();
							// Resets the first parameter value.
							parametersValues[0] = currentFieldValueItem;
							// If the validation is not successful.
							if (!validateField(field, validationMethod, methodClass, methodObject,
									parametersClasses, parametersValues)) {
								// Throws an exception.
								throw new FailedValidationException(getFailedValidationKey(field,
										validationMethod), null, null);
							}
						}
					}
					// If it is iterable.
					else if (Iterable.class.isAssignableFrom(fieldValue.getClass())) {
						// For each object in the array.
						for (Object currentFieldValueItem : (Iterable<?>) fieldValue) {
							// Resets the first parameter class.
							parametersClasses[0] = currentFieldValueItem.getClass();
							// Resets the first parameter value.
							parametersValues[0] = currentFieldValueItem;
							// If the validation is not successful.
							if (!validateField(field, validationMethod, methodClass, methodObject,
									parametersClasses, parametersValues)) {
								// Throws an exception.
								throw new FailedValidationException(getFailedValidationKey(field,
										validationMethod), null, null);
							}
						}
					}
				}
			}
			// If it is supposed to validate the field value itself.
			else {
				// If the validation is not successful.
				if (!validateField(field, validationMethod, methodClass, methodObject, parametersClasses,
						parametersValues)) {
					// Throws an exception.
					throw new FailedValidationException(getFailedValidationKey(field, validationMethod),
							null, null);
				}
			}
		}
		// If the the field value cannot be retrieved due to invalid information.
		catch (InvalidParameterException exception) {
			// Throws an exception.
			throw new ImpossibleValidationException(getFailedValidationKey(field, validationMethod),
					exception, null);
		}
		// If the the field value cannot be retrieved due to a getter exception.
		catch (InvocationException exception) {
			// Throws an exception.
			throw new ImpossibleValidationException(getFailedValidationKey(field, validationMethod),
					exception, null);
		}
		// If the JNDI object cannot be retrieved.
		catch (NamingException exception) {
			// Throws an exception.
			throw new ImpossibleValidationException(getFailedValidationKey(field, validationMethod),
					exception, null);
		}
	}
	
	/**
	 * Validates a field value recursively (the field value as an object to be validated). The validation
	 * annotations will be used.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param field
	 *            Field to be validated.
	 * @param underValidation
	 *            Objects already under validation (used to prevent infinity cycles).
	 * @throws FailedValidationsException
	 *             If there are failed validations for the field.
	 */
	private static void validateFieldRecursively(final Object object, final Field field,
			final Set<Object> underValidation) throws FailedValidationsException {
		// List with the failed validations.
		final List<Exception> failedValidations = new ArrayList<Exception>();
		// Tries to get the field value.
		try {
			final Object fieldValue = FieldHandler.getFieldValue(object, field.getName(), false);
			// If the field value is not null.
			if (fieldValue != null) {
				// If the field value is an array.
				if (fieldValue.getClass().isArray()) {
					// For each object in the array.
					for (Object currentFieldValueItem : (Object[]) fieldValue) {
						// If the current value is not null.
						if (currentFieldValueItem != null) {
							// Tries to validate the value as an object.
							try {
								validateObject(currentFieldValueItem, underValidation);
							}
							// If the validation has failed.
							catch (FailedValidationsException exception) {
								// Adds the failed validations to the list.
								failedValidations.addAll(exception.getFailedValidations());
							}
						}
					}
				}
				// If it is iterable.
				else if (Iterable.class.isAssignableFrom(fieldValue.getClass())) {
					// For each object in the array.
					for (Object currentFieldValueItem : (Iterable<?>) fieldValue) {
						// If the current value is not null.
						if (currentFieldValueItem != null) {
							// Tries to validate the value as an object.
							try {
								validateObject(currentFieldValueItem, underValidation);
							}
							// If the validation has failed.
							catch (FailedValidationsException exception) {
								// Adds the failed validations to the list.
								failedValidations.addAll(exception.getFailedValidations());
							}
						}
					}
				}
				// Otherwise.
				else {
					// Tries to validate the value as an object.
					try {
						validateObject(fieldValue, underValidation);
					}
					// If the validation has failed.
					catch (FailedValidationsException exception) {
						// Adds the failed validations to the list.
						failedValidations.addAll(exception.getFailedValidations());
					}
				}
			}
		}
		// If the getter cannot be invoked.
		catch (InvalidParameterException exception) {
			// Add a failed validation exception to the list.
			failedValidations.add(new ImpossibleValidationException(getFailedValidationKey(field, null),
					exception, null));
		}
		// If the getter throws an exception.
		catch (InvocationException exception) {
			// Add a failed validation exception to the list.
			failedValidations.add(new ImpossibleValidationException(getFailedValidationKey(field, null),
					exception, null));
		}
		// If there are failed validations.
		if (!failedValidations.isEmpty()) {
			// Throws an exception with the failed validations.
			throw new FailedValidationsException(failedValidations);
		}
	}
	
	/**
	 * Validates a field value. The validation annotations will be used.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param field
	 *            Field to be validated.
	 * @param underValidation
	 *            Objects already under validation (used to prevent infinity cycles).
	 * @throws FailedValidationsException
	 *             If there are failed validations for the field.
	 */
	private static void validateField(final Object object, final Field field,
			final Set<Object> underValidation) throws FailedValidationsException {
		// List with the failed validations.
		final List<Exception> failedValidations = new ArrayList<Exception>();
		// Gets the annotation with the validations information.
		final Validations validations = field.getAnnotation(Validations.class);
		// If there are any validations.
		if (validations != null) {
			// For each validation method.
			for (ValidationMethod currentValidationMethod : validations.validationMethods()) {
				// Tries to run the current validation method.
				try {
					validateField(object, field, currentValidationMethod);
				}
				// If the validation has failed.
				catch (Exception exception) {
					// Adds the failed validation in the failed validations list.
					failedValidations.add(exception);
				}
			}
			// If the validation is defined as recursive.
			if (validations.recursiveValidation()) {
				// Tries to validate the field recursively.
				try {
					validateFieldRecursively(object, field, underValidation);
				}
				// If the validation has failed.
				catch (FailedValidationsException exception) {
					// Adds the failed validations in the failed validations list.
					failedValidations.addAll(exception.getFailedValidations());
				}
			}
		}
		// If there are failed validations.
		if (!failedValidations.isEmpty()) {
			// Throws an exception with the failed validations.
			throw new FailedValidationsException(failedValidations);
		}
	}
	
	/**
	 * Validates a field value. The validation annotations will be used.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param field
	 *            Field to be validated.
	 * @throws FailedValidationsException
	 *             If there are failed validations for the field.
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty).
	 */
	public static void validateField(final Object object, final Field field)
			throws FailedValidationsException, InvalidParameterException {
		// If the object is null.
		if (object == null) {
			// Throws an exception. 
			throw new EmptyParameterException("class"); // TODO
		}
		// Objects already under validation (used to prevent infinity cycles).
		final Set<Object> underValidation = new HashSet<Object>();
		// Tries to validate the field.
		validateField(object, field, underValidation);
	}
	
	/**
	 * Validates a field value. The validation annotations will be used.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param fieldPath
	 *            Path for the field to be validated.
	 * @throws FailedValidationsException
	 *             If there are failed validations for the field.
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty or not accessible).
	 * @throws InvocationException
	 *             If an exception is thrown by a getter of the field.
	 */
	public static void validateField(Object object, final String fieldPath)
			throws FailedValidationsException, InvalidParameterException, InvocationException {
		// If the object is null.
		if (object == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// Find the position of the last '.'.
		final Integer lastFieldStartPos = fieldPath.lastIndexOf('.') + 1;
		// If there is only one field.
		if (lastFieldStartPos <= 0) {
			// Gets the path for the fields (but for the last one).
			final String firstFieldsPath = fieldPath.substring(0, lastFieldStartPos - 1);
			// Gets the value of the field right before the last one.
			object = FieldHandler.getFieldValue(object, firstFieldsPath, false);
		}
		// If the object is not null.
		if (object != null) {
			// Gets the last field name.
			final String lastFieldName = fieldPath.substring(lastFieldStartPos);
			// Uses the value of the field before the last as the object to
			// validate the field.
			final Field field = FieldHandler.getField(object.getClass(), lastFieldName);
			// Validates the field.
			validateField(object, field);
		}
	}
	
	/**
	 * Validates an object. The validation annotations will be used.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @param underValidation
	 *            Objects already under validation (used to prevent infinity cycles).
	 * @throws FailedValidationsException
	 *             If there are failed validations for the field.
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty).
	 */
	private static void validateObject(final Object object, final Set<Object> underValidation)
			throws FailedValidationsException, InvalidParameterException {
		// If the object is null.
		if (object == null) {
			// Throws an exception.
			throw new EmptyParameterException("class"); // TODO
		}
		// List with the failed validations.
		final List<Exception> failedValidations = new ArrayList<Exception>();
		// If the object is not already under validation.
		if (!underValidation.contains(object)) {
			// Adds it to the validation list.
			underValidation.add(object);
			// For each field of the object.
			for (Field currentField : FieldHandler.getAllFields(object.getClass())) {
				// Tries to validate the current field.
				try {
					validateField(object, currentField, underValidation);
				}
				// If the validation has failed.
				catch (FailedValidationsException exception) {
					// Adds the failed validations in the failed validations list.
					failedValidations.addAll(exception.getFailedValidations());
				}
			}
		}
		// If there are failed validations.
		if (!failedValidations.isEmpty()) {
			// Throws an exception with the failed validations.
			throw new FailedValidationsException(failedValidations);
		}
	}
	
	/**
	 * Validates an object. The validation annotations will be used.
	 * 
	 * @param object
	 *            Object to be validated.
	 * @throws FailedValidationsException
	 *             If there are failed validations for the field.
	 * @throws InvalidParameterException
	 *             If the parameters for the method are not valid (empty).
	 */
	public static void validateObject(final Object object) throws FailedValidationsException,
			InvalidParameterException {
		// Objects already under validation (used to prevent infinity cycles).
		final Set<Object> underValidation = new HashSet<Object>();
		// Tries to validate the object.
		validateObject(object, underValidation);
	}
}
