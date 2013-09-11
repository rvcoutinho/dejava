package org.dejava.service.message.component;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * MDB for the email sender.
 */
// @MessageDriven(activationConfig = {
// @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
// @ActivationConfigProperty(propertyName = "destination", propertyValue = "/Queue/Message/EmailSender") },
// mappedName = "/Queue/Message/EmailSender")
public class EmailSenderComponent implements MessageListener {

	/**
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(final Message message) {
		// TODO Auto-generated method stub

	}

}
