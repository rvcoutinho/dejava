package org.dejava.service.accesscontrol.component.principal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.accesscontrol.dao.principal.PrincipalDAO;
import org.dejava.service.accesscontrol.model.principal.Principal;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * EJB service for principal.
 */
@AccessControlCtx
@Stateless(name = "Component/AccessControl/Principal")
public class PrincipalComponent extends AbstractGenericComponent<Principal, Integer> {

	/**
	 * The principal DAO.
	 */
	@Inject
	@AccessControlCtx
	private PrincipalDAO principalDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Principal, Integer> getEntityDAO() {
		return principalDAO;
	}

}
