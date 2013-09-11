package org.dejava.service.philanthropy.component.party;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.philanthropy.dao.party.PhilanthropyPartyDAO;
import org.dejava.service.philanthropy.model.party.PhilanthropyParty;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Philanthropy party EJB component.
 */
@PhilanthropyCtx
@Stateless(name = "Component/Philanthropy/PhilanthropyParty")
public class PhilanthropyPartyComponent extends AbstractGenericComponent<PhilanthropyParty, Integer> {

	/**
	 * The philanthropy party DAO.
	 */
	@Inject
	@PhilanthropyCtx
	private PhilanthropyPartyDAO philanthropyPartyDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<PhilanthropyParty, Integer> getEntityDAO() {
		return philanthropyPartyDAO;
	}

}
