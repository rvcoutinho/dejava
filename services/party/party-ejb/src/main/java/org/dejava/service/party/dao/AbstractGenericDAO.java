package org.dejava.service.party.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Implements the default behavior of an JPA entity DAO.
 * 
 * @param <Entity>
 *            Any entity.
 */
public abstract class AbstractGenericDAO<Entity> {

	/**
	 * The class of the generic entity.
	 */
	private Class<Entity> entityClass;

	/**
	 * Gets the class of the generic entity.
	 * 
	 * @return The class of the generic entity.
	 */
	@SuppressWarnings("unchecked")
	public Class<Entity> getEntityClass() {
		// If the class has not been resolved yet.
		if (entityClass == null) {
			// Gets the generic superclass of the DAO class (this class).
			// FIXME If this class is not the direct superclass there might be a problem.
			final ParameterizedType genericClass = (ParameterizedType) getClass().getGenericSuperclass();
			// Gets the entity class as the first generic parameter (Entity).
			entityClass = ((Class<Entity>) genericClass.getActualTypeArguments()[0]);
		}
		// Returns the class of the generic entity.
		return entityClass;
	}

	/**
	 * Gets the entity manager for the DAO.
	 * 
	 * @return The entity manager for the DAO.
	 */
	protected abstract EntityManager getEntityManager();

	/**
	 * TODO
	 * 
	 * @param query
	 *            TODO
	 * @param firstResult
	 *            TODO
	 * @param maxResults
	 *            TODO
	 * @return TODO
	 */
	public Query limitResultList(Query query, Integer firstResult, Integer maxResults) {
		// If the first result is given.
		if (firstResult != null) {
			// Sets the first result of the query.
			query.setFirstResult(firstResult);
		}
		// If a maximum results number is given.
		if (maxResults != null) {
			// Sets the maximum results number of the query.
			query.setMaxResults(maxResults);
		}
		// Returns the query.
		return query;
	}

	/**
	 * Gets an entity by its id.
	 * 
	 * @param id
	 *            The id of the entity.
	 * @return An entity by its id.
	 */
	public Entity getEntityById(final Object id) {
		// Tries to return the entity.
		return getEntityManager().find(getEntityClass(), id);
	}

	/**
	 * Gets all entities of the kind.
	 * 
	 * @param firstResult
	 *            TODO
	 * @param maxResults
	 *            TODO
	 * 
	 * @return All entities of the kind.
	 */
	public Collection<Entity> getAllEntities(Integer firstResult, Integer maxResults) {
		// Creates a new criteria query.
		CriteriaQuery<Entity> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(
				getEntityClass());
		// Creates the root for the query.
		Root<Entity> from = criteriaQuery.from(getEntityClass());
		// Defines the select for the query.
		criteriaQuery.select(from);

		// Executes the query and gets all the entities.
		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}

}
