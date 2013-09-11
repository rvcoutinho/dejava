package org.dejava.service.contact.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;

/**
 * Represents a contact.
 */
@Entity
@Table(name = "contact")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Contact extends AbstractIdentifiedEntity {

	/**
	 * Generated serial
	 */
	private static final long serialVersionUID = 8317664521891805070L;

	/**
	 * Party id for the contact.
	 */
	private Integer partyId;

	/**
	 * Gets the party id for the contact.
	 * 
	 * @return The party id for the contact.
	 */
	@Column(name = "party")
	public Integer getPartyId() {
		return partyId;
	}

	/**
	 * Sets the party id for the contact.
	 * 
	 * @param partyId
	 *            New party id for the contact.
	 */
	public void setPartyId(final Integer partyId) {
		this.partyId = partyId;
	}

}
