package org.dejava.component.util.message.handler;

import java.util.Locale;

import org.dejava.component.util.exception.localized.InvalidParameterException;
import org.dejava.component.util.message.exception.MessageNotFoundException;
import org.dejava.component.util.message.model.ApplicationMessageType;

/**
 * Helps handling internationalization messages in a application environment.
 */
public interface I18nApplicationMessageHandler extends I18nMessageHandler {
	
	/**
	 * Adds a message with the given key and parameters values of the defined type to the application.
	 * 
	 * @param locale
	 *            Locale for the message.
	 * @param type
	 *            Type of the message.
	 * @param key
	 *            Key for the message in the bundle.
	 * @param parametersValues
	 *            Parameter values for the message.
	 * @return A message with the given key and parameters values of the defined type.
	 * @throws MessageNotFoundException
	 *             If the message cannot be added to the application.
	 * @throws InvalidParameterException
	 *             If the parameter values are not valid.
	 */
	String addMessage(Locale locale, ApplicationMessageType type, String key, Object[] parametersValues)
			throws MessageNotFoundException, InvalidParameterException;
}
