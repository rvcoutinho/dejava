package org.dejava.service.party.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.party.dao.PartyDAO;
import org.dejava.service.party.model.Party;
import org.dejava.service.party.util.PartyCtx;

/**
 * Party EJB component.
 */
@PartyCtx
@Stateless(name = "Component/Party/Party")
public class PartyComponent extends AbstractGenericComponent<Party, Integer> {

	/**
	 * The party DAO.
	 */
	@Inject
	@PartyCtx
	private PartyDAO partyDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Party, Integer> getEntityDAO() {
		return partyDAO;
	}

}
