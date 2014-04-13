package org.dejava.service.accesscontrol.dao;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.constant.DAOParamKeys;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.component.validation.method.PreConditions;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.credentials.Credentials;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * DAO for user.
 */
@AccessControlCtx
public class UserDAO extends AbstractGenericDAO<User, Integer> {

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
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#persist(java.lang.Object)
	 */
	@Override
	public User persist(final User entity) {
		// Asserts that the entity is not null. FIXME
		PreConditions.assertParamNotNull(DAOParamKeys.ENTITY, entity);
		// Gets the credentials for the user.
		final Collection<Credentials> credentials = entity.getCredentials();
		// Remove the principals from the user (credentials might use the user id for hash calculations).
		entity.setCredentials(null);
		// Persists the user.
		super.persist(entity);
		// Re-sets the credentials for the given user (to prevent problems with old references).
		entity.setCredentials(credentials, true);
		// Returns the persisted user.
		return entity;
	}
}
