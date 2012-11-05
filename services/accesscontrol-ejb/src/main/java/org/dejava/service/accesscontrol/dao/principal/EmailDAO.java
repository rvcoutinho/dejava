package org.dejava.service.accesscontrol.dao.principal;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.service.accesscontrol.model.principal.Email;
import org.dejava.service.accesscontrol.util.AccessControl;

/**
 * DAO for email.
 */
public class EmailDAO extends AbstractGenericDAO<Email, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@AccessControl
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.javaee.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
