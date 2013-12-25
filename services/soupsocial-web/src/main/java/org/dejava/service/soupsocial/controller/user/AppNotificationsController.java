package org.dejava.service.soupsocial.controller.user;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.dejava.component.faces.message.FacesMessageHandler;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageHandler;
import org.dejava.service.message.component.AppNotificationComponent;
import org.dejava.service.message.model.AppNotification;
import org.dejava.service.message.util.MessageCtx;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * Application notifications controller.
 */
@RequestScoped
@SoupSocialCtx
@Named("appNotificationsController")
public class AppNotificationsController {

	/**
	 * The user controller.
	 */
	@Inject
	@SoupSocialCtx
	private UserController userController;

	/**
	 * The application notification component.
	 */
	@Inject
	@MessageCtx
	private AppNotificationComponent appNotificationComponent;

	/**
	 * Gets the current user notifications.
	 * 
	 * @return The current user notifications.
	 */
	public Collection<AppNotification> getNotifications() {
		// If the user is not logged in.
		if (userController.getParty() == null) {
			return null;
		}
		// If the user is logged in.
		else {
			// Returns the current user notifications.
			return appNotificationComponent.getByAttribute("recipient", userController.getParty()
					.getIdentifier(), null, null);
		}
	}

	/**
	 * TODO
	 */
	@Inject
	@SoupSocialCtx
	private FacesContext facesContext;

	/**
	 * Faces message handler.
	 */
	private FacesMessageHandler facesMessageHandler;

	/**
	 * Gets the facesMessageHandler.
	 * 
	 * @return The facesMessageHandler.
	 */
	public FacesMessageHandler getFacesMessageHandler() {
		return new FacesMessageHandler(SimpleMessageHandler.getDefaultMessageHandler(), facesContext);
	}

}
