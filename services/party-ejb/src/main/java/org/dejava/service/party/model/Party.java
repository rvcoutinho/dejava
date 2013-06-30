package org.dejava.service.party.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.ejb.entity.ExternalEntity;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.contact.model.Contact;
import org.dejava.service.location.model.Location;

/**
 * 
 * Represents a abstract party.
 */
@Entity
@Table(name = "party")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Party extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -6745781199872773566L;

	/**
	 * Name of the party.
	 */
	private String name;

	/**
	 * Gets the name of the party.
	 * 
	 * @return The name of the party.
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the party.
	 * 
	 * @param name
	 *            New name of the party.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Name to be used and displayed regularly.
	 */
	private String displayName;

	/**
	 * Gets the name to be used and displayed regularly.
	 * 
	 * @return The name to be used and displayed regularly.
	 */
	@Column(name = "display_name")
	public String getDisplayName() {
		// If the display name is null.
		if (displayName == null) {
			// The display name is the party name.
			displayName = getName();
		}
		// Returns the display name.
		return displayName;
	}

	/**
	 * Sets the name to be used and displayed regularly.
	 * 
	 * @param displayName
	 *            New name to be used and displayed regularly.
	 */
	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	/**
	 * The identifier of the user.
	 */
	private Integer userId;

	/**
	 * Gets the user identifier.
	 * 
	 * @return The user identifier.
	 */
	@Column(name = "u5er")
	protected Integer getUserId() {
		return userId;
	}

	/**
	 * Sets the user identifier.
	 * 
	 * @param userId
	 *            New user identifier.
	 */
	protected void setUserId(final Integer userId) {
		this.userId = userId;
	}

	/**
	 * Sets the user.
	 * 
	 * @param user
	 *            New user.
	 */
	public final void setUser(final User user) {
		// If the user is given.
		if (user != null) {
			// Sets the user id for the party.
			setUserId(user.getIdentifier());
		}
	}

	/**
	 * Addresses for the party.
	 */
	@ExternalEntity(retrieveObj = "java:/global/ear/location-ejb/Component/Location/Location", mappedBy = "partyId", singleEntity = false)
	private Collection<Location> addresses;

	/**
	 * Gets the addresses.
	 * 
	 * @return The addresses.
	 */
	@Transient
	public Collection<Location> getAddresses() {
		// If the address set is null.
		if (addresses == null) {
			// Creates a new set.
			addresses = new ArrayList<>();
		}
		// Returns the addresses.
		return addresses;
	}

	/**
	 * Sets the addresses.
	 * 
	 * @param addresses
	 *            New addresses.
	 */
	public void setAddresses(final Collection<Location> addresses) {
		this.addresses = addresses;
	}

	/**
	 * Contacts for the party.
	 */
	@ExternalEntity(retrieveObj = "java:/global/ear/contact-ejb/Component/Contact/Contact", mappedBy = "partyId", singleEntity = false)
	private Collection<Contact> contacts;

	/**
	 * Gets the contacts.
	 * 
	 * @return The contacts.
	 */
	@Transient
	public Collection<Contact> getContacts() {
		// If the contact set is null.
		if (contacts == null) {
			// Creates a new set.
			contacts = new ArrayList<>();
		}
		// Returns the contacts.
		return contacts;
	}

	/**
	 * Gets the contacts for the given type.
	 * 
	 * @param contactType
	 *            Any contact type
	 * @param <ConcreteContact>
	 *            A concrete contact type.
	 * @return The contacts for the given type.
	 */
	@SuppressWarnings("unchecked")
	@Transient
	public <ConcreteContact extends Contact> Collection<ConcreteContact> getContacts(
			final Class<ConcreteContact> contactType) {
		// Creates a new set.
		final Collection<ConcreteContact> contacts = new ArrayList<>();
		// For each contact.
		for (final Contact currentContact : getContacts()) {
			// If the current contact is an instance of the given contact type.
			if (contactType.isAssignableFrom(currentContact.getClass())) {
				// Adds the current contact to the list.
				contacts.add((ConcreteContact) currentContact);
			}
		}
		// Returns the contacts for the types.
		return contacts;
	}

	/**
	 * Sets the contacts.
	 * 
	 * @param contacts
	 *            New contacts.
	 */
	public void setContacts(final Collection<Contact> contacts) {
		this.contacts = contacts;
	}

	/**
	 * Default constructor.
	 */
	public Party() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            Name of the party.
	 * @param user
	 *            User for the party.
	 */
	public Party(final String name, final User user) {
		super();
		// Sets the basic info for the party.
		this.name = name;
		setUser(user);
	}

}
