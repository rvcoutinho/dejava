package org.dejava.component.test.exception;

import org.dejava.component.exception.localized.ExceptionMessageCommand;
import org.dejava.component.i18n.message.handler.MessageCommand;

/**
 * Test assertion related errors.
 */
public abstract class AbstractTestAssertionError extends AssertionError {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -7691561117136485140L;

	/**
	 * Message command for the localized error.
	 */
	private MessageCommand messageCommand;

	/**
	 * Gets the message command for the localized error.
	 * 
	 * @return The message command for the localized error.
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
	 * Sets the message command for the localized error.
	 * 
	 * @param messageCommand
	 *            New message command for the localized error.
	 */
	public void setMessageCommand(final MessageCommand messageCommand) {
		this.messageCommand = messageCommand;
	}

	/**
	 * Basic constructor.
	 * 
	 * @param messageKey
	 *            Message key that describes the error.
	 * @param cause
	 *            Error cause.
	 * @param testName
	 *            The name of the test for the error.
	 */
	public AbstractTestAssertionError(final String messageKey, final Throwable cause, final String testName) {
		// Calls the Exception constructor.
		super(cause);
		// Sets the basic fields.
		getMessageCommand().setMessageKey(messageKey);
		getMessageCommand().setParameters(new Object[] { testName });
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