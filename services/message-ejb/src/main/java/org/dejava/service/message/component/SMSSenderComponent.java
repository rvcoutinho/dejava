package org.dejava.service.message.component;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * MDB for the SMS sender.
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "/Queue/Message/SMSSender") }, mappedName = "/Queue/Message/SMSSender")
public class SMSSenderComponent implements MessageListener {

	/**
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(final Message message) {
		// TODO Auto-generated method stub

	}

}
