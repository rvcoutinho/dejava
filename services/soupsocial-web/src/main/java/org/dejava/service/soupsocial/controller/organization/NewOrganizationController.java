package org.dejava.service.soupsocial.controller.organization;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.dejava.service.accesscontrol.interceptor.Secured;
import org.dejava.service.contact.model.EmailAddress;
import org.dejava.service.contact.model.PhoneNumber;
import org.dejava.service.party.component.PartyComponent;
import org.dejava.service.party.model.Organization;
import org.dejava.service.party.util.PartyCtx;
import org.dejava.service.place.component.PlaceComponent;
import org.dejava.service.place.util.PlaceCtx;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * The controller for creating new organizations.
 */
@ConversationScoped
@SoupSocialCtx
@Named("newOrganizationController")
public class NewOrganizationController implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 7241295372952013780L;

	/**
	 * Faces context.
	 */
	@Inject
	@SoupSocialCtx
	protected FacesContext facesContext;

	/**
	 * The place EJB service.
	 */
	@Inject
	@PlaceCtx
	private PlaceComponent placeComponent;

	/**
	 * The party EJB service.
	 */
	@Inject
	@PartyCtx
	private PartyComponent partyComponent;

	/**
	 * A new organization.
	 */
	private Organization newOrganization;

	/**
	 * Gets the new organization.
	 * 
	 * @return The new organization.
	 */
	public Organization getNewOrganization() {
		// If the organization is null.
		if (newOrganization == null) {
			// Creates a new organization.
			newOrganization = new Organization();
		}
		// Returns the organization.
		return newOrganization;
	}

	/**
	 * Sets the new organization.
	 * 
	 * @param newOrganization
	 *            New organization.
	 */
	public void setNewOrganization(final Organization newOrganization) {
		this.newOrganization = newOrganization;
	}

	/**
	 * Gets the new phone number.
	 * 
	 * @return The new phone number.
	 */
	public EmailAddress getNewEmailAddress() {
		// If the organization has no phone number.
		if (getNewOrganization().getContacts(EmailAddress.class).isEmpty()) {
			// Creates a new phone number.
			final EmailAddress newEmailAddress = new EmailAddress();
			// Adds the email address to the organization contact set.
			getNewOrganization().getContacts().add(newEmailAddress);
		}
		// Returns the organization email address.
		return getNewOrganization().getContacts(EmailAddress.class).iterator().next();
	}

	/**
	 * Gets the new phone number.
	 * 
	 * @return The new phone number.
	 */
	public PhoneNumber getNewPhoneNumber() {
		// If the organization has no phone number.
		if (getNewOrganization().getContacts(PhoneNumber.class).isEmpty()) {
			// Creates a new phone number.
			final PhoneNumber newPhoneNumber = new PhoneNumber();
			// Adds the phone number to the organization contact set.
			getNewOrganization().getContacts().add(newPhoneNumber);
		}
		// Returns the organization phone number.
		return getNewOrganization().getContacts(PhoneNumber.class).iterator().next();
	}

	/**
	 * The address reference.
	 */
	private String addressReference;

	/**
	 * Gets the address reference.
	 * 
	 * @return The address reference.
	 */
	public String getAddressReference() {
		return addressReference;
	}

	/**
	 * Sets the address reference.
	 * 
	 * @param addressReference
	 *            New address reference.
	 */
	public void setAddressReference(final String addressReference) {
		this.addressReference = addressReference;
	}

	/**
	 * Updates the party address details for a given reference.
	 * 
	 * @throws IOException
	 *             If the google place URL cannot be processed. FIXME
	 */
	public void updateAddressDetails() throws IOException {
		// Removes previous addresses.
		getNewOrganization().setAddresses(null);
		// Sets the new address to the party.
		getNewOrganization().getAddresses().add(placeComponent.getByGoogleReference(getAddressReference()));
	}

	/**
	 * Creates the new organization.
	 * 
	 * @throws IOException
	 *             If the google place URL cannot be processed. FIXME
	 */
	@Secured
	@RequiresAuthentication
	public void createOrganization() throws IOException {
		// Updates the party address.
		updateAddressDetails();
		// Creates the organization.
		partyComponent.addOrUpdate(getNewOrganization());
		// Adds a success message to the context. FIXME
		facesContext.addMessage(null, new FacesMessage("Mensagem de teste", "Detalhe da mensagem de teste"));
	}
}
