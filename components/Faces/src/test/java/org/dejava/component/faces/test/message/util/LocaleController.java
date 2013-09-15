package org.dejava.component.faces.test.message.util;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.dejava.component.faces.i18n.AbstractLocaleController;

/**
 * Locale controller.
 */
@Faces
@SessionScoped
public class LocaleController extends AbstractLocaleController {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 7051902979943883438L;

	/**
	 * Faces context.
	 */
	@Inject
	@Faces
	private FacesContext facesContext;

	/**
	 * @see org.dejava.component.faces.i18n.AbstractLocaleController#getFacesContext()
	 */
	@Override
	protected FacesContext getFacesContext() {
		return facesContext;
	}

}
