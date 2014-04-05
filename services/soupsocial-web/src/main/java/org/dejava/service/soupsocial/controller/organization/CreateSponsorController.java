package org.dejava.service.soupsocial.controller.organization;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.dejava.service.party.model.Organization;
import org.dejava.service.philanthropy.component.PartyTaskComponent;
import org.dejava.service.philanthropy.model.party.Sponsor;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * The controller for creating new organizations.
 */
@ConversationScoped
@SoupSocialCtx
@Named("newSponsorController")
public class CreateSponsorController extends AbstractCreatePartyController implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 9205330772605622014L;

	/**
	 * New party.
	 */
	private Sponsor newParty;

	/**
	 * @see org.dejava.service.soupsocial.controller.organization.AbstractCreatePartyController#getParty()
	 */
	@Override
	public Sponsor getParty() {
		// If there no new party yet.
		if (newParty == null) {
			// Creates a new Sponsor.
			newParty = new Sponsor(new Organization());
		}
		// Returns the new party.
		return newParty;
	}

	/**
	 * Philanthropy party component.
	 */
	@Inject
	@PhilanthropyCtx
	private PartyTaskComponent partyComponent;

	/**
	 * Creates a new sponsor.
	 * 
	 * @throws IOException
	 *             TODO
	 */
	public void createSponsor() throws IOException {
		// Updates the party address.
		updateAddressDetails();
		// Creates the new party.
		partyComponent.createParty(getParty());
	}
}
