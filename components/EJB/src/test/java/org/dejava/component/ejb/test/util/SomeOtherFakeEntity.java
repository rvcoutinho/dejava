package org.dejava.component.ejb.test.util;

import java.io.Serializable;

import org.dejava.component.ejb.entity.ExternalEntity;

/**
 * Fake entity.
 */
public class SomeOtherFakeEntity implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -1265699242725340044L;

	/**
	 * The external entity identifier.
	 */
	private Integer externalEntityId;

	/**
	 * Gets the external entity identifier.
	 * 
	 * @return The external entity identifier.
	 */
	public Integer getExternalEntityId() {
		// If the external entity is null.
		if (externalEntity == null) {
			// Returns the persisted id.
			return externalEntityId;
		}
		// If the external entity is not null.
		else {
			// Returns the external entity id.
			return externalEntity.getIdentifier();
		}
	}

	/**
	 * Sets the external entity identifier.
	 * 
	 * @param externalEntityId
	 *            New external entity identifier.
	 */
	public void setExternalEntityId(final Integer externalEntityId) {
		this.externalEntityId = externalEntityId;
	}

	/**
	 * The external entity.
	 */
	@ExternalEntity(retrieveObj = "java:/global/test/Component/Test/FakeEntityComponent", idsMethod = "getExternalEntityId")
	private FakeEntity externalEntity;

	/**
	 * Gets the external entity.
	 * 
	 * @return The external entity.
	 */
	public FakeEntity getExternalEntity() {
		return externalEntity;
	}

	/**
	 * Sets the external entity.
	 * 
	 * @param externalEntity
	 *            New external entity.
	 */
	public void setExternalEntity(final FakeEntity externalEntity) {
		// Sets the external entity.
		this.externalEntity = externalEntity;
		// Resets the persisted identifier.
		setExternalEntityId(null);
	}

	/**
	 * Default constructor.
	 * 
	 * @param externalEntityId
	 *            The external entity id.
	 */
	public SomeOtherFakeEntity(final Integer externalEntityId) {
		super();
		this.externalEntityId = externalEntityId;
	}

}
