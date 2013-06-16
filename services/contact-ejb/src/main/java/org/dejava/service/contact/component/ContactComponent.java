package org.dejava.service.contact.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.contact.dao.ContactDAO;
import org.dejava.service.contact.model.Contact;
import org.dejava.service.contact.util.ContactCtx;

/**
 * EJB component for the email address.
 */
@ContactCtx
@Stateless(name = "Component/Contact/Contact")
public class ContactComponent extends AbstractGenericComponent<Contact, Integer> {

	/**
	 * The person DAO.
	 */
	@Inject
	@ContactCtx
	private ContactDAO emailDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Contact, Integer> getEntityDAO() {
		return emailDAO;
	}

}
