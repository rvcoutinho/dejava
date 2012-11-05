package org.dejava.component.exception.localized;

import java.util.Locale;

import org.dejava.component.exception.constant.ErrorKeys;
import org.dejava.component.i18n.message.annotation.MessageBundle;
import org.dejava.component.i18n.message.annotation.MessageBundles;
import org.dejava.component.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageCommand;

/**
 * Default exception message command.
 */
@MessageBundles(defaultType = "error", messageBundles = { @MessageBundle(baseName = "org.dejava.component.exception.properties.error", type = "error") })
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
	 * @see SimpleMessageCommand#SimpleMessageCommand(Object, Locale, String, String, Object[])
	 */
	public ExceptionMessageCommand(final Object bundleInfo, final Locale locale, final String type,
			final String messageKey, final Object[] parameters) {
		super(bundleInfo, locale, type, messageKey, parameters);
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
			return getMessageHandler().getMessage(ExceptionMessageCommand.class, getLocale(), null,
					ErrorKeys.GENERIC, null);
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
			return getMessageHandler().getMessage(ExceptionMessageCommand.class, Locale.US, null,
					ErrorKeys.GENERIC, null);
		}
	}
}
