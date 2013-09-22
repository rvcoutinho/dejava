package org.dejava.service.message.component;

import org.dejava.service.message.model.Message;

/**
 * Message sender interface.
 */
public interface MessageSender {

	/**
	 * Sends a message.
	 * 
	 * @param message
	 *            Message to be sent.
	 */
	void sendMessage(Message message);
}
