package org.dejava.component.i18n.message.handler;

import java.io.Serializable;
import java.util.Locale;

import org.dejava.component.i18n.message.exception.MessageNotFoundException;

/**
 * An implementation of the command design pattern in order to enable messages to be processed later.
 */
public interface MessageCommand extends Serializable {

	/**
	 * Sets the type for the message.
	 * 
	 * @param type
	 *            New type for the message.
	 */
	void setType(Object type);

	/**
	 * Gets the locale for the message.
	 * 
	 * @return The locale for the message.
	 */
	Locale getLocale();

	/**
	 * Sets the locale for the message.
	 * 
	 * @param locale
	 *            New locale for the message.
	 */
	void setLocale(final Locale locale);

	/**
	 * Sets the key for the message.
	 * 
	 * @param messageKey
	 *            New key for the message.
	 */
	void setMessageKey(final String messageKey);

	/**
	 * Sets the parameters for the message.
	 * 
	 * @param parameters
	 *            New parameters for the message.
	 */
	void setParameters(final Object[] parameters);

	/**
	 * Gets a message by evaluating the given information.
	 * 
	 * @param messageHandler
	 *            The message handler to be used.
	 * @return The evaluated localized message.
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 */
	String getMessage(MessageHandler messageHandler) throws MessageNotFoundException;

	/**
	 * Adds a message to the application context by evaluating the given information.
	 * 
	 * @param appMessageHandler
	 *            The application message handler to be used.
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 */
	void addMessage(ApplicationMessageHandler appMessageHandler) throws MessageNotFoundException;

}
