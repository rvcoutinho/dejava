package org.dejava.service.soupsocial.controller;

import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.dejava.component.faces.i18n.AbstractLocaleController;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * Locale controller.
 */
@SessionScoped
@SoupSocialCtx
@Named(value = "localeController")
public class LocaleController extends AbstractLocaleController {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -7357528560630317702L;

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

	/**
	 * The session locale attribute name.
	 */
	public static final String SESSION_LOCALE_ATTR_NAME = "locale";

	/**
	 * Gets the session info.
	 * 
	 * @return The session info.
	 */
	private Session getSessionInfo() {
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * Gets the session locale.
	 * 
	 * @return The session locale.
	 */
	public Locale getLocale() {
		// Gets the session locale.
		Locale sessionLocale = (Locale) getSessionInfo().getAttribute(SESSION_LOCALE_ATTR_NAME);
		// If there is no session locale yet.
		if (sessionLocale == null) {
			// Gets the view locale.
			sessionLocale = facesContext.getViewRoot().getLocale();
			// Sets the locale to the session.
			getSessionInfo().setAttribute(SESSION_LOCALE_ATTR_NAME, sessionLocale);
		}
		// Returns the session locale.
		return sessionLocale;
	}

}
