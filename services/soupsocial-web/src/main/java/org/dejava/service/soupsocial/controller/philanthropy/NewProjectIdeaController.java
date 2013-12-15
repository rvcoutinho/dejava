package org.dejava.service.soupsocial.controller.philanthropy;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.dejava.service.accesscontrol.interceptor.Secured;
import org.dejava.service.philanthropy.component.project.PhilanthropyProjectComponent;
import org.dejava.service.philanthropy.model.party.Supporter;
import org.dejava.service.philanthropy.model.project.plan.ProjectIdea;
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
	private PhilanthropyProjectComponent philanthropyProjectComponent;

	/**
	 * The new project idea.
	 */
	private ProjectIdea newProjectIdea;

	/**
	 * Gets the new project idea.
	 * 
	 * @return New project idea.
	 */
	public ProjectIdea getNewProjectIdea() {
		// If the idea is null.
		if (newProjectIdea == null) {
			// Creates a new idea.
			newProjectIdea = new ProjectIdea();
		}
		// Returns the idea.
		return newProjectIdea;
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
	@Secured
	@RequiresAuthentication
	public void createProject() throws IOException {
		// Sets the idea for the new project.
		getNewProject().setIdea(getNewProjectIdea());
		// Updates the idea target area.
		getNewProjectIdea().setTargetArea(placeComponent.getByGoogleReference(getAddressReference()));
		// Sets the current user as the author of the new idea.
		getNewProjectIdea().getAuthors().add((Supporter) userController.getPhilanthropyParty());
		// Updates the idea goals.
		getNewProjectIdea().setGoals(getGoals());
		// Creates the project.
		philanthropyProjectComponent.addOrUpdate(getNewProject());
		// Adds a success message to the context. FIXME
		facesContext.addMessage(null, new FacesMessage("Mensagem de teste", "Detalhe da mensagem de teste"));
	}
}
