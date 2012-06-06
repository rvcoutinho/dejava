package org.dejava.component.util.i18n.message.handler.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.dejava.component.util.exception.localized.InvalidParameterException;
import org.dejava.component.util.i18n.message.annotation.MessageBundle;
import org.dejava.component.util.i18n.message.annotation.MessageBundles;
import org.dejava.component.util.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.util.i18n.message.handler.I18nMessageHandler;
import org.dejava.component.util.i18n.message.model.ApplicationMessageType;
import org.dejava.component.util.reflection.handler.AnnotationHandler;
import org.dejava.component.util.reflection.handler.ClassHandler;

/**
 * Default message handler. Handles getting messages from within a class using the information provided by the
 * annotations {@link MessageBundles} and {@link MessageBundle}.
 * 
 * It works by searching the message bundle information at runtime (via annotations). It looks the classes at
 * the current stack trace and tries to retrieve the annotations from these classes. The default depth of the
 * search is 5. You can change this behavior by setting a new maximum search depth ({@link #setMaxSearchDepth}
 * ). If the message cannot be found with the information of the first class in the stack, it keeps looking in
 * the next classes until the message is found or the maximum search depth is reached.
 * 
 * Enjoy!
 */
public class DefaultMessageHandler implements I18nMessageHandler {
	
	/**
	 * Message handlers stored by locale.
	 */
	private static Map<Locale, I18nMessageHandler> messageHandlers;
	
	/**
	 * Gets the message handlers stored by locale.
	 * 
	 * @return The message handlers stored by locale.
	 */
	public static Map<Locale, I18nMessageHandler> getMessageHandlers() {
		// Grants that the map will just me created once.
		synchronized (DefaultMessageHandler.class) {
			// If the map is null.
			if (messageHandlers == null) {
				// Creates a new map.
				messageHandlers = new HashMap<Locale, I18nMessageHandler>();
			}
		}
		// Returns the map.
		return messageHandlers;
	}
	
	/**
	 * Sets the message handlers stored by locale.
	 * 
	 * @param messageHandlers
	 *            New message handlers stored by locale.
	 */
	public static void setMessageHandlers(final Map<Locale, I18nMessageHandler> messageHandlers) {
		DefaultMessageHandler.messageHandlers = messageHandlers;
	}
	
	/**
	 * Locale to find the messages.
	 */
	private transient Locale locale;
	
	/**
	 * Gets the locale.
	 * 
	 * @return The locale.
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
	 */
	private Integer maxSearchDepth = 5;
	
	/**
	 * Gets the max depth to search for annotations with message bundles.
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
	 * Protected constructor.
	 */
	protected DefaultMessageHandler() {
		super();
	}
	
