package org.dejava.component.ejb.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Some versioned entity (for optimistic locking).
 */
@MappedSuperclass
public abstract class AbstractVersionedEntity extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 7195553701369620244L;

	/**
	 * Version for the entity.
	 */
	private Integer version;

	/**
	 * Gets the version for the entity.
	 * 
	 * @return The version for the entity.
	 */
	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return version;
	}

	/**
	 * Sets the version for the entity.
	 * 
	 * @param version
	 *            New version for the entity.
	 */
	public void setVersion(final Integer version) {
		this.version = version;
	}

}
