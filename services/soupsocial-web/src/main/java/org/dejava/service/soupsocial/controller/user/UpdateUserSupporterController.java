package org.dejava.service.soupsocial.controller.user;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.dejava.service.accesscontrol.interceptor.Secured;
import org.dejava.service.accesscontrol.interceptor.SuppressSecurityExceptions;
import org.dejava.service.contact.model.EmailAddress;
import org.dejava.service.contact.model.PhoneNumber;
import org.dejava.service.philanthropy.component.PartyTaskComponent;
import org.dejava.service.philanthropy.model.party.Supporter;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;
import org.dejava.service.soupsocial.controller.organization.AbstractCreatePartyController;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * Party controller.
 */
@SoupSocialCtx
@RequestScoped
@Named("updateUserSupporterController")
public class UpdateUserSupporterController extends AbstractCreatePartyController {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 8805533362617587217L;

	/**
	 * The user controller.
	 */
	@Inject
	@SoupSocialCtx
	private UserController userController;

	/**
	 * @see org.dejava.service.soupsocial.controller.organization.AbstractCreatePartyController#getParty()
	 */
	@Override
	@Secured
	@RequiresUser
	@SuppressSecurityExceptions
	public Supporter getParty() {
		return (Supporter) userController.getPhilanthropyParty();
	}

	/**
	 * @see org.dejava.service.soupsocial.controller.organization.AbstractCreatePartyController#getEmailAddress()
	 */
	@Override
	@Secured
	@RequiresUser
	@SuppressSecurityExceptions
	public EmailAddress getEmailAddress() {
		return super.getEmailAddress();
	}

	/**
	 * @see org.dejava.service.soupsocial.controller.organization.AbstractCreatePartyController#getPhoneNumber()
	 */
	@Override
	@Secured
	@RequiresUser
	@SuppressSecurityExceptions
	public PhoneNumber getPhoneNumber() {
		return super.getPhoneNumber();
	}

	/**
	 * Philanthropy party component.
	 */
	@Inject
	@PhilanthropyCtx
	private PartyTaskComponent partyComponent;

	/**
	 * Updates the supporter info.
	 * 
	 * @throws IOException
	 *             TODO
	 */
	public void updateSupporter() throws IOException {
		// Updates the party address.
		updateAddressDetails();
		// Creates the new party.
		// partyComponent.updateParty(getParty());
	}

}
