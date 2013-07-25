package org.dejava.service.soupsocial.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.Properties;

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
import org.dejava.service.soupsocial.constant.GooglePlacesAPIKeys;
import org.dejava.service.soupsocial.place.GooglePlaceResult;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;

/**
 * The controller for creating new organizations.
 */
@Path("/party")
@RequestScoped
@Named("organizationController")
public class OrganizationController {

	/**
	 * The path for the google place properties file.
	 */
	private static final String PLACE_PROPERTIES_PATH = "org/dejava/service/soupsocial/properties/google-places-api_.properties";

	/**
	 * Gets the application properties object (from file).
	 * 
	 * @return The application properties object (from file).
	 */
	private static Properties getAppProperties() {
		// Gets the current thread class loader.
		final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		// Creates a new properties object.
		final Properties appProperties = new Properties();
		// Tries to load the properties content.
		try {
			appProperties.load(contextClassLoader.getResourceAsStream(PLACE_PROPERTIES_PATH));
		}
		// If an exception is raised.
		catch (final Exception exception) {
			// Throws an exception. TODO
			throw new InvalidParameterException();
		}
		// Returns the new properties object.
		return appProperties;
	}

	/**
	 * The google place properties file.
	 */
	private static final Properties PLACE_PROPERTIES = getAppProperties();

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
	 * Gets the place search customized URL.
	 * 
	 * @return The place search customized URL.
	 * @throws UnsupportedEncodingException
	 *             If the named encoding is not supported .
	 */
	private String getPlaceSearchURL() throws UnsupportedEncodingException {
		// Gets the initial URL.
		final StringBuffer url = new StringBuffer(
				PLACE_PROPERTIES.getProperty(GooglePlacesAPIKeys.PLACE_DETAILS_URL));
		// Appends the key to URL.
		url.append("key=" + PLACE_PROPERTIES.getProperty(GooglePlacesAPIKeys.API_KEY));
		// Appends the place reference.
		url.append("&reference=" + getAddressReference());
		// Appends the sensor value.
		url.append("&sensor=false");
		// Returns the URL.
		return url.toString();
	}

	/**
	 * Updates the party address details for a given reference.
	 * 
	 * @throws IOException
	 *             If the google place URL cannot be processed. FIXME
	 */
	public void updateAddressDetails() throws IOException {
		// Gets the URL google place detail.
		final URL placeURL = new URL(getPlaceSearchURL());
		// Parses the result into a google place result.
		final GooglePlaceResult placeResult = new JsonObjectParser(new JacksonFactory()).parseAndClose(
				new InputStreamReader(placeURL.openStream()), GooglePlaceResult.class);
		// Removes previous addresses.
		getNewOrganization().setAddresses(null);
		// Sets the new address to the party.
		getNewOrganization().getAddresses().add(placeResult.getPlace());
	}

	/**
	 * Creates the new organization.
	 * 
	 * @throws IOException
	 *             If the google place URL cannot be processed. FIXME
	 */
	public void createOrganization() throws IOException {
		// Updates the party address.
		updateAddressDetails();
		// Creates the organization.
		partyComponent.addOrUpdate(getNewOrganization());
		// Adds a success message to the context. FIXME
		facesContext.addMessage(null, new FacesMessage("Mensagem de teste", "Detalhe da mensagem de teste"));
	}
}
