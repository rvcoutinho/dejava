package org.dejava.service.message.component;

import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.dejava.service.message.model.AppNotification;

/**
 * MDB for the application notification sender.
 */
@MessageDriven(mappedName = "/Queue/Notification/App/Send")
public class AppNotificationSender implements MessageListener {

	/**
	 * The application notification EJB component.
	 */
	@Inject
	private AppNotificationComponent messageComponent;

	/**
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(final Message message) {
		// Tries to send the application notification.
		try {
			// Gets the original notification.
			AppNotification appNotification = message.getBody(AppNotification.class);
			// Persists the message.
			messageComponent.addOrUpdate(appNotification);
		}
		// If the JMS message cannot be processed.
		catch (JMSException exception) {
			// TODO Auto-generated catch block

		}
	}
}
