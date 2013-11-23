package org.dejava.service.soupsocial.controller.philanthropy;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.dejava.service.philanthropy.component.project.ProjectComponent;
import org.dejava.service.philanthropy.model.project.ProjectPlan;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;
import org.dejava.service.place.component.PlaceComponent;
import org.dejava.service.place.util.PlaceCtx;
import org.dejava.service.soupsocial.controller.user.UserController;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * The controller for creating new ideas.
 */
@ConversationScoped
@SoupSocialCtx
@Named("newProjectPlanController")
public class NewProjectPlanController extends AbstractNewProjectController implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 7684658950396621485L;

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
	 * A new idea.
	 */
	private ProjectPlan newProject;

	/**
	 * @see org.dejava.service.soupsocial.controller.philanthropy.AbstractNewProjectController#getNewProject()
	 */
	public ProjectPlan getNewProject() {
		// If the idea is null.
		if (newProject == null) {
			// Creates a new idea.
			newProject = new ProjectPlan();
		}
		// Returns the idea.
		return newProject;
	}

	/**
	 * The subject.
	 */
	@Inject
	@SoupSocialCtx
	private UserController userController;

	/**
	 * Faces context. FIXME
	 */
	@Inject
	@SoupSocialCtx
	private FacesContext facesContext;

	/**
	 * @see org.dejava.service.soupsocial.controller.philanthropy.AbstractNewProjectController#createProject()
	 */
	public void createProject() throws IOException {
		// Updates the idea target area.
		getNewProject().setTargetArea(placeComponent.getByGoogleReference(getAddressReference()));
		// Updates the goals of the project.
		updateGoals();
		// Creates the idea.
		projectComponent.addOrUpdate(getNewProject());
		// Adds a success message to the context. FIXME
		facesContext.addMessage(null, new FacesMessage("Mensagem de teste", "Detalhe da mensagem de teste"));
	}
}
