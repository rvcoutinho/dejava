package org.dejava.service.message.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.message.dao.SMSMessageDAO;
import org.dejava.service.message.model.SMSMessage;
import org.dejava.service.message.util.MessageCtx;

/**
 * EJB component for the SMS message.
 */
@MessageCtx
@Stateless(name = "Component/Message/SMSMessage")
public class SMSMessageComponent extends AbstractGenericComponent<SMSMessage, Integer> {

	/**
	 * The DAO for the entity.
	 */
	@Inject
	@MessageCtx
	private SMSMessageDAO smsMessageDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<SMSMessage, Integer> getEntityDAO() {
		return smsMessageDAO;
	}

}
