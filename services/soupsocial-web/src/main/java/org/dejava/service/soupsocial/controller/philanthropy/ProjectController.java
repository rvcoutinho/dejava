package org.dejava.service.soupsocial.controller.philanthropy;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.dejava.component.i18n.message.handler.ApplicationMessageHandler;
import org.dejava.service.accesscontrol.interceptor.Secured;
import org.dejava.service.philanthropy.component.ProjectComponent;
import org.dejava.service.philanthropy.model.party.NonProfitOrg;
import org.dejava.service.philanthropy.model.project.Project;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;
import org.dejava.service.soupsocial.constant.InfoKeys;
import org.dejava.service.soupsocial.controller.user.UserController;
import org.dejava.service.soupsocial.util.MessageTypes;
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
	 * The faces message handler.
	 */
	@Inject
	@SoupSocialCtx
	protected ApplicationMessageHandler messageHandler;

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
			project = projectComponent.getProject(getProjectId());
		}
		// Returns the project.
		return project;
	}

	/**
	 * The subject.
	 */
	@Inject
	@SoupSocialCtx
	private UserController userController;

	/**
	 * Supports the project.
	 */
	@Secured
	@RequiresAuthentication
	public void supportProject() {
		// Shares the project.
		final Long shares = projectComponent.shareProjectAndCount(getProject().getIdentifier(),
				userController.getPhilanthropyParty().getIdentifier());
		// Updates the project shares.
		getProject().setShares(shares);
		// Adds a success message to the context.
		messageHandler.addMessage(MessageTypes.Info.class, null, InfoKeys.CREATE_PROJECT, null);
	}

	/**
	 * Gets the active tab for the project.
	 * 
	 * @return The active tab for the project.
	 */
	public String getActiveTab() {
		// If the project has a plan.
		if (getProject().getPlan() != null) {
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
		// If the project has a plan.
		if (getProject().getPlan() != null) {
			// If there is a original idea.
			if (getProject().getIdea() != null) {
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

	/**
	 * Proposes a new plan for a project idea.
	 * 
	 * @param proposer
	 *            The proposer.
	 */
	public void proposePlan(final NonProfitOrg proposer) {
		// Adds a new project plan proposal.
		projectComponent.proposeProjectPlan(getProject().getIdentifier(), proposer.getIdentifier());
	}
}
