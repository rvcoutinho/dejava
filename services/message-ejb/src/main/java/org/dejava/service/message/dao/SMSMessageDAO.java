package org.dejava.service.message.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.message.model.SMSMessage;
import org.dejava.service.message.util.Message;

/**
 * DAO for the SMS message.
 */
@Message
public class SMSMessageDAO extends AbstractGenericDAO<SMSMessage, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@Message
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
