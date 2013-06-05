package org.dejava.service.location.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.location.util.Location;

/**
 * DAO for location.
 */
@Location
public class LocationDAO extends AbstractGenericDAO<Location, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@Location
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
