package org.dejava.component.validation.test.object.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.i18n.message.handler.ApplicationMessageHandler;
import org.dejava.component.i18n.message.handler.MessageHandler;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageHandler;

/**
 * Fake application message handler.
 */
public class FakeApplicationMessageHandler implements ApplicationMessageHandler {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -4561835340410735842L;

	/**
	 * Decorated message handler.
	 */
	private final MessageHandler decoratedMessageHandler = SimpleMessageHandler.getMessageHandler(Locale
			.getDefault());

	/**
	 * Fake application message context. Holds the message information for a fake application.
	 */
	private List<String> fakeAppMessageContext;

	/**
	 * Gets the fakeAppMessageContext.
	 * 
	 * @return The fakeAppMessageContext.
	 */
	public List<String> getFakeAppMessageContext() {
		// If the context is null.
		if (fakeAppMessageContext == null) {
			// Creates a new list for the context.
			fakeAppMessageContext = new ArrayList<>();
		}
		// Returns the context.
		return fakeAppMessageContext;
	}

	/**
	 * @see org.dejava.component.i18n.message.handler.MessageHandler#getMessage(java.lang.Object,
	 *      java.util.Locale, java.lang.String, java.lang.Object[])
	 */
	@Override
	public String getMessage(final Object type, final Locale locale, final String key,
			final Object[] parametersValues) throws MessageNotFoundException, InvalidParameterException {
		return decoratedMessageHandler.getMessage(type, locale, key, parametersValues);
	}

	/**
	 * @see org.dejava.component.i18n.message.handler.ApplicationMessageHandler#addMessage(java.lang.Object,
	 *      java.util.Locale, java.lang.String, java.lang.Object[])
	 */
	@Override
	public String addMessage(final Object type, final Locale locale, final String key,
			final Object[] parametersValues) throws MessageNotFoundException, InvalidParameterException {
		// Gets the message for the given information.
		final String message = getMessage(type, locale, key, parametersValues);
		// Adds the message to the fake application context.
		getFakeAppMessageContext().add(message);
		// Returns the message.
		return message;
	}

}
