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
import org.dejava.service.message.dao.EmailMessageDAO;
import org.dejava.service.message.model.EmailMessage;
import org.dejava.service.message.model.Message;
import org.dejava.service.message.util.MessageCtx;

/**
 * EJB component for the email message.
 */
@MessageCtx
@Stateless(name = "Component/Message/EmailMessage")
public class EmailMessageComponent extends AbstractGenericComponent<EmailMessage, Integer> {

	/**
	 * The message send queue.
	 */
	@Resource(lookup = "java:module/jms/Queue/EmailMessage/Send")
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
	private EmailMessageDAO emailMessageDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<EmailMessage, Integer> getEntityDAO() {
		return emailMessageDAO;
	}

	/**
	 * Sends a message.
	 * 
	 * @param message
	 *            Message to be sent.
	 */
	public void sendMessage(Message message) {
		// Asserts that the message is not null.
		PreConditions.assertParamNotNull(DAOParamKeys.ENTITY, message);
		// Validates the current message (and throws an exception for the found violations).
		ValidationException.throwViolationExceptions(Validation.buildDefaultValidatorFactory().getValidator()
				.validate(message));
		// Sends the message to the queue.
		jmsContext.createProducer().send(sendQueue, message);
	}

}
