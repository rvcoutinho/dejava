package org.dejava.service.contact.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.contact.dao.PhoneNumberDAO;
import org.dejava.service.contact.model.PhoneNumber;
import org.dejava.service.contact.util.ContactCtx;

/**
 * EJB component for the phone number.
 */
@ContactCtx
@Stateless(name = "Component/Contact/PhoneNumber")
public class PhoneNumberComponent extends AbstractGenericComponent<PhoneNumber, Integer> {

	/**
	 * The person DAO.
	 */
	@Inject
	@ContactCtx
	private PhoneNumberDAO emailAddressDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<PhoneNumber, Integer> getEntityDAO() {
		return emailAddressDAO;
	}

}
