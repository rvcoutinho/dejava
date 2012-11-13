package org.dejava.service.accesscontrol.business.principal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.service.accesscontrol.dao.principal.NameDAO;
import org.dejava.service.accesscontrol.model.principal.Name;

/**
 * EJB service for name.
 */
@Stateless
public class NameService extends AbstractGenericService<Name, Integer> {

	/**
	 * The name DAO.
	 */
	@Inject
	private NameDAO nameDAO;

	/**
	 * @see org.dejava.component.javaee.service.AbstractGenericService#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Name, Integer> getEntityDAO() {
		return nameDAO;
	}

}
