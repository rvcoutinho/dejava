package org.dejava.service.accesscontrol.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.dejava.service.accesscontrol.authc.FacebookUserToken;
import org.dejava.service.accesscontrol.model.User;

/**
 * Authentication and authorization realm via EJB services.
 */
public class FacebookEJBRealm extends AbstractEJBRealm {

	/**
	 * @see org.apache.shiro.realm.AuthenticatingRealm#getAuthenticationTokenClass()
	 */
	@Override
	public Class<?> getAuthenticationTokenClass() {
		return FacebookUserToken.class;
	}

	/**
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token)
			throws AuthenticationException {
		// If the token is a facebook token.
		if (token instanceof FacebookUserToken) {
			// Tries to get the user for the facebook id.
			final User user = getUserAuthenticationComponent().getUserByFacebookUserId(
					((FacebookUserToken) token).getFacebookIdentifier());
			// If there is an user for the given principal.
			if (user != null) {
				// Creates a new principal collection.
				final PrincipalCollection principals = new SimplePrincipalCollection(user.getPrincipals(),
						getName());
				// Creates a new simple authentication.
				final SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
				// Sets the principals for the authentication info.
				authenticationInfo.setPrincipals(principals);
				// Returns the authentication information.
				return authenticationInfo;
			}
		}
		// If there are no users for the given principal, returns null.
		return null;
	}

	/**
	 * @see org.apache.shiro.realm.AuthenticatingRealm#assertCredentialsMatch(org.apache.shiro.authc.AuthenticationToken,
	 *      org.apache.shiro.authc.AuthenticationInfo)
	 */
	@Override
	protected void assertCredentialsMatch(final AuthenticationToken token, final AuthenticationInfo info)
			throws AuthenticationException {
		// Does nothing (the authentication was made through the facebook API).
	}

}
