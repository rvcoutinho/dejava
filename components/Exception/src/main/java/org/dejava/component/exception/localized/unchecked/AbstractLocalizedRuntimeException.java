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
	 * @param type
	 *            The type for the message.
	 * @param messageKey
	 *            Message key that describes the exception.
	 * @param parameters
	 *            Parameters for the exception (and message).
	 * @param cause
	 *            Exception cause.
	 */
	public AbstractLocalizedRuntimeException(final Object type, final String messageKey,
			final Object[] parameters, final Throwable cause) {
		// Calls the exception constructor.
		super(cause);
		// Sets the basic fields.
		getMessageCommand().setType(type);
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