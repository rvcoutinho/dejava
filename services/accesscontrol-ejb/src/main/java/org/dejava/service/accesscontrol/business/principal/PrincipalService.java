package org.dejava.service.accesscontrol.business.principal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.service.accesscontrol.dao.principal.PrincipalDAO;
import org.dejava.service.accesscontrol.model.principal.Principal;
import org.dejava.service.accesscontrol.util.AccessControl;

/**
 * EJB service for principal.
 */
@AccessControl
@Stateless(name = "Service/AccessControl/PrincipalService")
public class PrincipalService extends AbstractGenericService<Principal, Integer> {

	/**
	 * The principal DAO.
	 */
	@Inject
	@AccessControl
	private PrincipalDAO principalDAO;

	/**
	 * @see org.dejava.component.javaee.service.AbstractGenericService#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Principal, Integer> getEntityDAO() {
		return principalDAO;
	}

}
