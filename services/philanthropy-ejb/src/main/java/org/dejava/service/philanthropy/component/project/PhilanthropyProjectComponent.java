package org.dejava.service.philanthropy.component.project;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.dao.project.PhilanthropyProjectDAO;
import org.dejava.service.philanthropy.dao.project.plan.ProjectIdeaDAO;
import org.dejava.service.philanthropy.model.project.PhilanthropyProject;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Philanthropy project EJB component.
 */
@PhilanthropyCtx
@Stateless(name = "Component/Philanthropy/PhilanthropyProject")
public class PhilanthropyProjectComponent extends AbstractGenericComponent<PhilanthropyProject, Integer> {

	/**
	 * The project DAO.
	 */
	@Inject
	@PhilanthropyCtx
	private PhilanthropyProjectDAO philanthropyProjectDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<PhilanthropyProject, Integer> getEntityDAO() {
		return philanthropyProjectDAO;
	}

	/**
	 * Gets all not planned project ideas.
	 * 
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * 
	 * @return All not planned project ideas.
	 */
	public Collection<PhilanthropyProject> getAllProjectIdeas(final Integer firstResult,
			final Integer maxResults) {
		return getByAttribute("plan", null, firstResult, maxResults);
	}

	/**
	 * Gets all project plans.
	 * 
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * 
	 * @return All project plans.
	 */
	public Collection<PhilanthropyProject> getAllProjectPlans(final Integer firstResult,
			final Integer maxResults) {
		return getAll(firstResult, maxResults);
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
		return philanthropyProjectDAO.getAllSharedBySupporter(supporterId, firstResult, maxResults);
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
		return philanthropyProjectDAO.getAllCreatedBySupporter(supporterId, firstResult, maxResults);
	}

	/**
	 * The project idea DAO.
	 */
	@Inject
	@PhilanthropyCtx
	private ProjectIdeaDAO projectIdeaDAO;

	/**
	 * Counts the number of ideas created by a supporter.
	 * 
	 * @param supporterId
	 *            The supporter id.
	 * @return The number of ideas created by a supporter.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Long countIdeasBySupporter(final Integer supporterId) {
		return projectIdeaDAO.countIdeasBySupporter(supporterId);
	}

}
