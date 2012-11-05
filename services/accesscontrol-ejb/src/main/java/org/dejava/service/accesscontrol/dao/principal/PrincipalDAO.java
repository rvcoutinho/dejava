package org.dejava.service.accesscontrol.dao.principal;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.service.accesscontrol.model.principal.Principal;
import org.dejava.service.accesscontrol.util.AccessControl;

/**
 * DAO for principal.
 */
public class PrincipalDAO extends AbstractGenericDAO<Principal, Integer> {

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
