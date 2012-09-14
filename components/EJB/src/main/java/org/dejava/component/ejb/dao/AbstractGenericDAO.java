package org.dejava.component.ejb.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
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
	 * Gets the criteria builder instance.
	 * 
	 * @return The criteria builder instance.
	 */
	protected CriteriaBuilder getCriteriaBuilder() {
		return getEntityManager().getCriteriaBuilder();
	}

	/**
	 * Limit the results of a query.
	 * 
	 * @param query
	 *            The query to have its results limited.
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return Return the limited query.
	 */
	protected Query limitResultList(final Query query, final Integer firstResult, final Integer maxResults) {
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
	 * @param identifier
	 *            The id of the entity.
	 * @return An entity by its id.
	 */
	public Entity getEntityById(final Object identifier) {
		// Tries to return the entity.
		return getEntityManager().find(getEntityClass(), identifier);
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
		// Creates a new criteria query.
		final CriteriaQuery<Entity> criteriaQuery = getCriteriaBuilder().createQuery(getEntityClass());
		// Creates the root for the query.
		final Root<Entity> criteriaRoot = criteriaQuery.from(getEntityClass());
		// Sets the condition for the given attribute value.
		criteriaQuery.where(getCriteriaBuilder().equal(criteriaRoot.get(attributeName), attributeValue));
		// Gets the JPA query.
		final TypedQuery<Entity> query = getEntityManager().createQuery(criteriaQuery);
		// Limits the query results.
		limitResultList(query, firstResult, maxResults);
		// Executes the query and gets all the entities.
		return query.getResultList();
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
		// Creates a new criteria query.
		final CriteriaQuery<Entity> criteriaQuery = getCriteriaBuilder().createQuery(getEntityClass());
		// Creates the root for the query.
		final Root<Entity> criteriaRoot = criteriaQuery.from(getEntityClass());
		// Defines the select for the query.
		criteriaQuery.select(criteriaRoot);
		// Gets the JPA query.
		final TypedQuery<Entity> query = getEntityManager().createQuery(criteriaQuery);
		// Limits the query results.
		limitResultList(query, firstResult, maxResults);
		// Executes the query and gets all the entities.
		return query.getResultList();
	}

}
