package org.dejava.service.soupsocial.controller.philanthropy;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.dejava.service.philanthropy.component.project.PhilanthropyProjectComponent;
import org.dejava.service.philanthropy.component.share.ProjectShareComponent;
import org.dejava.service.philanthropy.model.project.PhilanthropyProject;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;
import org.dejava.service.soupsocial.controller.user.UserController;
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
	private PhilanthropyProjectComponent philanthropyProjectComponent;

	/**
	 * Ideas for the given filter.
	 */
	private Collection<PhilanthropyProject> projectIdeas;

	/**
	 * Gets the project ideas for the given filter.
	 * 
	 * @return The project ideas for the given filter.
	 */
	public Collection<PhilanthropyProject> getProjectIdeas() {
		// If there are no projectIdeas.
		if (projectIdeas == null) {
			// Gets the project ideas for the filter.
			projectIdeas = philanthropyProjectComponent.getAllProjectIdeas(null, null);
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
	public void setProjectIdeas(final Collection<PhilanthropyProject> projectIdeas) {
		this.projectIdeas = projectIdeas;
	}

	/**
	 * Ideas for the given filter.
	 */
	private Collection<PhilanthropyProject> projectPlans;

	/**
	 * Gets the project ideas for the given filter.
	 * 
	 * @return The project ideas for the given filter.
	 */
	public Collection<PhilanthropyProject> getProjectPlans() {
		// If there are no projectPlans.
		if (projectPlans == null) {
			// Gets the project ideas for the filter.
			projectPlans = philanthropyProjectComponent.getAllProjectPlans(null, null);
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
	public void setProjectPlans(final Collection<PhilanthropyProject> projectPlans) {
		this.projectPlans = projectPlans;
	}

	/**
	 * The user controller.
	 */
	@Inject
	@SoupSocialCtx
	private UserController userController;

	/**
	 * Gets the projects created by a supporter.
	 * 
	 * @return The projects created by a supporter.
	 */
	public Collection<PhilanthropyProject> getProjectsCreatedBySupporter() {
		return philanthropyProjectComponent.getAllCreatedBySupporter(userController.getPhilanthropyParty()
				.getIdentifier(), null, null);
	}

	/**
	 * Counts the number of ideas for a supporter.
	 * 
	 * @return The number of ideas for a supporter.
	 */
	public Long countIdeasBySupporter() {
		return philanthropyProjectComponent.countIdeasBySupporter(userController.getPhilanthropyParty()
				.getIdentifier());
	}

	/**
	 * The project share component.
	 */
	@Inject
	@PhilanthropyCtx
	private ProjectShareComponent projectShareComponent;

	/**
	 * Gets the projects shared by a supporter.
	 * 
	 * @return The projects shared by a supporter.
	 */
	public Collection<PhilanthropyProject> getProjectsSharedBySupporter() {
		return philanthropyProjectComponent.getAllSharedBySupporter(userController.getPhilanthropyParty()
				.getIdentifier(), null, null);
	}

	/**
	 * Counts the number of shares for a supporter.
	 * 
	 * @return The number of shares for a supporter.
	 */
	public Long countSharesBySupporter() {
		return projectShareComponent.countSharesBySupporter(userController.getPhilanthropyParty()
				.getIdentifier());
	}

}
