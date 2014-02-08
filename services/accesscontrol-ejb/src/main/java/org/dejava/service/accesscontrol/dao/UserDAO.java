package org.dejava.service.accesscontrol.dao;

import java.util.ArrayList;
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
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#merge(java.lang.Object)
	 */
	@Override
	public User merge(final User entity) {
		// Asserts that the entity is not null.
		PreConditions.assertParamNotNull(DAOParamKeys.ENTITY, entity);
		// If the user has no id.
		if (entity.getIdentifier() == null) {
			// Gets the credentials for the user.
			final Collection<Credentials> credentials = entity.getCredentials();
			// Remove the principals from the user (credentials might use the user id for hash calculations).
			entity.setCredentials(null);
			// Merges the user.
			User mergedUser = super.merge(entity);
			// Re-adds the credentials to the merged user.
			mergedUser.setCredentials(new ArrayList<>(credentials), true);
			// Re-sets the credentials for the given user (to prevent problems with old references).
			entity.setCredentials(credentials, true);
			// Returns the merged user.
			return mergedUser;
		}
		// If it has an id.
		else {
			// Merges the user normally.
			return super.merge(entity);
		}
	}

}
