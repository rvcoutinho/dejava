package org.dejava.component.util.i18n.message.handler.impl;

import java.util.Locale;

import org.dejava.component.util.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.util.i18n.message.handler.MessageCommand;
import org.dejava.component.util.i18n.message.handler.MessageHandler;

/**
 * The default implementation of the {@link MessageCommand}.
 */
public class DefaultMessageCommand implements MessageCommand {
	
	/**
	 * Message handler to render localized messages.
	 */
	private MessageHandler messageHandler;
	
	/**
	 * Gets the message handler to render localized messages.
	 * 
	 * @return The message handler to render localized messages.
	 */
	protected MessageHandler getMessageHandler() {
		// If the message handler is null.
		if (messageHandler == null) {
			// Creates a default message handler for the default locale.
			messageHandler = DefaultI18nMessageHandler.getMessageHandler(Locale.getDefault());
		}
		// Returns the message handler.
		return messageHandler;
	}
	
	/**
	 * Sets the message handler to render localized messages.
	 * 
	 * @param messageHandler
	 *            The message handler to render localized messages.
	 */
	protected final void setMessageHandler(final MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}
	
	/**
	 * Message handler to render messages for the en_US locale.
	 */
	private MessageHandler usMessageHandler;
	
	/**
	 * Gets the message handler to render messages for the en_US locale.
	 * 
	 * @return The message handler to render messages for the en_US locale.
	 */
	protected MessageHandler getUsMessageHandler() {
		// If the message handler is null.
		if (messageHandler == null) {
			// Creates a default message handler for the default locale.
			messageHandler = DefaultI18nMessageHandler.getMessageHandler(Locale.US);
		}
		// Returns the message handler.
		return messageHandler;
	}
	
	/**
	 * @see org.dejava.component.util.i18n.message.handler.MessageCommand#setLocale(java.util.Locale)
	 */
	@Override
	public final void setLocale(final Locale locale) {
		// Creates a new message handler for the given locale.
		setMessageHandler(DefaultI18nMessageHandler.getMessageHandler(locale));
	}
	
	/**
	 * Type for the message.
	 */
	private String type;
	
	/**
	 * Gets the type for the message.
	 * 
	 * @return The type for the message.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @see org.dejava.component.util.i18n.message.handler.MessageCommand#setType(java.lang.String)
	 */
	@Override
	public void setType(final String type) {
		this.type = type;
	}
	
	/**
	 * Parameters for the message.
	 */
	private Object[] parameters;
	
	/**
	 * Returns the parameters for the message.
	 * 
	 * @return The parameters for the message.
	 */
	public Object[] getParameters() {
		// If the array is null.
		if (parameters == null) {
			// Creates an empty array.
			parameters = new Object[0];
		}
		// Returns the parameters.
		return parameters;
	}
	
	/**
	 * @see org.dejava.component.util.i18n.message.handler.MessageCommand#setParameters(java.lang.Object[])
	 */
	@Override
	public void setParameters(final Object[] parameters) {
		this.parameters = parameters;
	}
	
	/**
	 * Key for the message.
	 */
	private String messageKey;
	
	/**
	 * Gets the key for the message.
	 * 
	 * @return The key for the message.
	 */
	public String getMessageKey() {
		return messageKey;
	}
	
	/**
	 * @see org.dejava.component.util.i18n.message.handler.MessageCommand#setMessageKey(java.lang.String)
	 */
	@Override
	public void setMessageKey(final String messageKey) {
		this.messageKey = messageKey;
	}
	
	/**
	 * Default message command constructor.
	 */
	public DefaultMessageCommand() {
		super();
	}
	
	/**
	 * Default message command constructor.
	 * 
	 * @param locale
	 *            Locale for the message.
	 * @param type
	 *            Type for the message.
	 * @param parameters
	 *            Parameters for the message.
	 * @param messageKey
	 *            Key for the message.
	 */
	public DefaultMessageCommand(final Locale locale, final String type, final Object[] parameters,
			final String messageKey) {
		super();
		// Sets the locale.
		setLocale(locale);
		// Sets the main fields for the object.
		this.type = type;
		this.parameters = parameters;
		this.messageKey = messageKey;
	}
	
	/**
	 * @see org.dejava.component.util.i18n.message.handler.MessageCommand#getMessage()
	 */
	@Override
	public String getMessage() throws MessageNotFoundException {
		// Tries to return the localized message.
		return getMessageHandler().getMessage(getType(), getMessageKey(), getParameters());
	}
	
	/**
	 * @see org.dejava.component.util.i18n.message.handler.MessageCommand#getUsMessage()
	 */
	@Override
	public String getUsMessage() throws MessageNotFoundException {
		// Tries to return the localized message.
		return getUsMessageHandler().getMessage(getType(), getMessageKey(), getParameters());
	}
}
