package org.dejava.service.soupsocial.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

import org.dejava.service.contact.model.EmailAddress;
import org.dejava.service.contact.model.PhoneNumber;
import org.dejava.service.party.component.PartyComponent;
import org.dejava.service.party.model.Organization;
import org.dejava.service.party.util.PartyCtx;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * The controller for creating new organizations.
 */
@Path("/party")
@RequestScoped
@Named("organizationController")
public class OrganizationController {

	/**
	 * Faces context.
	 */
	@Inject
	@SoupSocialCtx
	protected FacesContext facesContext;

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
	 * Creates the new organization.
	 */
	public void createOrganization() {
		// Creates the organization.
		partyComponent.addOrUpdate(getNewOrganization());
		// Adds a success message to the context. FIXME
		facesContext.addMessage(null, new FacesMessage("Mensagem de teste", "Detalhe da mensagem de teste"));
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

}
