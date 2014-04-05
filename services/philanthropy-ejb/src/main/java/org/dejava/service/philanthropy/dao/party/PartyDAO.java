package org.dejava.service.philanthropy.dao.party;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.model.party.Party;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Philanthropy party DAO.
 */
@PhilanthropyCtx
public class PartyDAO extends AbstractGenericDAO<Party, Integer> {

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
	public Party merge(final Party entity) {
		// Merges the party.
		final Party newParty = super.merge(entity);
		// Asserts that the original party is set.
		newParty.setParty(entity.getParty());
		// Returns the merged party.
		return newParty;
	}

}
