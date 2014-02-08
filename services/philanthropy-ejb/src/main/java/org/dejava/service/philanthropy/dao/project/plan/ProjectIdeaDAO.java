package org.dejava.service.philanthropy.dao.project.plan;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.model.project.plan.ProjectIdea;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Project idea DAO.
 */
@PhilanthropyCtx
public class ProjectIdeaDAO extends AbstractGenericDAO<ProjectIdea, Integer> {

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

}
