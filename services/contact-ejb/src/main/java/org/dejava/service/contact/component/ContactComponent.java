package org.dejava.service.contact.component;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.businessrule.GenericEntityBusinessRuleSet;
import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.contact.businessrule.ContactBusinessRuleSet;
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
	 * The contact DAO.
	 */
	@Inject
	@ContactCtx
	private ContactDAO contactDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Contact, Integer> getEntityDAO() {
		return contactDAO;
	}

	/**
	 * The application message business rule set.
	 */
	@Inject
	@ContactCtx
	private ContactBusinessRuleSet contactBusinessRuleSet;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityBusinessRuleSet()
	 */
	@Override
	public GenericEntityBusinessRuleSet<Contact> getEntityBusinessRuleSet() {
		return contactBusinessRuleSet;
	}

	/**
	 * Gets the contact by the party identifier.
	 * 
	 * @param partyId
	 *            The party identifier.
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return The contact by the party identifier.
	 */
	public Collection<Contact> getByPartyId(final Integer partyId, final Integer firstResult,
			final Integer maxResults) {
		return getEntityDAO().getByAttribute("partyId", partyId, firstResult, maxResults);
	}

}
