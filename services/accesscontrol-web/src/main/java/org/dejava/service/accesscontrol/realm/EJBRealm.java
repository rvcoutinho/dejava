package org.dejava.service.accesscontrol.realm;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.dejava.service.accesscontrol.business.crendentials.PasswordService;
import org.dejava.service.accesscontrol.business.principal.EmailService;
import org.dejava.service.accesscontrol.business.principal.NameService;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.credentials.Password;
import org.dejava.service.accesscontrol.model.principal.Principal;
import org.dejava.service.accesscontrol.util.AccessControl;

/**
 * Authentication and authorization realm via EJB services.
 */
public class EJBRealm extends AuthenticatingRealm {

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
	 * The relative JNDI path for name EJB service.
	 */
	private String relNameServicePath = "Service/AccessControl/NameService";

	/**
	 * Gets the relative JNDI path for name EJB service.
	 * 
	 * @return The relative JNDI path for name EJB service.
	 */
	public String getRelNameServicePath() {
		return relNameServicePath;
	}

	/**
	 * Sets the relative JNDI path for name EJB service.
	 * 
	 * @param relNameServicePath
	 *            New relative JNDI path for name EJB service.
	 */
	public void setRelNameServicePath(final String relNameServicePath) {
		this.relNameServicePath = relNameServicePath;
	}

	/**
	 * The name EJB service.
	 */
	@Inject
	@AccessControl
	private NameService nameService;

	/**
	 * Gets the name EJB service.
	 * 
	 * @return The name EJB service.
	 */
	public NameService getNameService() {
		// If the service is null.
		if (nameService == null) {
			// Tries to get the object via JNDI.
			try {
				nameService = InitialContext.doLookup(GLOBAL_PRE_PATH + getAppModulePath() + "/"
						+ getRelNameServicePath());
			}
			// If the path is not found.
			catch (final NamingException exception) {
				// Throws an exception.
				// TODO throw new InvalidParameterException(bundleInfo, messageKey, parameters, cause);
			}
		}
		// Returns the service.
		return nameService;
	}

	/**
	 * The relative JNDI path for email EJB service.
	 */
	private String relEmailServicePath = "Service/AccessControl/EmailService";

	/**
	 * Gets the relative JNDI path for email EJB service.
	 * 
	 * @return The relative JNDI path for email EJB service.
	 */
	public synchronized String getRelEmailServicePath() {
		return relEmailServicePath;
	}

	/**
	 * Sets the relative JNDI path for email EJB service.
	 * 
	 * @param relEmailServicePath
	 *            New relative JNDI path for email EJB service.
	 */
	public synchronized void setRelEmailServicePath(final String relEmailServicePath) {
		this.relEmailServicePath = relEmailServicePath;
	}

	/**
	 * The email EJB service.
	 */
	@Inject
	@AccessControl
	private EmailService emailService;

	/**
	 * Gets the email EJB service.
	 * 
	 * @return The email EJB service.
	 */
	public EmailService getEmailService() {
		// If the service is null.
		if (emailService == null) {
			// Tries to get the object via JNDI.
			try {
				emailService = InitialContext.doLookup(GLOBAL_PRE_PATH + getAppModulePath() + "/"
						+ getRelEmailServicePath());
			}
			// If the path is not found.
			catch (final NamingException exception) {
				// Throws an exception.
				// TODO throw new InvalidParameterException(bundleInfo, messageKey, parameters, cause);
			}
		}
		// Returns the service.
		return emailService;
	}

	/**
	 * The relative JNDI path for password EJB service.
	 */
	private String relPasswordServicePath = "Service/AccessControl/PasswordService";

	/**
	 * Gets the relative JNDI path for password EJB service.
	 * 
	 * @return The relative JNDI path for password EJB service.
	 */
	public synchronized String getRelPasswordServicePath() {
		return relPasswordServicePath;
	}

	/**
	 * Sets the relative JNDI path for password EJB service.
	 * 
	 * @param relPasswordServicePath
	 *            New relative JNDI path for password EJB service.
	 */
	public synchronized void setRelPasswordServicePath(final String relPasswordServicePath) {
		this.relPasswordServicePath = relPasswordServicePath;
	}

	/**
	 * The password EJB service.
	 */
	@Inject
	@AccessControl
	private PasswordService passwordService;

	/**
	 * Gets the password EJB service.
	 * 
	 * @return The password EJB service.
	 */
	public PasswordService getPasswordService() {
		// If the service is null.
		if (passwordService == null) {
			// Tries to get the object via JNDI.
			try {
				passwordService = InitialContext.doLookup(GLOBAL_PRE_PATH + getAppModulePath() + "/"
						+ getRelPasswordServicePath());
			}
			// If the path is not found.
			catch (final NamingException exception) {
				// Throws an exception.
				// TODO throw new InvalidParameterException(bundleInfo, messageKey, parameters, cause);
			}
		}
		// Returns the service.
		return passwordService;
	}

	/**
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token)
			throws AuthenticationException {
		// If the token is an username/password token.
		if (token instanceof UsernamePasswordToken) {
			// Casts the token into an username/password token.
			final UsernamePasswordToken upToken = (UsernamePasswordToken) token;
			// The user principal.
			Principal userPrincipal = null;
			// The user for the given principal.
			User user = null;
			// Tries to get a similar registered user name.
			userPrincipal = getNameService().getByAttribute("name", upToken.getUsername());
			// If there is no user for the name.
			if (userPrincipal == null) {
				// Tries to get a similar registered user email.
				userPrincipal = getEmailService().getByAttribute("email", upToken.getUsername());
			}
			// If there is an user for the given name/email.
			if (userPrincipal != null) {
				// Gets the user for the email.
				user = userPrincipal.getUser();
			}
			// If there is an user for the given principal.
			if (user != null) {
				// Creates a new principal collection.
				final PrincipalCollection principals = new SimplePrincipalCollection(user.getRawPrincipals(),
						getName());
				// Gets the password for the user.
				final Password password = getPasswordService().getByAttribute("user", user);
				// Creates a new simple authentication (with the user principals and credentials).
				final SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principals,
						password);
				// Returns the authentication information.
				return authenticationInfo;
			}
		}
		// If there are no users for the given principal, returns null.
		return null;

	}

}
