package org.dejava.component.ejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

/**
 * Some identified entity.
 */
@MappedSuperclass
public abstract class AbstractIdentifiedEntity implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 354830267253289769L;

	/**
	 * Identifier for the entity.
	 */
	private Integer identifier;

	/**
	 * Gets the identifier for the entity.
	 * 
	 * @return The identifier for the entity.
	 */
	@Id
	@Column(name = "identifier")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the identifier for the entity.
	 * 
	 * @param identifier
	 *            New identifier for the entity.
	 */
	public void setIdentifier(final Integer identifier) {
		this.identifier = identifier;
	}

	/**
	 * Loads all external entities for the entity.
	 */
	@PostLoad
	@PostPersist
	@PostUpdate
	protected void loadAllExternalEntities() {
		// Tries to load all external entities for the entity.
		ExternalEntityLoader.loadAllExternalEntities(this, false);
	}

}
