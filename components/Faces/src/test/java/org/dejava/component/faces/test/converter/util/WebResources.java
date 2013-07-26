package org.dejava.component.faces.test.converter.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * Resources to be injected via CDI.
 */
@Faces
public class WebResources {

	/**
	 * Returns the faces context.
	 * 
	 * @return The faces context.
	 */
	@Faces
	@Produces
	@RequestScoped
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

}
