package org.dejava.service.accesscontrol.dao.principal;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.accesscontrol.model.principal.Facebook;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * DAO for facebook.
 */
@AccessControlCtx
public class FacebookDAO extends AbstractGenericDAO<Facebook, Integer> {

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
