package org.dejava.component.faces.test.message.controller;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.dejava.component.faces.i18n.AbstractLocaleController;
import org.dejava.component.faces.test.message.util.Faces;

/**
 * The locale controller.
 */
@SessionScoped
@Named("localeController")
public class LocaleController extends AbstractLocaleController {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 3523918133963828704L;

	/**
	 * Faces context.
	 */
	@Faces
	@Inject
	protected FacesContext context;

	/**
	 * @see org.dejava.component.faces.i18n.AbstractLocaleController#getFacesContext()
	 */
	@Override
	protected FacesContext getFacesContext() {
		return context;
	}

}
