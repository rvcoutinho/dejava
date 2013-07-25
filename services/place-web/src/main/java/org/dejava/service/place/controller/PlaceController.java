package org.dejava.service.place.controller;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.Path;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.faces.controller.AbstractGenericController;
import org.dejava.service.place.component.PlaceComponent;
import org.dejava.service.place.model.Place;
import org.dejava.service.place.util.PlaceCtx;

/**
 * Place front controller.
 */
@Model
@Path("/place")
public class PlaceController extends AbstractGenericController<Place, Integer> {

	/**
	 * Place EJB service.
	 */
	@Inject
	@PlaceCtx
	private PlaceComponent placeComponent;

	/**
	 * @see org.dejava.component.faces.controller.AbstractGenericController#getBusinessComponent()
	 */
	@Override
	protected AbstractGenericComponent<Place, Integer> getBusinessComponent() {
		return placeComponent;
	}
}
