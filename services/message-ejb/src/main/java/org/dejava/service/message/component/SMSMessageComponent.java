package org.dejava.service.message.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.message.dao.EmailMessageDAO;
import org.dejava.service.message.model.EmailMessage;
import org.dejava.service.message.util.Message;

/**
 * EJB component for the SMS message.
 */
@Message
@Stateless(name = "Component/Message/EmailMessage")
public class SMSMessageComponent extends AbstractGenericComponent<EmailMessage, Integer> {

	/**
	 * The DAO for the entity.
	 */
	@Inject
	@Message
	private EmailMessageDAO smsMessageDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<EmailMessage, Integer> getEntityDAO() {
		return smsMessageDAO;
	}

}
