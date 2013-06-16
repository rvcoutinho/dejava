package org.dejava.component.ejb.test.util;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Transient;

import org.dejava.component.ejb.entity.ExternalEntity;
import org.dejava.component.ejb.entity.IdentifiedEntity;

/**
 * Fake entity.
 */
public class SomeOtherFakeEntity extends IdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -1265699242725340044L;

	/**
	 * The external entity identifier.
	 */
	private Integer extEntityId;

	/**
	 * Gets the raw external entity identifier.
	 * 
	 * @return The raw external entity identifier.
	 */
	public Integer getRawExtEntityId() {
		return extEntityId;
	}

	/**
	 * Gets the external entity identifier.
	 * 
	 * @return The external entity identifier.
	 */
	public Integer getExtEntityId() {
		// If the external entity is null.
		if (extEntity == null) {
			// Returns null.
			return null;
		}
		// If the external entity is not null.
		else {
			// Returns the external entity id.
			return extEntity.getIdentifier();
		}
	}

	/**
	 * Sets the external entity identifier.
	 * 
	 * @param extEntityId
	 *            New external entity identifier.
	 */
	public void setExtEntityId(final Integer extEntityId) {
		this.extEntityId = extEntityId;
	}

	/**
	 * The external entity.
	 */
	@ExternalEntity(retrieveObj = "java:/global/test/Component/Test/FakeEntityComponent", idsMethod = "getRawExtEntityId")
	private FakeEntity extEntity;

	/**
	 * Gets the external entity.
	 * 
	 * @return The external entity.
	 */
	public FakeEntity getExtEntity() {
		return extEntity;
	}

	/**
	 * Sets the external entity.
	 * 
	 * @param extEntity
	 *            New external entity.
	 */
	public void setExtEntity(final FakeEntity extEntity) {
		this.extEntity = extEntity;
	}

	/**
	 * The external entities identifiers.
	 */
	private Set<Integer> extEntitiesIds;

	/**
	 * Gets the raw external entities identifiers.
	 * 
	 * @return The raw external entities identifiers.
	 */
	@Transient
	public Set<Integer> getRawExtEntitiesIds() {
		return extEntitiesIds;
	}

	/**
	 * Gets the external entities identifiers.
	 * 
	 * @return The external entities identifiers.
	 */
	public Set<Integer> getExtEntitiesIds() {
		// If the external entities is null.
		if (extEntities == null) {
			// Returns the persisted ids.
			return null;
		}
		// If the external entities is not null.
		else {
			// Creates a new set.
			extEntitiesIds = new HashSet<>();
			// For each entity in the entity set.
			for (final FakeEntity curFakeEntity : extEntities) {
				// Adds the current entity id to the set.
				extEntitiesIds.add(curFakeEntity.getIdentifier());
			}
			// Returns the external entities ids.
			return extEntitiesIds;
		}
	}

	/**
	 * Sets the external entities identifiers.
	 * 
	 * @param extEntitiesIds
	 *            New external entities identifiers.
	 */
	public void setExtEntitiesIds(final Set<Integer> extEntitiesIds) {
		this.extEntitiesIds = extEntitiesIds;
	}

	/**
	 * The external entities.
	 */
	@ExternalEntity(lazyLoading = true, retrieveObj = "java:/global/test/Component/Test/FakeEntityComponent", idsMethod = "getRawExtEntitiesIds")
	private Set<FakeEntity> extEntities;

	/**
	 * Gets the external entities.
	 * 
	 * @return The external entities.
	 */
	public Set<FakeEntity> getExtEntities() {
		// If the set is null.
		if (extEntities == null) {
			// Creates a new set.
			extEntities = new HashSet<>();
		}
		// Returns the external entities.
		return extEntities;
	}

	/**
	 * Sets the external entities.
	 * 
	 * @param extEntities
	 *            New external entities.
	 */
	public void setExtEntities(final Set<FakeEntity> extEntities) {
		// Sets the external entities.
		this.extEntities = extEntities;
		// Resets the persisted identifier.
		setExtEntitiesIds(null);
	}

	/**
	 * Default constructor.
	 * 
	 * @param extEntityId
	 *            The external entity id.
	 * @param extEntitiesIds
	 *            The external entities ids.
	 */
	public SomeOtherFakeEntity(final Integer extEntityId, final Integer[] extEntitiesIds) {
		super();
		this.extEntityId = extEntityId;
		// If there are any external entities.
		if (extEntitiesIds != null) {
			// Creates a new set for the entities ids.
			this.extEntitiesIds = new HashSet<>();
			// For each given entity id.
			for (final Integer curExtEntityId : extEntitiesIds) {
				// Adds the id to the set.
				this.extEntitiesIds.add(curExtEntityId);
			}
		}
	}

}
