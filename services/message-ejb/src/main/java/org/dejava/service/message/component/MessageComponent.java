package org.dejava.service.message.component;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

import org.dejava.component.ejb.constant.DAOParamKeys;
import org.dejava.component.validation.method.PreConditions;
import org.dejava.service.message.businessrule.MessageBusinessRuleSet;
import org.dejava.service.message.dao.AppNotificationDAO;
import org.dejava.service.message.dao.MessageDAO;
import org.dejava.service.message.model.AppMessage;
import org.dejava.service.message.model.AppNotification;
import org.dejava.service.message.model.EmailMessage;
import org.dejava.service.message.model.Message;
import org.dejava.service.message.util.MessageCtx;

/**
 * EJB component for messages.
 */
@MessageCtx
@Stateless(name = "Component/Message/Message")
public class MessageComponent {

	/**
	 * The application message send queue.
	 */
	@Resource(mappedName = "java:/jms/Queue/AppMessage/Send")
	private Queue appMessageQueue;

	/**
	 * The email message send queue.
	 */
	@Resource(mappedName = "java:/jms/Queue/EmailMessage/Send")
	private Queue emailMessageQueue;

	/**
	 * The application notification send queue.
	 */
	@Resource(mappedName = "java:/jms/Queue/AppNotification/Send")
	private Queue appNotificationQueue;

	/**
	 * JMS context.
	 */
	@Inject
	private JMSContext jmsContext;

	/**
	 * The DAO for message.
	 */
	@Inject
	@MessageCtx
	private MessageDAO messageDAO;

	/**
	 * The DAO for application notification.
	 */
	@Inject
	@MessageCtx
	private AppNotificationDAO appNotificationDAO;

	/**
	 * The message business rule set.
	 */
	@Inject
	@MessageCtx
	private MessageBusinessRuleSet messageBusinessRuleSet;

	/**
	 * Sends an application message.
	 * 
	 * @param message
	 *            Message to be sent.
	 */
	public void sendAppMessage(final AppMessage message) {
		// Asserts that the message is not null.
		PreConditions.assertParamNotNull(DAOParamKeys.ENTITY, message);
		// Validates the message to be sent.
		messageBusinessRuleSet.validate(message);
		// Sends the message to the queue.
		jmsContext.createProducer().send(appMessageQueue, message);
	}

	/**
	 * Sends an email message.
	 * 
	 * @param message
	 *            Message to be sent.
	 */
	public void sendEmailMessage(final EmailMessage message) {
		// Asserts that the message is not null.
		PreConditions.assertParamNotNull(DAOParamKeys.ENTITY, message);
		// Validates the message to be sent.
		messageBusinessRuleSet.validate(message);
		// Sends the message to the queue.
		jmsContext.createProducer().send(emailMessageQueue, message);
	}

	/**
	 * Sends an application notification.
	 * 
	 * @param notification
	 *            Notification to be sent.
	 */
	public void sendAppNotification(final AppNotification notification) {
		// Asserts that the message is not null.
		PreConditions.assertParamNotNull(DAOParamKeys.ENTITY, notification);
		// Validates the message to be sent.
		messageBusinessRuleSet.validate(notification);
		// Sends the message to the queue.
		jmsContext.createProducer().send(appNotificationQueue, notification);
	}

	/**
	 * Creates a message.
	 * 
	 * @param message
	 *            New message.
	 * @return The new message.
	 */
	public Message createMessage(final Message message) {
		// Asserts that the message is not null.
		PreConditions.assertParamNotNull(DAOParamKeys.ENTITY, message);
		// Validates the message to be added.
		messageBusinessRuleSet.validate(message);
		// Adds the new message.
		return messageDAO.persist(message);
	}

	/**
	 * Gets the notifications by the recipient.
	 * 
	 * @param recipient
	 *            The recipient for the notification.
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return The notifications by the recipient.
	 */
	public Collection<AppNotification> getAppNotificationsByRecipient(final Integer recipient,
			final Integer firstResult, final Integer maxResults) {
		return appNotificationDAO.getByAttribute("recipient", recipient, firstResult, maxResults);
	}

	/**
	 * Counts the unread notifications by the recipient.
	 * 
	 * @param recipient
	 *            The recipient for the notification.
	 * @return The unread notifications count by the recipient.
	 */
	public Long countUnreadAppNotificationsByRecipient(final Integer recipient) {
		return appNotificationDAO.countUnreadByRecipient(recipient);
	}
}
