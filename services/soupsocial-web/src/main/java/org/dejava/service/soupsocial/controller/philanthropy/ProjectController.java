package org.dejava.service.soupsocial.controller.philanthropy;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.dejava.service.philanthropy.component.project.ProjectComponent;
import org.dejava.service.philanthropy.component.share.ProjectShareComponent;
import org.dejava.service.philanthropy.model.party.Supporter;
import org.dejava.service.philanthropy.model.project.PlannedProject;
import org.dejava.service.philanthropy.model.project.Project;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;
import org.dejava.service.soupsocial.controller.user.UserController;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * The controller for getting a project.
 */
@ConversationScoped
@SoupSocialCtx
@Named("projectController")
public class ProjectController implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4883088694766827281L;

	/**
	 * Faces context.
	 */
	@Inject
	@SoupSocialCtx
	protected FacesContext context;

	/**
	 * Project EJB component.
	 */
	@Inject
	@PhilanthropyCtx
	private ProjectComponent projectComponent;

	/**
	 * Project id.
	 */
	private Integer projectId;

	/**
	 * Gets the project id.
	 * 
	 * @return The project id.
	 */
	public Integer getProjectId() {
		// If the project id is null.
		if (projectId == null) {
			// The project id is retrieved from the request parameters.
			projectId = Integer.parseInt(context.getExternalContext().getRequestParameterMap()
					.get("projectId"));
		}
		// Returns the project id.
		return projectId;
	}

	/**
	 * Sets the project id.
	 * 
	 * @param projectId
	 *            New project id.
	 */
	public void setProjectId(final Integer projectId) {
		this.projectId = projectId;
	}

	/**
	 * The project.
	 */
	private Project project;

	/**
	 * Gets the project.
	 * 
	 * @return The project.
	 */
	public Project getProject() {
		// If the project is null.
		if (project == null) {
			// Gets the project by its id.
			project = projectComponent.getById(getProjectId());
		}
		// Returns the project.
		return project;
	}

	/**
	 * The project share EJB component.
	 */
	@Inject
	@PhilanthropyCtx
	private ProjectShareComponent projectShareComponent;

	/**
	 * The subject.
	 */
	@Inject
	@SoupSocialCtx
	private UserController userController;

	/**
	 * Supports the project.
	 */
	public void supportProject() {
		// Shares the project.
		final Long shares = projectShareComponent.share(getProject(),
				(Supporter) userController.getPhilanthropyParty());
		// Updates the project shares.
		getProject().setShares(shares);
	}

	/**
	 * Gets the active tab for the project.
	 * 
	 * @return The active tab for the project.
	 */
	public String getActiveTab() {
		// If the project is a planned project.
		if (getProject() instanceof PlannedProject) {
			// Returns "1" (the second tab).
			return "1";
		}
		// Otherwise (project idea).
		else {
			// Returns "0" (the first tab).
			return "0";
		}
	}

	/**
	 * Gets the disabled tabs for the project.
	 * 
	 * @return The disabled tabs for the project.
	 */
	public String getDisabledTabs() {
		// If the project is a planned project.
		if (getProject() instanceof PlannedProject) {
			// If there is a original idea.
			if (((PlannedProject) getProject()).getOriginalIdea() != null) {
				// Returns "2" (the third tab).
				return "2";
			}
			// If there is no original idea.
			else {
				// Returns "0, 2" (the first and third tabs).
				return "0, 2";
			}
		}
		// Otherwise (project idea).
		else {
			// Returns "1, 2" (the second and third tabs).
			return "1, 2";
		}
	}
}
