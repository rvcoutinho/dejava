package org.dejava.service.philanthropy.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.service.party.model.Party;
import org.dejava.service.philanthropy.businessrule.party.PhilanthropyPartyBusinessRuleSet;
import org.dejava.service.philanthropy.dao.party.PhilanthropyPartyDAO;
import org.dejava.service.philanthropy.model.party.PhilanthropyParty;
import org.dejava.service.philanthropy.model.party.Supporter;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Philanthropy party EJB component.
 */
@PhilanthropyCtx
@Stateless(name = "Component/Philanthropy/PhilanthropyParty")
public class PhilanthropyPartyComponent {

	/**
	 * Party DAO.
	 */
	@Inject
	@PhilanthropyCtx
	private PhilanthropyPartyDAO partyDAO;

	/**
	 * Party business rule set.
	 */
	@Inject
	@PhilanthropyCtx
	private PhilanthropyPartyBusinessRuleSet partyBusinessRuleSet;

	/**
	 * Creates a philanthropy party.
	 * 
	 * @param party
	 *            New philanthropy party.
	 * @return The new philanthropy party.
	 */
	public PhilanthropyParty createParty(final PhilanthropyParty party) {
		// Validates the party to be added.
		partyBusinessRuleSet.validate(party);
		// Adds persists the new party.
		return partyDAO.merge(party);
	}

	/**
	 * Updetes a philanthropy party.
	 * 
	 * @param party
	 *            Philanthropy party.
	 * @return The updated philanthropy party.
	 */
	public PhilanthropyParty updateParty(final PhilanthropyParty party) {
		// Validates the party to be added.
		partyBusinessRuleSet.validate(party);
		// Adds persists the new party.
		return partyDAO.merge(party);
	}

	/**
	 * Gets a philanthropy party by its original party identifier.
	 * 
	 * @param originalPartyId
	 *            The original paty identifier.
	 * @return A philanthropy party by its original party identifier.
	 */
	public PhilanthropyParty getPartyByOriginalParty(final Integer originalPartyId) {
		return partyDAO.getByAttribute("partyId", originalPartyId);
	}

	/**
	 * Gets the philanthropy party by the party. If there is no supporter for the party yet, a new supporter
	 * is created and returned.
	 * 
	 * @param party
	 *            The original party.
	 * @return The philanthropy party by the party.
	 */
	public PhilanthropyParty getOrAddSupporterByParty(final Party party) {
		// Tries to get the philanthropy party for the party id.
		PhilanthropyParty philanthropyParty = getPartyByOriginalParty(party.getIdentifier());
		// If there is no philanthropy party yet.
		if (philanthropyParty == null) {
			// Creates and persists a new supporter for the party id.
			philanthropyParty = createParty(new Supporter(party));
		}
		// Returns the philanthropy party for the party.
		return philanthropyParty;
	}

}
