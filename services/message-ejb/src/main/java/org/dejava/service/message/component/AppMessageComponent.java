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
import org.dejava.service.message.dao.AppMessageDAO;
import org.dejava.service.message.model.AppMessage;
import org.dejava.service.message.util.MessageCtx;

/**
 * EJB component for the application message.
 */
@MessageCtx
@Stateless(name = "Component/Message/AppMessage")
public class AppMessageComponent extends AbstractGenericComponent<AppMessage, Integer> {

	/**
	 * The message send queue.
	 */
	@Resource(mappedName = "java:/jms/Queue/AppMessage/Send")
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
	private AppMessageDAO appMessageDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<AppMessage, Integer> getEntityDAO() {
		return appMessageDAO;
	}

	/**
	 * Sends a message.
	 * 
	 * @param message
	 *            Message to be sent.
	 */
	public void sendMessage(AppMessage message) {
		// Asserts that the message is not null.
		PreConditions.assertParamNotNull(DAOParamKeys.ENTITY, message);
		// Validates the current message (and throws an exception for the found violations).
		ValidationException.throwViolationExceptions(Validation.buildDefaultValidatorFactory().getValidator()
				.validate(message));
		// Sends the message to the queue.
		jmsContext.createProducer().send(sendQueue, message);
	}

}
