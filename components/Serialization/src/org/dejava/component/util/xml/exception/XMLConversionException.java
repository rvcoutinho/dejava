package org.dejava.component.util.xml.exception;

import org.dejava.component.util.exception.localized.AbstractLocalizedException;

/**
 * Exception related to the conversion from/to XML.
 */
public class XMLConversionException extends AbstractLocalizedException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -2664321495227876291L;
	
	/**
	 * @see AbstractLocalizedException#AbstractLocalizedException(String, Throwable, Object[])
	 */
	public XMLConversionException(final String message, final Throwable cause, final Object[] parameters) {
		super(message, cause, parameters);
	}
}
