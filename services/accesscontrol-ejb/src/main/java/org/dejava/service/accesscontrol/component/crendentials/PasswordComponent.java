package org.dejava.service.accesscontrol.component.crendentials;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.accesscontrol.dao.credentials.PasswordDAO;
import org.dejava.service.accesscontrol.model.credentials.Password;
import org.dejava.service.accesscontrol.util.AccessControl;

/**
 * EJB service for password.
 */
@AccessControl
@Stateless(name = "Component/AccessControl/Password")
public class PasswordComponent extends AbstractGenericComponent<Password, Integer> {

	/**
	 * The password DAO.
	 */
	@Inject
	@AccessControl
	private PasswordDAO passwordDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Password, Integer> getEntityDAO() {
		return passwordDAO;
	}

}
