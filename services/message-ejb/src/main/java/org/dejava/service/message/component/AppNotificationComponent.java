package org.dejava.service.message.component;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.message.dao.AppNotificationDAO;
import org.dejava.service.message.model.AppNotification;
import org.dejava.service.message.model.Message;
import org.dejava.service.message.util.MessageCtx;

/**
 * EJB component for the application notification.
 */
@MessageCtx
@Stateless(name = "Component/Message/AppNotification")
public class AppNotificationComponent extends AbstractGenericComponent<AppNotification, Integer> implements
		MessageSender {

	/**
	 * The message send queue.
	 */
	@Resource(lookup = "/Queue/Notification/App/Send")
	private Queue sendQueue;

	/**
	 * JMS context.
	 */
	@Inject
	private JMSContext jmsContext;

	/**
	 * The DAO for the entity.
	 */
	@Inject
	@MessageCtx
	private AppNotificationDAO appNotificationDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<AppNotification, Integer> getEntityDAO() {
		return appNotificationDAO;
	}

	/**
	 * @see org.dejava.service.message.component.MessageSender#sendMessage(org.dejava.service.message.model.Message)
	 */
	@Override
	public void sendMessage(Message message) {
		// Sends the message to the queue.
		jmsContext.createProducer().send(sendQueue, message);
	}

}
