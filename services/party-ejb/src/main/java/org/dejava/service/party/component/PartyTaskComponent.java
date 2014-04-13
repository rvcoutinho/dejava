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
import org.dejava.service.contact.component.ContactComponent;
import org.dejava.service.contact.model.Contact;
import org.dejava.service.contact.util.ContactCtx;
import org.dejava.service.message.component.MessageComponent;
import org.dejava.service.message.model.AppNotification;
import org.dejava.service.message.util.MessageCtx;
import org.dejava.service.party.constant.Permissions;
import org.dejava.service.party.model.Party;
import org.dejava.service.party.model.Person;
import org.dejava.service.party.util.PartyCtx;
import org.dejava.service.photo.component.PhotoComponent;
import org.dejava.service.photo.constant.PhotoAlbums;
import org.dejava.service.photo.model.Album;
import org.dejava.service.photo.model.Photo;
import org.dejava.service.photo.util.PhotoCtx;
import org.dejava.service.place.component.PlaceComponent;
import org.dejava.service.place.util.PlaceCtx;

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
	 * Photo EJB component.
	 */
	@Inject
	@PhotoCtx
	private PhotoComponent photoComponent;

	/**
	 * Contact EJB component.
	 */
	@Inject
	@ContactCtx
	private ContactComponent contactComponent;

	/**
	 * Place EJB component.
	 */
	@Inject
	@PlaceCtx
	private PlaceComponent placeComponent;

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
	 * Creates a party with its contacts and addresses.
	 * 
	 * @param party
	 *            The party to be created.
	 * @return Returns the created party.
	 */
	public Party createPartyWithContactsAndAddresses(final Party party) {
		// Creates the party addresses.
		party.setAddresses(placeComponent.createPlaces(party.getAddresses()));
		// Gets the party contacts.
		final Collection<Contact> contacts = party.getContacts();
		// Adds the new party.
		final Party newParty = partyComponent.createParty(party);
		// For each party contact.
		for (final Contact currentContact : contacts) {
			// Updates the current contact with the party identifier.
			currentContact.setParty(newParty.getIdentifier());
		}
		// Creates the party contacts.
		newParty.setContacts(contactComponent.createContacts(contacts));
		// Returns the created party.
		return newParty;
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
		// Creates a the person with the facebook information.
		Person person = new Person(facebookUser);
		// Sets the person user.
		person.setUser(user);
		// Creates the person, its addresses and contacts.
		person = (Person) createPartyWithContactsAndAddresses(person);
		// Creates a new photo for the user avatar.
		photoComponent.createPhoto(new Photo(photoComponent.getFacebookUserAvatar(), new Album(
				PhotoAlbums.AVATAR_ALBUM, person.getIdentifier(), null)));
		// Notifies the user with a welcome message.
		messageComponent.sendAppNotification(new AppNotification(person.getIdentifier(),
				new SimpleMessageCommand(MessageTypes.URL.class, null, URLKeys.NEW_USER, null),
				new SimpleMessageCommand(MessageTypes.URL.class, null, URLKeys.NEW_USER_IMAGE, null),
				new SimpleMessageCommand(MessageTypes.Info.class, null, InfoKeys.NEW_USER,
						new Object[] { person.toString() })));

	}
}
