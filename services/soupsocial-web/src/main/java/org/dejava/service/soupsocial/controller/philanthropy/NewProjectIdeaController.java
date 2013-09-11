package org.dejava.service.soupsocial.controller.philanthropy;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.dejava.service.philanthropy.component.project.ProjectComponent;
import org.dejava.service.philanthropy.model.party.Supporter;
import org.dejava.service.philanthropy.model.project.ProjectIdea;
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
@Named("newProjectIdeaController")
public class NewProjectIdeaController extends AbstractNewProjectController implements Serializable {

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
	private ProjectIdea newProject;

	/**
	 * @see org.dejava.service.soupsocial.controller.philanthropy.AbstractNewProjectController#getNewProject()
	 */
	public ProjectIdea getNewProject() {
		// If the idea is null.
		if (newProject == null) {
			// Creates a new idea.
			newProject = new ProjectIdea();
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
		// If there is a known user. FIXME
		if (userController.getUsername() != null) {
			// Sets the current user to the new idea.
			getNewProject().setAuthor((Supporter) userController.getPhilanthropyParty());
		}
		// Updates the goals of the project.
		updateGoals();
		// Creates the idea.
		projectComponent.addOrUpdate(getNewProject());
		// Adds a success message to the context. FIXME
		facesContext.addMessage(null, new FacesMessage("Mensagem de teste", "Detalhe da mensagem de teste"));
	}
}
