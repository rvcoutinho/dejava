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
	 * @param bundleInfo
	 *            Information regarding the message bundle to be used in the message retrieval.
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
	 *             If the message cannot be found.
	 * @throws InvalidParameterException
	 *             If any of the parameters values are not valid.
	 */
	String addMessage(Object bundleInfo, Locale locale, String type, String key, Object[] parametersValues)
			throws MessageNotFoundException, InvalidParameterException;

}
