package org.dejava.component.ejb.component;

import java.util.Collection;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.component.validation.object.ThrowerValidator;

/**
 * Implements the default behavior of an JPA entity EJB component.
 * 
 * @param <Entity>
 *            Any entity.
 * @param <Key>
 *            Key of the entity.
 */
public abstract class AbstractGenericComponent<Entity, Key> implements GenericComponent<Entity, Key> {

	/**
	 * Gets the entityDAO.
	 * 
	 * @return The entityDAO.
	 */
	public abstract AbstractGenericDAO<Entity, Key> getEntityDAO();

	/**
	 * @see org.dejava.component.ejb.component.GenericComponent#addOrUpdate(java.lang.Object)
	 */
	@Override
	public Entity addOrUpdate(final Entity entity) {
		// If the entity is not null.
		if (entity != null) {
			// Validates the current entity.
			ThrowerValidator.getDefaultThrowerValidator().validate(entity);
		}
		// Merges the entity.
		return getEntityDAO().merge(entity);
	}

	/**
	 * @see org.dejava.component.ejb.component.GenericComponent#addOrUpdate(java.util.Collection)
	 */
	@Override
	public Collection<Entity> addOrUpdate(final Collection<Entity> entities) {
		// If there are entities to be added.
		if (entities != null) {
			// For each entity.
			for (final Entity currentEntity : entities) {
				// Validates the current entity.
				ThrowerValidator.getDefaultThrowerValidator().validate(currentEntity);
			}
		}
		// Merges the entities.
		return getEntityDAO().merge(entities);
	}

	/**
	 * @see org.dejava.component.ejb.component.GenericComponent#remove(java.lang.Object)
	 */
	@Override
	public void remove(final Entity entity) {
		// Tries to remove the entity.
		getEntityDAO().remove(entity);
	}

	/**
	 * @see org.dejava.component.ejb.component.GenericComponent#remove(java.util.Collection)
	 */
	@Override
	public void remove(final Collection<Entity> entities) {
		// Tries to remove the entities.
		getEntityDAO().remove(entities);
	}

	/**
	 * @see org.dejava.component.ejb.component.GenericComponent#getById(java.lang.Object)
	 */
	@Override
	public Entity getById(final Key identifier) {
		// Tries to return the entity.
		return getEntityDAO().getById(identifier);
	}

	/**
	 * @see org.dejava.component.ejb.component.GenericComponent#getByAttribute(java.lang.String,
	 *      java.lang.Object, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Collection<Entity> getByAttribute(final String attributeName, final Object attributeValue,
			final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return getEntityDAO().getByAttribute(attributeName, attributeValue, firstResult, maxResults);
	}

	/**
	 * @see org.dejava.component.ejb.component.GenericComponent#getByAttribute(java.lang.String,
	 *      java.lang.Object)
	 */
	@Override
	public Entity getByAttribute(final String attributeName, final Object attributeValue) {
		// Tries to get the entity.
		return getEntityDAO().getByAttribute(attributeName, attributeValue);
	}

	/**
	 * @see org.dejava.component.ejb.component.GenericComponent#getAll(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Collection<Entity> getAll(final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return getEntityDAO().getAll(firstResult, maxResults);
	}
}
