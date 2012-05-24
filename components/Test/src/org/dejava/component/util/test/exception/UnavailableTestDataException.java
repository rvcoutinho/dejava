package org.dejava.component.util.test.exception;

import org.dejava.component.util.exception.localized.AbstractLocalizedException;

/**
 * Exception related to unavailable test data.
 */
public class UnavailableTestDataException extends AbstractLocalizedException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -7501622556068793489L;
	
	/**
	 * @see AbstractLocalizedException#AbstractLocalizedException(String, Throwable, Object[])
	 */
	public UnavailableTestDataException(final String messageKey, final Throwable cause,
			final Object[] parameters) {
		super(messageKey, cause, parameters);
	}
	
}
