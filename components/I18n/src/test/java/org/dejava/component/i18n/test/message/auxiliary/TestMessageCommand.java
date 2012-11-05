package org.dejava.component.i18n.test.message.auxiliary;

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
	 * @param bundleInfo
	 *            Information regarding the message bundle to be used in the message retrieval. It might be a
	 *            class (annotated with org.dejava.component.i18n.message.annotation.MessageBundles), a
	 *            collection of annotated classes, or null (the stack trace classes will be used).
	 * @param locale
	 *            Locale for the message.
	 * @param type
	 *            Type for the message.
	 * @param messageKey
	 *            Key for the message.
	 * @param parameters
	 *            Parameters for the message.
	 * @param expectedMessage
	 *            The expected message for this message command.
	 */
	public TestMessageCommand(final Object bundleInfo, final Locale locale, final String type,
			final String messageKey, final Object[] parameters, final String expectedMessage) {
		super(bundleInfo, locale, type, messageKey, parameters);
		// Sets the expected message.
		this.expectedMessage = expectedMessage;
	}

}
