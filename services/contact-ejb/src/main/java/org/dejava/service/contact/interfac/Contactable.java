package org.dejava.service.contact.interfac;

import java.util.Collection;

import org.dejava.service.contact.model.Contact;

/**
 * Something that is contactable.
 */
public interface Contactable {

	/**
	 * Gets the contacts.
	 * 
	 * @return The contacts.
	 */
	public Collection<Contact> getContacts();

	/**
	 * Gets the contacts for the given type.
	 * 
	 * @param contactType
	 *            Any contact type
	 * @param <ConcreteContact>
	 *            A concrete contact type.
	 * @return The contacts for the given type.
	 */
	public <ConcreteContact extends Contact> Collection<ConcreteContact> getContacts(
			Class<ConcreteContact> contactType);

	/**
	 * Sets the contacts.
	 * 
	 * @param contacts
	 *            New contacts.
	 */
	public void setContacts(Collection<Contact> contacts);

}