package org.dejava.service.soupsocial.controller.user;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.dejava.service.accesscontrol.component.principal.NameComponent;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.principal.Name;
import org.dejava.service.accesscontrol.util.AccessControlCtx;
import org.dejava.service.party.component.PartyComponent;
import org.dejava.service.party.model.Party;
import org.dejava.service.party.util.PartyCtx;
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
	 * The user name being searched.
	 */
	private String username;

	/**
	 * Gets the user name being searched.
	 * 
	 * @return The user name being searched.
	 */
	public String getUsername() {
		// If the user name is not given.
		if ((username == null) || (username.isEmpty())) {
			// If the user is known. FIXME
			if ((getSubject().isAuthenticated()) || (getSubject().isRemembered())) {
				// Gets the name principals for the current user. FIXME
				final Collection<Name> usernames = getSubject().getPrincipals().byType(Name.class);
				// If there is a user name.
				if (!usernames.isEmpty()) {
					// The user name is the principals name.
					username = usernames.iterator().next().getName();
				}
			}
		}
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
	 * The party EJB component.
	 */
	@Inject
	@PartyCtx
	private PartyComponent partyComponent;

	/**
	 * The party with the user name.
	 */
	private Party party;

	/**
	 * Gets the party with the user name.
	 * 
	 * @return The party with the user name.
	 */
	public Party getParty() {
		// If the party has not been retrieved yet.
		if (party == null) {
			// Tries to get the party with the user name.
			try {
				// Tries to find the user by the user name.
				final User user = nameComponent.getByAttribute("name", getUsername()).getUser();
				// Tries to get the party for the user.
				this.party = partyComponent.getByAttribute("userId", user.getIdentifier());
			}
			// If the party cannot be retrieved.
			catch (final Exception exception) {
				// TODO Throws an exception.
			}
		}
		// Returns the party.
		return party;
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
