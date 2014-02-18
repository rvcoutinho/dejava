package org.dejava.service.accesscontrol.dao.permission;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.accesscontrol.model.permission.Permission;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * DAO for permission.
 */
@AccessControlCtx
public class PermissionDAO extends AbstractGenericDAO<Permission, Integer> {

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
