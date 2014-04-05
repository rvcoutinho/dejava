package org.dejava.service.philanthropy.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.service.philanthropy.businessrule.party.PartyBusinessRuleSet;
import org.dejava.service.philanthropy.dao.party.PartyDAO;
import org.dejava.service.philanthropy.model.party.Party;
import org.dejava.service.philanthropy.model.party.Supporter;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Philanthropy party EJB component.
 */
@PhilanthropyCtx
@Stateless(name = "Component/Philanthropy/Party")
public class PartyComponent {

	/**
	 * Party DAO.
	 */
	@Inject
	@PhilanthropyCtx
	private PartyDAO partyDAO;

	/**
	 * Party business rule set.
	 */
	@Inject
	@PhilanthropyCtx
	private PartyBusinessRuleSet partyBusinessRuleSet;

	/**
	 * Creates a philanthropy party.
	 * 
	 * @param party
	 *            New philanthropy party.
	 * @return The new philanthropy party.
	 */
	public Party createParty(final Party party) {
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
	public Party updateParty(final Party party) {
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
	public Party getPartyByOriginalParty(final Integer originalPartyId) {
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
	public Party getOrAddSupporterByParty(final org.dejava.service.party.model.Party party) {
		// Tries to get the philanthropy party for the party id.
		Party philanthropyParty = getPartyByOriginalParty(party.getIdentifier());
		// If there is no philanthropy party yet.
		if (philanthropyParty == null) {
			// Creates and persists a new supporter for the party id.
			philanthropyParty = createParty(new Supporter(party));
		}
		// Returns the philanthropy party for the party.
		return philanthropyParty;
	}

}
