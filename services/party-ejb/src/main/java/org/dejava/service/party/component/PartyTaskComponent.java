package org.dejava.service.party.component;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.i18n.message.handler.impl.SimpleMessageCommand;
import org.dejava.service.accesscontrol.component.UserAuthorizationComponent;
import org.dejava.service.accesscontrol.component.UserComponent;
import org.dejava.service.accesscontrol.constant.InfoKeys;
import org.dejava.service.accesscontrol.constant.URLKeys;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.permission.UserPermission;
import org.dejava.service.accesscontrol.util.AccessControlCtx;
import org.dejava.service.accesscontrol.util.MessageTypes;
import org.dejava.service.message.component.MessageComponent;
import org.dejava.service.message.model.AppNotification;
import org.dejava.service.message.util.MessageCtx;
import org.dejava.service.party.constant.Permissions;
import org.dejava.service.party.model.Person;
import org.dejava.service.party.util.PartyCtx;

/**
 * Party coreography EJB component.
 */
@PartyCtx
@Stateless(name = "Component/Party/PartyTask")
public class PartyTaskComponent {

	/**
	 * Party EJB component.
	 */
	@Inject
	@PartyCtx
	private PartyComponent partyComponent;

	/**
	 * User EJB component.
	 */
	@Inject
	@AccessControlCtx
	private UserComponent userComponent;

	/**
	 * User authorization EJB component.
	 */
	@Inject
	@AccessControlCtx
	private UserAuthorizationComponent userAuthorizationComponent;

	/**
	 * Application notification component.
	 */
	@Inject
	@MessageCtx
	private MessageComponent messageComponent;

	/**
	 * Adds the administrator permissions for a party.
	 * 
	 * @param partyId
	 *            The identifier of the party to give the permission.
	 * @param userId
	 *            The identifier of the user to give the permission for.
	 */
	public void addPartyAdminPermissions(final Integer partyId, final Integer userId) {
		// Creates a new party administrator permission (for the current party).
		final String partyAdminPerm = Permissions.PARTY_ADMIN.replace(Permissions.PARTY_ID_WC,
				partyId.toString());
		// Adds the new user permission.
		userAuthorizationComponent.addUserPermission(userId, partyAdminPerm);
	}

	/**
	 * Gets the parties (identifiers) administered by a given user.
	 * 
	 * @param userId
	 *            The user identifier.
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return The parties (identifiers) administered by a given user.
	 */
	public Collection<Integer> getPartiesAdministeredByUser(final Integer userId, final Integer firstResult,
			final Integer maxResults) {
		// Creates the party list to be returned.
		final Collection<Integer> parties = new ArrayList<>();
		// Gets the administrative permission name without the party wildcard.
		final String admintrativePerm = Permissions.PARTY_ADMIN.replace(Permissions.PARTY_ID_WC, "");
		// Gets the party administrative permissions for the user.
		final Collection<UserPermission> userPartyAdminPerms = userAuthorizationComponent
				.getUserPermissionsByUserAndName(userId, admintrativePerm + "%", firstResult, maxResults);
		// For each permission.
		for (final UserPermission currentPerm : userPartyAdminPerms) {
			// Gets the id for party regarding the current permission.
			final Integer currentPartyId = Integer.parseInt(currentPerm.getPermission().getName()
					.substring(admintrativePerm.length()));
			// Adds the current party id to the final list.
			parties.add(currentPartyId);
		}
		// Returns the parties administered by the given user.
		return parties;
	}

	/**
	 * Creates a person and a user using the facebook user information.
	 * 
	 * @param facebookUser
	 *            The facebook user information.
	 */
	public void createPersonAndUser(final com.restfb.types.User facebookUser) {
		// Adds the new user.
		final User user = userComponent.createUser(new User(facebookUser));
		// Creates the new person.
		Person person = new Person(facebookUser);
		// Sets the user for the person.
		person.setUser(user.getIdentifier());
		// Adds the new person.
		person = (Person) partyComponent.createParty(person);
		// Notifies the user with a welcome message.
		messageComponent.sendAppNotification(new AppNotification(person.getIdentifier(),
				new SimpleMessageCommand(MessageTypes.URL.class, null, URLKeys.NEW_USER, null),
				new SimpleMessageCommand(MessageTypes.URL.class, null, URLKeys.NEW_USER_IMAGE, null),
				new SimpleMessageCommand(MessageTypes.Info.class, null, InfoKeys.NEW_USER,
						new Object[] { person.toString() })));

	}
}
