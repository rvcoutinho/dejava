package org.dejava.component.javaee.service;

import java.util.Collection;

/**
 * Defines the expected interface of a entity service.
 * 
 * @param <Entity>
 *            Any entity.
 */
public interface GenericService<Entity> {

	/**
	 * Adds or update the persistent entity.
	 * 
	 * @param entity
	 *            The entity to be persisted.
	 * @return The persisted (and updated) entity.
	 */
	Entity addOrUpdate(final Entity entity);

	/**
	 * Adds or update all the persistent entities.
	 * 
	 * @param entities
	 *            The entities to be persisted.
	 * @return The persisted (and updated) entity.
	 */
	Collection<Entity> addOrUpdateAll(final Collection<Entity> entities);

	/**
	 * Removes a persistent entity.
	 * 
	 * @param entity
	 *            Entity to be removed.
	 */
	void remove(final Entity entity);

	/**
	 * Removes persistent entities.
	 * 
	 * @param entities
	 *            The entities to be removed.
	 */
	void removeAll(final Collection<Entity> entities);

	/**
	 * Gets an entity by its id.
	 * 
	 * @param identifier
	 *            The id of the entity.
	 * @return An entity by its id.
	 */
	Entity getEntityById(final Object identifier);

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
	Collection<Entity> getEntitiesByAttribute(final String attributeName, final Object attributeValue,
			final Integer firstResult, final Integer maxResults);

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
	Collection<Entity> getAllEntities(final Integer firstResult, final Integer maxResults);
}
