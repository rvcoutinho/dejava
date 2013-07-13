package org.dejava.component.i18n.message.handler.impl;

import java.util.Locale;

import org.dejava.component.i18n.message.annotation.MessageBundle;
import org.dejava.component.i18n.message.constant.ErrorKeys;
import org.dejava.component.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.i18n.message.handler.ApplicationMessageHandler;
import org.dejava.component.i18n.message.handler.MessageCommand;
import org.dejava.component.i18n.message.handler.MessageHandler;
import org.dejava.component.i18n.message.util.MessageTypes;
import org.dejava.component.validation.method.PreConditions;

/**
 * The default implementation of the {@link MessageCommand}.
 */
public class SimpleMessageCommand implements MessageCommand {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4013415156070982536L;

	/**
	 * Locale for the message.
	 */
	private Locale locale;

	/**
	 * @see org.dejava.component.i18n.message.handler.MessageCommand#getLocale()
	 */
	@Override
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
	 * Type of the message. Must be annotated with {@link MessageBundle}.
	 */
	private Class<?> type;

	/**
	 * Gets the type for the message.
	 * 
	 * @return The type for the message. Must be annotated with {@link MessageBundle}.
	 */
	public Class<?> getType() {
		return type;
	}

	/**
	 * Sets the type for the message.
	 * 
	 * New type for the message. Must be a class annotated with {@link MessageBundle}.
	 */
	@Override
	public void setType(final Object type) {
		// Assures that the type is a class.
		PreConditions.assertParamValid(type instanceof Class<?>, MessageTypes.Error.class,
				ErrorKeys.INVALID_TYPE, new Object[] { type });
		// Sets the new type.
		this.type = (Class<?>) type;
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
	 * @param type
	 *            Type for the message. Must be annotated with {@link MessageBundle}.
	 * @param locale
	 *            Locale for the message.
	 * @param messageKey
	 *            Key for the message.
	 * @param parameters
	 *            Parameters for the message.
	 */
	public SimpleMessageCommand(final Class<?> type, final Locale locale, final String messageKey,
			final Object[] parameters) {
		// Sets the main fields for the object.
		this.type = type;
		this.locale = locale;
		this.parameters = parameters;
		this.messageKey = messageKey;
	}

	/**
	 * @see org.dejava.component.i18n.message.handler.MessageCommand#getMessage(org.dejava.component.i18n.message.handler.MessageHandler)
	 */
	@Override
	public String getMessage(final MessageHandler messageHandler) throws MessageNotFoundException {
		// Tries to return the localized message.
		return messageHandler.getMessage(getType(), getLocale(), getMessageKey(), getParameters());
	}

	/**
	 * @see org.dejava.component.i18n.message.handler.MessageCommand#addMessage(org.dejava.component.i18n.message.handler.ApplicationMessageHandler)
	 */
	@Override
	public void addMessage(final ApplicationMessageHandler appMessageHandler) throws MessageNotFoundException {
		// Tries to add the localized message.
		appMessageHandler.addMessage(getType(), getLocale(), getMessageKey(), getParameters());
	}

}
