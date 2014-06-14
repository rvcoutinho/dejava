package org.dejava.component.i18n.message.handler.impl;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.i18n.message.annotation.MessageBundle;
import org.dejava.component.i18n.message.constant.ErrorKeys;
import org.dejava.component.i18n.message.constant.MessageHandlerParamKeys;
import org.dejava.component.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.i18n.message.handler.MessageHandler;
import org.dejava.component.i18n.message.util.MessageTypes;
import org.dejava.component.reflection.AnnotationMirror;
import org.dejava.component.reflection.ClassMirror;
import org.dejava.component.validation.method.PreConditions;

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
	 * If the message handler should raise an exception when the message is not found. If false, the message
	 * key is returned when the message cannot be found.
	 */
	private Boolean throwsException = false;

	/**
	 * Gets if the message handler should raise an exception when the message is not found.
	 * 
	 * @return If the message handler should raise an exception when the message is not found.
	 */
	public Boolean getThrowsException() {
		return throwsException;
	}

	/**
	 * Sets if the message handler should raise an exception when the message is not found.
	 * 
	 * @param throwsException
	 *            If the message handler should raise an exception when the message is not found. If false,
	 *            the message key is returned when the message cannot be found.
	 */
	public void setThrowsException(Boolean throwsException) {
		this.throwsException = throwsException;
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
	 * 
	 * @param locale
	 *            Locale for the new instance.
	 * @param throwsException
	 *            If the message handler should raise an exception when the message is not found. If false,
	 *            the message key is returned when the message cannot be found.
	 */
	protected SimpleMessageHandler(final Locale locale, Boolean throwsException) {
		super();
		// Sets the basic parameters.
		this.locale = locale;
		this.throwsException = throwsException;
	}

	/**
	 * Gets the message handler for the current environment.
	 * 
	 * @param locale
	 *            Locale for the message handler to use.
	 * @param throwsException
	 *            If the message handler should raise an exception when the message is not found. If false,
	 *            the message key is returned when the message cannot be found.
	 * @return The message handler for the current environment.
	 */
	public static MessageHandler getMessageHandler(final Locale locale, Boolean throwsException) {
		// Creates a new instance of the default i18n message handler.
		MessageHandler messageHandler = new SimpleMessageHandler(locale, throwsException);
		// Returns the message handler.
		return messageHandler;
	}

	/**
	 * Gets the message handler for the current environment.
	 * 
	 * @param locale
	 *            Locale for the message handler to use.
	 * @return The message handler for the current environment.
	 */
	public static MessageHandler getMessageHandler(final Locale locale) {
		return getMessageHandler(locale, false);
	}

	/**
	 * Gets the message handler for the current environment with default locale.
	 * 
	 * @return The message handler for the current environment with default locale.
	 */
	public static MessageHandler getDefaultMessageHandler() {
		// Gets the message handler for the default locale.
		return getMessageHandler(Locale.getDefault());
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
		// If no parameters values are given.
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
		// Asserts that the type is not null.
		PreConditions.assertParamNotNull(MessageHandlerParamKeys.TYPE, type);
		// Asserts that the key is not empty.
		PreConditions.assertParamNotEmpty(MessageHandlerParamKeys.KEY, key);
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
		// If no localized message is found, and an exceptions is expected.
		if (getThrowsException()) {
			// Throws an exception.
			throw new MessageNotFoundException(new Object[] { type, locale, key, parametersValues }, null);
		}
		// If no localized message is found, and no exceptions is expected.
		else {
			// Returns the key itself.
			return key;
		}
	}

	/**
	 * @see org.dejava.component.i18n.message.handler.MessageHandler#getMessage(java.lang.Object,
	 *      java.util.Locale, java.lang.String, java.lang.Object[])
	 */
	@Override
	public String getMessage(final Object type, final Locale locale, final String key,
			final Object[] parametersValues) throws MessageNotFoundException, InvalidParameterException {
		// Assures that the type is a class.
		PreConditions.assertParamValid(type instanceof Class<?>, MessageTypes.Error.class,
				ErrorKeys.INVALID_TYPE, new Object[] { type });
		// Tries to return the message.
		return getMessage((Class<?>) type, locale, key, parametersValues);
	}

}
