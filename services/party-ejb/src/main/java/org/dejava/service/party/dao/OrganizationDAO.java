package org.dejava.service.party.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.party.model.Organization;
import org.dejava.service.party.util.PartyCtx;

/**
 * Organization DAO.
 */
@PartyCtx
public class OrganizationDAO extends AbstractGenericDAO<Organization, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@PartyCtx
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
