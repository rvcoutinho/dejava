package org.dejava.component.util.validation.exception;

import org.dejava.component.util.exception.localized.checked.AbstractLocalizedException;

/**
 * Exception related to the impossibility to run a validation.
 */
public class ImpossibleValidationException extends AbstractLocalizedException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -5378438036457592281L;
	
	/**
	 * @see AbstractLocalizedException#AbstractLocalizedException(String, Throwable, Object[])
	 */
	public ImpossibleValidationException(final String message, final Throwable cause,
			final Object[] parameters) {
		super(message, cause, parameters);
	}
}
