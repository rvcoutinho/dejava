package org.dejava.component.util.message.handler;

import java.util.Locale;

import org.dejava.component.util.exception.localized.InvalidParameterException;
import org.dejava.component.util.message.exception.MessageNotFoundException;
import org.dejava.component.util.message.model.ApplicationMessageType;

/**
 * Helps handling internationalization messages.
 */
public interface I18nMessageHandler {
	
	/**
	 * Gets a message with the given key and parameters values of the defined type.
	 * 
	 * @param locale
	 *            Locale for the message.
	 * @param type
	 *            Type of the message.
	 * @param key
	 *            Key for the message in the bundle.
	 * @param parametersValues
	 *            Parameter values for the message.
	 * @param exceptionOnUnaccessible
	 *            If an exception must be thrown when the message is not accessible. If false, tries to add
	 *            the message with default locale.
	 * @return A message with the given key and parameters values of the defined type.
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 * @throws InvalidParameterException
	 *             If the parameter values are not valid.
	 */
	String getMessage(Locale locale, ApplicationMessageType type, String key, Object[] parametersValues,
			Boolean exceptionOnUnaccessible) throws MessageNotFoundException, InvalidParameterException;
}
