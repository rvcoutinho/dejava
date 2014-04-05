package org.dejava.service.philanthropy.model.party;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.ejb.entity.ExternalEntity;
import org.dejava.service.contact.interfac.Contactable;
import org.dejava.service.contact.model.Contact;
import org.dejava.service.place.interfac.Addressable;
import org.dejava.service.place.model.Place;

/**
 * Philanthropy party super class.
 */
@Entity
@Table(name = "party")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Party extends AbstractIdentifiedEntity implements Contactable, Addressable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -775706443440597246L;

	/**
	 * The (original) party id.
	 */
	private Integer partyId;

	/**
	 * Gets the (original) party id.
	 * 
	 * @return The (original) party id.
	 */
	@Column(name = "party")
	protected Integer getPartyId() {
		return partyId;
	}

	/**
	 * Sets the (original) party id.
	 * 
	 * @param partyId
	 *            New (original) party id.
	 */
	protected void setPartyId(final Integer partyId) {
		this.partyId = partyId;
	}

	/**
	 * The (original) party.
	 */
	@ExternalEntity(retrieveObj = "java:app/party-ejb/Component/Party/Party", retrieveMethod = "getPartyById")
	private org.dejava.service.party.model.Party party;

	/**
	 * Gets the (original) party.
	 * 
	 * @return The (original) party.
	 */
	@Valid
	@Transient
	public org.dejava.service.party.model.Party getParty() {
		return party;
	}

	/**
	 * Sets the (original) party.
	 * 
	 * @param party
	 *            New (original) party.
	 */
	public void setParty(final org.dejava.service.party.model.Party party) {
		// If the party is null.
		if (party == null) {
			// Sets the party id to null.
			setPartyId(null);
		}
		// If the party is not null.
		else {
			// Sets the new party id.
			setPartyId(party.getIdentifier());
		}
		// Updates the party.
		this.party = party;
	}

	/**
	 * @see org.dejava.service.place.interfac.Addressable#getAddresses()
	 */
	@Override
	@Transient
	public Collection<Place> getAddresses() {
		// If there is a party.
		if (getParty() != null) {
			// Returns the original party addresses.
			return getParty().getAddresses();
		}
		// If there is no party.
		else {
			// Returns null.
			return null;
		}
	}

	/**
	 * @see org.dejava.service.place.interfac.Addressable#setAddresses(java.util.Collection)
	 */
	@Override
	public void setAddresses(final Collection<Place> addresses) {
		// If there is a party.
		if (getParty() != null) {
			// Sets the original party addresses.
			getParty().setAddresses(addresses);
		}
	}

	/**
	 * @see org.dejava.service.contact.interfac.Contactable#getContacts()
	 */
	@Override
	@Transient
	public Collection<Contact> getContacts() {
		// If there is a party.
		if (getParty() != null) {
			// Returns the original party contacts.
			return getParty().getContacts();
		}
		// If there is no party.
		else {
			// Returns null.
			return null;
		}
	}

	/**
	 * @see org.dejava.service.contact.interfac.Contactable#getContacts(java.lang.Class)
	 */
	@Override
	@Transient
	public <ConcreteContact extends Contact> Collection<ConcreteContact> getContacts(
			final Class<ConcreteContact> contactType) {
		// If there is a party.
		if (getParty() != null) {
			// Returns the original party contacts.
			return getParty().getContacts(contactType);
		}
		// If there is no party.
		else {
			// Returns null.
			return null;
		}
	}

	/**
	 * @see org.dejava.service.contact.interfac.Contactable#setContacts(java.util.Collection)
	 */
	@Override
	public void setContacts(final Collection<Contact> contacts) {
		// If there is a party.
		if (getParty() != null) {
			// Sets the original party contacts.
			getParty().setContacts(contacts);
		}
	}

	/**
	 * Default constructor.
	 */
	protected Party() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param identifier
	 *            The party identifier.
	 */
	public Party(final Integer identifier) {
		super();
		// Sets the party identifier.
		setIdentifier(identifier);
	}

	/**
	 * Default constructor.
	 * 
	 * @param party
	 *            The original party.
	 */
	public Party(final org.dejava.service.party.model.Party party) {
		super();
		// Sets the party id.
		setParty(party);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getParty().toString();
	}

}
