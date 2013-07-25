package org.dejava.service.philanthropy.controller;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.Path;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.faces.controller.AbstractGenericController;
import org.dejava.service.philanthropy.component.IdeaComponent;
import org.dejava.service.philanthropy.model.Idea;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Idea front controller.
 */
@Model
@Path("/idea")
public class IdeaController extends AbstractGenericController<Idea, Integer> {

	/**
	 * The idea EJB service.
	 */
	@Inject
	@PhilanthropyCtx
	private IdeaComponent ideaComponent;

	/**
	 * @see org.dejava.component.faces.controller.AbstractGenericController#getBusinessComponent()
	 */
	@Override
	protected AbstractGenericComponent<Idea, Integer> getBusinessComponent() {
		return ideaComponent;
	}
}
