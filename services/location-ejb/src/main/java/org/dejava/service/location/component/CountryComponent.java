package org.dejava.service.location.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.service.location.dao.CountryDAO;
import org.dejava.service.location.model.Country;
import org.dejava.service.location.util.Location;

/**
 * EJB service for country.
 */
@Location
@Stateless(name = "Component/Location/Country")
public class CountryComponent extends AbstractGenericComponent<Country, Integer> {

	/**
	 * The country DAO.
	 */
	@Inject
	@Location
	private CountryDAO countryDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Country, Integer> getEntityDAO() {
		return countryDAO;
	}

}
