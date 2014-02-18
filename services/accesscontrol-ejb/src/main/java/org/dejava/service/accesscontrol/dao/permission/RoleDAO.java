package org.dejava.service.accesscontrol.dao.permission;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.accesscontrol.model.permission.Role;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * DAO for role.
 */
@AccessControlCtx
public class RoleDAO extends AbstractGenericDAO<Role, Integer> {

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
