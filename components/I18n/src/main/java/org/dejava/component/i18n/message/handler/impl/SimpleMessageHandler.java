package org.dejava.component.i18n.message.handler.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.i18n.message.annotation.MessageBundle;
import org.dejava.component.i18n.message.constant.ErrorKeys;
import org.dejava.component.i18n.message.constant.MessageHandlerParamKeys;
import org.dejava.component.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.i18n.message.handler.MessageHandler;
import org.dejava.component.reflection.AnnotationMirror;
import org.dejava.component.reflection.ClassMirror;

/**
 * Default i18n message handler. Handles getting messages from within a class using the information provided
 * by the annotations {@link MessageBundle}. Uses the default Java i18n API.
 * 
 * It works by searching the localized message bundle information at runtime (via annotations). If the
 * localized message (with this internationalization handler) cannot be found with the information of the
 * given type, it keeps looking in the super classes (and interfaces) until the localized message is found.
 * 
 * Enjoy Java i18n and l10n!
 */
public class SimpleMessageHandler implements MessageHandler {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -6806683128717270649L;

	/**
	 * Localized message handlers stored by locale.
	 */
	private static Map<Locale, MessageHandler> messageHandlers;

	/**
	 * Gets the localized message handlers stored by locale.
	 * 
	 * @return The localized message handlers stored by locale.
	 */
	public static Map<Locale, MessageHandler> getMessageHandlers() {
		// Grants that the map will just me created once.
		synchronized (SimpleMessageHandler.class) {
			// If the map is null.
			if (messageHandlers == null) {
				// Creates a new map.
				messageHandlers = new HashMap<Locale, MessageHandler>();
			}
		}
		// Returns the map.
		return messageHandlers;
	}

	/**
	 * Sets the localized message handlers stored by locale.
	 * 
	 * @param messageHandlers
	 *            New localized message handlers stored by locale.
	 */
	public static void setMessageHandlers(final Map<Locale, MessageHandler> messageHandlers) {
		SimpleMessageHandler.messageHandlers = messageHandlers;
	}

	/**
	 * Locale to find the messages.
	 */
	private transient Locale locale;

	/**
	 * Gets the locale for the messages.
	 * 
	 * @return Locale for the messages.
	 */
	public Locale getLocale() {
		// If the locale is null.
		if (locale == null) {
			// It is the default locale.
			locale = Locale.getDefault();
		}
		// Returns the locale.
		return locale;
	}

	/**
	 * Protected constructor for the default internationalization message handler.
	 */
	protected SimpleMessageHandler() {
		super();
	}

	/**
	 * Protected constructor for the default internationalization message handler.
	 * 
	 * @param locale
	 *            Locale for the new instance.
	 */
	protected SimpleMessageHandler(final Locale locale) {
		super();
		// Sets the locale.
		this.locale = locale;
	}

	/**
	 * Gets the message handler for the current environment.
	 * 
	 * @param locale
	 *            Locale for the message handler to use.
	 * @return The message handler for the current environment.
	 */
	public static MessageHandler getMessageHandler(final Locale locale) {
		// Tries to get the default message handler for the locale in the map.
		MessageHandler messageHandler = getMessageHandlers().get(locale);
		// If the message handler is not yet.
		if (messageHandler == null) {
			// Creates a new instance of the default i18n message handler.
			messageHandler = new SimpleMessageHandler(locale);
			// Puts it in the map.
			getMessageHandlers().put(locale, messageHandler);
		}
		// Returns the message handler.
		return messageHandler;
	}

	/**
	 * Adds the parameter values to a given message.
	 * 
	 * @param message
	 *            localized message to add the parameter values.
	 * @param parametersValues
	 *            Parameter values for the message.
	 * @return The localized message with the given parameter values added.
	 */
	private String addParameters(final String message, final Object[] parametersValues) {
		// If no parameter values are given.
		if ((parametersValues == null) || (parametersValues.length == 0)) {
			// Returns the raw message.
			return message;
		}
		// Otherwise.
		else {
			// Creates a new localized message format.
			final MessageFormat messageFormat = new MessageFormat(message, getLocale());
			// Format the message.
			return messageFormat.format(parametersValues);
		}
	}

