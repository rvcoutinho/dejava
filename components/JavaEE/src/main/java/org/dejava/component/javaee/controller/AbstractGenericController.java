package org.dejava.component.javaee.controller;

import java.util.Collection;

import javax.enterprise.inject.Model;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.component.javaee.service.GenericService;

/**
 * TODO
 * 
 * @param <Entity>
 *            Any entity.
 */
@Model
public abstract class AbstractGenericController<Entity> implements GenericService<Entity> {

	/**
	 * Gets the business service related to the entity.
	 * 
	 * @return The business service related to the entity.
	 */
	protected abstract AbstractGenericService<Entity> getBusinessService();

	/**
	 * @see org.dejava.component.javaee.service.GenericService#addOrUpdate(java.lang.Object)
	 */
	@Override
	public Entity addOrUpdate(final Entity entity) {
		// Adds the entity.
		return getBusinessService().addOrUpdate(entity);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#addOrUpdateAll(java.util.Collection)
	 */
	@Override
	public Collection<Entity> addOrUpdateAll(final Collection<Entity> entities) {
		// Adds the entities.
		return getBusinessService().addOrUpdateAll(entities);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#remove(java.lang.Object)
	 */
	@Override
	public void remove(final Entity entity) {
		// Tries to remove the entity.
		getBusinessService().remove(entity);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#removeAll(java.util.Collection)
	 */
	@Override
	public void removeAll(final Collection<Entity> entities) {
		// Tries to remove the entities.
		getBusinessService().removeAll(entities);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#getEntityById(java.lang.Object)
	 */
	@Override
	public Entity getEntityById(final Object identifier) {
		// Tries to return the entity.
		return getBusinessService().getEntityById(identifier);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#getEntitiesByAttribute(java.lang.String,
	 *      java.lang.Object, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Collection<Entity> getEntitiesByAttribute(final String attributeName, final Object attributeValue,
			final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return getBusinessService().getEntitiesByAttribute(attributeName, attributeValue, firstResult,
				maxResults);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#getAllEntities(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Collection<Entity> getAllEntities(final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return getBusinessService().getAllEntities(firstResult, maxResults);
	}

}
