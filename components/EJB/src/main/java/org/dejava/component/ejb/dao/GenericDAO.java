package org.dejava.component.ejb.dao;

import java.util.Collection;

/**
 * A generic DAO.
 * 
 * @param <Entity>
 *            Any entity.
 * @param <Key>
 *            Key of the entity.
 */
public interface GenericDAO<Entity, Key> {

	/**
	 * Persists the entity with the persistence context (so its state is persisted).
	 * 
	 * @param entity
	 *            The entity to be persisted.
	 * @return The persisted instance.
	 */
	public abstract Entity persist(Entity entity);

	/**
	 * Persists the entities with the persistence context (so its state is persisted).
	 * 
	 * @param entities
	 *            The entities to be persisted.
	 * @return The persisted instances.
	 */
	public abstract Collection<Entity> persist(Collection<Entity> entities);

	/**
	 * Merges the entity with the persistence context (so its state is persisted).
	 * 
	 * @param entity
	 *            The entity to be merged.
	 * @return The merged instance.
	 */
	public abstract Entity merge(Entity entity);

	/**
	 * Merges the entities with the persistence context (so its state is persisted).
	 * 
	 * @param entities
	 *            The entities to be merged.
	 * @return The merged instances.
	 */
	public abstract Collection<Entity> merge(Collection<Entity> entities);

	/**
	 * Removes a persistent entity.
	 * 
	 * @param entity
	 *            The entity to be persisted.
	 */
	public abstract void remove(Entity entity);

	/**
	 * Removes a persistent entities.
	 * 
	 * @param entities
	 *            The entities to be persisted.
	 */
	public abstract void remove(Collection<Entity> entities);

	/**
	 * Gets an entity by its identifier.
	 * 
	 * @param identifier
	 *            The identifier of the entity.
	 * @return An entity by its identifier.
	 */
	public abstract Entity getById(Key identifier);

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
	public abstract Collection<Entity> getByAttribute(String attributeName, Object attributeValue,
			Integer firstResult, Integer maxResults);

	/**
	 * Gets the entity with the given attribute value.
	 * 
	 * @param attributeName
	 *            The attribute name.
	 * @param attributeValue
	 *            The attribute value.
	 * @return The entity with the given attribute value.
	 */
	public abstract Entity getByAttribute(String attributeName, Object attributeValue);

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
	public abstract Collection<Entity> getAll(Integer firstResult, Integer maxResults);

}