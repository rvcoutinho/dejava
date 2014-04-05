package org.dejava.service.soupsocial.controller.philanthropy;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.dejava.service.philanthropy.component.ProjectComponent;
import org.dejava.service.philanthropy.model.Category;
import org.dejava.service.philanthropy.model.project.Project;
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
	private ProjectComponent projectComponent;

	/**
	 * The category for the projects.
	 */
	private Category category;

	/**
	 * Gets the category for the projects.
	 * 
	 * @return The category for the projects.
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Sets the category for the projects.
	 * 
	 * @param category
	 *            New category for the projects.
	 */
	public void setCategory(final Category category) {
		this.category = category;
	}

	/**
	 * The tag for the projects.
	 */
	private String tag;

	/**
	 * Gets the tag for the projects.
	 * 
	 * @return The tag for the projects.
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Sets the tag for the projects.
	 * 
	 * @param tag
	 *            New tag for the projects.
	 */
	public void setTag(final String tag) {
		this.tag = tag;
	}

	/**
	 * Ongoing projects.
	 */
	private Collection<Project> projects;

	/**
	 * Gets the projects.
	 * 
	 * @return The projects.
	 */
	public Collection<Project> getProjects() {
		// If there are no projects.
		if (projects == null) {
			// Gets the projects.
			projects = projectComponent.getProjectsByCategoryOrTag(getCategory(), getTag(), null, null);
		}
		// Returns the projects.
		return projects;
	}

	/**
	 * Ideas.
	 */
	private Collection<Project> ideas;

	/**
	 * Gets the project ideas.
	 * 
	 * @return The project ideas.
	 */
	public Collection<Project> getIdeas() {
		// If there are no ideas.
		if (ideas == null) {
			// Gets the ideas.
			ideas = projectComponent.getIdeas(null, null);
		}
		// Returns the ideas.
		return ideas;
	}

	/**
	 * Planned projects.
	 */
	private Collection<Project> plannedProjects;

	/**
	 * Gets the planned projects.
	 * 
	 * @return The planned projects.
	 */
	public Collection<Project> getPlannedProjects() {
		// If there are no plannedProjects.
		if (plannedProjects == null) {
			// Gets the planned projects.
			plannedProjects = projectComponent.getPlannedProjects(null, null);
		}
		// Returns the plannedProjects.
		return plannedProjects;
	}

	/**
	 * Ongoing projects.
	 */
	private Collection<Project> ongoingProjects;

	/**
	 * Gets the ongoing projects.
	 * 
	 * @return The ongoing projects.
	 */
	public Collection<Project> getOngoingProjects() {
		// If there are no ongoingProjects.
		if (ongoingProjects == null) {
			// Gets the ongoing projects.
			ongoingProjects = projectComponent.getOngoingProjects(null, null);
		}
		// Returns the ongoingProjects.
		return ongoingProjects;
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
	public Collection<Project> getProjectsCreatedBySupporter() {
		return projectComponent.getProjectsCreatedBySupporter(userController.getPhilanthropyParty()
				.getIdentifier(), null, null);
	}

	/**
	 * Counts the number of ideas for a supporter.
	 * 
	 * @return The number of ideas for a supporter.
	 */
	public Long countProjectsCreatedBySupporter() {
		return projectComponent.countProjectsCreatedBySupporter(userController.getPhilanthropyParty()
				.getIdentifier());
	}

	/**
	 * Gets the projects shared by a supporter.
	 * 
	 * @return The projects shared by a supporter.
	 */
	public Collection<Project> getProjectsSharedBySupporter() {
		return projectComponent.getProjectsSharedBySupporter(userController.getPhilanthropyParty()
				.getIdentifier(), null, null);
	}

	/**
	 * Counts the number of shares for a supporter.
	 * 
	 * @return The number of shares for a supporter.
	 */
	public Long countSharesBySupporter() {
		return projectComponent.countProjectSharesBySupporter(userController.getPhilanthropyParty()
				.getIdentifier());
	}

}
