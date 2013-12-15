package org.dejava.service.philanthropy.dao.project.author;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.model.project.author.ProjectAuthor;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Project author DAO.
 */
@PhilanthropyCtx
public class ProjectAuthorDAO extends AbstractGenericDAO<ProjectAuthor, Integer> {

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
