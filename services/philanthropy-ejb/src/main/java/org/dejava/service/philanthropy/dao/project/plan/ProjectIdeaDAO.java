package org.dejava.service.philanthropy.dao.project.plan;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

	/**
	 * Counts the number of ideas for a supporter.
	 * 
	 * @param supporterId
	 *            The supporter id.
	 * @return The number of ideas for a supporter.
	 */
	public Long countIdeasBySupporter(final Integer supporterId) {
		// Gets the named query for the count.
		final TypedQuery<Long> countIdeasBySupporter = getEntityManager().createNamedQuery(
				"countIdeasBySupporter", Long.class);
		// Sets the supporter id to the named query.
		countIdeasBySupporter.setParameter("supporterId", supporterId);
		// Returns the result for the count.
		return countIdeasBySupporter.getSingleResult();
	}

}
