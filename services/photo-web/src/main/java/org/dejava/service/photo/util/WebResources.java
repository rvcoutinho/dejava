package org.dejava.service.photo.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * Web resources to be injected CDI.
 */
@PhotoCtx
public class WebResources {

	/**
	 * Produces the faces context.
	 * 
	 * @return The faces context.
	 */
	@Produces
	@RequestScoped
	@PhotoCtx
	public FacesContext produceFacesContext() {
		return FacesContext.getCurrentInstance();
	}

}
