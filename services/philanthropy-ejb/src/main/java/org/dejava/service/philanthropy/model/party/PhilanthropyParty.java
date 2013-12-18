package org.dejava.service.philanthropy.model.party;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.ejb.entity.ExternalEntity;
import org.dejava.service.party.model.Party;

/**
 * Philanthropy party super class.
 */
@Entity
@Table(name = "philanthropy_party")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PhilanthropyParty extends AbstractIdentifiedEntity {

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
	@ExternalEntity(retrieveObj = "java:app/party-ejb/Component/Party/Party")
	private Party party;

	/**
	 * Gets the (original) party.
	 * 
	 * @return The (original) party.
	 */
	@Valid
	@Transient
	public Party getParty() {
		return party;
	}

	/**
	 * Sets the (original) party.
	 * 
	 * @param party
	 *            New (original) party.
	 */
	public void setParty(final Party party) {
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
	 * Default constructor.
	 */
	public PhilanthropyParty() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param party
	 *            The original party.
	 */
	public PhilanthropyParty(Party party) {
		super();
		// Sets the party id.
		setParty(party);
	}

}
