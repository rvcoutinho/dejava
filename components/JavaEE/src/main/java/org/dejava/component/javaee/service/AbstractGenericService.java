package org.dejava.component.javaee.service;

import java.util.ArrayList;
import java.util.Collection;

import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.javaee.dao.AbstractGenericDAO;

/**
 * Implements the default behavior of an JPA entity EJB service.
 * 
 * @param <Entity>
 *            Any entity.
 * @param <Key>
 *            Key of the entity.
 */
public abstract class AbstractGenericService<Entity, Key> implements GenericService<Entity, Key> {

	/**
	 * Gets the entityDAO.
	 * 
	 * @return The entityDAO.
	 */
	public abstract AbstractGenericDAO<Entity, Key> getEntityDAO();

	/**
	 * @see org.dejava.component.javaee.service.GenericService#addOrUpdate(java.lang.Object)
	 */
	@Override
	public Entity addOrUpdate(final Entity entity) {
		// Merges the entity.
		return getEntityDAO().merge(entity);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#addOrUpdate(java.util.Collection)
	 */
	@Override
	public Collection<Entity> addOrUpdate(final Collection<Entity> entities) {
		// Creates a new list of entities.
		final Collection<Entity> mergedEntities = new ArrayList<>();
		// If the given collection is not empty.
		if (entities != null) {
			// For each given entity.
			for (final Entity currentEntity : entities) {
				// Tries to add or update the current entity.
				mergedEntities.add(addOrUpdate(currentEntity));
			}
		}
		// Returns the list of merged entities.
		return mergedEntities;
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#remove(java.lang.Object)
	 */
	@Override
	public void remove(final Entity entity) {
		// Tries to remove the entity.
		getEntityDAO().remove(entity);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#remove(java.util.Collection)
	 */
	@Override
	public void remove(final Collection<Entity> entities) {
		// If the given collection is not empty.
		if (entities != null) {
			// For each given entity.
			for (final Entity currentEntity : entities) {
				// Tries to remove the current entity.
				remove(currentEntity);
			}
		}
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#getById(java.lang.Object)
	 */
	@Override
	public Entity getById(final Key identifier) {
		// Tries to return the entity.
		return getEntityDAO().getById(identifier);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#getByAttribute(java.lang.String,
	 *      java.lang.Object, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Collection<Entity> getByAttribute(final String attributeName, final Object attributeValue,
			final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return getEntityDAO().getByAttribute(attributeName, attributeValue, firstResult, maxResults);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#getByAttribute(java.lang.String,
	 *      java.lang.Object)
	 */
	@Override
	public Entity getByAttribute(final String attributeName, final Object attributeValue) {
		// Tries to get the entities.
		final Collection<Entity> entities = getEntityDAO().getByAttribute(attributeName,
				attributeValue, null, null);
		// The entity is null by default.
		Entity entity = null;
		// If there are any entities.
		if ((entities != null) && (!entities.isEmpty())) {
			// If there is more than a single entity.
			if (entities.size() > 1) {
				// Throws an exception. TODO
				throw new InvalidParameterException("", null, null);
			}
			// If there is a single entity.
			else {
				// Gets the first entity.
				entity = entities.iterator().next();
			}
		}
		// Return the entity found.
		return entity;
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#getAll(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public Collection<Entity> getAll(final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return getEntityDAO().getAll(firstResult, maxResults);
	}
}
