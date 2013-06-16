package org.dejava.service.accesscontrol.component.principal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.accesscontrol.dao.principal.FacebookDAO;
import org.dejava.service.accesscontrol.model.principal.Facebook;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * EJB service for facebook.
 */
@AccessControlCtx
@Stateless(name = "Component/AccessControl/Facebook")
public class FacebookComponent extends AbstractGenericComponent<Facebook, Integer> {

	/**
	 * The facebook DAO.
	 */
	@Inject
	@AccessControlCtx
	private FacebookDAO facebookDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Facebook, Integer> getEntityDAO() {
		return facebookDAO;
	}

}
