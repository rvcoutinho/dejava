package org.dejava.service.contact.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.contact.dao.EmailAddressDAO;
import org.dejava.service.contact.model.EmailAddress;
import org.dejava.service.contact.util.ContactCtx;

/**
 * EJB component for the email address.
 */
@ContactCtx
@Stateless(name = "Component/Contact/EmailAddress")
public class EmailAddressComponent extends AbstractGenericComponent<EmailAddress, Integer> {

	/**
	 * The person DAO.
	 */
	@Inject
	@ContactCtx
	private EmailAddressDAO emailAddressDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<EmailAddress, Integer> getEntityDAO() {
		return emailAddressDAO;
	}

}
