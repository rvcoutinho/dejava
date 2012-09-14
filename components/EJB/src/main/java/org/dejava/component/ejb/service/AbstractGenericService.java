package org.dejava.component.ejb.service;

import java.util.Collection;

import javax.inject.Inject;

import org.dejava.component.ejb.dao.AbstractGenericDAO;

/**
 * Implements the default behavior of an JPA entity EJB service.
 * 
 * @param <Entity>
 *            Any entity.
 */
public abstract class AbstractGenericService<Entity> {

	/**
	 * Entity DAO.
	 */
	@Inject
	private AbstractGenericDAO<Entity> entityDAO;

	/**
	 * Gets an entity by its id.
	 * 
	 * @param identifier
	 *            The id of the entity.
	 * @return An entity by its id.
	 */
	public Entity getEntityById(final Object identifier) {
		// Tries to return the entity.
		return entityDAO.getEntityById(identifier);
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
	public Collection<Entity> getEntitiesByAttribute(final String attributeName, final Object attributeValue,
			final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return getEntitiesByAttribute(attributeName, attributeValue, firstResult, maxResults);
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
	public Collection<Entity> getAllEntities(final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return getAllEntities(firstResult, maxResults);
	}

}
