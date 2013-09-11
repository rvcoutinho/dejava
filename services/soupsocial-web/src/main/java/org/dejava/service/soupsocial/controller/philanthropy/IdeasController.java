package org.dejava.service.soupsocial.controller.philanthropy;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.dejava.service.philanthropy.component.project.ProjectComponent;
import org.dejava.service.philanthropy.model.project.ProjectIdea;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * The controller for getting project ideas.
 */
@ConversationScoped
@SoupSocialCtx
@Named("ideasController")
public class IdeasController implements Serializable {

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
	public Collection<ProjectIdea> getIdeas() {
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
	public void setIdeas(final Collection<ProjectIdea> projectIdeas) {
		this.projectIdeas = projectIdeas;
	}

}
