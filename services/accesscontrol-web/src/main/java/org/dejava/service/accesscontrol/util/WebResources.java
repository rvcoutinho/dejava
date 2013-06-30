package org.dejava.service.accesscontrol.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * Web resources to be injected CDI.
 */
@AccessControlCtx
public class WebResources {

	/**
	 * Produces the faces context.
	 * 
	 * @return The faces context.
	 */
	@Produces
	@RequestScoped
	@AccessControlCtx
	public FacesContext produceFacesContext() {
		return FacesContext.getCurrentInstance();
	}

}
