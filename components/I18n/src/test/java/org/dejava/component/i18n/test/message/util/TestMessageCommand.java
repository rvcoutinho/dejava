package org.dejava.component.i18n.test.message.util;

import java.util.Locale;

import org.dejava.component.i18n.message.handler.impl.SimpleMessageCommand;

/**
 * A message command to be used in the tests.
 */
public class TestMessageCommand extends SimpleMessageCommand {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4278970373812796168L;

	/**
	 * The expected message for this message command.
	 */
	private String expectedMessage;

	/**
	 * Gets the expected message for this message command.
	 * 
	 * @return The expected message for this message command.
	 */
	public String getExpectedMessage() {
		return expectedMessage;
	}

	/**
	 * Sets the expected message for this message command.
	 * 
	 * @param expectedMessage
	 *            New expected message for this message command.
	 */
	public void setExpectedMessage(final String expectedMessage) {
		this.expectedMessage = expectedMessage;
	}

	/**
	 * Public constructor.
	 * 
	 * @param type
	 *            Type for the message.
	 * @param locale
	 *            Locale for the message.
	 * @param messageKey
	 *            Key for the message.
	 * @param parameters
	 *            Parameters for the message.
	 * @param expectedMessage
	 *            The expected message for this message command.
	 */
	public TestMessageCommand(final Class<?> type, final Locale locale, final String messageKey,
			final Object[] parameters, final String expectedMessage) {
		super(type, locale, messageKey, parameters);
		// Sets the expected message.
		this.expectedMessage = expectedMessage;
	}

}
