package org.dejava.component.util.validation.exception;

import org.dejava.component.util.exception.localized.checked.AbstractLocalizedException;
import org.dejava.component.util.exception.localized.checked.BusinessRuleException;

/**
 * Exception related to the impossibility to run a validation.
 */
public class FailedValidationException extends BusinessRuleException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -5378438036457592281L;
	
	/**
	 * @see AbstractLocalizedException#AbstractLocalizedException(String, Throwable, Object[])
	 */
	public FailedValidationException(final String message, final Throwable cause, final Object[] parameters) {
		super(message, cause, parameters);
	}
}
