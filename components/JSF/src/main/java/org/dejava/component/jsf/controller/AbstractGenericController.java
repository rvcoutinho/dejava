package org.dejava.component.jsf.controller;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dejava.component.ejb.service.AbstractGenericService;
import org.dejava.component.ejb.service.GenericService;

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
	 * @see org.dejava.component.ejb.service.GenericService#addOrUpdate(java.lang.Object)
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
	 * @see org.dejava.component.ejb.service.GenericService#addOrUpdate(java.util.Collection)
	 */
	@PUT
	@Path(value = "/s")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Collection<Entity> addOrUpdate(final Collection<Entity> entities) {
		// Adds the entities.
		return getBusinessService().addOrUpdate(entities);
	}

	/**
	 * @see org.dejava.component.ejb.service.GenericService#remove(java.lang.Object)
	 */
	@DELETE
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Override
	public void remove(final Entity entity) {
		// Tries to remove the entity.
		getBusinessService().remove(entity);
	}

	/**
	 * @see org.dejava.component.ejb.service.GenericService#remove(java.util.Collection)
	 */
	@DELETE
	@Path(value = "/s")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Override
	public void remove(final Collection<Entity> entities) {
		// Tries to remove the entities.
		getBusinessService().remove(entities);
	}

	/**
	 * @see org.dejava.component.ejb.service.GenericService#getById(java.lang.Object)
	 */
	@GET
	@Path(value = "/{identifier}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Entity getById(@PathParam(value = "identifier") final Key identifier) {
		// Tries to return the entity.
		return getBusinessService().getById(identifier);
	}

	/**
	 * @see org.dejava.component.ejb.service.GenericService#getByAttribute(java.lang.String,
	 *      java.lang.Object, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Collection<Entity> getByAttribute(final String attributeName, final Object attributeValue,
			final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return getBusinessService().getByAttribute(attributeName, attributeValue, firstResult,
				maxResults);
	}

	/**
	 * @see org.dejava.component.ejb.service.GenericService#getByAttribute(java.lang.String,
	 *      java.lang.Object)
	 */
	@Override
	public Entity getByAttribute(final String attributeName, final Object attributeValue) {
		// Tries to get the entity.
		return getBusinessService().getByAttribute(attributeName, attributeValue);
	}

	/**
	 * @see org.dejava.component.ejb.service.GenericService#getAll(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Collection<Entity> getAll(final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return getBusinessService().getAll(firstResult, maxResults);
	}

}