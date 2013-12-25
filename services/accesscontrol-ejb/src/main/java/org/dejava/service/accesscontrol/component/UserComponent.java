package org.dejava.service.accesscontrol.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageCommand;
import org.dejava.service.accesscontrol.constant.InfoKeys;
import org.dejava.service.accesscontrol.constant.URLKeys;
import org.dejava.service.accesscontrol.dao.UserDAO;
import org.dejava.service.accesscontrol.dao.principal.EmailDAO;
import org.dejava.service.accesscontrol.dao.principal.FacebookDAO;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.principal.Principal;
import org.dejava.service.accesscontrol.util.AccessControlCtx;
import org.dejava.service.accesscontrol.util.MessageTypes;
import org.dejava.service.message.component.AppNotificationComponent;
import org.dejava.service.message.model.AppNotification;
import org.dejava.service.message.util.MessageCtx;

/**
 * EJB service for user.
 */
@AccessControlCtx
@Stateless(name = "Component/AccessControl/User")
public class UserComponent extends AbstractGenericComponent<User, Integer> {

	/**
	 * The user DAO.
	 */
	@Inject
	@AccessControlCtx
	private UserDAO userDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<User, Integer> getEntityDAO() {
		return userDAO;
	}

	/**
	 * Application notification component.
	 */
	@Inject
	@MessageCtx
	private AppNotificationComponent appNotificationComponent;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#addOrUpdate(java.lang.Object)
	 */
	@Override
	public User addOrUpdate(final User entity) {
		// Adds the user.
		final User user = super.addOrUpdate(entity);
		// If the user is a new one.
		if (entity.getIdentifier() == null) {
			// If there is a party for the user.
			if (user.getParty() != null) {
				// Notifies the user with a welcome message.
				appNotificationComponent.sendMessage(new AppNotification(user.getParty().getIdentifier(),
						new SimpleMessageCommand(MessageTypes.URL.class, null, URLKeys.NEW_USER, null),
						new SimpleMessageCommand(MessageTypes.URL.class, null, URLKeys.NEW_USER_IMAGE, null),
						new SimpleMessageCommand(MessageTypes.Info.class, null, InfoKeys.NEW_USER,
								new Object[] { user.toString() })));
			}
		}
		// Returns the new user.
		return user;
	}

	/**
	 * The facebook principal DAO.
	 */
	@Inject
	@AccessControlCtx
	private FacebookDAO facebookDAO;

	/**
	 * The email principal DAO.
	 */
	@Inject
	@AccessControlCtx
	private EmailDAO emailDAO;

	/**
	 * Gets the the user by the facebook user.
	 * 
	 * @param fbUser
	 *            The facebook user.
	 * @return The user by the facebook user.
	 */
	public User getByFacebookUser(final com.restfb.types.User fbUser) {
		// Tries to get the facebook principal by the facebook user id.
		Principal principal = facebookDAO.getByAttribute("facebookIdentifier", fbUser.getId());
		// If there is no user for the facebook id.
		if (principal == null) {
			// Tries to get the email principal by the facebook user email.
			principal = emailDAO.getByAttribute("email", fbUser.getEmail());
		}
		// If there is no user for the email either.
		if (principal == null) {
			// Returns null.
			return null;
		}
		// If there is a user for the given facebook user.
		else {
			// Returns the retrieved user.
			return principal.getUser();
		}
	}
}
