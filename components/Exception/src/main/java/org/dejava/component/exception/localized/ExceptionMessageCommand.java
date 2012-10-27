package org.dejava.component.exception.localized;

import java.util.Locale;

import org.dejava.component.exception.constant.ErrorKeys;
import org.dejava.component.i18n.message.annotation.MessageBundle;
import org.dejava.component.i18n.message.annotation.MessageBundles;
import org.dejava.component.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.i18n.message.handler.impl.DefaultMessageCommand;

/**
 * Default exception message command.
 */
@MessageBundles(defaultType = "error", messageBundles = { @MessageBundle(baseName = "org.dejava.component.exception.properties.error", type = "error") })
public class ExceptionMessageCommand extends DefaultMessageCommand {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 2192474983110077792L;

	/**
	 * @see DefaultMessageCommand#DefaultMessageCommand()
	 */
	public ExceptionMessageCommand() {
		super();
	}
	
	/**
	 * @see DefaultMessageCommand#DefaultMessageCommand(Locale, String, Object[], String)
	 */
	public ExceptionMessageCommand(final Locale locale, final String type, final Object[] parameters,
			final String messageKey) {
		super(locale, type, parameters, messageKey);
	}
	
	/**
	 * @see org.dejava.component.i18n.message.handler.impl.DefaultMessageCommand#getMessage()
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
			return getMessageHandler().getMessage(ErrorKeys.GENERIC, null);
		}
	}
	
	/**
	 * @see org.dejava.component.i18n.message.handler.impl.DefaultMessageCommand#getUsMessage()
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
			return getUsMessageHandler().getMessage(ErrorKeys.GENERIC, null);
		}
	}
}
