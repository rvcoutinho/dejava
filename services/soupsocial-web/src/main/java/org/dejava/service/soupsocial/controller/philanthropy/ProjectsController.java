package org.dejava.service.soupsocial.controller.philanthropy;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.dejava.service.philanthropy.component.project.ProjectComponent;
import org.dejava.service.philanthropy.model.project.ProjectIdea;
import org.dejava.service.philanthropy.model.project.ProjectPlan;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * The controller for getting project ideas.
 */
@ConversationScoped
@SoupSocialCtx
@Named("projectsController")
public class ProjectsController implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -1868433535769298361L;

	/**
	 * The idea EJB service.
	 */
	@Inject
	@PhilanthropyCtx
	private ProjectComponent projectComponent;

	/**
	 * Ideas for the given filter.
	 */
	private Collection<ProjectIdea> projectIdeas;

	/**
	 * Gets the project ideas for the given filter.
	 * 
	 * @return The project ideas for the given filter.
	 */
	public Collection<ProjectIdea> getProjectIdeas() {
		// If there are no projectIdeas.
		if (projectIdeas == null) {
			// Gets the project ideas for the filter.
			projectIdeas = projectComponent.getAllProjectIdeas(null, null);
		}
		// Returns the projectIdeas.
		return projectIdeas;
	}

	/**
	 * Sets the project ideas for the given filter.
	 * 
	 * @param projectIdeas
	 *            New project ideas for the given filter.
	 */
	public void setProjectIdeas(final Collection<ProjectIdea> projectIdeas) {
		this.projectIdeas = projectIdeas;
	}

	/**
	 * Ideas for the given filter.
	 */
	private Collection<ProjectPlan> projectPlans;

	/**
	 * Gets the project ideas for the given filter.
	 * 
	 * @return The project ideas for the given filter.
	 */
	public Collection<ProjectPlan> getProjectPlans() {
		// If there are no projectPlans.
		if (projectPlans == null) {
			// Gets the project ideas for the filter.
			projectPlans = projectComponent.getAllProjectPlans(null, null);
		}
		// Returns the projectPlans.
		return projectPlans;
	}

	/**
	 * Sets the project ideas for the given filter.
	 * 
	 * @param projectPlans
	 *            New project ideas for the given filter.
	 */
	public void setProjectPlans(final Collection<ProjectPlan> projectPlans) {
		this.projectPlans = projectPlans;
	}

}
