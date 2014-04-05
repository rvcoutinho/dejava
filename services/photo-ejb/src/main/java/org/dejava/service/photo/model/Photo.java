package org.dejava.service.photo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;

/**
 * Represents a photo.
 */
@Entity
@Table(name = "photo")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Photo extends AbstractIdentifiedEntity {

	/**
	 * Generated serial
	 */
	private static final long serialVersionUID = 8317664521891805070L;

	/**
	 * Party id for the photo.
	 */
	private Integer partyId;

	/**
	 * Gets the party id for the photo.
	 * 
	 * @return The party id for the photo.
	 */
	@Column(name = "party")
	public Integer getPartyId() {
		return partyId;
	}

	/**
	 * Sets the party id for the photo.
	 * 
	 * @param partyId
	 *            New party id for the photo.
	 */
	public void setPartyId(final Integer partyId) {
		this.partyId = partyId;
	}

}
