package org.dejava.component.test.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	 * @param bundleInfo
	 *            The information regarding the message bundle to be used in the message retrieval
	 * @param messageKey
	 *            Message key that describes the exception.
	 * @param testName
	 *            The test name.
	 * @param parameters
	 *            Parameters for the exception (and message).
	 * @param cause
	 *            Error cause.
	 */
	public AbstractTestAssertionError(final Object bundleInfo, final String messageKey,
			final String testName, final Object[] parameters, final Throwable cause) {
		// Calls the Exception constructor.
		super(cause);
		// Sets the basic fields.
		getMessageCommand().setBundleInfo(bundleInfo);
		getMessageCommand().setMessageKey(messageKey);
		// List of exception parameters (including test name).
		final List<Object> allParams = new ArrayList<Object>();
		// Adds the test name as the first parameter.
		allParams.add(testName);
		// If there are any parameters.
		if (parameters != null) {
			// Adds all the parameters values for the test method.
			allParams.addAll(Arrays.asList(parameters));
		}
		// Sets the parameters of the exception.
		getMessageCommand().setParameters(allParams.toArray());
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