package org.dejava.component.exception.localized;

import java.util.Locale;

import org.dejava.component.exception.constant.ErrorKeys;
import org.dejava.component.exception.util.MessageTypes;
import org.dejava.component.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.i18n.message.handler.ApplicationMessageHandler;
import org.dejava.component.i18n.message.handler.MessageHandler;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageCommand;

/**
 * Default exception message command.
 */
public class ExceptionMessageCommand extends SimpleMessageCommand {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 2192474983110077792L;

	/**
	 * @see SimpleMessageCommand#SimpleMessageCommand()
	 */
	public ExceptionMessageCommand() {
		super();
	}

	/**
	 * @see SimpleMessageCommand#SimpleMessageCommand(Class, Locale, String, Object[])
	 */
	public ExceptionMessageCommand(final Class<?> type, final Locale locale, final String messageKey,
			final Object[] parameters) {
		super(type, locale, messageKey, parameters);
	}

	/**
	 * @see org.dejava.component.i18n.message.handler.impl.SimpleMessageCommand#getMessage(org.dejava.component.i18n.message.handler.MessageHandler)
	 */
	@Override
	public String getMessage(final MessageHandler messageHandler) throws MessageNotFoundException {
		// Tries to return the localized message.
		try {
			return super.getMessage(messageHandler);
		}
		// If the message cannot be found.
		catch (final Exception exception) {
			// Returns a generic error message.
			return messageHandler.getMessage(MessageTypes.Error.class, getLocale(), ErrorKeys.GENERIC, null);
		}
	}

	/**
	 * @see org.dejava.component.i18n.message.handler.impl.SimpleMessageCommand#addMessage(org.dejava.component.i18n.message.handler.ApplicationMessageHandler)
	 */
	@Override
	public void addMessage(final ApplicationMessageHandler appMessageHandler) throws MessageNotFoundException {
		// Tries to add the localized message.
		try {
			super.addMessage(appMessageHandler);
		}
		// If the message cannot be found.
		catch (final Exception exception) {
			// Adds a generic error message.
			appMessageHandler.addMessage(MessageTypes.Error.class, getLocale(), ErrorKeys.GENERIC, null);
		}
	}
}
