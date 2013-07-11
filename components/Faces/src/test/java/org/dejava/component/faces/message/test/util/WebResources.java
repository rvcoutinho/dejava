package org.dejava.component.faces.message.test.util;

import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

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
	 * Gets the default implementation for the message handler.
	 * 
	 * @return The default implementation for the message handler.
	 */
	@Faces
	@Produces
	public MessageHandler getMessageHandler() {
		return SimpleMessageHandler.getMessageHandler(Locale.getDefault());
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
