package org.dejava.service.accesscontrol.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.credentials.Password;

/**
 * Authentication and authorization realm via EJB services.
 */
public class PasswordEJBRealm extends AbstractEJBRealm {

	/**
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token)
			throws AuthenticationException {
		// If the token is an username/password token.
		if (token instanceof UsernamePasswordToken) {
			// Tries to get the user for the name or email.
			final User user = getUserAuthenticationComponent().getUserByNameOrEmail(
					((UsernamePasswordToken) token).getUsername());
			// If there is an user for the given principal.
			if (user != null) {
				// Creates a new principal collection.
				final PrincipalCollection principals = new SimplePrincipalCollection(user.getPrincipals(),
						getName());
				// Creates a new authentication info.
				final AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principals,
						user.getCredentials(Password.class), getName());
				// Returns the authentication information.
				return authenticationInfo;
			}
		}
		// If there are no users for the given principal, returns null.
		return null;
	}

}