	/**
	 * Public constructor.
	 * 
	 * @param locale
	 *            Locale for the new instance.
	 */
	protected DefaultMessageHandler(final Locale locale) {
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
	public static I18nMessageHandler getMessageHandler(final Locale locale) {
		// Tries to get the default message handler for the locale in the map.
		I18nMessageHandler messageHandler = getMessageHandlers().get(locale);
		// If the message handler is not yet.
		if (messageHandler == null) {
			// Creates a new instance of the default message handler.
			messageHandler = new DefaultMessageHandler(locale);
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
	 *            Message to add the parameter values.
	 * @param parametersValues
	 *            Parameter values for the message.
	 * @return The message with the given parameter values added.
	 */
	private String addParameters(final String message, final Object[] parametersValues) {
		// If no parameter values are given.
		if (parametersValues == null) {
			// Returns the raw message.
			return message;
		}
		// Otherwise.
		else {
			// Creates a new message format.
			final MessageFormat messageFormat = new MessageFormat(message);
			// Format the message.
			return messageFormat.format(parametersValues);
		}
	}
	
	/**
	 * Gets a message from a given bundle.
	 * 
	 * @param bundleBaseName
	 *            Message bundle base name.
	 * @param key
	 *            Key for the message.
	 * @param parametersValues
	 *            Parameter values for the message.
	 * @return Returns the message found.
	 * @throws MessageNotFoundException
	 *             If message for the key cannot be found.
	 */
	private String getMessageByBundle(final String bundleBaseName, final String key,
			final Object[] parametersValues) throws MessageNotFoundException {
		// Tries to get the message for the key.
		try {
			// Gets the bundle for the given base name.
			final ResourceBundle messageBundle = ResourceBundle.getBundle(bundleBaseName, getLocale());
			// Get the message for the key.
			final String message = messageBundle.getString(key);
			// Adds the parameters and returns the message.
			return addParameters(message, parametersValues);
		}
		// If the key is not found.
		catch (final MissingResourceException exception) {
			// Throws an exception.
			throw new MessageNotFoundException(exception, null);
		}
	}
	
	/**
	 * @see org.dejava.component.util.i18n.message.handler.I18nMessageHandler#getMessage(java.util.Locale,
	 *      java.lang.String, java.lang.String, java.lang.Object[])
	 */
	@Override
	public String getMessage(final Locale locale, final String type, final String key,
			final Object[] parametersValues) throws MessageNotFoundException, InvalidParameterException {
		// Tries to get the current class in the stack.
		Class<?> currentClass = ClassHandler.getCurrentClass(1);
		// While the annotation with bundles is not found (or max depth is reached).
		for (Integer currentStackDepth = 1; (currentClass != null)
				&& (currentStackDepth <= getMaxSearchDepth()); currentStackDepth++) {
			// Tries to get the message with the information on the current class on the stack.
			try {
				// Tries to get the annotation with the bundles for the current class.
				final MessageBundles messageBundles = AnnotationHandler.getAnnotation(currentClass,
						MessageBundles.class);
				// If the annotation is found.
				if (messageBundles != null) {
					// For each defined message bundle.
					for (final MessageBundle currentMessageBundle : messageBundles.messageBundles()) {
						// If the correct message bundle information is found.
						if (currentMessageBundle.type().equalsIgnoreCase(type)) {
							// Tries to get the message.
							return getMessageByBundle(currentMessageBundle.baseName(), key, parametersValues);
						}
					}
				}
				// Tries to get current class with a higher depth.
				currentClass = ClassHandler.getCurrentClass(currentStackDepth);
			}
			// If the message is not found with current information.
			catch (final Exception exception) {
				// Ignores and continues the loop.
			}
		}
		// If no message is found, throws an exception.
		throw new MessageNotFoundException(null, null);
	}
	
	/**
	 * @see org.dejava.component.util.i18n.message.handler.I18nMessageHandler#getMessage(java.util.Locale,
	 *      org.dejava.component.util.i18n.message.model.ApplicationMessageType, java.lang.String,
	 *      java.lang.Object[])
	 */
	@Override
	public String getMessage(final Locale locale, final ApplicationMessageType type, final String key,
			final Object[] parametersValues) throws MessageNotFoundException, InvalidParameterException {
		// Gets the message.
		return getMessage(locale, type.name(), key, parametersValues);
	}
	
	/**
	 * Gets a message with the given key and parameters values of the defined type.
	 * 
	 * @param type
	 *            Type of the message.
	 * @param key
	 *            Key for the message in the bundle.
	 * @param parametersValues
	 *            Parameter values for the message.
	 * @return A message with the given key and parameters values of the defined type.
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 * @throws InvalidParameterException
	 *             If the parameter values are not valid.
	 */
	public String getMessage(final String type, final String key, final Object[] parametersValues)
			throws MessageNotFoundException, InvalidParameterException {
		// Gets the message.
		return getMessage(getLocale(), type, key, parametersValues);
	}
	
	/**
	 * Gets a message with the given key and parameters values of the defined type.
	 * 
	 * @param type
	 *            Type of the message.
	 * @param key
	 *            Key for the message in the bundle.
	 * @param parametersValues
	 *            Parameter values for the message.
	 * @return A message with the given key and parameters values of the defined type.
	 * @throws MessageNotFoundException
	 *             If the message cannot be found.
	 * @throws InvalidParameterException
	 *             If the parameter values are not valid.
	 */
	public String getMessage(final ApplicationMessageType type, final String key,
			final Object[] parametersValues) throws MessageNotFoundException, InvalidParameterException {
		// Gets the message.
		return getMessage(type, key, parametersValues);
	}
}
