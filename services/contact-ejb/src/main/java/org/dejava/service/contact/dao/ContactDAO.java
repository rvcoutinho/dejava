package org.dejava.service.contact.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.contact.model.Contact;
import org.dejava.service.contact.util.ContactCtx;

/**
 * DAO for the contact.
 */
@ContactCtx
public class ContactDAO extends AbstractGenericDAO<Contact, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@ContactCtx
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
