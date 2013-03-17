package org.dejava.component.exception.localized;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.dejava.component.exception.constant.ErrorKeys;
import org.dejava.component.exception.util.MessageTypes;
import org.dejava.component.i18n.message.exception.MessageNotFoundException;
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
	 * @see org.dejava.component.i18n.message.handler.impl.SimpleMessageCommand#getMessage()
	 */
	@Override
	public String getMessage() throws MessageNotFoundException {
		// Tries to return the localized message.
		try {
			return super.getMessage();
		}
		// If the message cannot be found.
		catch (final Exception exception) {
			// Returns a generic error message.
			return getMessageHandler().getMessage(MessageTypes.Error.class, getLocale(), ErrorKeys.GENERIC, null);
		}
	}

	/**
	 * @see org.dejava.component.i18n.message.handler.impl.SimpleMessageCommand#getUsMessage()
	 */
	@Override
	public String getUsMessage() throws MessageNotFoundException {
		// Tries to return the localized message.
		try {
			return super.getUsMessage();
		}
		// If the message cannot be found.
		catch (final Exception exception) {
			// Returns a generic error message.
			return getMessageHandler().getMessage(MessageTypes.Error.class, Locale.US, ErrorKeys.GENERIC, null);
		}
	}
	
}
