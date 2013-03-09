package org.dejava.service.accesscontrol.business.principal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.service.accesscontrol.dao.principal.FacebookDAO;
import org.dejava.service.accesscontrol.model.principal.Facebook;
import org.dejava.service.accesscontrol.util.AccessControl;

/**
 * EJB service for facebook.
 */
@AccessControl
@Stateless(name = "Service/AccessControl/FacebookService")
public class FacebookService extends AbstractGenericService<Facebook, Integer> {

	/**
	 * The facebook DAO.
	 */
	@Inject
	@AccessControl
	private FacebookDAO facebookDAO;

	/**
	 * @see org.dejava.component.javaee.service.AbstractGenericService#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Facebook, Integer> getEntityDAO() {
		return facebookDAO;
	}

}
