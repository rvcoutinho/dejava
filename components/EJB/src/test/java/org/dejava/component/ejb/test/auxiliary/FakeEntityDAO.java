package org.dejava.component.ejb.test.auxiliary;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;

/**
 * Fake entity DAO.
 */
public class FakeEntityDAO extends AbstractGenericDAO<FakeEntity> {

	/**
	 * Entity manager.
	 */
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
