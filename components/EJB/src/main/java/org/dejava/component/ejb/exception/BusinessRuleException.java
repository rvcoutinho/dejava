package org.dejava.component.ejb.exception;

import javax.ejb.ApplicationException;

import org.dejava.component.exception.localized.checked.AbstractLocalizedException;

/**
 * Exception related business rules.
 */
@ApplicationException(rollback = true)
public class BusinessRuleException extends AbstractLocalizedException {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -315210606548717238L;

	/**
	 * @see AbstractLocalizedException#AbstractLocalizedException(Object, String, Object[], Throwable)
	 */
	public BusinessRuleException(final Object type, final String messageKey, final Object[] parameters,
			final Throwable cause) {
		super(type, messageKey, parameters, cause);
	}
}
