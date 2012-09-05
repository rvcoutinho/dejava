package org.dejava.component.exception.localized.checked;

/**
 * Exception related business rules.
 */
public class BusinessRuleException extends AbstractLocalizedException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -315210606548717238L;
	
	/**
	 * @see AbstractLocalizedException#AbstractLocalizedException(String, Throwable, Object[])
	 */
	public BusinessRuleException(final String message, final Throwable cause, final Object[] parameters) {
		super(message, cause, parameters);
	}
}
