package org.dejava.service.photo.component;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.businessrule.GenericEntityBusinessRuleSet;
import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.photo.businessrule.PhotoBusinessRuleSet;
import org.dejava.service.photo.dao.PhotoDAO;
import org.dejava.service.photo.model.Photo;
import org.dejava.service.photo.util.PhotoCtx;

/**
 * EJB component for the email address.
 */
@PhotoCtx
@Stateless(name = "Component/Photo/Photo")
public class PhotoComponent extends AbstractGenericComponent<Photo, Integer> {

	/**
	 * The photo DAO.
	 */
	@Inject
	@PhotoCtx
	private PhotoDAO photoDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Photo, Integer> getEntityDAO() {
		return photoDAO;
	}

	/**
	 * The application message business rule set.
	 */
	@Inject
	@PhotoCtx
	private PhotoBusinessRuleSet photoBusinessRuleSet;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityBusinessRuleSet()
	 */
	@Override
	public GenericEntityBusinessRuleSet<Photo> getEntityBusinessRuleSet() {
		return photoBusinessRuleSet;
	}

	/**
	 * Gets the photo by the party identifier.
	 * 
	 * @param partyId
	 *            The party identifier.
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return The photo by the party identifier.
	 */
	public Collection<Photo> getByPartyId(final Integer partyId, final Integer firstResult,
			final Integer maxResults) {
		return getEntityDAO().getByAttribute("partyId", partyId, firstResult, maxResults);
	}

}
