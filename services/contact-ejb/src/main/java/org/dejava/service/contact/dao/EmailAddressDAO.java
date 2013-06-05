package org.dejava.service.contact.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.contact.model.EmailAddress;
import org.dejava.service.contact.util.Contact;

/**
 * DAO for the email address.
 */
@Contact
public class EmailAddressDAO extends AbstractGenericDAO<EmailAddress, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@Contact
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}