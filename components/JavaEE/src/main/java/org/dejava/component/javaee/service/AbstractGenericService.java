package org.dejava.component.javaee.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import org.dejava.component.javaee.dao.AbstractGenericDAO;

/**
 * Implements the default behavior of an JPA entity EJB service.
 * 
 * @param <Entity>
 *            Any entity.
 */
public abstract class AbstractGenericService<Entity> implements GenericService<Entity> {

	/**
	 * Entity DAO.
	 */
	@Inject
	private AbstractGenericDAO<Entity> entityDAO;

	/**
	 * Gets the entityDAO.
	 * 
	 * @return The entityDAO.
	 */
	public AbstractGenericDAO<Entity> getEntityDAO() {
		return entityDAO;
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#addOrUpdate(java.lang.Object)
	 */
	@Override
	public Entity addOrUpdate(final Entity entity) {
		// Merges the entity.
		return getEntityDAO().merge(entity);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#addOrUpdateAll(java.util.Collection)
	 */
	@Override
	public Collection<Entity> addOrUpdateAll(final Collection<Entity> entities) {
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
	 * @see org.dejava.component.javaee.service.GenericService#removeAll(java.util.Collection)
	 */
	@Override
	public void removeAll(final Collection<Entity> entities) {
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
	 * @see org.dejava.component.javaee.service.GenericService#getEntityById(java.lang.Object)
	 */
	@Override
	public Entity getEntityById(final Object identifier) {
		// Tries to return the entity.
		return getEntityDAO().getEntityById(identifier);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#getEntitiesByAttribute(java.lang.String,
	 *      java.lang.Object, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Collection<Entity> getEntitiesByAttribute(final String attributeName, final Object attributeValue,
			final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return getEntityDAO().getEntitiesByAttribute(attributeName, attributeValue, firstResult, maxResults);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#getAllEntities(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public Collection<Entity> getAllEntities(final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return getEntityDAO().getAllEntities(firstResult, maxResults);
	}
}
