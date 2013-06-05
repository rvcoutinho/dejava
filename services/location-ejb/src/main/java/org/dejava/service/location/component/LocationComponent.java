package org.dejava.service.location.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.service.location.dao.LocationDAO;
import org.dejava.service.location.util.Location;

/**
 * EJB service for location.
 */
@Location
@Stateless(name = "Component/Location/Location")
public class LocationComponent extends AbstractGenericComponent<Location, Integer> {

	/**
	 * The location DAO.
	 */
	@Inject
	@Location
	private LocationDAO locationDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Location, Integer> getEntityDAO() {
		return locationDAO;
	}

}
