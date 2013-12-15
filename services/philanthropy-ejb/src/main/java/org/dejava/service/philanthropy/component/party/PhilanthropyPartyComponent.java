package org.dejava.service.philanthropy.component.party;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.party.model.Party;
import org.dejava.service.philanthropy.dao.party.PhilanthropyPartyDAO;
import org.dejava.service.philanthropy.model.party.PhilanthropyParty;
import org.dejava.service.philanthropy.model.party.Supporter;
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

	/**
	 * Gets the philanthropy party by the party. If there is no philanthropy party for the party yet, a new
	 * supporter is created and returned.
	 * 
	 * @param party
	 *            The original party.
	 * @return The philanthropy party by the party.
	 */
	public PhilanthropyParty getPhilanthropyPartyByParty(final Party party) {
		// Tries to get the philanthropy party for the party id.
		PhilanthropyParty philanthropyParty = getByAttribute("partyId", party.getIdentifier());
		// If there is no philanthropy party yet.
		if (philanthropyParty == null) {
			// Creates and persists a new supporter for the party id.
			philanthropyParty = addOrUpdate(new Supporter(party));
		}
		// Returns the philanthropy party for the party.
		return philanthropyParty;
	}

}
