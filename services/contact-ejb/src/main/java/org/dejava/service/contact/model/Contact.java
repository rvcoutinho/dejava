package org.dejava.service.contact.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.validation.constant.MessageTemplateWildCards;
import org.dejava.service.contact.util.MessageTypes;

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
	private Integer party;

	/**
	 * Gets the party id for the contact.
	 * 
	 * @return The party id for the contact.
	 */
	@Column(name = "party")
	@NotNull(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".party.notnull")
	public Integer getParty() {
		return party;
	}

	/**
	 * Sets the party id for the contact.
	 * 
	 * @param party
	 *            New party id for the contact.
	 */
	public void setParty(final Integer party) {
		this.party = party;
	}

	/**
	 * Default constructor.
	 */
	protected Contact() {
	}

	/**
	 * Default constructor.
	 * 
	 * @param party
	 *            The contact party identifier.
	 */
	protected Contact(Integer party) {
		this.party = party;
	}

}
