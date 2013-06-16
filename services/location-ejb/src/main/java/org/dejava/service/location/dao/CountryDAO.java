package org.dejava.service.location.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.location.model.Country;
import org.dejava.service.location.util.LocationCtx;

/**
 * DAO for country.
 */
@LocationCtx
public class CountryDAO extends AbstractGenericDAO<Country, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@LocationCtx
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
