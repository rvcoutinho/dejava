package org.dejava.service.accesscontrol.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.service.accesscontrol.dao.UserDAO;
import org.dejava.service.accesscontrol.dao.principal.EmailDAO;
import org.dejava.service.accesscontrol.dao.principal.FacebookDAO;
import org.dejava.service.accesscontrol.dao.principal.NameDAO;
import org.dejava.service.accesscontrol.dao.principal.PrincipalDAO;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.principal.Email;
import org.dejava.service.accesscontrol.model.principal.Facebook;
import org.dejava.service.accesscontrol.model.principal.Name;
import org.dejava.service.accesscontrol.model.principal.Principal;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * User EJB component.
 */
@AccessControlCtx
@Stateless(name = "Component/AccessControl/UserAuthentication")
public class UserAuthenticationComponent {

	/**
	 * The user DAO.
	 */
	@Inject
	@AccessControlCtx
	private UserDAO userDAO;

	/**
	 * The principal DAO.
	 */
	@Inject
	@AccessControlCtx
	private PrincipalDAO principalDAO;

	/**
	 * Adds a new user principal.
	 * 
	 * @param principal
	 *            New user principal.
	 * @return New principal.
	 */
	public Principal addUserPrincipal(final Principal principal) {
		// Validates the principal to be added. TODO
		// Adds the new principal.
		return principalDAO.persist(principal);
	}

	/**
	 * The name principal DAO.
	 */
	@Inject
	@AccessControlCtx
	private NameDAO nameDAO;

	/**
	 * Gets the user by name.
	 * 
	 * @param name
	 *            The user name.
	 * @return The user by name.
	 */
	public User getUserByName(final String name) {
		// The user for the name is originally null.
		User user = null;
		// Tries to get the name principal information.
		final Name userName = nameDAO.getByAttribute("name", name);
		// If the name does exist.
		if (userName != null) {
			// Updates the user for the name.
			user = userName.getUser();
		}
		// Returns the user.
		return user;
	}

	/**
	 * The email principal DAO.
	 */
	@Inject
	@AccessControlCtx
	private EmailDAO emailDAO;

	/**
	 * Gets the the user by email.
	 * 
	 * @param email
	 *            The user email.
	 * @return The user by email.
	 */
	public User getUserByEmail(final String email) {
		// The user for the email is originally null.
		User user = null;
		// Tries to get the email principal information.
		final Email userEmail = emailDAO.getByAttribute("email", email);
		// If the email does exist.
		if (userEmail != null) {
			// Updates the user for the email.
			user = userEmail.getUser();
		}
		// Returns the user.
		return user;
	}

	/**
	 * Gets the the user by name or email.
	 * 
	 * @param nameOrEmail
	 *            The user name or email.
	 * @return The user by name or email.
	 */
	public User getUserByNameOrEmail(final String nameOrEmail) {
		// The user for the name or email is originally null.
		User user = null;
		// Tries to get the user by name.
		user = getUserByName(nameOrEmail);
		// If there is no user for the name.
		if (user == null) {
			// Tries to get the user by email.
			user = getUserByEmail(nameOrEmail);
		}
		// Returns the user.
		return user;
	}

	/**
	 * The facebook principal DAO.
	 */
	@Inject
	@AccessControlCtx
	private FacebookDAO facebookDAO;

	/**
	 * Gets the the user by facebook user identifier.
	 * 
	 * @param facebookUserId
	 *            The facebook user identifier.
	 * @return The user by facebook user identifier.
	 */
	public User getUserByFacebookUserId(final String facebookUserId) {
		// The user for the facebook user identifier is originally null.
		User user = null;
		// Tries to get the facebook principal information.
		final Facebook facebook = facebookDAO.getByAttribute("facebookIdentifier", facebookUserId);
		// If the facebook user identifier does exist.
		if (facebook != null) {
			// Updates the user for the facebook user identifier.
			user = facebook.getUser();
		}
		// Returns the user.
		return user;
	}

	/**
	 * Gets the the user by facebook user identifier.
	 * 
	 * @param facebookUserId
	 *            The facebook user identifier.
	 * @param email
	 *            The email of the user.
	 * @return The user by facebook user identifier.
	 */
	public User getUserByFacebookUserIdOrEmail(final String facebookUserId, final String email) {
		// The user for the facebook user identifier or email is originally null.
		User user = null;
		// Tries to get the user by the facebook user identifier.
		user = getUserByFacebookUserId(facebookUserId);
		// If there is no user for the facebook user identifier.
		if ((user == null) && ((email != null) && (!email.isEmpty()))) {
			// Tries to get the user by email.
			user = getUserByEmail(email);
			// If there is a user for the email.
			if (user != null) {
				// Adds a new principal (facebook user identifier) to the user.
				user = addUserPrincipal(new Facebook(facebookUserId)).getUser();
			}
		}
		// Returns the user.
		return user;
	}

}
