package org.dejava.service.philanthropy.component.project;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.dao.project.ProjectDAO;
import org.dejava.service.philanthropy.dao.project.ProjectIdeaDAO;
import org.dejava.service.philanthropy.dao.project.ProjectPlanDAO;
import org.dejava.service.philanthropy.model.project.AbstractProject;
import org.dejava.service.philanthropy.model.project.ProjectIdea;
import org.dejava.service.philanthropy.model.project.ProjectPlan;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * AbstractProject EJB component.
 */
@PhilanthropyCtx
@Stateless(name = "Component/Philanthropy/AbstractProject")
public class ProjectComponent extends AbstractGenericComponent<AbstractProject, Integer> {

	/**
	 * The project DAO.
	 */
	@Inject
	@PhilanthropyCtx
	private ProjectDAO projectDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<AbstractProject, Integer> getEntityDAO() {
		return projectDAO;
	}

	/**
	 * The project idea DAO.
	 */
	@Inject
	@PhilanthropyCtx
	private ProjectIdeaDAO projectIdeaDAO;

	/**
	 * Gets all project ideas.
	 * 
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * 
	 * @return All entities of the kind.
	 */
	public Collection<ProjectIdea> getAllProjectIdeas(final Integer firstResult, final Integer maxResults) {
		return projectIdeaDAO.getAll(firstResult, maxResults);
	}

	/**
	 * The project plan DAO.
	 */
	@Inject
	@PhilanthropyCtx
	private ProjectPlanDAO projectPlanDAO;

	/**
	 * Gets all project plans.
	 * 
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * 
	 * @return All entities of the kind.
	 */
	public Collection<ProjectPlan> getAllProjectPlans(final Integer firstResult, final Integer maxResults) {
		return projectPlanDAO.getAll(firstResult, maxResults);
	}

}
