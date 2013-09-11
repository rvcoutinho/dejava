package org.dejava.service.philanthropy.dao.share;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.model.share.ProjectShare;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Project share DAO.
 */
@PhilanthropyCtx
public class ProjectShareDAO extends AbstractGenericDAO<ProjectShare, Integer> {

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
	 * Counts the number of shares for a project.
	 * 
	 * @param projectId
	 *            The project id.
	 * @return The number of shares for a project.
	 */
	public Long countProjectShares(final Integer projectId) {
		// Gets the named query for the count.
		final TypedQuery<Long> countProjectShares = getEntityManager().createNamedQuery("countProjectShares",
				Long.class);
		// Sets the project id to the named query.
		countProjectShares.setParameter("projectId", projectId);
		// Returns the result for the count.
		return countProjectShares.getSingleResult();
	}

}
