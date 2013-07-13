package org.dejava.component.validation.object;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.metadata.BeanDescriptor;

/**
 * A validator implementation that throws exceptions when there are constraint violations.
 */
public class ThrowerValidator implements Validator {

	/**
	 * The decorated validator to be used.
	 */
	private final Validator decoratedValidator;

	/**
	 * Throws an exception with the given violations or returns null.
	 * 
	 * @param <Bean>
	 *            The bean being validated.
	 * @param violations
	 *            The constraint violations that might have been fired.
	 * @return Returns null (when no violations are given).
	 * @throws ValidationException
	 *             An exception with all the violations.
	 */
	private <Bean> Set<ConstraintViolation<Bean>> throwConstraintException(
			final Set<ConstraintViolation<Bean>> violations) throws ValidationException {
		// If there are no constraint violations.
		if ((violations == null) || (violations.isEmpty())) {
			// Returns null.
			return null;
		}
		// If there are violations.
		else {
			// Throws a validation exception.
			throw new ValidationException(new HashSet<ConstraintViolation<?>>(violations));
		}
	}

	/**
	 * @see javax.validation.Validator#validate(java.lang.Object, java.lang.Class[])
	 */
	@Override
	public <Bean> Set<ConstraintViolation<Bean>> validate(final Bean object, final Class<?>... groups) {
		return throwConstraintException(decoratedValidator.validate(object, groups));
	}

	/**
	 * @see javax.validation.Validator#validateProperty(java.lang.Object, java.lang.String, java.lang.Class[])
	 */
	@Override
	public <Bean> Set<ConstraintViolation<Bean>> validateProperty(final Bean object,
			final String propertyName, final Class<?>... groups) {
		return throwConstraintException(decoratedValidator.validateProperty(object, propertyName, groups));
	}

	/**
	 * @see javax.validation.Validator#validateValue(java.lang.Class, java.lang.String, java.lang.Object,
	 *      java.lang.Class[])
	 */
	@Override
	public <Bean> Set<ConstraintViolation<Bean>> validateValue(final Class<Bean> beanType,
			final String propertyName, final Object value, final Class<?>... groups) {
		return throwConstraintException(decoratedValidator.validateValue(beanType, propertyName, value,
				groups));
	}

	/**
	 * @see javax.validation.Validator#getConstraintsForClass(java.lang.Class)
	 */
	@Override
	public BeanDescriptor getConstraintsForClass(final Class<?> clazz) {
		return decoratedValidator.getConstraintsForClass(clazz);
	}

	/**
	 * @see javax.validation.Validator#unwrap(java.lang.Class)
	 */
	@Override
	public <Bean> Bean unwrap(final Class<Bean> type) {
		return decoratedValidator.unwrap(type);
	}

	/**
	 * Decorator constructor.
	 * 
	 * @param decoratedValidator
	 *            The validator implementation to be decorated.
	 */
	public ThrowerValidator(final Validator decoratedValidator) {
		this.decoratedValidator = decoratedValidator;
	}

	/**
	 * Gets the thrower validator that decorates the default validator implementation.
	 * 
	 * @return The thrower validator that decorates the default validator implementation.
	 */
	public static Validator getDefaultThrowerValidator() {
		return new ThrowerValidator(Validation.buildDefaultValidatorFactory().getValidator());
	}

}
