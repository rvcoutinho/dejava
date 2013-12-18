package org.dejava.service.message.component;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.validation.Validation;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.constant.DAOParamKeys;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.component.validation.method.PreConditions;
import org.dejava.component.validation.object.ValidationException;
import org.dejava.service.message.dao.AppNotificationDAO;
import org.dejava.service.message.model.AppNotification;
import org.dejava.service.message.util.MessageCtx;

/**
 * EJB component for the application notification.
 */
@MessageCtx
@Stateless(name = "Component/Message/AppNotification")
public class AppNotificationComponent extends AbstractGenericComponent<AppNotification, Integer> {

	/**
	 * The message send queue.
	 */
	@Resource(mappedName = "java:/jms/Queue/AppNotification/Send")
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
	 * Sends a notification.
	 * 
	 * @param notification
	 *            Notification to be sent.
	 */
	public void sendMessage(AppNotification notification) {
		// Asserts that the message is not null.
		PreConditions.assertParamNotNull(DAOParamKeys.ENTITY, notification);
		// Validates the current message (and throws an exception for the found violations).
		ValidationException.throwViolationExceptions(Validation.buildDefaultValidatorFactory().getValidator()
				.validate(notification));
		// Sends the notification to the queue. FIXME
		jmsContext.createProducer().send(sendQueue, notification);
	}
}
