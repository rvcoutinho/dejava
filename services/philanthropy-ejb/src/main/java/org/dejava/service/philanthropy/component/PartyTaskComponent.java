package org.dejava.service.philanthropy.component;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.dejava.component.ejb.constant.DAOParamKeys;
import org.dejava.component.validation.method.PreConditions;
import org.dejava.service.accesscontrol.component.UserAuthorizationComponent;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.principal.Principal;
import org.dejava.service.accesscontrol.util.AccessControlCtx;
import org.dejava.service.party.util.PartyCtx;
import org.dejava.service.philanthropy.model.party.Party;
import org.dejava.service.philanthropy.model.party.Supporter;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;

/**
 * Philanthropy party coreography EJB component.
 */
@PhilanthropyCtx
@Stateless(name = "Component/Philanthropy/PartyTask")
public class PartyTaskComponent {

	/**
	 * Party task component.
	 */
	@Inject
	@PartyCtx
	private org.dejava.service.party.component.PartyTaskComponent partyTaskComponent;

	/**
	 * Philanthropy party component.
	 */
	@Inject
	@PhilanthropyCtx
	private PartyComponent philanthropyPartyComponent;

	/**
	 * User authorization component.
	 */
	@Inject
	@AccessControlCtx
	private UserAuthorizationComponent userAuthorizationComponent;

	/**
	 * Adds the administrator permissions for a party being created.
	 * 
	 * @param party
	 *            The party to add the administrator to.
	 */
	private void addPartyAdminPermissions(final Party party) {
		// If the party is a supporter.
		if (!(party instanceof Supporter)) {
			// Gets the current user (subject).
			final Subject subject = SecurityUtils.getSubject();
			// If there is a current user.
			if ((subject.isAuthenticated()) || (subject.isRemembered())) {
				// Gets the actual user.
				final User user = ((Principal) subject.getPrincipals().getPrimaryPrincipal()).getUser();
				// Adds the new user permission.
				partyTaskComponent.addPartyAdminPermissions(party.getParty().getIdentifier(),
						user.getIdentifier());
			}
		}
	}

	/**
	 * Creates a philanthropy party (and its underlying original party).
	 * 
	 * @param party
	 *            New philanthropy party.
	 * @return The new philanthropy party.
	 */
	public Party createParty(final Party party) {
		// Asserts that the entity is not null.
		PreConditions.assertParamNotNull(DAOParamKeys.ENTITY, party);
		// If the party has an original party.
		if (party.getParty() != null) {
			// Adds the original party for the party.
			party.setParty(partyTaskComponent.createPartyWithContactsAndAddresses(party.getParty()));
		}
		// Persists the party.
		final Party newParty = philanthropyPartyComponent.createParty(party);
		// Adds the administrator permissions for a party being created.
		addPartyAdminPermissions(newParty);
		// Persists and returns the party.
		return newParty;
	}

	/**
	 * Gets the parties administered by a given user.
	 * 
	 * @param userId
	 *            The user identifier.
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return The parties administered by a given user.
	 */
	public Collection<Party> getPartiesAdministeredByUser(final Integer userId, final Integer firstResult,
			final Integer maxResults) {
		// Creates the party list to be returned.
		final Collection<Party> parties = new ArrayList<>();
		// For each party administered by the user.
		for (final Integer currentPartyId : partyTaskComponent.getPartiesAdministeredByUser(userId,
				firstResult, maxResults)) {
			// Gets the current party for the id and adds it in the final list.
			parties.add(philanthropyPartyComponent.getPartyByOriginalParty(currentPartyId));
		}
		// Returns the parties administered by the given user.
		return parties;
	}

	/**
	 * Gets the parties administered by a given user.
	 * 
	 * @param <PartyType>
	 *            Any philanthropy party subtype.
	 * @param userId
	 *            The user identifier.
	 * @param partyType
	 *            The party type to be considered.
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return The parties administered by a given user.
	 */
	public <PartyType extends Party> Collection<PartyType> getPartiesAdministeredByUser(final Integer userId,
			final Class<PartyType> partyType, final Integer firstResult, final Integer maxResults) {
		// Gets the parties administered by the given user.
		final Collection<Party> allParties = getPartiesAdministeredByUser(userId, firstResult, maxResults);
		// Creates the party list to be returned.
		final Collection<PartyType> partiesWithType = new ArrayList<>();
		// For each party administered by the given user.
		for (final Party currentParty : allParties) {
			// If the current party is an instance of the given type.
			if (partyType.isAssignableFrom(currentParty.getClass())) {
				// Adds the current party to the final list.
				partiesWithType.add((PartyType) currentParty);
			}
		}
		// Returns the parties administered by the given user (and type).
		return partiesWithType;
	}

}
