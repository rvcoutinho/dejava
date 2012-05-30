package org.dejava.component.util.test;

import java.util.Locale;

import org.dejava.component.util.message.handler.I18nMessageHandler;
import org.dejava.component.util.message.handler.impl.DefaultMessageHandler;
import org.dejava.component.util.message.model.ApplicationMessageType;
import org.junit.Assert;

/**
 * Localized assert class.
 */
public final class LocalizedAssert extends Assert {
	
	/**
	 * Locale for the current assertion messages. Will be used to render localized messages.
	 */
	private static Locale locale;
	
	/**
	 * Gets the locale for the current assertion messages. Will be used to render localized messages.
	 * 
	 * @return The locale for the current assertion messages. Will be used to render localized messages.
	 */
	public static Locale getLocale() {
		// If the locale is null.
		if (locale == null) {
			// Uses the default locale.
			locale = Locale.getDefault();
		}
		// Returns the locale.
		return locale;
	}
	
	/**
	 * Sets the locale for the current assertion messages. Will be used to render localized messages.
	 * 
	 * @param locale
	 *            The locale for the current assertion messages. Will be used to render localized messages.
	 */
	public static void setLocale(final Locale locale) {
		// Sets the locale.
		LocalizedAssert.locale = locale;
		// Forces the message handler to be reloaded.
		setMessageHandler(null);
	}
	
	/**
	 * Message handler to render localized messages.
	 */
	private static I18nMessageHandler messageHandler;
	
	/**
	 * Gets the message handler to render localized messages.
	 * 
	 * @return The message handler to render localized messages.
	 */
	public static I18nMessageHandler getMessageHandler() {
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
	public static void setMessageHandler(final I18nMessageHandler messageHandler) {
		LocalizedAssert.messageHandler = messageHandler;
	}
	
	/**
	 * Default constructor.
	 */
	protected LocalizedAssert() {
		super();
	}
	
	/**
	 * TODO
	 * 
	 * @param messageKey
	 *            f
	 */
	public static void fail(final String messageKey) {
		// Tries to raise a new assertion error with the message for the given key.
		try {
			throw new AssertionError(getMessageHandler().getMessage(getLocale(),
					ApplicationMessageType.ERROR, messageKey, null, true));
		}
		// If it is not possible to find the appropriate message.
		catch (Exception exception) {
			// Throws a new assertion error (with no message).
			throw new AssertionError();
		}
	}
}
