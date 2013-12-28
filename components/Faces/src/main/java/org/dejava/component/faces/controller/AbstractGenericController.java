package org.dejava.component.faces.controller;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dejava.component.ejb.component.AbstractGenericComponent;

/**
 * Implements the default behavior of an entity JSF controller (including JAX-RS pre-configuration).
 * 
 * @param <Entity>
 *            Any entity.
 * @param <Key>
 *            Key of the entity.
 */
public abstract class AbstractGenericController<Entity, Key> implements GenericController<Entity, Key> {

	/**
	 * Gets the business component related to the entity.
	 * 
	 * @return The business component related to the entity.
	 */
	protected abstract AbstractGenericComponent<Entity, Key> getBusinessComponent();

	/**
	 * @see org.dejava.component.faces.controller.GenericController#addOrUpdate(java.lang.Object)
	 */
	@PUT
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Entity addOrUpdate(final Entity entity) {
		// Adds the entity.
		return getBusinessComponent().addOrUpdate(entity);
	}

	/**
	 * @see org.dejava.component.faces.controller.GenericController#addOrUpdate(java.util.Collection)
	 */
	@PUT
	@Path(value = "/s")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Collection<Entity> addOrUpdate(final Collection<Entity> entities) {
		// Adds the entities.
		return getBusinessComponent().addOrUpdate(entities);
	}

	/**
	 * @see org.dejava.component.faces.controller.GenericController#remove(java.lang.Object)
	 */
	@DELETE
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Override
	public void remove(final Entity entity) {
		// Tries to remove the entity.
		getBusinessComponent().remove(entity);
	}

	/**
	 * @see org.dejava.component.faces.controller.GenericController#remove(java.util.Collection)
	 */
	@DELETE
	@Path(value = "/s")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Override
	public void remove(final Collection<Entity> entities) {
		// Tries to remove the entities.
		getBusinessComponent().remove(entities);
	}

	/**
	 * @see org.dejava.component.faces.controller.GenericController#getById(java.lang.Object)
	 */
	@GET
	@Path(value = "/{identifier}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Entity getById(@PathParam(value = "identifier") final Key identifier) {
		// Tries to return the entity.
		return getBusinessComponent().getById(identifier);
	}

	/**
	 * @see org.dejava.component.faces.controller.GenericController#getAll(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Override
	public Collection<Entity> getAll(final Integer firstResult, final Integer maxResults) {
		// Tries to get the entities.
		return getBusinessComponent().getAll(firstResult, maxResults);
	}

}
