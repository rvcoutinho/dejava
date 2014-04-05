package org.dejava.component.faces.test.controller.util;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Fake fakeEntity stateless component.
 */
@Stateless(name = "Component/Test/FakeEntityComponent")
public class FakeEntityComponent {

	/**
	 * The fake fakeEntity DAO.
	 */
	@Faces
	@Inject
	private FakeEntityDAO fakeEntityDAO;

	/**
	 * The fake fakeEntity rule set.
	 */
	@Faces
	@Inject
	private FakeEntityBusinessRuleSet fakeEntityBusinessRuleSet;

	/**
	 * Adds or update the persistent entity.
	 * 
	 * @param fakeEntity
	 *            The entity to be persisted.
	 * @return The persisted (and updated) entity.
	 */
	public FakeEntity addOrUpdate(final FakeEntity fakeEntity) {
		// Validates the new fakeEntity.
		fakeEntityBusinessRuleSet.validate(fakeEntity);
		// Merges the fakeEntity.
		return fakeEntityDAO.merge(fakeEntity);
	}

	/**
	 * Adds or update all the persistent entities.
	 * 
	 * @param entities
	 *            The entities to be persisted.
	 * @return The persisted (and updated) entity.
	 */
	public Collection<FakeEntity> addOrUpdate(final Collection<FakeEntity> entities) {
		// Validates the new fakeEntity.
		fakeEntityBusinessRuleSet.validate(entities);
		// Merges the entities.
		return fakeEntityDAO.merge(entities);
	}

	/**
	 * Removes a persistent entity.
	 * 
	 * @param fakeEntity
	 *            The entity to be removed.
	 */
	public void remove(final FakeEntity fakeEntity) {
		// Tries to remove the fakeEntity.
		fakeEntityDAO.remove(fakeEntity);
	}

	/**
	 * Removes persistent entities.
	 * 
	 * @param entities
	 *            The entities to be removed.
	 */
	public void remove(final Collection<FakeEntity> entities) {
		// Tries to remove the entities.
		fakeEntityDAO.remove(entities);
	}

	/**
	 * Gets an entity by its identifier.
	 * 
	 * @param identifier
	 *            The identifier of the entity.
	 * @return An entity by its identifier.
	 */
	public FakeEntity getById(final Integer identifier) {
		// Tries to return the fakeEntity.
		return fakeEntityDAO.getById(identifier);
	}

	/**
	 * Gets all entities of the kind.
	 * 
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * 
	 * @return All entities of the kind.
	 */
	public Collection<FakeEntity> getAll(final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return fakeEntityDAO.getAll(firstResult, maxResults);
	}

	/**
	 * Gets all entities with the given attribute value.
	 * 
	 * @param attributeName
	 *            The attribute name.
	 * @param attributeValue
	 *            The attribute value.
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return All entities with the given attribute value.
	 */
	public Collection<FakeEntity> getByAttribute(final String attributeName, final Object attributeValue,
			final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return fakeEntityDAO.getByAttribute(attributeName, attributeValue, firstResult, maxResults);
	}

	/**
	 * Gets an fakeEntity by its attribute.
	 * 
	 * @param attributeName
	 *            The attribute name.
	 * @param attributeValue
	 *            The attribute value.
	 * @return The fakeEntity by its attribute.
	 */
	public FakeEntity getByAttribute(final String attributeName, final Object attributeValue) {
		// Tries to get the fakeEntity.
		return fakeEntityDAO.getByAttribute(attributeName, attributeValue);
	}

	/**
	 * Gets all entities with the given attribute value.
	 * 
	 * @param attributeValue
	 *            The attribute value.
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return All entities with the given attribute value.
	 */
	public Collection<FakeEntity> getBySomeOtherFakeEntityId2(final Integer attributeValue,
			final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return fakeEntityDAO
				.getByAttribute("someOtherFakeEntityId2", attributeValue, firstResult, maxResults);
	}

	/**
	 * Gets the fakeEntity with the given attribute value.
	 * 
	 * @param attributeValue
	 *            The attribute value.
	 * @return The fakeEntity with the given attribute value.
	 */
	public FakeEntity getBySomeOtherFakeEntityId(final Integer attributeValue) {
		// Tries to get the entities.
		return fakeEntityDAO.getByAttribute("someOtherFakeEntityId", attributeValue);
	}

}
