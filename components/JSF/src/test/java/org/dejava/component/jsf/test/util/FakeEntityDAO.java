package org.dejava.component.jsf.test.util;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;

/**
 * Fake entity DAO.
 */
@EJB
public class FakeEntityDAO extends AbstractGenericDAO<FakeEntity, Integer> {

	/**
	 * Entity manager.
	 */
	@EJB
	@Inject
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
