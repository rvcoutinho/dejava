package org.dejava.service.accesscontrol.dao.permission;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.accesscontrol.model.permission.UserPermission;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * DAO for user permission.
 */
@AccessControlCtx
public class UserPermissionDAO extends AbstractGenericDAO<UserPermission, Integer> {

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

	/**
	 * Gets the user permissions by name.
	 * 
	 * @param userId
	 *            The user identifier.
	 * @param name
	 *            The permission name (or name expression).
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return The user permissions by name.
	 */
	public Collection<UserPermission> getByName(final Integer userId, final String name,
			final Integer firstResult, final Integer maxResults) {
		// Gets the named query for the method.
		final TypedQuery<UserPermission> getByName = getEntityManager().createNamedQuery("getByName",
				UserPermission.class);
		// Sets the user id to the named query.
		getByName.setParameter("userId", userId);
		// Sets the permission name to the named query.
		getByName.setParameter("name", name);
		// Limits the query results.
		limitResultList(getByName, firstResult, maxResults);
		// Returns the result for the query.
		return getByName.getResultList();
	}

}
