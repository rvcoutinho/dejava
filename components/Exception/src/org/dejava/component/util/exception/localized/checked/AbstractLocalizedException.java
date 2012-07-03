package org.dejava.component.util.exception.localized.checked;

import org.dejava.component.util.exception.localized.ExceptionMessageCommand;
import org.dejava.component.util.i18n.message.handler.MessageCommand;
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
	 * Message command for the localized exception.
	 */
	private MessageCommand messageCommand;
	
	/**
	 * Gets the message command for the localized exception.
	 * 
	 * @return The message command for the localized exception.
	 */
	public MessageCommand getMessageCommand() {
		// If the message command is null.
		if (messageCommand == null) {
			// Creates a new message command.
			messageCommand = new ExceptionMessageCommand();
		}
		// Returns the message command.
		return messageCommand;
	}
	
	/**
	 * Sets the message command for the localized exception.
	 * 
	 * @param messageCommand
	 *            New message command for the localized exception.
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
	public String getLocalizedMessage() {
		// Tries to return the localized message.
		return getMessageCommand().getMessage();
	}
	
	/**
	 * @see Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		// Tries to return the localized message.
		return getMessageCommand().getUsMessage();
	}
}