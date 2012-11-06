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
	 * @see AbstractLocalizedException#AbstractLocalizedException(Object, String, Object[], Throwable)
	 */
	public BusinessRuleException(final Object bundleInfo, final String messageKey,
			final Object[] parameters, final Throwable cause) {
		super(bundleInfo, messageKey, parameters, cause);
	}
}
