package org.dejava.service.soupsocial.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * Web resources to be injected CDI.
 */
@SoupSocialCtx
public class WebResources {

	/**
	 * Produces the faces context.
	 * 
	 * @return The faces context.
	 */
	@Produces
	@RequestScoped
	@SoupSocialCtx
	public FacesContext produceFacesContext() {
		return FacesContext.getCurrentInstance();
	}

}
