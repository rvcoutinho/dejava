package org.dejava.component.ejb.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.dejava.component.ejb.constant.DAOParamKeys;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.validation.method.PreConditions;

/**
 * Implements the default behavior of an JPA entity DAO.
 * 
 * @param <Entity>
 *            Any entity.
 * @param <Key>
 *            Key of the entity.
 */
public abstract class AbstractGenericDAO<Entity, Key> implements GenericDAO<Entity, Key> {

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
	 * @see org.dejava.component.ejb.dao.GenericDAO#merge(java.lang.Object)
	 */
	@Override
	public Entity merge(final Entity entity) {
		// Asserts that the entity is not null.
		PreConditions.assertParamNotNull(DAOParamKeys.ENTITY, entity);
		// TODO Check if already is persisted?
		// Merges the given entity with the persistence context.
		return getEntityManager().merge(entity);
	}

	/**
	 * @see org.dejava.component.ejb.dao.GenericDAO#merge(java.util.Collection)
	 */
	@Override
	public Collection<Entity> merge(final Collection<Entity> entities) {
		// Creates a new list of entities.
		final Collection<Entity> mergedEntities = new ArrayList<>();
		// If the given collection is not empty.
		if (entities != null) {
			// For each given entity.
			for (final Entity currentEntity : entities) {
				// Tries to add or update the current entity.
				mergedEntities.add(merge(currentEntity));
			}
		}
		// Returns the list of merged entities.
		return mergedEntities;
	}

	/**
	 * @see org.dejava.component.ejb.dao.GenericDAO#remove(java.lang.Object)
	 */
	@Override
	public void remove(final Entity entity) {
		// Asserts that the entity is not null.
		PreConditions.assertParamNotNull(DAOParamKeys.ENTITY, entity);
		// Merges the given entity with the persistence context.
		final Entity mergedEntity = getEntityManager().merge(entity);
		// Tries to remove the entity.
		getEntityManager().remove(mergedEntity);
	}

	/**
	 * @see org.dejava.component.ejb.dao.GenericDAO#remove(java.util.Collection)
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
	 * @see org.dejava.component.ejb.dao.GenericDAO#getById(java.lang.Object)
	 */
	@Override
	public Entity getById(final Key identifier) {
		// Asserts that the identifier is not null.
		PreConditions.assertParamNotNull(DAOParamKeys.IDENTIFIER, identifier);
		// Tries to return the entity.
		return getEntityManager().find(getEntityClass(), identifier);
	}

	/**
	 * @see org.dejava.component.ejb.dao.GenericDAO#getByAttribute(java.lang.String, java.lang.Object,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Collection<Entity> getByAttribute(final String attributeName, final Object attributeValue,
			final Integer firstResult, final Integer maxResults) {
		// Asserts that the attribute name is not null.
		PreConditions.assertParamNotNull(DAOParamKeys.ATTR_NAME, attributeName);
		// Creates a new criteria query.
		final CriteriaQuery<Entity> criteriaQuery = getCriteriaBuilder().createQuery(getEntityClass());
		// Creates the root for the query.
		final Root<Entity> criteriaRoot = criteriaQuery.from(getEntityClass());
		// If the attribute value is null.
		if (attributeValue == null) {
			// Sets the is null condition for the given attribute value.
			criteriaQuery.where(getCriteriaBuilder().isTrue(criteriaRoot.get(attributeName).isNull()));
		}
		// If the attribute value is not null.
		else {
			// Sets the condition for the given attribute value.
			criteriaQuery.where(getCriteriaBuilder().equal(criteriaRoot.get(attributeName), attributeValue));
		}
		// Gets the JPA query.
		final TypedQuery<Entity> query = getEntityManager().createQuery(criteriaQuery);
		// Limits the query results.
		limitResultList(query, firstResult, maxResults);
		// Executes the query and gets all the entities.
		return query.getResultList();
	}

	/**
	 * @see org.dejava.component.ejb.dao.GenericDAO#getByAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public Entity getByAttribute(final String attributeName, final Object attributeValue) {
		// Tries to get the entities.
		final Collection<Entity> entities = getByAttribute(attributeName, attributeValue, null, null);
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
	 * @see org.dejava.component.ejb.dao.GenericDAO#getAll(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Collection<Entity> getAll(final Integer firstResult, final Integer maxResults) {
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
