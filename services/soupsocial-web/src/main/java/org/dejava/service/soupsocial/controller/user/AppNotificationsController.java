package org.dejava.service.soupsocial.controller.user;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.dejava.component.faces.message.FacesMessageHandler;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageHandler;
import org.dejava.service.accesscontrol.interceptor.Secured;
import org.dejava.service.accesscontrol.interceptor.SuppressSecurityExceptions;
import org.dejava.service.message.component.AppNotificationComponent;
import org.dejava.service.message.model.AppNotification;
import org.dejava.service.message.util.MessageCtx;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * Application notifications controller.
 */
@SoupSocialCtx
@RequestScoped
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
	@Secured
	@RequiresAuthentication
	@SuppressSecurityExceptions
	public Collection<AppNotification> getNotifications() {
		return appNotificationComponent.getByRecipient(userController.getParty().getIdentifier(), null, null);
	}

	/**
	 * Counts the unread notifications for the current user.
	 * 
	 * @return The unread notifications for the current user.
	 */
	@Secured
	@RequiresAuthentication
	@SuppressSecurityExceptions
	public Long countUnread() {
		// Returns the current user unread notifications count.
		return appNotificationComponent.countUnreadByParty(userController.getParty().getIdentifier());
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
