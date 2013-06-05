package org.dejava.service.accesscontrol.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.accesscontrol.dao.UserDAO;
import org.dejava.service.accesscontrol.dao.principal.EmailDAO;
import org.dejava.service.accesscontrol.dao.principal.FacebookDAO;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.principal.Principal;
import org.dejava.service.accesscontrol.util.AccessControl;

/**
 * EJB service for user.
 */
@AccessControl
@Stateless(name = "Component/AccessControl/User")
public class UserComponent extends AbstractGenericComponent<User, Integer> {

	/**
	 * The user DAO.
	 */
	@Inject
	@AccessControl
	private UserDAO userDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<User, Integer> getEntityDAO() {
		return userDAO;
	}

	/**
	 * The facebook principal DAO.
	 */
	@Inject
	@AccessControl
	private FacebookDAO facebookDAO;

	/**
	 * The email principal DAO.
	 */
	@Inject
	@AccessControl
	private EmailDAO emailDAO;

	/**
	 * Gets the the user by the facebook user.
	 * 
	 * @param fbUser
	 *            The facebook user.
	 * @return The user by the facebook user.
	 */
	public User getByFacebookUser(final com.restfb.types.User fbUser) {
		// Tries to get the facebook principal by the facebook user id.
		Principal principal = facebookDAO.getByAttribute("identifier", fbUser.getId());
		// If there is no user for the facebook id.
		if (principal == null) {
			// Tries to get the email principal by the facebook user email.
			principal = emailDAO.getByAttribute("email", fbUser.getEmail());
		}
		// If there is no user for the email either.
		if (principal == null) {
			// Returns null.
			return null;
		}
		// If there is a user for the given facebook user.
		else {
			// Returns the retrieved user.
			return principal.getUser();
		}
	}
}
