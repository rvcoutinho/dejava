package org.dejava.service.philanthropy.dao.project;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.model.project.ProjectPlan;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * AbstractProject plan DAO.
 */
@PhilanthropyCtx
public class ProjectPlanDAO extends AbstractGenericDAO<ProjectPlan, Integer> {

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
