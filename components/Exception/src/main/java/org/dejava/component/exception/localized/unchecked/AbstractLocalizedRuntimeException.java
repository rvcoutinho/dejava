package org.dejava.component.exception.localized.unchecked;

import org.dejava.component.exception.localized.ExceptionMessageCommand;
import org.dejava.component.i18n.message.handler.MessageCommand;

/**
 * General localized runtime exception.
 */
public abstract class AbstractLocalizedRuntimeException extends RuntimeException {

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
			messageCommand = new ExceptionMessageCommand();
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
	public AbstractLocalizedRuntimeException(final String messageKey, final Throwable cause,
			final Object[] parameters) {
		// Calls the Exception constructor.
		super(cause);
		// Sets the basic fields.
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