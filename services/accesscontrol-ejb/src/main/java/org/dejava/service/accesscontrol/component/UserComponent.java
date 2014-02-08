package org.dejava.service.accesscontrol.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.service.accesscontrol.businessrule.UserBusinessRuleSet;
import org.dejava.service.accesscontrol.dao.UserDAO;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * User EJB component.
 */
@AccessControlCtx
@Stateless(name = "Component/AccessControl/User")
public class UserComponent {

	/**
	 * The user DAO.
	 */
	@Inject
	@AccessControlCtx
	private UserDAO userDAO;

	/**
	 * The user business rule set.
	 */
	@Inject
	@AccessControlCtx
	private UserBusinessRuleSet userBizRuleSet;

	/**
	 * Creates a new user.
	 * 
	 * @param user
	 *            New user.
	 * @return The new user.
	 */
	public User createUser(final User user) {
		// Validates the user to be added.
		userBizRuleSet.validate(user);
		// Adds the new user.
		return userDAO.merge(user);
	}

}
