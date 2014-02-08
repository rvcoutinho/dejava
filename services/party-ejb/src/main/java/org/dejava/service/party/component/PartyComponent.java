package org.dejava.service.party.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.service.party.businessrule.PartyBusinessRuleSet;
import org.dejava.service.party.dao.PartyDAO;
import org.dejava.service.party.model.Party;
import org.dejava.service.party.util.PartyCtx;

/**
 * Party EJB component.
 */
@PartyCtx
@Stateless(name = "Component/Party/Party")
public class PartyComponent {

	/**
	 * The party DAO.
	 */
	@Inject
	@PartyCtx
	private PartyDAO partyDAO;

	/**
	 * The party business rule set.
	 */
	@Inject
	@PartyCtx
	private PartyBusinessRuleSet partyBusinessRuleSet;

	/**
	 * Creates a party.
	 * 
	 * @param party
	 *            New party.
	 * @return The new party.
	 */
	public Party createParty(final Party party) {
		// Validates the party to be added.
		partyBusinessRuleSet.validate(party);
		// Adds the new party.
		return partyDAO.merge(party);
	}

	/**
	 * Updates a party simple info.
	 * 
	 * @param party
	 *            Party info.
	 * @return The update party.
	 */
	public Party updatePartySimpleInfo(final Party party) {
		// Validates the party to be added.
		partyBusinessRuleSet.validate(party);
		// Adds the new party.
		return partyDAO.merge(party);
	}

	/**
	 * Gets a party by its identifier.
	 * 
	 * @param identifier
	 *            The identifier of the for party.
	 * @return A person by its identifier.
	 */
	public Party getPartyById(final Integer identifier) {
		return partyDAO.getById(identifier);
	}

	/**
	 * Gets a party by its user identifier.
	 * 
	 * @param userId
	 *            The identifier of the user for party.
	 * @return A person by its user identifier.
	 */
	public Party getPartyByUser(final Integer userId) {
		return partyDAO.getByAttribute("user", userId);
	}

}
