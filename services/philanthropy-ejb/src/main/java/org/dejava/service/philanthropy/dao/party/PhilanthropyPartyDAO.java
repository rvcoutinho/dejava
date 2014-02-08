package org.dejava.service.philanthropy.dao.party;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.model.party.PhilanthropyParty;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Philanthropy party DAO.
 */
@PhilanthropyCtx
public class PhilanthropyPartyDAO extends AbstractGenericDAO<PhilanthropyParty, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@PhilanthropyCtx
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
	public PhilanthropyParty merge(final PhilanthropyParty entity) {
		// Merges the party.
		final PhilanthropyParty newParty = super.merge(entity);
		// Asserts that the original party is set.
		newParty.setParty(entity.getParty());
		// Returns the merged party.
		return newParty;
	}

}
