package org.dejava.service.contact.component;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.service.contact.businessrule.ContactBusinessRuleSet;
import org.dejava.service.contact.dao.ContactDAO;
import org.dejava.service.contact.model.Contact;
import org.dejava.service.contact.util.ContactCtx;

/**
 * EJB component for contact.
 */
@ContactCtx
@Stateless(name = "Component/Contact/Contact")
public class ContactComponent {

	/**
	 * The contact DAO.
	 */
	@Inject
	@ContactCtx
	private ContactDAO contactDAO;

	/**
	 * The application message business rule set.
	 */
	@Inject
	@ContactCtx
	private ContactBusinessRuleSet contactBusinessRuleSet;

	/**
	 * Creates a new contact.
	 * 
	 * @param contact
	 *            The new contact.
	 * @return The created contact.
	 */
	public Contact createContact(final Contact contact) {
		// Validates the contact to be added.
		contactBusinessRuleSet.validate(contact);
		// Adds the new contact.
		return contactDAO.persist(contact);
	}

	/**
	 * Creates new contacts.
	 * 
	 * @param contacts
	 *            The new contacts.
	 * @return The created contacts.
	 */
	public Collection<Contact> createContacts(final Collection<Contact> contacts) {
		// Validates the contacts to be added.
		contactBusinessRuleSet.validate(contacts);
		// Adds the new contacts.
		return contactDAO.persist(contacts);
	}

	/**
	 * Gets the contact by the party identifier.
	 * 
	 * @param party
	 *            The party identifier.
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return The contact by the party identifier.
	 */
	public Collection<Contact> getContactByParty(final Integer party, final Integer firstResult,
			final Integer maxResults) {
		return contactDAO.getByAttribute("party", party, firstResult, maxResults);
	}

	/**
	 * Gets a contact by its identifier.
	 * 
	 * @param identifier
	 *            The identifier of the for contact.
	 * @return A person by its identifier.
	 */
	public Contact getContactById(final Integer identifier) {
		return contactDAO.getById(identifier);
	}

}
