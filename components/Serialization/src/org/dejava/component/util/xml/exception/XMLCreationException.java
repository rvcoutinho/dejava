package org.dejava.component.util.xml.exception;

import org.dejava.component.util.exception.localized.AbstractLocalizedException;

/**
 * Exception related to the creation of a XML.
 */
public class XMLCreationException extends AbstractLocalizedException {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 58316167132809394L;
	
	/**
	 * @see AbstractLocalizedException#AbstractLocalizedException(String, Throwable, Object[])
	 */
	public XMLCreationException(final String message, final Throwable cause, final Object[] parameters) {
		super(message, cause, parameters);
	}
}
