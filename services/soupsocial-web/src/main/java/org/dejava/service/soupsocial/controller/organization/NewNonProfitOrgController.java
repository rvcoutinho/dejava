package org.dejava.service.soupsocial.controller.organization;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.dejava.service.party.model.Organization;
import org.dejava.service.philanthropy.component.PhilanthropyPartyTaskComponent;
import org.dejava.service.philanthropy.model.party.NonProfitOrg;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * The controller for creating new non-profit organization.
 */
@ConversationScoped
@SoupSocialCtx
@Named("newNonProfitOrgController")
public class NewNonProfitOrgController extends AbstractPhilanthropyPartyController implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 6231322501346946737L;

	/**
	 * New party.
	 */
	private NonProfitOrg newParty;

	/**
	 * @see org.dejava.service.soupsocial.controller.organization.AbstractPhilanthropyPartyController#getParty()
	 */
	@Override
	public NonProfitOrg getParty() {
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
	private PhilanthropyPartyTaskComponent partyComponent;

	/**
	 * Creates a new non profit organization.
	 * 
	 * @throws IOException
	 *             TODO
	 */
	public void createNonProfitOrg() throws IOException {
		// Updates the party address.
		updateAddressDetails();
		// Creates the new party.
		partyComponent.createPhilanthropyParty(getParty());
	}
}
