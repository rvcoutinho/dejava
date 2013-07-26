package org.dejava.component.faces.test.controller.util;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;

/**
 * Fake entity DAO.
 */
@Faces
public class FakeEntityDAO extends AbstractGenericDAO<FakeEntity, Integer> {

	/**
	 * Entity manager.
	 */
	@Inject
	@Faces
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
