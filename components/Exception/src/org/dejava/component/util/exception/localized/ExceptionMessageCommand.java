package org.dejava.component.util.exception.localized;

import java.util.Locale;

import org.dejava.component.util.exception.constant.ErrorKeys;
import org.dejava.component.util.i18n.message.annotation.MessageBundle;
import org.dejava.component.util.i18n.message.annotation.MessageBundles;
import org.dejava.component.util.i18n.message.exception.MessageNotFoundException;
import org.dejava.component.util.i18n.message.handler.impl.DefaultMessageCommand;

/**
 * Default exception message command.
 */
@MessageBundles(defaultType = "error", messageBundles = { @MessageBundle(baseName = "org.dejava.component.util.exception.properties.error", type = "error") })
public class ExceptionMessageCommand extends DefaultMessageCommand {
	
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
	 * @see org.dejava.component.util.i18n.message.handler.impl.DefaultMessageCommand#getMessage()
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
}
