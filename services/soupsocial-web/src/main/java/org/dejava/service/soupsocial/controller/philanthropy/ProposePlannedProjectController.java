package org.dejava.service.soupsocial.controller.philanthropy;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.dejava.service.accesscontrol.interceptor.Secured;
import org.dejava.service.philanthropy.component.ProjectComponent;
import org.dejava.service.philanthropy.model.project.plan.ProjectPlan;
import org.dejava.service.philanthropy.util.MessageTypes;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;
import org.dejava.service.place.component.PlaceComponent;
import org.dejava.service.place.util.PlaceCtx;
import org.dejava.service.soupsocial.constant.InfoKeys;
import org.dejava.service.soupsocial.controller.user.UserController;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * The controller for proposing project plans to existing ideas.
 */
@ConversationScoped
@SoupSocialCtx
@Named("proposeProjectPlanController")
public class ProposePlannedProjectController extends AbstractCreateProjectController implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 8863755203824546163L;

	/**
	 * The place EJB service.
	 */
	@Inject
	@PlaceCtx
	private PlaceComponent placeComponent;

	/**
	 * The idea EJB service.
	 */
	@Inject
	@PhilanthropyCtx
	private ProjectComponent projectComponent;

	/**
	 * The new project plan.
	 */
	private ProjectPlan newProjectPlan;

	/**
	 * Gets the new project plan.
	 * 
	 * @return The new project plan.
	 */
	public ProjectPlan getNewProjectPlan() {
		// If the idea is null.
		if (newProjectPlan == null) {
			// Creates a new plan.
			newProjectPlan = new ProjectPlan();
		}
		// Returns the plan.
		return newProjectPlan;
	}

	/**
	 * The subject.
	 */
	@Inject
	@SoupSocialCtx
	private UserController userController;

	/**
	 * Creates a new planned project.
	 * 
	 * @throws IOException
	 *             TODO
	 */
	@Secured
	@RequiresAuthentication
	public void createProject() throws IOException {
		// Sets the plan for the new project.
		getNewProject().setPlan(getNewProjectPlan());
		// Updates the plan target area.
		getNewProjectPlan().setTargetArea(placeComponent.getByGoogleReference(getAddressReference()));
		// Updates the plan goals.
		getNewProjectPlan().setGoals(getGoals());
		// Creates the project.
		projectComponent.createPlannedProject(getNewProject());
		// Adds a success message to the context.
		messageHandler.addMessage(MessageTypes.Model.class, null, InfoKeys.CREATE_PROJECT, null);
	}
}
