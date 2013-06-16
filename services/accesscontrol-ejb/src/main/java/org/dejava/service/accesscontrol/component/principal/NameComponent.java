package org.dejava.service.accesscontrol.component.principal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.accesscontrol.dao.principal.NameDAO;
import org.dejava.service.accesscontrol.model.principal.Name;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * EJB service for name.
 */
@AccessControlCtx
@Stateless(name = "Component/AccessControl/Name")
public class NameComponent extends AbstractGenericComponent<Name, Integer> {

	/**
	 * The name DAO.
	 */
	@Inject
	@AccessControlCtx
	private NameDAO nameDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Name, Integer> getEntityDAO() {
		return nameDAO;
	}

}
