package org.dejava.service.soupsocial.controller.locale;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.dejava.component.faces.i18n.AbstractLocaleController;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * Session locale controller.
 */
@SessionScoped
@Named(value = "localeController")
@SoupSocialCtx
public class LocaleController extends AbstractLocaleController {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -2054152782016693113L;

	/**
	 * Faces context.
	 */
	@Inject
	@SoupSocialCtx
	private FacesContext context;

	/**
	 * @see org.dejava.component.faces.i18n.AbstractLocaleController#getFacesContext()
	 */
	@Override
	protected FacesContext getFacesContext() {
		return context;
	}

}