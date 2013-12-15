package org.dejava.service.philanthropy.dao.project;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.dejava.component.ejb.constant.DAOParamKeys;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.component.validation.method.PreConditions;
import org.dejava.service.philanthropy.dao.project.author.ProjectAuthorDAO;
import org.dejava.service.philanthropy.model.party.Supporter;
import org.dejava.service.philanthropy.model.project.PhilanthropyProject;
import org.dejava.service.philanthropy.model.project.author.ProjectAuthor;
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
	public Collection<PhilanthropyProject> getAllCreatedBySupporter(final Integer supporterId,
			final Integer firstResult, final Integer maxResults) {
		// Gets the named query for the method.
		final TypedQuery<PhilanthropyProject> getAllCreatedBySupporter = getEntityManager().createNamedQuery(
				"getAllCreatedBySupporter", PhilanthropyProject.class);
		// Sets the supporter id to the named query.
		getAllCreatedBySupporter.setParameter("supporterId", supporterId);
		// Limits the query results.
		limitResultList(getAllCreatedBySupporter, firstResult, maxResults);
		// Returns the result for the query.
		return getAllCreatedBySupporter.getResultList();
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
	public Collection<PhilanthropyProject> getAllSharedBySupporter(final Integer supporterId,
			final Integer firstResult, final Integer maxResults) {
		// Gets the named query for the method.
		final TypedQuery<PhilanthropyProject> getAllSharedBySupporter = getEntityManager().createNamedQuery(
				"getAllSharedBySupporter", PhilanthropyProject.class);
		// Sets the supporter id to the named query.
		getAllSharedBySupporter.setParameter("supporterId", supporterId);
		// Limits the query results.
		limitResultList(getAllSharedBySupporter, firstResult, maxResults);
		// Returns the result for the query.
		return getAllSharedBySupporter.getResultList();
	}

	/**
	 * The project author DAO.
	 */
	@Inject
	@PhilanthropyCtx
	private ProjectAuthorDAO projectAuthorDAO;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#merge(java.lang.Object)
	 */
	@Override
	public PhilanthropyProject merge(PhilanthropyProject entity) {
		// Asserts that the entity is not null.
		PreConditions.assertParamNotNull(DAOParamKeys.ENTITY, entity);
		// If there is an idea for the project.
		if (entity.getIdea() != null) {
			// Removes the previous project authors.
			projectAuthorDAO.remove(entity.getIdea().getProjectAuthors());
			// Resets the previous project authors.
			entity.getIdea().setProjectAuthors(null);
			// And for the current authors.
			for (Supporter currentAuthor : entity.getIdea().getAuthors()) {
				// Adds a new project author.
				entity.getIdea().getProjectAuthors().add(new ProjectAuthor(entity, currentAuthor));
			}
		}
		// Finally, persists the project (and authors).
		return super.merge(entity);
	}
}
