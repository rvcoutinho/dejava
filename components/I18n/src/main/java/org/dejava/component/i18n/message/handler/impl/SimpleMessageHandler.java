package org.dejava.component.i18n.message.handler.impl;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.i18n.message.annotation.MessageBundle;
import org.dejava.component.i18n.message.annotation.MessageBundles;
import org.dejava.component.i18n.message.constant.MessageHandlerParamKeys;
import org.dejava.component.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.i18n.message.handler.MessageHandler;
import org.dejava.component.reflection.AnnotationMirror;
import org.dejava.component.reflection.ClassMirror;

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
	 * Gets the classes in the stack trace (limited by maximum search depth).
	 * 
	 * @return The classes in the stack trace (limited by maximum search depth).
	 */
	private Set<Class<?>> getStackTraceClasses() {
		// Creates a new set for the classes in the stack trace.
		final Set<Class<?>> stackTraceClasses = new LinkedHashSet<>();
		// While the annotation with bundles is not found (or max depth is reached).
		for (Integer currentStackDepth = 2; currentStackDepth <= (getMaxSearchDepth() + 2); currentStackDepth++) {
			// Tries to get the localized message with the information on the current class on the stack.
			try {
				// Tries to add the current class to set.
				stackTraceClasses.add(new ClassMirror<Object>(currentStackDepth).getReflectedClass());
			}
			// If the classes cannot be added to the set.
			catch (final Exception exception) {
				// Ignores and continues...
			}
		}
		// Returns the set with classes in the stack.
		return stackTraceClasses;
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
	 * @param annotatedClass
	 *            Class that contains the message bundle information (via {@link MessageBundles}). The given
	 *            class must not be null.
	 * @param locale
	 *            Locale for the message. If no locale is provided, the message handler locale is used.
	 * @param type
	 *            Type of the message. If no type is given, a default type will be used.
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
	public String getMessage(final Class<?> annotatedClass, final Locale locale, final String type,
			final String key, final Object[] parametersValues) throws MessageNotFoundException,
			InvalidParameterException {
		// If the annotated class is not given.
		if (annotatedClass == null) {
			// Throws an exception.
			throw new EmptyParameterException(MessageHandlerParamKeys.ANNOTATED_CLASS);
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
		for (AnnotationMirror<MessageBundles> currentMsgBundles : new ClassMirror<>(annotatedClass)
				.getAnnotations(MessageBundles.class)) {
			// If the annotation is found.
			if (currentMsgBundles != null) {
				// Actual type for the message.
				String actualType = type;
				// If the type is not given.
				if (actualType == null) {
					// If the default type is empty.
					if (currentMsgBundles.getReflectedAnnotation().defaultType().isEmpty()) {
						// Throws an exception.
						throw new EmptyParameterException(MessageHandlerParamKeys.TYPE);
					}
					// If it is given.
					else {
						// The actual type is the default one.
						actualType = currentMsgBundles.getReflectedAnnotation().defaultType();
					}
				}
				// For each defined localized message bundle.
				for (final MessageBundle currentMessageBundle : currentMsgBundles.getReflectedAnnotation()
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
		// If no localized message is found, throws an exception.
		throw new MessageNotFoundException(null, null);
	}

	/**
	 * Gets a localized message with the given key and parameters values of the defined type. The first
	 * message found with the given information is retrieved.
	 * 
	 * @param annotatedClasses
	 *            Classes that might contain the message bundle information (via {@link MessageBundles}). The
	 *            interfaces and super classes are also taken under consideration. The given collection must
	 *            not be empty or null.
	 * @param locale
	 *            Locale for the message. If no locale is provided, the message handler locale is used.
	 * @param type
	 *            Type of the message. If no type is given, a default type will be used.
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
	public String getMessage(final Collection<Class<?>> annotatedClasses, final Locale locale,
			final String type, final String key, final Object[] parametersValues)
			throws MessageNotFoundException, InvalidParameterException {
		// If the collection is null or empty.
		if ((annotatedClasses == null) || (annotatedClasses.isEmpty())) {
			// Throws an exception.
			throw new EmptyParameterException(MessageHandlerParamKeys.ANNOTATED_CLASSES);
		}
		// If the key is not given.
		if ((key == null) || (key.isEmpty())) {
			// Throws an exception.
			throw new EmptyParameterException(MessageHandlerParamKeys.KEY);
		}
		// For each class in the collection.
		for (final Class<?> currentAnnotatedClass : annotatedClasses) {
			// Tries to return the message for the current class.
			try {
				return getMessage(currentAnnotatedClass, locale, type, key, parametersValues);
			}
			// If the message cannot be found for the current class.
			catch (final Exception exception) {
				// Ignores and continues...
			}
		}
		// If no localized message is found, throws an exception.
		throw new MessageNotFoundException(null, null);
	}

	/**
	 * Gets a localized message with the given key and parameters values of the defined type. The first
	 * message found with the given information is retrieved. The classes in the stack trace (limited by the
	 * maximum search depth) are used to find the bundle information (via {@link MessageBundles}).
	 * 
	 * @param locale
	 *            Locale for the message. If no locale is provided, the message handler locale is used.
	 * @param type
	 *            Type of the message. If no type is given, a default type will be used.
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
	public String getMessage(final Locale locale, final String type, final String key,
			final Object[] parametersValues) throws MessageNotFoundException, InvalidParameterException {
		// Tries to get the message with the classes in the stack trace.
		return getMessage(getStackTraceClasses(), locale, type, key, parametersValues);
	}

	/**
	 * 
	 * If the bundle info is a class, search for {@link MessageBundles} annotations. If it is a collection,
	 * search the classes in the collection for {@link MessageBundles} annotations. Otherwise, check the stack
	 * trace classes for {@link MessageBundles} annotations
	 * 
	 * @see org.dejava.component.i18n.message.handler.MessageHandler#getMessage(java.lang.Object,
	 *      java.util.Locale, java.lang.String, java.lang.String, java.lang.Object[])
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getMessage(final Object bundleInfo, final Locale locale, final String type,
			final String key, final Object[] parametersValues) throws MessageNotFoundException,
			InvalidParameterException {
		// If the bundle info is a class.
		if (bundleInfo instanceof Class<?>) {
			// Tries to return the message with a annotated class.
			return getMessage((Class<?>) bundleInfo, locale, type, key, parametersValues);
		}
		// If it is a collection.
		else if (bundleInfo instanceof Collection<?>) {
			// Tries to return the message with a collection of annotated classes.
			return getMessage((Collection<Class<?>>) bundleInfo, locale, type, key, parametersValues);
		}
		// If it is not a class, nor a collection.
		else {
			// Tries to return the message with the stack trace information.
			return getMessage(locale, type, key, parametersValues);
		}
	}

}
