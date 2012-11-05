package org.dejava.service.accesscontrol.dao.credential;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.service.accesscontrol.model.credential.Password;
import org.dejava.service.accesscontrol.util.AccessControl;

/**
 * DAO for password.
 */
public class PasswordDAO extends AbstractGenericDAO<Password, Integer> {

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
