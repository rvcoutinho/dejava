package org.dejava.service.philanthropy.model.party;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.ejb.entity.ExternalEntity;
import org.dejava.service.party.model.Party;

/**
 * Philanthropy party super class.
 */
@MappedSuperclass
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
	public Integer getPartyId() {
		return partyId;
	}

	/**
	 * Sets the (original) party id.
	 * 
	 * @param partyId
	 *            New (original) party id.
	 */
	public void setPartyId(final Integer partyId) {
		this.partyId = partyId;
	}

	/**
	 * The (original) party.
	 */
	@ExternalEntity(retrieveObj = "java:/global/ear/place-ejb/Component/Party/Party")
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
		this.party = party;
	}

}
