package org.dejava.service.location.controller;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.Path;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.jsf.controller.AbstractGenericController;
import org.dejava.service.location.component.LocationComponent;
import org.dejava.service.location.util.Location;

/**
 * Location front controller.
 */
@Model
@Path("/location")
public class LocationController extends AbstractGenericController<Location, Integer> {

	/**
	 * Location EJB service.
	 */
	@Inject
	@Location
	private LocationComponent locationComponent;

	/**
	 * @see org.dejava.component.jsf.controller.AbstractGenericController#getBusinessComponent()
	 */
	@Override
	protected AbstractGenericComponent<Location, Integer> getBusinessComponent() {
		return locationComponent;
	}
}
