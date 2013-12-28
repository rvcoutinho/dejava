package org.dejava.component.faces.controller;

import java.util.Collection;

/**
 * Defines the expected interface of a entity controller.
 * 
 * @param <Entity>
 *            Any entity.
 * @param <Key>
 *            Key of the entity.
 */
public interface GenericController<Entity, Key> {

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
	Collection<Entity> addOrUpdate(final Collection<Entity> entities);

	/**
	 * Removes a persistent entity.
	 * 
	 * @param entity
	 *            The entity to be removed.
	 */
	void remove(final Entity entity);

	/**
	 * Removes persistent entities.
	 * 
	 * @param entities
	 *            The entities to be removed.
	 */
	void remove(final Collection<Entity> entities);

	/**
	 * Gets an entity by its identifier.
	 * 
	 * @param identifier
	 *            The identifier of the entity.
	 * @return An entity by its identifier.
	 */
	Entity getById(final Key identifier);

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
	Collection<Entity> getAll(final Integer firstResult, final Integer maxResults);

}
