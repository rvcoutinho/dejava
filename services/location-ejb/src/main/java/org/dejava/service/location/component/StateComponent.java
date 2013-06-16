package org.dejava.service.location.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.location.dao.StateDAO;
import org.dejava.service.location.model.State;
import org.dejava.service.location.util.LocationCtx;

/**
 * EJB service for state.
 */
@LocationCtx
@Stateless(name = "Component/Location/State")
public class StateComponent extends AbstractGenericComponent<State, Integer> {

	/**
	 * The state DAO.
	 */
	@Inject
	@LocationCtx
	private StateDAO stateDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<State, Integer> getEntityDAO() {
		return stateDAO;
	}

}
