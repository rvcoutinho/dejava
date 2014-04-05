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
	public Long countSharesByProject(final Integer projectId) {
		// Gets the named query for the count.
		final TypedQuery<Long> countSharesByProject = getEntityManager().createNamedQuery(
				"countSharesByProject", Long.class);
		// Sets the project id to the named query.
		countSharesByProject.setParameter("projectId", projectId);
		// Returns the result for the count.
		return countSharesByProject.getSingleResult();
	}

	/**
	 * Counts the number of shares for a supporter.
	 * 
	 * @param supporterId
	 *            The supporter id.
	 * @return The number of shares for a supporter.
	 */
	public Long countSharesBySupporter(final Integer supporterId) {
		// Gets the named query for the count.
		final TypedQuery<Long> countSharesBySupporter = getEntityManager().createNamedQuery(
				"countSharesBySupporter", Long.class);
		// Sets the supporter id to the named query.
		countSharesBySupporter.setParameter("supporterId", supporterId);
		// Returns the result for the count.
		return countSharesBySupporter.getSingleResult();
	}

	/**
	 * Counts the number of shares for a project and a supporter.
	 * 
	 * @param projectId
	 *            The project id.
	 * @param supporterId
	 *            The supporter id.
	 * @return The number of shares for a project and a supporter.
	 */
	public Long countSharesByProjectAndSupporter(final Integer projectId, final Integer supporterId) {
		// Gets the named query for the count.
		final TypedQuery<Long> getShareByProjectAndSupporter = getEntityManager().createNamedQuery(
				"countShareByProjectAndSupporter", Long.class);
		// Sets the project id to the named query.
		getShareByProjectAndSupporter.setParameter("projectId", projectId);
		// Sets the supporter id to the named query.
		getShareByProjectAndSupporter.setParameter("supporterId", supporterId);
		// Returns the result for the count.
		return getShareByProjectAndSupporter.getSingleResult();
	}

}
