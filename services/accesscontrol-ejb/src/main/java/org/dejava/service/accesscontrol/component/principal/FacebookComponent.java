package org.dejava.service.accesscontrol.component.principal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.accesscontrol.dao.principal.FacebookDAO;
import org.dejava.service.accesscontrol.model.principal.Facebook;
import org.dejava.service.accesscontrol.util.AccessControl;

/**
 * EJB service for facebook.
 */
@AccessControl
@Stateless(name = "Component/AccessControl/Facebook")
public class FacebookComponent extends AbstractGenericComponent<Facebook, Integer> {

	/**
	 * The facebook DAO.
	 */
	@Inject
	@AccessControl
	private FacebookDAO facebookDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Facebook, Integer> getEntityDAO() {
		return facebookDAO;
	}

}