	/**
	 * Gets a localized message from a given bundle.
	 * 
	 * @param locale
	 *            Locale for the message.
	 * @param bundleBaseName
	 *            localized message bundle base name.
	 * @param key
	 *            Key for the message.
	 * @param parametersValues
	 *            Parameter values for the message.
	 * @return Returns the localized message found.
	 * @throws MissingResourceException
	 *             If localized message for the key cannot be found.
	 */
	private String getMessageByBundle(final Locale locale, final String bundleBaseName, final String key,
			final Object[] parametersValues) throws MissingResourceException {
		// Gets the bundle for the given base name.
		final ResourceBundle messageBundle = ResourceBundle.getBundle(bundleBaseName, locale);
		// Get the localized message for the key.
		final String message = messageBundle.getString(key);
		// Adds the parameters and returns the message.
		return addParameters(message, parametersValues);
	}

	/**
	 * Gets a localized message with the given key and parameters values of the defined type.
	 * 
	 * @param type
	 *            Type of the message. Must be annotated with {@link MessageBundle}.
	 * @param locale
	 *            Locale for the message. If no locale is provided, the message handler locale is used.
	 * @param key
	 *            Key for the localized message in the bundle.
	 * @param parametersValues
	 *            Parameter values for the message.
	 * @return A localized message with the given key and parameters values of the defined type.
	 * @throws MessageNotFoundException
	 *             If the localized message cannot be found.
	 * @throws InvalidParameterException
	 *             If any of the parameters values are not valid.
	 */
	public String getMessage(final Class<?> type, final Locale locale, final String key,
			final Object[] parametersValues) throws MessageNotFoundException, InvalidParameterException {
		// If the type is not given.
		if (type == null) {
			// Throws an exception.
			throw new EmptyParameterException(MessageHandlerParamKeys.TYPE);
		}
		// If the key is not given.
		if ((key == null) || (key.isEmpty())) {
			// Throws an exception.
			throw new EmptyParameterException(MessageHandlerParamKeys.KEY);
		}
		// The actual locale is the given one.
		Locale actualLocale = locale;
		// If the actual locale is null.
		if (actualLocale == null) {
			// The actual locale is the object one.
			actualLocale = getLocale();
		}
		// For each bundle annotation.
		for (final AnnotationMirror<MessageBundle> currentMessageBundle : new ClassMirror<>(type)
				.getAnnotations(MessageBundle.class)) {
			// Tries to get the message.
			try {
				return getMessageByBundle(actualLocale, currentMessageBundle.getReflectedAnnotation()
						.baseName(), key, parametersValues);
			}
			// If the message cannot be found for the current bundle information.
			catch (final Exception exception) {
				// Keeps trying with the next annotation.
			}
		}
		// If no localized message is found, throws an exception.
		throw new MessageNotFoundException(new Object[] { type, locale, key, parametersValues }, null);
	}

	/**
	 * @see org.dejava.component.i18n.message.handler.MessageHandler#getMessage(java.lang.Object,
	 *      java.util.Locale, java.lang.String, java.lang.Object[])
	 */
	@Override
	public String getMessage(final Object type, final Locale locale, final String key,
			final Object[] parametersValues) throws MessageNotFoundException, InvalidParameterException {
		// If the type is not a class.
		if (!(type instanceof Class<?>)) {
			// Throws an exception.
			throw new InvalidParameterException(MessageHandlerParamKeys.TYPE, ErrorKeys.INVALID_TYPE,
					new Object[] { type }, null);
		}
		// Tries to return the message.
		return getMessage((Class<?>) type, locale, key, parametersValues);
	}

}
