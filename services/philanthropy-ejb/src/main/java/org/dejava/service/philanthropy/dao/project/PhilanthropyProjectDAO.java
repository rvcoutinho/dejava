package org.dejava.service.philanthropy.dao.project;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.model.project.PhilanthropyProject;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Philanthropy project DAO.
 */
@PhilanthropyCtx
public class PhilanthropyProjectDAO extends AbstractGenericDAO<PhilanthropyProject, Integer> {

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
	public Long countCreatedBySupporter(final Integer supporterId) {
		// Gets the named query for the count.
		final TypedQuery<Long> countCreatedBySupporter = getEntityManager().createNamedQuery(
				"countCreatedBySupporter", Long.class);
		// Sets the supporter id to the named query.
		countCreatedBySupporter.setParameter("supporterId", supporterId);
		// Returns the result for the count.
		return countCreatedBySupporter.getSingleResult();
	}

	/**
	 * Gets the projects created by a supporter.
	 * 
	 * @param supporterId
	 *            The supporter id.
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return The projects created by a supporter.
	 */
	public Collection<PhilanthropyProject> getCreatedBySupporter(final Integer supporterId,
			final Integer firstResult, final Integer maxResults) {
		// Gets the named query for the method.
		final TypedQuery<PhilanthropyProject> getCreatedBySupporter = getEntityManager().createNamedQuery(
				"getCreatedBySupporter", PhilanthropyProject.class);
		// Sets the supporter id to the named query.
		getCreatedBySupporter.setParameter("supporterId", supporterId);
		// Limits the query results.
		limitResultList(getCreatedBySupporter, firstResult, maxResults);
		// Returns the result for the query.
		return getCreatedBySupporter.getResultList();
	}

	/**
	 * Gets the projects shared by a supporter.
	 * 
	 * @param supporterId
	 *            The supporter id.
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return The projects shared by a supporter.
	 */
	public Collection<PhilanthropyProject> getSharedBySupporter(final Integer supporterId,
			final Integer firstResult, final Integer maxResults) {
		// Gets the named query for the method.
		final TypedQuery<PhilanthropyProject> getSharedBySupporter = getEntityManager().createNamedQuery(
				"getSharedBySupporter", PhilanthropyProject.class);
		// Sets the supporter id to the named query.
		getSharedBySupporter.setParameter("supporterId", supporterId);
		// Limits the query results.
		limitResultList(getSharedBySupporter, firstResult, maxResults);
		// Returns the result for the query.
		return getSharedBySupporter.getResultList();
	}

}
