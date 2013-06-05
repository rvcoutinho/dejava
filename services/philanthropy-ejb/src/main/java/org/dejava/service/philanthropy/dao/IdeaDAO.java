package org.dejava.service.philanthropy.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.model.Idea;
import org.dejava.service.philanthropy.util.Philanthropy;

/**
 * TODO
 */
@Philanthropy
public class IdeaDAO extends AbstractGenericDAO<Idea, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@Philanthropy
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
