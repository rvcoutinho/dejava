package org.dejava.service.soupsocial.controller.user;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.dejava.service.accesscontrol.component.UserAuthenticationComponent;
import org.dejava.service.accesscontrol.interceptor.Secured;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.principal.Principal;
import org.dejava.service.accesscontrol.util.AccessControlCtx;
import org.dejava.service.party.util.PartyCtx;
import org.dejava.service.philanthropy.component.PartyComponent;
import org.dejava.service.philanthropy.model.party.Party;
import org.dejava.service.philanthropy.util.PhilanthropyCtx;
import org.dejava.service.soupsocial.util.SoupSocialCtx;

/**
 * User controller.
 */
@SoupSocialCtx
@ConversationScoped
@Named("userController")
public class UserController implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 7108460866687912714L;

	/**
	 * TODO
	 */
	@Secured
	@RequiresAuthentication
	public void checkAuthenticated() {

	}

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
	@Secured
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
	 * The user authentication EJB component.
	 */
	@Inject
	@AccessControlCtx
	private UserAuthenticationComponent userAuthenticationComponent;

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
			return userAuthenticationComponent.getUserByName(getUsername());
		}
	}

	/**
	 * Party component.
	 */
	@Inject
	@PartyCtx
	private org.dejava.service.party.component.PartyComponent partyComponent;

	/**
	 * Gets the party with the user name.
	 * 
	 * @return The party with the user name.
	 */
	public org.dejava.service.party.model.Party getParty() {
		// Returns the party.
		return partyComponent.getPartyByUser(getUser().getIdentifier());
	}

	/**
	 * Philanthropy party component.
	 */
	@Inject
	@PhilanthropyCtx
	private PartyComponent philanthropyPartyComponent;

	/**
	 * The philanthropy party with the user name.
	 */
	private Party philanthropyParty;

	/**
	 * Gets the philanthropy party with the user name.
	 * 
	 * @return The philanthropy party with the user name.
	 */
	public Party getPhilanthropyParty() {
		// If the party has not been retrieved yet.
		if (philanthropyParty == null) {
			// Tries to get the party.
			philanthropyParty = philanthropyPartyComponent.getOrAddSupporterByParty(getParty());
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
