package org.dejava.service.accesscontrol.dao.credentials;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.accesscontrol.model.credentials.Password;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * DAO for password.
 */
@AccessControlCtx
public class PasswordDAO extends AbstractGenericDAO<Password, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@AccessControlCtx
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
