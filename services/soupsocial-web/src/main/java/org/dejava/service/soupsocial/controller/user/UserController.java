package org.dejava.service.soupsocial.controller.user;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.dejava.service.accesscontrol.component.principal.NameComponent;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.principal.Principal;
import org.dejava.service.accesscontrol.util.AccessControlCtx;
import org.dejava.service.party.model.Party;
import org.dejava.service.philanthropy.component.party.PhilanthropyPartyComponent;
import org.dejava.service.philanthropy.model.party.PhilanthropyParty;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * User controller.
 */
@ConversationScoped
@SoupSocialCtx
@Named("userController")
public class UserController implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 7108460866687912714L;

	/**
	 * Gets the application subject.
	 * 
	 * @return The application subject.
	 */
	public Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * Gets the current user.
	 * 
	 * @return The current user.
	 */
	@RequiresUser
	public User getCurrentUser() {
		// Returns the user from a subject principal.
		return ((Principal) getSubject().getPrincipals().getPrimaryPrincipal()).getUser();
	}

	/**
	 * The user name being searched.
	 */
	private String username;

	/**
	 * Gets the user name being searched.
	 * 
	 * @return The user name being searched.
	 */
	private String getUsername() {
		// Returns the user name.
		return username;
	}

	/**
	 * Sets the user name being searched.
	 * 
	 * @param username
	 *            New user name being searched.
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * The user name EJB component.
	 */
	@Inject
	@AccessControlCtx
	private NameComponent nameComponent;

	/**
	 * Gets the user for the given user name.
	 * 
	 * @return The user for the given user name.
	 */
	@RequiresUser
	public User getUser() {
		// If there is no user name given.
		if ((getUsername() == null) || (getUsername().isEmpty())) {
			// Returns the current user.
			return getCurrentUser();
		}
		// If an user name is given.
		else {
			// Returns the user for the given user name.
			return nameComponent.getByAttribute("name", getUsername()).getUser();
		}
	}

	/**
	 * Gets the party with the user name.
	 * 
	 * @return The party with the user name.
	 */
	public Party getParty() {
		// Returns the party.
		return getUser().getParty();
	}

	/**
	 * The philanthropy party EJB component.
	 */
	@Inject
	@PhilanthropyCtx
	private PhilanthropyPartyComponent philanthropyPartyComponent;

	/**
	 * The philanthropy party with the user name.
	 */
	private PhilanthropyParty philanthropyParty;

	/**
	 * Gets the philanthropy party with the user name.
	 * 
	 * @return The philanthropy party with the user name.
	 */
	public PhilanthropyParty getPhilanthropyParty() {
		// If the party has not been retrieved yet.
		if (philanthropyParty == null) {
			// Tries to get the party.
			philanthropyParty = philanthropyPartyComponent.getPhilanthropyPartyByParty(getParty());
		}
		// Returns the party.
		return philanthropyParty;
	}

	/**
	 * Logs the current user out.
	 * 
	 * @return Returns the index page.
	 */
	public String logout() {
		// Logs the user out.
		getSubject().logout();
		// Returns the the index page.
		return "pretty:index";
	}

}
