package org.dejava.component.ejb.test.util;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.ejb.entity.ExternalEntity;

/**
 * Fake entity.
 */
public class SomeOtherFakeEntity extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -1265699242725340044L;

	/**
	 * The external entity identifier.
	 */
	private Integer extEntityId;

	/**
	 * Gets the external entity identifier.
	 * 
	 * @return The external entity identifier.
	 */
	public Integer getExtEntityId() {
		return extEntityId;
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
	@ExternalEntity(retrieveObj = "java:/global/test/Component/Test/FakeEntityComponent", paramsValuesMethod = "getExtEntityId")
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
	 * Gets the external entities identifiers.
	 * 
	 * @return The external entities identifiers.
	 */
	public Set<Integer> getExtEntitiesIds() {
		return extEntitiesIds;
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
	@ExternalEntity(lazyLoading = true, retrieveObj = "java:/global/test/Component/Test/FakeEntityComponent", paramsValuesMethod = "getExtEntitiesIds", singleEntity = false)
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
	 * The external entity identifier.
	 */
	private Integer extEntity2Id;

	/**
	 * Gets the external entity identifier.
	 * 
	 * @return The external entity identifier.
	 */
	public Integer getExtEntity2Id() {
		return extEntity2Id;
	}

	/**
	 * Sets the external entity identifier.
	 * 
	 * @param extEntity2Id
	 *            New external entity identifier.
	 */
	public void setExtEntity2Id(final Integer extEntity2Id) {
		this.extEntity2Id = extEntity2Id;
	}

	/**
	 * The external entity.
	 */
	@ExternalEntity(retrieveObj = "java:/global/test/Component/Test/FakeEntityComponent")
	private FakeEntity extEntity2;

	/**
	 * Gets the external entity.
	 * 
	 * @return The external entity.
	 */
	public FakeEntity getExtEntity2() {
		return extEntity2;
	}

	/**
	 * Sets the external entity.
	 * 
	 * @param extEntity2
	 *            New external entity.
	 */
	public void setExtEntity2(final FakeEntity extEntity2) {
		this.extEntity2 = extEntity2;
	}

	/**
	 * The external entities identifiers.
	 */
	private Set<Integer> extEntities2Ids;

	/**
	 * Gets the external entities identifiers.
	 * 
	 * @return The external entities identifiers.
	 */
	public Set<Integer> getExtEntities2Ids() {
		return extEntities2Ids;
	}

	/**
	 * Sets the external entities identifiers.
	 * 
	 * @param extEntities2Ids
	 *            New external entities identifiers.
	 */
	public void setExtEntities2Ids(final Set<Integer> extEntities2Ids) {
		this.extEntities2Ids = extEntities2Ids;
	}

	/**
	 * The external entities.
	 */
	@ExternalEntity(retrieveObj = "java:/global/test/Component/Test/FakeEntityComponent", singleEntity = false)
	private Set<FakeEntity> extEntities2;

	/**
	 * Gets the external entities.
	 * 
	 * @return The external entities.
	 */
	public Set<FakeEntity> getExtEntities2() {
		// If the set is null.
		if (extEntities2 == null) {
			// Creates a new set.
			extEntities2 = new HashSet<>();
		}
		// Returns the external entities.
		return extEntities2;
	}

	/**
	 * Sets the external entities.
	 * 
	 * @param extEntities2
	 *            New external entities.
	 */
	public void setExtEntities2(final Set<FakeEntity> extEntities2) {
		// Sets the external entities.
		this.extEntities2 = extEntities2;
		// Resets the persisted identifier.
		setExtEntities2Ids(null);
	}

	/**
	 * Default constructor.
	 * 
	 * @param extEntityId
	 *            The external entity id.
	 * @param extEntitiesIds
	 *            The external entities ids.
	 * @param extEntity2Id
	 *            The external entity id.
	 * @param extEntities2Ids
	 *            The external entities ids.
	 */
	public SomeOtherFakeEntity(final Integer extEntityId, final Integer[] extEntitiesIds,
			final Integer extEntity2Id, final Integer[] extEntities2Ids) {
		super();
		this.extEntityId = extEntityId;
		this.extEntity2Id = extEntity2Id;
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
		// If there are any external entities.
		if (extEntities2Ids != null) {
			// Creates a new set for the entities ids.
			this.extEntities2Ids = new HashSet<>();
			// For each given entity id.
			for (final Integer curExtEntityId : extEntities2Ids) {
				// Adds the id to the set.
				this.extEntities2Ids.add(curExtEntityId);
			}
		}
	}

	/**
	 * Update the external entity id with the actual external entity id.
	 */
	protected void updateExtEntityId() {
		// If the external entity is null.
		if (extEntity == null) {
			// Sets the external entity id to null.
			extEntityId = null;
		}
		// If the external entity is not null.
		else {
			// Sets the external entity id to the external entity actual identifier.
			extEntityId = extEntity.getIdentifier();
		}
	}

	/**
	 * Update the external entities ids with the actual external entities ids.
	 */
	protected void updateExtEntitiesIds() {
		// If the external entities is null.
		if (extEntities == null) {
			// Sets the external entities ids to null.
			extEntitiesIds = null;
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
		}
	}

	/**
	 * Update the external entity id with the actual external entity id.
	 */
	protected void updateExtEntity2Id() {
		// If the external entity is null.
		if (extEntity2 == null) {
			// Sets the external entity id to null.
			extEntity2Id = null;
		}
		// If the external entity is not null.
		else {
			// Sets the external entity id to the external entity actual identifier.
			extEntity2Id = extEntity2.getIdentifier();
		}
	}

	/**
	 * Update the external entities ids with the actual external entities ids.
	 */
	protected void updateExtEntities2Ids() {
		// If the external entities is null.
		if (extEntities2 == null) {
			// Sets the external entities ids to null.
			extEntities2Ids = null;
		}
		// If the external entities is not null.
		else {
			// Creates a new set.
			extEntities2Ids = new HashSet<>();
			// For each entity in the entity set.
			for (final FakeEntity curFakeEntity : extEntities2) {
				// Adds the current entity id to the set.
				extEntities2Ids.add(curFakeEntity.getIdentifier());
			}
		}
	}

	/**
	 * Update the external entities ids to be persisted.
	 */
	@PreUpdate
	@PrePersist
	protected void updateAllExtEntitiesIds() {
		// Updates the external entities ids.
		updateExtEntityId();
		updateExtEntitiesIds();
		updateExtEntity2Id();
		updateExtEntities2Ids();
	}

}
