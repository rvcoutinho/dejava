package org.dejava.service.accesscontrol.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.service.accesscontrol.dao.UserDAO;
import org.dejava.service.accesscontrol.model.User;

/**
 * EJB service for user.
 */
@Stateless
public class UserService extends AbstractGenericService<User, Integer> {

	/**
	 * The user DAO.
	 */
	@Inject
	private UserDAO userDAO;

	/**
	 * @see org.dejava.component.javaee.service.AbstractGenericService#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<User, Integer> getEntityDAO() {
		return userDAO;
	}

}
