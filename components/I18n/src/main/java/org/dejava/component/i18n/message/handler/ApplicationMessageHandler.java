package org.dejava.component.i18n.message.handler;

import java.util.Locale;

import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.i18n.message.exception.MessageNotFoundException;

/**
 * Helps handling internationalization messages in a application environment.
 */
public interface ApplicationMessageHandler extends MessageHandler {

	/**
	 * Adds a message with the given key and parameters values of the defined type to the application.
	 * 
	 * @param type
	 *            Type of the message (containing the bundle information).
	 * @param locale
	 *            Locale for the message.
	 * @param key
	 *            Key for the message in the bundle.
	 * @param parametersValues
	 *            Parameter values for the message.
	 * @return A message with the given key and parameters values of the defined type.
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 * @throws InvalidParameterException
	 *             If any of the parameters values are not valid.
	 */
	String addMessage(final Object type, final Locale locale, String key, Object[] parametersValues)
			throws MessageNotFoundException, InvalidParameterException;

}
