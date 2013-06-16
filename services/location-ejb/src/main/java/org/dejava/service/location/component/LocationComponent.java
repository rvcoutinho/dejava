package org.dejava.service.location.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.location.dao.LocationDAO;
import org.dejava.service.location.model.Location;
import org.dejava.service.location.util.LocationCtx;

/**
 * EJB service for location.
 */
@LocationCtx
@Stateless(name = "Component/Location/Location")
public class LocationComponent extends AbstractGenericComponent<Location, Integer> {

	/**
	 * The location DAO.
	 */
	@Inject
	@LocationCtx
	private LocationDAO locationDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Location, Integer> getEntityDAO() {
		return locationDAO;
	}

}
