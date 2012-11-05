package org.dejava.service.accesscontrol.business.crendential;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.service.accesscontrol.dao.credential.PasswordDAO;
import org.dejava.service.accesscontrol.model.credential.Password;

/**
 * EJB service for password.
 */
@Remote
@Stateless(name = "AccessControl/PasswordService/remote")
public class PasswordService extends AbstractGenericService<Password, Integer> {

	/**
	 * The password DAO.
	 */
	@Inject
	private PasswordDAO passwordDAO;

	/**
	 * @see org.dejava.component.javaee.service.AbstractGenericService#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Password, Integer> getEntityDAO() {
		return passwordDAO;
	}

}
