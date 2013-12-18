package org.dejava.service.message.component;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.dejava.service.message.model.AppMessage;
import org.dejava.service.message.util.MessageCtx;

/**
 * MDB for the application message sender.
 */
@MessageCtx
@MessageDriven(name = "Queue/AppMessage/Send", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/Queue/AppMessage/Send"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
@JMSDestinationDefinition(name = "java:/jms/Queue/AppMessage/Send", interfaceName = "javax.jms.Queue", destinationName = "AppMessageSendQueue")
public class AppMessageSender implements MessageListener {

	/**
	 * The application message EJB component.
	 */
	@Inject
	@MessageCtx
	private AppMessageComponent messageComponent;

	/**
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(final Message message) {
		// Tries to send the application message.
		try {
			// Gets the original message.
			AppMessage appMessage = message.getBody(AppMessage.class);
			// Persists the message.
			messageComponent.addOrUpdate(appMessage);
		}
		// If the JMS message cannot be processed.
		catch (JMSException exception) {
			// TODO Auto-generated catch block

		}
	}
}
