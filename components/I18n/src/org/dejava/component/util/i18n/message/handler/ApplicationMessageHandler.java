package org.dejava.component.util.i18n.message.handler;

import org.dejava.component.util.exception.localized.InvalidParameterException;
import org.dejava.component.util.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.util.i18n.message.model.ApplicationMessageType;

/**
 * Helps handling internationalization messages in a application environment.
 */
public interface ApplicationMessageHandler extends MessageHandler {
	
	/**
	 * Adds a message with the given key and parameters values of the defined type to the application.
	 * 
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
	String addMessage(String type, String key, Object[] parametersValues) throws MessageNotFoundException,
			InvalidParameterException;
	
	/**
	 * Adds a message with the given key and parameters values of the defined type to the application.
	 * 
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
	String addMessage(ApplicationMessageType type, String key, Object[] parametersValues)
			throws MessageNotFoundException, InvalidParameterException;
}
