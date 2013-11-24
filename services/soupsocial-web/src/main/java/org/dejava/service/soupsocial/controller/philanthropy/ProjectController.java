package org.dejava.service.soupsocial.controller.philanthropy;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.dejava.service.philanthropy.component.project.ProjectComponent;
import org.dejava.service.philanthropy.component.share.ProjectShareComponent;
import org.dejava.service.philanthropy.model.party.Supporter;
import org.dejava.service.philanthropy.model.project.AbstractProject;
import org.dejava.service.philanthropy.model.project.ProjectPlan;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;
import org.dejava.service.soupsocial.controller.user.UserController;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * The controller for getting a abstractProject.
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
	 * AbstractProject EJB component.
	 */
	@Inject
	@PhilanthropyCtx
	private ProjectComponent projectComponent;

	/**
	 * AbstractProject id.
	 */
	private Integer projectId;

	/**
	 * Gets the abstractProject id.
	 * 
	 * @return The abstractProject id.
	 */
	public Integer getProjectId() {
		// If the abstractProject id is null.
		if (projectId == null) {
			// The abstractProject id is retrieved from the request parameters.
			projectId = Integer.parseInt(context.getExternalContext().getRequestParameterMap()
					.get("projectId"));
		}
		// Returns the abstractProject id.
		return projectId;
	}

	/**
	 * Sets the abstractProject id.
	 * 
	 * @param projectId
	 *            New abstractProject id.
	 */
	public void setProjectId(final Integer projectId) {
		this.projectId = projectId;
	}

	/**
	 * The abstractProject.
	 */
	private AbstractProject abstractProject;

	/**
	 * Gets the abstractProject.
	 * 
	 * @return The abstractProject.
	 */
	public AbstractProject getProject() {
		// If the abstractProject is null.
		if (abstractProject == null) {
			// Gets the abstractProject by its id.
			abstractProject = projectComponent.getById(getProjectId());
		}
		// Returns the abstractProject.
		return abstractProject;
	}

	/**
	 * The abstractProject share EJB component.
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
	 * Supports the abstractProject.
	 */
	public void supportProject() {
		// Shares the abstractProject.
		final Long shares = projectShareComponent.share(getProject(),
				(Supporter) userController.getPhilanthropyParty());
		// Updates the abstractProject shares.
		getProject().setShares(shares);
	}

	/**
	 * Gets the active tab for the abstractProject.
	 * 
	 * @return The active tab for the abstractProject.
	 */
	public String getActiveTab() {
		// If the abstractProject is a planned abstractProject.
		if (getProject() instanceof ProjectPlan) {
			// Returns "1" (the second tab).
			return "1";
		}
		// Otherwise (abstractProject idea).
		else {
			// Returns "0" (the first tab).
			return "0";
		}
	}

	/**
	 * Gets the disabled tabs for the abstractProject.
	 * 
	 * @return The disabled tabs for the abstractProject.
	 */
	public String getDisabledTabs() {
		// If the abstractProject is a planned abstractProject.
		if (getProject() instanceof ProjectPlan) {
			// If there is a original idea.
			if (((ProjectPlan) getProject()).getOriginalIdea() != null) {
				// Returns "2" (the third tab).
				return "2";
			}
			// If there is no original idea.
			else {
				// Returns "0, 2" (the first and third tabs).
				return "0, 2";
			}
		}
		// Otherwise (abstractProject idea).
		else {
			// Returns "1, 2" (the second and third tabs).
			return "1, 2";
		}
	}
}
