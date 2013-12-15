package org.dejava.service.soupsocial.controller.user;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
		// Returns the current user notifications.
		return appNotificationComponent.getByAttribute("recipientId", userController.getParty()
				.getIdentifier(), null, null);
	}

}
