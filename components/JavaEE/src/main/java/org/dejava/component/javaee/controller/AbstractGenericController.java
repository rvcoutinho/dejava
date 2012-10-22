package org.dejava.component.javaee.controller;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.component.javaee.service.GenericService;

/**
 * Implements the default behavior of an JPA entity JSF controller (including JAX-RS pre-configuration).
 * 
 * @param <Entity>
 *            Any entity.
 * @param <Key>
 *            Key of the entity.
 */
public abstract class AbstractGenericController<Entity, Key> implements GenericService<Entity, Key> {

	/**
	 * Gets the business service related to the entity.
	 * 
	 * @return The business service related to the entity.
	 */
	protected abstract AbstractGenericService<Entity, Key> getBusinessService();

	/**
	 * @see org.dejava.component.javaee.service.GenericService#addOrUpdate(java.lang.Object)
	 */
	@PUT
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Entity addOrUpdate(final Entity entity) {
		// Adds the entity.
		return getBusinessService().addOrUpdate(entity);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#addOrUpdateAll(java.util.Collection)
	 */
	@PUT
	@Path(value = "/s")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Collection<Entity> addOrUpdateAll(final Collection<Entity> entities) {
		// Adds the entities.
		return getBusinessService().addOrUpdateAll(entities);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#remove(java.lang.Object)
	 */
	@DELETE
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Override
	public void remove(final Entity entity) {
		// Tries to remove the entity.
		getBusinessService().remove(entity);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#removeAll(java.util.Collection)
	 */
	@DELETE
	@Path(value = "/s")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Override
	public void removeAll(final Collection<Entity> entities) {
		// Tries to remove the entities.
		getBusinessService().removeAll(entities);
	}

	/**
	 * @see org.dejava.component.javaee.service.GenericService#getEntityById(java.lang.Object)
	 */
	@GET
	@Path(value = "/{identifier}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Entity getEntityById(@PathParam(value = "identifier") final Key identifier) {
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
	 * @see org.dejava.component.javaee.service.GenericService#getEntityByAttribute(java.lang.String,
	 *      java.lang.Object)
	 */
	@Override
	public Entity getEntityByAttribute(final String attributeName, final Object attributeValue) {
		// Tries to get the entity.
		return getBusinessService().getEntityByAttribute(attributeName, attributeValue);
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
