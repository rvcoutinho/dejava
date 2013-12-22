package org.dejava.service.accesscontrol.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.component.i18n.message.handler.impl.SimpleMessageCommand;
import org.dejava.service.accesscontrol.constant.InfoKeys;
import org.dejava.service.accesscontrol.constant.URLKeys;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.credentials.Credentials;
import org.dejava.service.accesscontrol.util.AccessControlCtx;
import org.dejava.service.accesscontrol.util.MessageTypes;
import org.dejava.service.message.component.AppNotificationComponent;
import org.dejava.service.message.model.AppNotification;
import org.dejava.service.message.util.MessageCtx;
import org.dejava.service.party.component.PartyComponent;
import org.dejava.service.party.util.PartyCtx;

/**
 * DAO for user.
 */
@AccessControlCtx
public class UserDAO extends AbstractGenericDAO<User, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@AccessControlCtx
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Application notification component.
	 */
	@Inject
	@MessageCtx
	private AppNotificationComponent appNotificationComponent;

	/**
	 * Party component.
	 */
	@Inject
	@PartyCtx
	private PartyComponent partyComponent;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#merge(java.lang.Object)
	 */
	@Override
	public User merge(final User entity) {
		// If the entity has a party.
		if (entity.getParty() != null) {
			// Adds or updates the party for the user.
			partyComponent.addOrUpdate(entity.getParty());
		}
		// If the user has no id.
		if (entity.getIdentifier() == null) {
			// Gets the credentials for the user.
			final Collection<Credentials> credentials = entity.getCredentials();
			// Remove the principals from the user (credentials might use the user id for hash calculations).
			entity.setCredentials(null);
			// Merges the user.
			User mergedUser = super.merge(entity);
			// Re-adds the credentials to the merged user.
			mergedUser.setCredentials(new ArrayList<>(credentials), true);
			// Re-merges the (merged) user again (with its credentials),
			mergedUser = super.merge(mergedUser);
			// Re-sets the credentials for the given user (to prevent problems with old references).
			entity.setCredentials(credentials, true);
			// Notifies the user with a welcome message. FIXME Locale.
			appNotificationComponent.sendMessage(new AppNotification(mergedUser.getParty(),
					new SimpleMessageCommand(MessageTypes.URL.class, null, URLKeys.NEW_USER, null),
					new SimpleMessageCommand(MessageTypes.URL.class, null, URLKeys.NEW_USER_IMAGE, null),
					new SimpleMessageCommand(MessageTypes.Info.class, null, InfoKeys.NEW_USER,
							new Object[] { mergedUser.getParty().getDisplayName() })));
			// Returns the merged user.
			return mergedUser;
		}
		// If it has an id.
		else {
			// Merges the user normally.
			return super.merge(entity);
		}
	}

}
