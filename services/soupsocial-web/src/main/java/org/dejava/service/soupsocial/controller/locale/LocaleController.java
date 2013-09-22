package org.dejava.service.soupsocial.controller.locale;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.dejava.component.faces.i18n.AbstractLocaleController;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * Locale controller.
 */
@SoupSocialCtx
@SessionScoped
@Named(value = "localeController")
public class LocaleController extends AbstractLocaleController {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 7051902979943883438L;

	/**
	 * Faces context.
	 */
	@Inject
	@SoupSocialCtx
	private FacesContext facesContext;

	/**
	 * @see org.dejava.component.faces.i18n.AbstractLocaleController#getFacesContext()
	 */
	@Override
	protected FacesContext getFacesContext() {
		return facesContext;
	}

}
