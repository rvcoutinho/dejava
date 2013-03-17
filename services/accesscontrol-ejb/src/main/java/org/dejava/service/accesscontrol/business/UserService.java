package org.dejava.service.accesscontrol.business;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.service.accesscontrol.dao.UserDAO;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.util.AccessControl;

/**
 * EJB service for user.
 */
@AccessControl
@Stateless(name = "Service/AccessControl/UserService")
public class UserService extends AbstractGenericService<User, Integer> {

	/**
	 * The user DAO.
	 */
	@Inject
	@AccessControl
	private UserDAO userDAO;

	/**
	 * @see org.dejava.component.javaee.service.AbstractGenericService#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<User, Integer> getEntityDAO() {
		return userDAO;
	}

	/**
	 * TODO
	 * 
	 * @param fbUser
	 *            TODO
	 * @return TODO
	 */
	public User getByFacebookUser(com.restfb.types.User fbUser) {

		if (getByAttribute("identifier", fbUser.getId()) == null) {

		}
	}
}
