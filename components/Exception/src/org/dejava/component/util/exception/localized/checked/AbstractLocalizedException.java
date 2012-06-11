package org.dejava.component.util.exception.localized.checked;

import java.util.Locale;

import org.dejava.component.util.i18n.message.handler.MessageCommand;
import org.dejava.component.util.i18n.message.handler.impl.DefaultMessageCommand;
import org.dejava.component.util.i18n.message.handler.model.ApplicationMessageType;

/**
 * General localized exception.
 */
public abstract class AbstractLocalizedException extends Exception {
	
	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -6987571772328004454L;
	
	/**
	 * Message command for the exception.
	 */
	private MessageCommand messageCommand;
	
	/**
	 * Gets the messageCommand.
	 * 
	 * @return The messageCommand.
	 */
	public MessageCommand getMessageCommand() {
		// If the message command is null.
		if (messageCommand == null) {
			// Creates a new message command.
			messageCommand = new DefaultMessageCommand();
		}
		// Returns the message command.
		return messageCommand;
	}
	
	/**
	 * Sets the messageCommand.
	 * 
	 * @param messageCommand
	 *            New messageCommand.
	 */
	public void setMessageCommand(final MessageCommand messageCommand) {
		this.messageCommand = messageCommand;
	}
	
	/**
	 * Basic constructor.
	 * 
	 * @param messageKey
	 *            Message key that describes the exception.
	 * @param cause
	 *            Exception cause.
	 * @param parameters
	 *            Parameters for the exception (and message).
	 */
	public AbstractLocalizedException(final String messageKey, final Throwable cause,
			final Object[] parameters) {
		// Calls the Exception constructor.
		super(cause);
		// Sets the basic fields.
		getMessageCommand().setType(ApplicationMessageType.ERROR.name());
		getMessageCommand().setMessageKey(messageKey);
		getMessageCommand().setParameters(parameters);
	}
	
	/**
	 * @see Throwable#getLocalizedMessage()
	 */
	@Override
	public String getMessage() {
		// Tries to return the localized message.
		return getMessageCommand().getMessage();
	}
	
	/**
	 * Returns the localized message for the given locale.
	 * 
	 * @param locale
	 *            Locale to render the message.
	 * @return The localized message for the given locale.
	 */
	public String getMessage(final Locale locale) {
		// Sets the locale for the exception.
		getMessageCommand().setLocale(locale);
		// Returns the localized message.
		return getLocalizedMessage();
	}
}