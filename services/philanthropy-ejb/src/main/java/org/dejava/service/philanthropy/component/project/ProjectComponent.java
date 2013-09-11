package org.dejava.service.philanthropy.component.project;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.dao.project.ProjectDAO;
import org.dejava.service.philanthropy.dao.project.ProjectIdeaDAO;
import org.dejava.service.philanthropy.model.project.Project;
import org.dejava.service.philanthropy.model.project.ProjectIdea;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Project EJB component.
 */
@PhilanthropyCtx
@Stateless(name = "Component/Philanthropy/Project")
public class ProjectComponent extends AbstractGenericComponent<Project, Integer> {

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
	public AbstractGenericDAO<Project, Integer> getEntityDAO() {
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

}
