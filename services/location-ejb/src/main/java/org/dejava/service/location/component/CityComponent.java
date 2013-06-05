package org.dejava.service.location.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.location.dao.CityDAO;
import org.dejava.service.location.model.City;
import org.dejava.service.location.util.Location;

/**
 * EJB service for city.
 */
@Location
@Stateless(name = "Component/Location/City")
public class CityComponent extends AbstractGenericComponent<City, Integer> {

	/**
	 * The city DAO.
	 */
	@Inject
	@Location
	private CityDAO cityDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<City, Integer> getEntityDAO() {
		return cityDAO;
	}

}
