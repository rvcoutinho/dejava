package org.dejava.component.ejb.component;

import java.util.Collection;

import org.dejava.component.ejb.businessrule.GenericEntityBusinessRuleSet;
import org.dejava.component.ejb.dao.GenericDAO;

/**
 * Implements the default behavior of a CRUD entity EJB component.
 * 
 * @param <Entity>
 *            Any entity.
 * @param <Key>
 *            Key of the entity.
 */
public abstract class AbstractGenericComponent<Entity, Key> implements GenericComponent<Entity, Key> {

	/**
	 * Gets the entity DAO.
	 * 
	 * @return The entity DAO.
	 */
	public abstract GenericDAO<Entity, Key> getEntityDAO();

	/**
	 * Gets the entity business rule set.
	 * 
	 * @return The entity business rule set.
	 */
	public abstract GenericEntityBusinessRuleSet<Entity> getEntityBusinessRuleSet();

	/**
	 * @see org.dejava.component.ejb.component.GenericComponent#addOrUpdate(java.lang.Object)
	 */
	@Override
	public Entity addOrUpdate(final Entity entity) {
		// Validates the new entity.
		getEntityBusinessRuleSet().validate(entity);
		// Merges the entity.
		return getEntityDAO().merge(entity);
	}

	/**
	 * @see org.dejava.component.ejb.component.GenericComponent#addOrUpdate(java.util.Collection)
	 */
	@Override
	public Collection<Entity> addOrUpdate(final Collection<Entity> entities) {
		// Validates the new entity.
		getEntityBusinessRuleSet().validate(entities);
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
	 * @see org.dejava.component.ejb.component.GenericComponent#getAll(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Collection<Entity> getAll(final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return getEntityDAO().getAll(firstResult, maxResults);
	}
}
