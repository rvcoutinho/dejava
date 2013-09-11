package org.dejava.service.place.component;

import java.io.IOException;

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

	/**
	 * Gets the place from the google reference.
	 * 
	 * @param placeReference
	 *            The place reference.
	 * @return The place from the google reference.
	 * @throws IOException
	 *             If the google place URL cannot be processed. FIXME
	 */
	public Place getByGoogleReference(final String placeReference) throws IOException {
		return placeDAO.getByGoogleReference(placeReference);
	}

}
