package org.dejava.component.ejb.test.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.ejb.entity.ExternalEntity;

/**
 * TODO
 */
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
	private Integer extEntityIdTest;

	/**
	 * Gets the external entity identifier.
	 * 
	 * @return The external entity identifier.
	 */
	public Integer getExtEntityIdTest() {
		return extEntityIdTest;
	}

	/**
	 * Sets the external entity identifier.
	 * 
	 * @param extEntityIdTest
	 *            New external entity identifier.
	 */
	public void setExtEntityIdTest(final Integer extEntityIdTest) {
		this.extEntityIdTest = extEntityIdTest;
	}

	/**
	 * The external entity.
	 */
	@ExternalEntity(retrieveObj = "java:/global/test/Component/Test/FakeEntityComponent", paramsValuesMethod = "getExtEntityIdTest")
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
	private List<Integer> extEntitiesIdsTest;

	/**
	 * Gets the external entities identifiers.
	 * 
	 * @return The external entities identifiers.
	 */
	public List<Integer> getExtEntitiesIdsTest() {
		return extEntitiesIdsTest;
	}

	/**
	 * Sets the external entities identifiers.
	 * 
	 * @param extEntitiesIdsTest
	 *            New external entities identifiers.
	 */
	public void setExtEntitiesIdsTest(final List<Integer> extEntitiesIdsTest) {
		this.extEntitiesIdsTest = extEntitiesIdsTest;
	}

	/**
	 * The external entities.
	 */
	@ExternalEntity(lazyLoading = true, retrieveObj = "java:/global/test/Component/Test/FakeEntityComponent", paramsValuesMethod = "getExtEntitiesIdsTest", singleEntity = false)
	private List<FakeEntity> extEntities;

	/**
	 * Gets the external entities.
	 * 
	 * @return The external entities.
	 */
	public List<FakeEntity> getExtEntities() {
		// If the set is null.
		if (extEntities == null) {
			// Creates a new set.
			extEntities = new ArrayList<>();
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
	public void setExtEntities(final List<FakeEntity> extEntities) {
		this.extEntities = extEntities;
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
	private List<Integer> extEntities2Ids;

	/**
	 * Gets the external entities identifiers.
	 * 
	 * @return The external entities identifiers.
	 */
	public List<Integer> getExtEntities2Ids() {
		return extEntities2Ids;
	}

	/**
	 * Sets the external entities identifiers.
	 * 
	 * @param extEntities2Ids
	 *            New external entities identifiers.
	 */
	public void setExtEntities2Ids(final List<Integer> extEntities2Ids) {
		this.extEntities2Ids = extEntities2Ids;
	}

	/**
	 * The external entities.
	 */
	@ExternalEntity(retrieveObj = "java:/global/test/Component/Test/FakeEntityComponent", singleEntity = false)
	private List<FakeEntity> extEntities2;

	/**
	 * Gets the external entities.
	 * 
	 * @return The external entities.
	 */
	public List<FakeEntity> getExtEntities2() {
		// If the set is null.
		if (extEntities2 == null) {
			// Creates a new set.
			extEntities2 = new ArrayList<>();
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
	public void setExtEntities2(final List<FakeEntity> extEntities2) {
		this.extEntities2 = extEntities2;
	}

	/**
	 * An external entity that actually maps the relationship.
	 */
	@ExternalEntity(mappedBy = "someOtherEntityId", retrieveObj = "java:/global/test/Component/Test/FakeEntityComponent")
	private FakeEntity extMappedEntity;

	/**
	 * Gets an external entity that actually maps the relationship.
	 * 
	 * @return An external entity that actually maps the relationship.
	 */
	public FakeEntity getExtMappedEntity() {
		return extMappedEntity;
	}

	/**
	 * Sets an external entity that actually maps the relationship.
	 * 
	 * @param extMappedEntity
	 *            New external entity that actually maps the relationship.
	 */
	public void setExtMappedEntity(final FakeEntity extMappedEntity) {
		this.extMappedEntity = extMappedEntity;
	}

	/**
	 * Some external entities that actually map the relationship.
	 */
	@ExternalEntity(mappedBy = "someOtherEntityId2", retrieveObj = "java:/global/test/Component/Test/FakeEntityComponent", singleEntity = false)
	private Collection<FakeEntity> extMappedEntities;

	/**
	 * Gets some external entities that actually map the relationship.
	 * 
	 * @return Some external entities that actually map the relationship.
	 */
	public Collection<FakeEntity> getExtMappedEntities() {
		// If the set is null.
		if (extMappedEntities == null) {
			// Creates a new set.
			extMappedEntities = new ArrayList<>();
		}
		// Returns the external entities.
		return extMappedEntities;
	}

	/**
	 * Sets some external entities that actually map the relationship.
	 * 
	 * @param extMappedEntities
	 *            New external entities that actually map the relationship.
	 */
	public void setExtMappedEntities(final Collection<FakeEntity> extMappedEntities) {
		this.extMappedEntities = extMappedEntities;
	}

	/**
	 * Default constructor.
	 * 
	 * @param identifier
	 *            Identifier for the entity.
	 * @param extEntityIdTest
	 *            The external entity id.
	 * @param extEntitiesIdsTest
	 *            The external entities ids.
	 * @param extEntity2Id
	 *            The external entity id.
	 * @param extEntities2Ids
	 *            The external entities ids.
	 * @param extMappedEntity
	 *            An external entity that actually maps the relationship.
	 * @param extMappedEntities
	 *            Some external entities that actually map the relationship.
	 */
	public SomeOtherFakeEntity(final Integer identifier, final Integer extEntityIdTest,
			final Integer[] extEntitiesIdsTest, final Integer extEntity2Id, final Integer[] extEntities2Ids,
			final FakeEntity extMappedEntity, final Collection<FakeEntity> extMappedEntities) {
		super();
		setIdentifier(identifier);
		this.extEntityIdTest = extEntityIdTest;
		this.extEntity2Id = extEntity2Id;
		this.extMappedEntity = extMappedEntity;
		this.extMappedEntities = extMappedEntities;
		// If there are any external entities.
		if (extEntitiesIdsTest != null) {
			// Creates a new set for the entities ids.
			this.extEntitiesIdsTest = new ArrayList<>();
			// For each given entity id.
			for (final Integer curExtEntityId : extEntitiesIdsTest) {
				// Adds the id to the set.
				this.extEntitiesIdsTest.add(curExtEntityId);
			}
		}
		// If there are any external entities.
		if (extEntities2Ids != null) {
			// Creates a new set for the entities ids.
			this.extEntities2Ids = new ArrayList<>();
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
			extEntityIdTest = null;
		}
		// If the external entity is not null.
		else {
			// Sets the external entity id to the external entity actual identifier.
			extEntityIdTest = extEntity.getIdentifier();
		}
	}

	/**
	 * Update the external entities ids with the actual external entities ids.
	 */
	protected void updateExtEntitiesIds() {
		// If the external entities is null.
		if (extEntities == null) {
			// Sets the external entities ids to null.
			extEntitiesIdsTest = null;
		}
		// If the external entities is not null.
		else {
			// Creates a new set.
			extEntitiesIdsTest = new ArrayList<>();
			// For each entity in the entity set.
			for (final FakeEntity curFakeEntity : extEntities) {
				// Adds the current entity id to the set.
				extEntitiesIdsTest.add(curFakeEntity.getIdentifier());
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
			extEntities2Ids = new ArrayList<>();
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
