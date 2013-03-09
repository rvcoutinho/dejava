package org.dejava.service.accesscontrol.realm;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.dejava.service.accesscontrol.authc.FacebookUserToken;
import org.dejava.service.accesscontrol.business.principal.FacebookService;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.principal.Facebook;
import org.dejava.service.accesscontrol.util.AccessControl;

/**
 * Authentication and authorization realm via EJB services.
 */
public class FacebookEJBRealm extends AuthenticatingRealm {

	/**
	 * The global JNDI path (start).
	 */
	private static final String GLOBAL_PRE_PATH = "java:/global/";

	/**
	 * The application/module JNDI path.
	 */
	private String appModulePath = "ear/accesscontrol-ejb";

	/**
	 * Gets the application/module JNDI path.
	 * 
	 * @return The application/module JNDI path.
	 */
	public synchronized String getAppModulePath() {
		return appModulePath;
	}

	/**
	 * Sets the application/module JNDI path.
	 * 
	 * @param appModulePath
	 *            New application/module JNDI path.
	 */
	public synchronized void setAppModulePath(final String appModulePath) {
		this.appModulePath = appModulePath;
	}

	/**
	 * The relative JNDI path for facebook EJB service.
	 */
	private String relFacebookServicePath = "Service/AccessControl/FacebookService";

	/**
	 * Gets the relative JNDI path for facebook EJB service.
	 * 
	 * @return The relative JNDI path for facebook EJB service.
	 */
	public String getRelFacebookServicePath() {
		return relFacebookServicePath;
	}

	/**
	 * Sets the relative JNDI path for facebook EJB service.
	 * 
	 * @param relFacebookServicePath
	 *            New relative JNDI path for facebook EJB service.
	 */
	public void setRelFacebookServicePath(final String relFacebookServicePath) {
		this.relFacebookServicePath = relFacebookServicePath;
	}

	/**
	 * The facebook EJB service.
	 */
	@Inject
	@AccessControl
	private FacebookService facebookService;

	/**
	 * Gets the facebook EJB service.
	 * 
	 * @return The facebook EJB service.
	 */
	public FacebookService getFacebookService() {
		// If the service is null.
		if (facebookService == null) {
			// Tries to get the object via JNDI.
			try {
				facebookService = (InitialContext.doLookup(GLOBAL_PRE_PATH + getAppModulePath() + "/"
						+ getRelFacebookServicePath()));
			}
			// If the path is not found.
			catch (final NamingException exception) {
				// Throws an exception.
				// TODO throw new InvalidParameterException(bundleInfo, messageKey, parameters, cause);
			}
		}
		// Returns the service.
		return facebookService;
	}

	/**
	 * @see org.apache.shiro.realm.AuthenticatingRealm#supports(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	public boolean supports(final AuthenticationToken token) {
		return (token != null) && FacebookUserToken.class.isAssignableFrom(token.getClass());
	}

	/**
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token)
			throws AuthenticationException {
		// If the token is a facebook token.
		if (token instanceof FacebookUserToken) {
			// Casts the token into a facebook token.
			final FacebookUserToken facebookToken = (FacebookUserToken) token;
			// The user for the given principal.
			User user = null;
			// Tries to get a similar registered user facebook id.
			final Facebook facebook = getFacebookService().getEntityByAttribute("identifier",
					facebookToken.getIdentifier());
			// If there is an user for the given facebook id.
			if (facebook != null) {
				// Gets the user for the facebook principal.
				user = facebook.getUser();
			}
			// If there is an user for the given principal.
			if (user != null) {
				// Creates a new principal collection.
				final PrincipalCollection principals = new SimplePrincipalCollection(user.getRawPrincipals(),
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
