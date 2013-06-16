package org.dejava.service.location.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.location.model.City;
import org.dejava.service.location.util.LocationCtx;

/**
 * DAO for city.
 */
@LocationCtx
public class CityDAO extends AbstractGenericDAO<City, Integer> {

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
