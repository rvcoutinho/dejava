package org.dejava.service.location.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.location.dao.NeighborhoodDAO;
import org.dejava.service.location.model.Neighborhood;
import org.dejava.service.location.util.LocationCtx;

/**
 * EJB service for neighborhood.
 */
@LocationCtx
@Stateless(name = "Component/Location/Neighborhood")
public class NeighborhoodComponent extends AbstractGenericComponent<Neighborhood, Integer> {

	/**
	 * The neighborhood DAO.
	 */
	@Inject
	@LocationCtx
	private NeighborhoodDAO neighborhoodDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Neighborhood, Integer> getEntityDAO() {
		return neighborhoodDAO;
	}

}
