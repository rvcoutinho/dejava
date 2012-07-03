package org.dejava.component.util.i18n.message.handler;

import java.util.Locale;

import org.dejava.component.util.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.util.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.util.i18n.message.handler.model.ApplicationMessageType;

/**
 * Helps handling internationalization messages.
 */
public interface MessageHandler {
	
	/**
	 * Gets the locale for the messages.
	 * 
	 * @return Locale for the messages.
	 */
	Locale getLocale();
	
	/**
	 * Gets a message with the given key and parameters values of the defined type.
	 * 
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
	String getMessage(String key, Object[] parametersValues) throws MessageNotFoundException,
			InvalidParameterException;
	
	/**
	 * Gets a message with the given key and parameters values of the defined type.
	 * 
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
	String getMessage(String type, String key, Object[] parametersValues) throws MessageNotFoundException,
			InvalidParameterException;
	
	/**
	 * Gets a message with the given key and parameters values of the defined type.
	 * 
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
	String getMessage(ApplicationMessageType type, String key, Object[] parametersValues)
			throws MessageNotFoundException, InvalidParameterException;
}
