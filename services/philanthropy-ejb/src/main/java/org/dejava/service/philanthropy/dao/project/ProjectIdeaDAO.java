package org.dejava.service.philanthropy.dao.project;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.model.project.ProjectIdea;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * AbstractProject idea DAO.
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
