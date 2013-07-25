package org.dejava.service.place.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.place.dao.PlaceDAO;
import org.dejava.service.place.model.Place;
import org.dejava.service.place.util.PlaceCtx;

/**
 * EJB service for place.
 */
@PlaceCtx
@Stateless(name = "Component/Place/Place")
public class PlaceComponent extends AbstractGenericComponent<Place, Integer> {

	/**
	 * The place DAO.
	 */
	@Inject
	@PlaceCtx
	private PlaceDAO placeDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Place, Integer> getEntityDAO() {
		return placeDAO;
	}

}
