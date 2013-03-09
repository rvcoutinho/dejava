package org.dejava.service.accesscontrol.business.crendentials;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.service.accesscontrol.dao.credentials.PasswordDAO;
import org.dejava.service.accesscontrol.model.credentials.Password;
import org.dejava.service.accesscontrol.util.AccessControl;

/**
 * EJB service for password.
 */
@AccessControl
@Stateless(name = "Service/AccessControl/PasswordService")
public class PasswordService extends AbstractGenericService<Password, Integer> {

	/**
	 * The password DAO.
	 */
	@Inject
	@AccessControl
	private PasswordDAO passwordDAO;

	/**
	 * @see org.dejava.component.javaee.service.AbstractGenericService#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Password, Integer> getEntityDAO() {
		return passwordDAO;
	}

}
