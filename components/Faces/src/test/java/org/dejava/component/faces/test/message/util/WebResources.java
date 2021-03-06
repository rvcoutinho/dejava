package org.dejava.component.faces.test.message.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.dejava.component.faces.message.FacesMessageHandler;
import org.dejava.component.i18n.message.handler.ApplicationMessageHandler;
import org.dejava.component.i18n.message.handler.MessageHandler;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageHandler;

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

	/**
	 * The locale controller.
	 */
	@Inject
	@Faces
	private LocaleController localeController;

	/**
	 * Gets the default implementation for the message handler.
	 * 
	 * @return The default implementation for the message handler.
	 */
	private MessageHandler getMessageHandler() {
		return SimpleMessageHandler.getMessageHandler(localeController.getLocale());
	}

	/**
	 * Gets the default implementation for the application message handler.
	 * 
	 * @return The default implementation for the application message handler.
	 */
	@Faces
	@Produces
	@RequestScoped
	public ApplicationMessageHandler getAppMessageHandler() {
		return new FacesMessageHandler(getMessageHandler(), getFacesContext());
	}

}
