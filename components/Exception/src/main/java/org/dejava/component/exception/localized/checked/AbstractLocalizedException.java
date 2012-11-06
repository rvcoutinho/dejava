package org.dejava.component.exception.localized.checked;

import org.dejava.component.exception.localized.ExceptionMessageCommand;
import org.dejava.component.i18n.message.handler.MessageCommand;

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
	 * @param bundleInfo
	 *            The information regarding the message bundle to be used in the message retrieval
	 * @param messageKey
	 *            Message key that describes the exception.
	 * @param parameters
	 *            Parameters for the exception (and message).
	 * @param cause
	 *            Exception cause.
	 */
	public AbstractLocalizedException(final Object bundleInfo, final String messageKey,
			final Object[] parameters, final Throwable cause) {
		// Calls the Exception constructor.
		super(cause);
		// Sets the basic fields.
		getMessageCommand().setBundleInfo(bundleInfo);
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