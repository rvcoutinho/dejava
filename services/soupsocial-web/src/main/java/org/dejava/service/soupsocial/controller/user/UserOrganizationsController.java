package org.dejava.service.soupsocial.controller.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.dejava.service.philanthropy.component.PartyTaskComponent;
import org.dejava.service.philanthropy.model.party.NonProfitOrg;
import org.dejava.service.philanthropy.model.party.Sponsor;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * User (philanthropy) organizations controller.
 */
@SoupSocialCtx
@RequestScoped
@Named("userOrganizationsController")
public class UserOrganizationsController {

	/**
	 * The user controller.
	 */
	@Inject
	@SoupSocialCtx
	private UserController userController;

	/**
	 * Philanthropy party component.
	 */
	@Inject
	@PhilanthropyCtx
	private PartyTaskComponent partyComponent;

	/**
	 * Non-profit organizations administered by the user.
	 */
	private Collection<NonProfitOrg> nonProfitOrgs;

	/**
	 * Gets the sponsor organizations administered by the user.
	 * 
	 * @return The sponsor organizations administered by the user.
	 */
	public Collection<NonProfitOrg> getNonProfitOrgs() {
		// If the organization list is null.
		if (nonProfitOrgs == null) {
			// If the user is logged or remembered.
			if ((userController.getSubject().isAuthenticated())
					|| (userController.getSubject().isRemembered())) {
				// Gets the non-profit organizations administered by the user.
				nonProfitOrgs = partyComponent.getPartiesAdministeredByUser(userController.getCurrentUser()
						.getIdentifier(), NonProfitOrg.class, null, null);
			}
			// If the user is not logged or remembered.
			else {
				// Resets the list with an empty one.
				nonProfitOrgs = new ArrayList<>();
			}
		}
		// Returns the organization list.
		return nonProfitOrgs;
	}

	/**
	 * Sponsor organizations administered by the user.
	 */
	private Collection<Sponsor> sponsors;

	/**
	 * Gets the sponsor organizations administered by the user.
	 * 
	 * @return The sponsor organizations administered by the user.
	 */
	public Collection<Sponsor> getSponsors() {
		// If the organization list is null.
		if (sponsors == null) {
			// If the user is logged or remembered.
			if ((userController.getSubject().isAuthenticated())
					|| (userController.getSubject().isRemembered())) {
				// Gets the sponsor organizations administered by the user.
				sponsors = partyComponent.getPartiesAdministeredByUser(userController.getCurrentUser()
						.getIdentifier(), Sponsor.class, null, null);
			}
			// If the user is not logged or remembered.
			else {
				// Resets the list with an empty one.
				sponsors = new ArrayList<>();
			}
		}
		// Returns the organization list.
		return sponsors;
	}
}
