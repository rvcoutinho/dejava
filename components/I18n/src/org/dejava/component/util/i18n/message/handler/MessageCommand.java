package org.dejava.component.util.i18n.message.handler;

import java.util.Locale;

import org.dejava.component.util.i18n.message.exception.MessageNotFoundException;

/**
 * An implementation of the command design pattern in order to enable messages to be processed later.
 */
public interface MessageCommand {
	
	/**
	 * Sets the locale for the message.
	 * 
	 * @param locale
	 *            New locale for the message.
	 */
	void setLocale(final Locale locale);
	
	/**
	 * Sets the type for the message.
	 * 
	 * @param type
	 *            New type for the message.
	 */
	void setType(String type);
	
	/**
	 * Sets the parameters for the message.
	 * 
	 * @param parameters
	 *            New parameters for the message.
	 */
	void setParameters(final Object[] parameters);
	
	/**
	 * Sets the key for the message.
	 * 
	 * @param messageKey
	 *            New key for the message.
	 */
	void setMessageKey(final String messageKey);
	
	/**
	 * Gets a message by evaluating the given information.
	 * 
	 * @return The evaluated localized message.
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 */
	String getMessage() throws MessageNotFoundException;
	
}
