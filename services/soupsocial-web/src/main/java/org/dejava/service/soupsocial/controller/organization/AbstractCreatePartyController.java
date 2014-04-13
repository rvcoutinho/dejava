package org.dejava.service.soupsocial.controller.organization;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.dejava.component.i18n.message.handler.ApplicationMessageHandler;
import org.dejava.service.contact.model.EmailAddress;
import org.dejava.service.contact.model.PhoneNumber;
import org.dejava.service.party.model.Gender;
import org.dejava.service.party.util.MessageTypes;
import org.dejava.service.philanthropy.model.party.Party;
import org.dejava.service.place.component.PlaceComponent;
import org.dejava.service.place.util.PlaceCtx;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * The controller for creating new philanthropy parties.
 */
public abstract class AbstractCreatePartyController implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 7241295372952013780L;

	/**
	 * Application message handler.
	 */
	@Inject
	@SoupSocialCtx
	protected ApplicationMessageHandler messageHandler;

	/**
	 * The place EJB service.
	 */
	@Inject
	@PlaceCtx
	private PlaceComponent placeComponent;

	/**
	 * Gets the possible gender as a list of select items.
	 * 
	 * @return The possible gender as a list of select items
	 */
	public List<SelectItem> getGenderOptions() {
		// Creates a new list for the gender options.
		final List<SelectItem> genderOptions = new ArrayList<>();
		// Adds the empty option.
		genderOptions.add(new SelectItem(null, ""));
		// For each gender.
		for (final Gender currentGender : Gender.values()) {
			// Adds the current gender and its localized name in the select item list.
			genderOptions.add(new SelectItem(currentGender, messageHandler.getMessage(
					MessageTypes.Model.class, null, currentGender.toString(), null)));
		}
		// Returns the gender options as a list of select items.
		return genderOptions;
	}

	/**
	 * Gets the new party.
	 * 
	 * @return The new party.
	 */
	public abstract Party getParty();

	/**
	 * Gets the new phone number.
	 * 
	 * @return The new phone number.
	 */
	public EmailAddress getEmailAddress() {
		// If the organization has no phone number.
		if (getParty().getContacts(EmailAddress.class).isEmpty()) {
			// Creates a new phone number.
			final EmailAddress newEmailAddress = new EmailAddress();
			// Adds the email address to the organization contact set.
			getParty().getContacts().add(newEmailAddress);
		}
		// Returns the organization email address.
		return getParty().getContacts(EmailAddress.class).iterator().next();
	}

	/**
	 * Gets the new phone number.
	 * 
	 * @return The new phone number.
	 */
	public PhoneNumber getPhoneNumber() {
		// If the organization has no phone number.
		if (getParty().getContacts(PhoneNumber.class).isEmpty()) {
			// Creates a new phone number.
			final PhoneNumber newPhoneNumber = new PhoneNumber();
			// Adds the phone number to the organization contact set.
			getParty().getContacts().add(newPhoneNumber);
		}
		// Returns the organization phone number.
		return getParty().getContacts(PhoneNumber.class).iterator().next();
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
		getParty().setAddresses(null);
		// Sets the new address to the party.
		getParty().getAddresses().add(placeComponent.getPlaceByGoogleReference(getAddressReference()));
	}

}
