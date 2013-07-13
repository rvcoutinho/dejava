package org.dejava.component.exception.localized;

import org.dejava.component.i18n.message.handler.ApplicationMessageHandler;
import org.dejava.component.i18n.message.handler.MessageCommand;
import org.dejava.component.i18n.message.handler.MessageHandler;

/**
 * Localized exception contract.
 */
public interface LocalizedException {

	/**
	 * Sets the message handler to be used in order to render localized exception messages.
	 * 
	 * @param messageHandler
	 *            New message handler to be used in order to render localized exception messages.
	 */
	void setMessageHandler(final MessageHandler messageHandler);

	/**
	 * Sets the message command for the localized exception.
	 * 
	 * @param messageCommand
	 *            New message command for the localized exception.
	 */
	void setMessageCommand(final MessageCommand messageCommand);

	/**
	 * Add messages from the given exception to the application.
	 * 
	 * @param messageHandler
	 *            The application message handler.
	 */
	void addLocalizedMessages(final ApplicationMessageHandler messageHandler);
}
