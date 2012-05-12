package org.dejava.component.util.exception.localized;

import java.util.Locale;

import org.dejava.component.util.message.handler.I18nMessageHandler;
import org.dejava.component.util.message.handler.impl.DefaultMessageHandler;
import org.dejava.component.util.message.model.ApplicationMessageType;

/**
 * General localized exception.
 */
public abstract class AbstractLocalizedException extends Exception {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -6987571772328004454L;
	
	/**
	 * Locale for the current exception. Will be used to render a localized message.
	 */
	private Locale locale;
	
	/**
	 * Gets the locale for the current exception. Will be used to render a localized message.
	 * 
	 * @return The locale for the current exception. Will be used to render a localized message.
	 */
	public Locale getLocale() {
		// If the locale is null.
		if (locale == null) {
			// Uses the default locale.
			locale = Locale.getDefault();
		}
		// Returns the locale.
		return locale;
	}
	
	/**
	 * Sets the locale for the current exception. Will be used to render a localized message.
	 * 
	 * @param locale
	 *            The locale for the current exception. Will be used to render a localized message.
	 */
	public void setLocale(final Locale locale) {
		// Sets the locale.
		this.locale = locale;
		// Forces the message handler to be reloaded.
		setMessageHandler(null);
	}
	
	/**
	 * Message handler to render localized messages.
	 */
	private I18nMessageHandler messageHandler;
	
	/**
	 * Gets the message handler to render localized messages.
	 * 
	 * @return The message handler to render localized messages.
	 */
	public I18nMessageHandler getMessageHandler() {
		// If the message handler is null.
		if (messageHandler == null) {
			// Creates a default message handler for the locale.
			messageHandler = DefaultMessageHandler.getMessageHandler(getLocale());
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
	public void setMessageHandler(final I18nMessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}
	
	/**
	 * Key for the message of the exception. Will be used to render a localized message.
	 */
	private String messageKey;
	
	/**
	 * Gets the key for the message of the exception. Will be used to render a localized message.
	 * 
	 * @return The key for the message of the exception. Will be used to render a localized message.
	 */
	public String getMessageKey() {
		return messageKey;
	}
	
	/**
	 * Sets the key for the message of the exception. Will be used to render a localized message.
	 * 
	 * @param messageKey
	 *            New key for the message of the exception. Will be used to render a localized message.
	 */
	public void setMessageKey(final String messageKey) {
		this.messageKey = messageKey;
	}
	
	/**
	 * Parameters for the exception. Will be used to handle localized messages.
	 */
	private Object[] parameters;
	
	/**
	 * Returns the parameters.
	 * 
	 * @return The parameters.
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
	 * Sets the parameters.
	 * 
	 * @param parameters
	 *            New parameters.
	 */
	public void setParameters(final Object[] parameters) {
		this.parameters = parameters;
	}
	
	/**
	 * Basic constructor.
	 * 
	 * @param messageKey
	 *            Message key that describes the exception.
	 * @param cause
	 *            Exception cause.
	 * @param parameters
	 *            Parameters for the exception (and message).
	 */
	public AbstractLocalizedException(final String messageKey, final Throwable cause,
			final Object[] parameters) {
		// Calls the Exception constructor.
		super(cause);
		// Sets the basic fields.
		this.messageKey = messageKey;
		this.parameters = parameters;
	}
	
	/**
	 * @see Throwable#getLocalizedMessage()
	 */
	@Override
	public String getMessage() {
		// Tries to return the localized message.
		try {
			return getMessageHandler().getMessage(getLocale(), ApplicationMessageType.ERROR, getMessageKey(),
					getParameters(), true);
		}
		// If the localized message is not found.
		catch (Exception exception) {
			// Returns the raw message key.
			return getMessageKey();
		}
	}
	
	/**
	 * Returns the localized message for the given locale.
	 * 
	 * @param locale
	 *            Locale to render the message.
	 * @return The localized message for the given locale.
	 */
	public String getMessage(final Locale locale) {
		// Sets the locale for the exception.
		setLocale(locale);
		// Returns the localized message.
		return getLocalizedMessage();
	}
}