package org.dejava.service.philanthropy.dao.project;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.model.project.Project;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Philanthropy project DAO.
 */
@PhilanthropyCtx
public class ProjectDAO extends AbstractGenericDAO<Project, Integer> {

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
	 * Gets all projects by tag.
	 * 
	 * @param tag
	 *            The tag for the projects.
	 * @param firstResult
	 *            The first result that should be considered.
	 * @param maxResults
	 *            The maximum numbers of results to be considered.
	 * @return Projects by tag.
	 */
	public Collection<Project> getProjectsByTag(final String tag, final Integer firstResult,
			final Integer maxResults) {
		// Gets the named query for the method.
		final TypedQuery<Project> getProjectsByTag = getEntityManager().createNamedQuery(
				"getProjectsByTag", Project.class);
		// Sets the tag to the named query.
		getProjectsByTag.setParameter("tag", tag);
		// Limits the query results.
		limitResultList(getProjectsByTag, firstResult, maxResults);
		// Returns the result for the query.
		return getProjectsByTag.getResultList();
	}

	/**
	 * Gets the planned projects (not ongoing).
	 * 
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return The planned projects (not ongoing).
	 */
	public Collection<Project> getPlannedProjects(final Integer firstResult,
			final Integer maxResults) {
		// Gets the named query for the method.
		final TypedQuery<Project> getPlannedProjects = getEntityManager().createNamedQuery(
				"getPlannedProjects", Project.class);
		// Limits the query results.
		limitResultList(getPlannedProjects, firstResult, maxResults);
		// Returns the result for the query.
		return getPlannedProjects.getResultList();
	}

	/**
	 * Gets the ongoing projects (not ongoing).
	 * 
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return The ongoing projects (not ongoing).
	 */
	public Collection<Project> getOngoingProjects(final Integer firstResult,
			final Integer maxResults) {
		// Gets the named query for the method.
		final TypedQuery<Project> getOngoingProjects = getEntityManager().createNamedQuery(
				"getOngoingProjects", Project.class);
		// Limits the query results.
		limitResultList(getOngoingProjects, firstResult, maxResults);
		// Returns the result for the query.
		return getOngoingProjects.getResultList();
	}

	/**
	 * Counts the number of ideas for a supporter.
	 * 
	 * @param supporterId
	 *            The supporter id.
	 * @return The number of ideas for a supporter.
	 */
	public Long countProjectsCreatedBySupporter(final Integer supporterId) {
		// Gets the named query for the count.
		final TypedQuery<Long> countProjectsCreatedBySupporter = getEntityManager().createNamedQuery(
				"countProjectsCreatedBySupporter", Long.class);
		// Sets the supporter id to the named query.
		countProjectsCreatedBySupporter.setParameter("supporterId", supporterId);
		// Returns the result for the count.
		return countProjectsCreatedBySupporter.getSingleResult();
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
	public Collection<Project> getProjectsCreatedBySupporter(final Integer supporterId,
			final Integer firstResult, final Integer maxResults) {
		// Gets the named query for the method.
		final TypedQuery<Project> getProjectsCreatedBySupporter = getEntityManager()
				.createNamedQuery("getProjectsCreatedBySupporter", Project.class);
		// Sets the supporter id to the named query.
		getProjectsCreatedBySupporter.setParameter("supporterId", supporterId);
		// Limits the query results.
		limitResultList(getProjectsCreatedBySupporter, firstResult, maxResults);
		// Returns the result for the query.
		return getProjectsCreatedBySupporter.getResultList();
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
	public Collection<Project> getProjectsSharedBySupporter(final Integer supporterId,
			final Integer firstResult, final Integer maxResults) {
		// Gets the named query for the method.
		final TypedQuery<Project> getProjectsSharedBySupporter = getEntityManager()
				.createNamedQuery("getProjectsSharedBySupporter", Project.class);
		// Sets the supporter id to the named query.
		getProjectsSharedBySupporter.setParameter("supporterId", supporterId);
		// Limits the query results.
		limitResultList(getProjectsSharedBySupporter, firstResult, maxResults);
		// Returns the result for the query.
		return getProjectsSharedBySupporter.getResultList();
	}

}
