package org.dejava.service.soupsocial.controller.organization;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.dejava.service.party.model.Organization;
import org.dejava.service.philanthropy.component.party.PhilanthropyPartyComponent;
import org.dejava.service.philanthropy.model.party.NonProfitOrg;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * The controller for creating new non-profit organization.
 */
@ConversationScoped
@SoupSocialCtx
@Named("newNonProfitOrgController")
public class NewNonProfitOrgController extends AbstractNewPhilanthropyPartyController implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 6231322501346946737L;

	/**
	 * New party.
	 */
	private NonProfitOrg newParty;

	/**
	 * @see org.dejava.service.soupsocial.controller.organization.AbstractNewPhilanthropyPartyController#getNewParty()
	 */
	@Override
	public NonProfitOrg getNewParty() {
		// If there no new party yet.
		if (newParty == null) {
			// Creates a new Sponsor.
			newParty = new NonProfitOrg(new Organization());
		}
		// Returns the new party.
		return newParty;
	}

	/**
	 * Philanthropy party component.
	 */
	@Inject
	@PhilanthropyCtx
	private PhilanthropyPartyComponent partyComponent;

	/**
	 * @see org.dejava.service.soupsocial.controller.organization.AbstractNewPhilanthropyPartyController#createParty()
	 */
	@Override
	public void createParty() throws IOException {
		// Updates the party address.
		updateAddressDetails();
		// Creates the new party.
		partyComponent.addOrUpdate(getNewParty());

	}
}
