package org.dejava.component.javaee.test.util;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.javaee.dao.AbstractGenericDAO;

/**
 * Fake entity DAO.
 */
@JavaEE
public class FakeEntityDAO extends AbstractGenericDAO<FakeEntity, Integer> {

	/**
	 * Entity manager.
	 */
	@JavaEE
	@Inject
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.javaee.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
