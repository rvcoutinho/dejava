package org.dejava.service.soupsocial.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.dejava.component.i18n.message.handler.ApplicationMessageHandler;
import org.dejava.service.soupsocial.controller.LocaleController;

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
	public FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * The locale controller.
	 */
	@Inject
	@SoupSocialCtx
	private LocaleController localeController;

	/**
	 * Gets the default implementation for the application message handler.
	 * 
	 * @return The default implementation for the application message handler.
	 */
	@Produces
	@RequestScoped
	@SoupSocialCtx
	public ApplicationMessageHandler getAppMessageHandler() {
		return localeController.getAppMessageHandler(getContext());
	}

}
