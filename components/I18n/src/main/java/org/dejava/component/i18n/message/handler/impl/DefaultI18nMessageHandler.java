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
import org.dejava.component.i18n.message.annotation.MessageBundles;
import org.dejava.component.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.i18n.message.handler.MessageHandler;
import org.dejava.component.i18n.message.handler.model.ApplicationMessageType;
import org.dejava.component.util.reflection.AnnotationMirror;
import org.dejava.component.util.reflection.ClassMirror;

/**
 * Default i18n message handler. Handles getting messages from within a class using the information provided
 * by the annotations {@link MessageBundles} and {@link MessageBundle}. Uses the default Java i18n API.
 * 
 * It works by searching the localized message bundle information at runtime (via annotations). It looks the
 * classes at the current stack trace and tries to retrieve the annotations from these classes. The default
 * depth of the search is 5. You can change this behavior by setting a new maximum search depth (
 * {@link #setMaxSearchDepth}). If the localized message (with this internationalization handler) cannot be
 * found with the information of the first class in the stack, it keeps looking in the next classes until the
 * localized message is found or the maximum search depth is reached.
 * 
 * Enjoy Java i18n and l10n!
 */
public class DefaultI18nMessageHandler implements MessageHandler {
	
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
		synchronized (DefaultI18nMessageHandler.class) {
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
		DefaultI18nMessageHandler.messageHandlers = messageHandlers;
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
	 * Max depth to search for annotations with message bundles.
	 * 
	 * The default value is 5.
	 */
	private Integer maxSearchDepth = 5;
	
	/**
	 * Gets the max depth to search for annotations with message bundles.
	 * 
	 * The default value is 5.
	 * 
	 * @return The max depth to search for annotations with message bundles.
	 */
	public Integer getMaxSearchDepth() {
		return maxSearchDepth;
	}
	
	/**
	 * Sets the max depth to search for annotations with message bundles.
	 * 
	 * @param maxSearchDepth
	 *            New max depth to search for annotations with message bundles.
	 */
	public void setMaxSearchDepth(final Integer maxSearchDepth) {
		this.maxSearchDepth = maxSearchDepth;
	}
	
	/**
	 * Protected constructor for the default internationalization message handler.
	 */
	protected DefaultI18nMessageHandler() {
		super();
	}
	
	/**
	 * Protected constructor for the default internationalization message handler.
	 * 
	 * @param locale
	 *            Locale for the new instance.
	 */
	protected DefaultI18nMessageHandler(final Locale locale) {
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
			messageHandler = new DefaultI18nMessageHandler(locale);
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
		if (parametersValues == null) {
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
	 * @throws MessageNotFoundException
	 *             If localized message for the key cannot be found.
	 */
	private String getMessageByBundle(final Locale locale, final String bundleBaseName, final String key,
			final Object[] parametersValues) throws MessageNotFoundException {
		// Tries to get the localized message for the key.
		try {
			// Gets the bundle for the given base name.
			final ResourceBundle messageBundle = ResourceBundle.getBundle(bundleBaseName, locale);
			// Get the localized message for the key.
			final String message = messageBundle.getString(key);
			// Adds the parameters and returns the message.
			return addParameters(message, parametersValues);
		}
		// If the key is not found.
		catch (final MissingResourceException exception) {
			// Throws an exception.
			throw new MessageNotFoundException(exception, new Object[] { locale, bundleBaseName, key,
					parametersValues });
		}
	}
	
	/**
	 * Gets a localized message with the given key and parameters values of the defined type.
	 * 
	 * @param locale
	 *            Locale for the message.
	 * @param type
	 *            Type of the message. If no type is given, a default type will be used.
	 * @param key
	 *            Key for the localized message in the bundle.
	 * @param parametersValues
	 *            Parameter values for the message.
	 * @return A localized message with the given key and parameters values of the defined type.
	 * @throws MessageNotFoundException
	 *             If the localized message cannot be found.
	 * @throws EmptyParameterException
	 *             If the key for the localized message is empty.
	 */
	public String getMessage(final Locale locale, final String type, final String key,
			final Object[] parametersValues) throws MessageNotFoundException, EmptyParameterException {
		// If the key is not given.
		if ((key == null) || (key.isEmpty())) {
			// Throws an exception.
			throw new EmptyParameterException(3);
		}
		// The actual locale is the given one.
		Locale actualLocale = locale;
		// If the actual locale is null.
		if (actualLocale == null) {
			// The actual locale is the object one.
			actualLocale = getLocale();
		}
		// While the annotation with bundles is not found (or max depth is reached).
		for (Integer currentStackDepth = 2; currentStackDepth <= (getMaxSearchDepth() + 2); currentStackDepth++) {
			// Tries to get the localized message with the information on the current class on the stack.
			try {
				// Tries to get the current class in the stack.
				final ClassMirror<?> currentClass = new ClassMirror<Object>(currentStackDepth);
				// Tries to get the annotation with the bundles for the current class.
				final AnnotationMirror<MessageBundles> messageBundles = currentClass
						.getAnnotation(MessageBundles.class);
				// If the annotation is found.
				if (messageBundles != null) {
					// Actual type for the message.
					String actualType = type;
					// If the type is not given.
					if (actualType == null) {
						// If the default type is empty.
						if (messageBundles.getReflectedAnnotation().defaultType().isEmpty()) {
							// Throws an exception.
							throw new EmptyParameterException(2);
						}
						// If it is given.
						else {
							// The actual type is the default one.
							actualType = messageBundles.getReflectedAnnotation().defaultType();
						}
					}
					// For each defined localized message bundle.
					for (final MessageBundle currentMessageBundle : messageBundles.getReflectedAnnotation()
							.messageBundles()) {
						// If the correct localized message bundle information is found.
						if (currentMessageBundle.type().equalsIgnoreCase(actualType)) {
							// Tries to get the message.
							return getMessageByBundle(actualLocale, currentMessageBundle.baseName(), key,
									parametersValues);
						}
					}
				}
			}
			// If the localized message is not found with current information.
			catch (final Exception exception) {
				// Ignores and continues the loop.
			}
		}
		// If no localized message is found, throws an exception.
		throw new MessageNotFoundException(null, null);
	}
	
	/**
	 * @see org.dejava.component.i18n.test.message.handler.MessageHandler#getMessage(java.lang.String,
	 *      java.lang.String, java.lang.Object[])
	 */
	@Override
	public String getMessage(final String type, final String key, final Object[] parametersValues)
			throws MessageNotFoundException, InvalidParameterException {
		// If the key is not given.
		if ((key == null) || (key.isEmpty())) {
			// Throws an exception.
			throw new EmptyParameterException(2);
		}
		// Gets the message.
		return getMessage(getLocale(), type, key, parametersValues);
	}
	
	/**
	 * @see org.dejava.component.i18n.test.message.handler.MessageHandler#getMessage(org.dejava.component.i18n.test.message.handler.model.ApplicationMessageType,
	 *      java.lang.String, java.lang.Object[])
	 */
	@Override
	public String getMessage(final ApplicationMessageType type, final String key,
			final Object[] parametersValues) throws MessageNotFoundException, InvalidParameterException {
		// The actual type is null.
		String actualType = null;
		// If the given type is null.
		if (type != null) {
			// The actual type is the type name.
			actualType = type.name().toLowerCase();
		}
		// Gets the message.
		return getMessage(actualType, key, parametersValues);
	}
	
	/**
	 * @see org.dejava.component.i18n.test.message.handler.MessageHandler#getMessage(java.lang.String,
	 *      java.lang.Object[])
	 */
	@Override
	public String getMessage(final String key, final Object[] parametersValues)
			throws MessageNotFoundException, InvalidParameterException {
		// If the key is not given.
		if ((key == null) || (key.isEmpty())) {
			// Throws an exception.
			throw new EmptyParameterException(1);
		}
		// Gets the message.
		return getMessage((String) null, key, parametersValues);
	}
}
