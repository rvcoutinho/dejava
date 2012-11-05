package org.dejava.component.i18n.message.handler.impl;

import java.util.Locale;

import org.dejava.component.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.i18n.message.handler.MessageCommand;
import org.dejava.component.i18n.message.handler.MessageHandler;

/**
 * The default implementation of the {@link MessageCommand}.
 */
public class SimpleMessageCommand implements MessageCommand {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4013415156070982536L;

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
			messageHandler = SimpleMessageHandler.getMessageHandler(Locale.getDefault());
		}
		// Returns the message handler.
		return messageHandler;
	}

	/**
	 * Information regarding the message bundle to be used in the message retrieval. It might be a class
	 * (annotated with org.dejava.component.i18n.message.annotation.MessageBundles), a collection of annotated
	 * classes, or null (the stack trace classes will be used).
	 */
	private Object bundleInfo;

	/**
	 * Gets the information regarding the message bundle to be used in the message retrieval.
	 * 
	 * @return The information regarding the message bundle to be used in the message retrieval. It might be a
	 *         class (annotated with org.dejava.component.i18n.message.annotation.MessageBundles), a
	 *         collection of annotated classes, or null (the stack trace classes will be used).
	 */
	public Object getBundleInfo() {
		return bundleInfo;
	}

	/**
	 * Sets the information regarding the message bundle to be used in the message retrieval.
	 * 
	 * @param bundleInfo
	 *            New information regarding the message bundle to be used in the message retrieval. It might
	 *            be a class (annotated with org.dejava.component.i18n.message.annotation.MessageBundles), a
	 *            collection of annotated classes, or null (the stack trace classes will be used).
	 */
	@Override
	public void setBundleInfo(final Object bundleInfo) {
		this.bundleInfo = bundleInfo;
	}

	/**
	 * Locale for the message.
	 */
	private Locale locale;

	/**
	 * Gets the locale for the message.
	 * 
	 * @return The locale for the message.
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @see org.dejava.component.i18n.message.handler.MessageCommand#setLocale(java.util.Locale)
	 */
	@Override
	public final void setLocale(final Locale locale) {
		this.locale = locale;
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
	 * @see org.dejava.component.i18n.message.handler.MessageCommand#setType(java.lang.String)
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
	 * @see org.dejava.component.i18n.message.handler.MessageCommand#setParameters(java.lang.Object[])
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
	 * @see org.dejava.component.i18n.message.handler.MessageCommand#setMessageKey(java.lang.String)
	 */
	@Override
	public void setMessageKey(final String messageKey) {
		this.messageKey = messageKey;
	}

	/**
	 * Default message command constructor.
	 */
	public SimpleMessageCommand() {
		super();
	}

	/**
	 * Default message command constructor.
	 * 
	 * @param bundleInfo
	 *            Information regarding the message bundle to be used in the message retrieval. It might be a
	 *            class (annotated with org.dejava.component.i18n.message.annotation.MessageBundles), a
	 *            collection of annotated classes, or null (the stack trace classes will be used).
	 * @param locale
	 *            Locale for the message.
	 * @param type
	 *            Type for the message.
	 * @param messageKey
	 *            Key for the message.
	 * @param parameters
	 *            Parameters for the message.
	 */
	public SimpleMessageCommand(final Object bundleInfo, final Locale locale, final String type,
			final String messageKey, final Object[] parameters) {
		// Sets the main fields for the object.
		this.bundleInfo = bundleInfo;
		this.locale = locale;
		this.type = type;
		this.parameters = parameters;
		this.messageKey = messageKey;
	}

	/**
	 * @see org.dejava.component.i18n.message.handler.MessageCommand#getMessage()
	 */
	@Override
	public String getMessage() throws MessageNotFoundException {
		// Tries to return the localized message.
		return getMessageHandler().getMessage(getBundleInfo(), getLocale(), getType(), getMessageKey(),
				getParameters());
	}

	/**
	 * @see org.dejava.component.i18n.message.handler.MessageCommand#getUsMessage()
	 */
	@Override
	public String getUsMessage() throws MessageNotFoundException {
		// Tries to return the localized message.
		return getMessageHandler().getMessage(getBundleInfo(), Locale.US, getType(), getMessageKey(),
				getParameters());
	}
}
