package org.dejava.component.util.validation.exception;

import java.util.Collection;

import org.dejava.component.util.exception.localized.checked.BusinessRuleException;

/**
 * Exception related to an specific failed validation.
 */
public class FailedValidationsException extends BusinessRuleException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 5356509770929417083L;
	
	/**
	 * Constructor.
	 * 
	 * @param failedValidations
	 *            Validations that have failed
	 */
	public FailedValidationsException(final Collection<Exception> failedValidations) {
		super(null, null, null);
		// Sets the failed validations.
		this.failedValidations = failedValidations;
	}
	
	/**
	 * Validations that have failed.
	 */
	private Collection<Exception> failedValidations;
	
	/**
	 * Gets the validations that have failed.
	 * 
	 * @return The validations that have failed.
	 */
	public Collection<Exception> getFailedValidations() {
		return failedValidations;
	}
	
	/**
	 * Sets the validations that have failed.
	 * 
	 * @param failedValidations
	 *            New validations that have failed.
	 */
	public void setFailedValidations(final Collection<Exception> failedValidations) {
		this.failedValidations = failedValidations;
	}
}
