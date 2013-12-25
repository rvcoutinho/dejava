package org.dejava.service.accesscontrol.realm;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.dejava.service.accesscontrol.component.crendentials.PasswordComponent;
import org.dejava.service.accesscontrol.component.principal.EmailComponent;
import org.dejava.service.accesscontrol.component.principal.NameComponent;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.credentials.Password;
import org.dejava.service.accesscontrol.model.principal.Principal;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * Authentication and authorization realm via EJB services.
 */
public class EJBRealm extends AuthorizingRealm {

	/**
	 * The application JNDI path (start).
	 */
	private static final String APP_PRE_PATH = "java:app/";

	/**
	 * The application/module JNDI path.
	 */
	private String appModulePath = "accesscontrol-ejb";

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
	private String relNameComponentPath = "Component/AccessControl/Name";

	/**
	 * Gets the relative JNDI path for name EJB service.
	 * 
	 * @return The relative JNDI path for name EJB service.
	 */
	public String getRelNameComponentPath() {
		return relNameComponentPath;
	}

	/**
	 * Sets the relative JNDI path for name EJB service.
	 * 
	 * @param relNameComponentPath
	 *            New relative JNDI path for name EJB service.
	 */
	public void setRelNameComponentPath(final String relNameComponentPath) {
		this.relNameComponentPath = relNameComponentPath;
	}

	/**
	 * The name EJB service.
	 */
	@Inject
	@AccessControlCtx
	private NameComponent nameComponent;

	/**
	 * Gets the name EJB service.
	 * 
	 * @return The name EJB service.
	 */
	public NameComponent getNameComponent() {
		// If the service is null.
		if (nameComponent == null) {
			// Tries to get the object via JNDI.
			try {
				nameComponent = InitialContext.doLookup(APP_PRE_PATH + getAppModulePath() + "/"
						+ getRelNameComponentPath());
			}
			// If the path is not found.
			catch (final NamingException exception) {
				// Throws an exception.
				// TODO throw new InvalidParameterException(bundleInfo, messageKey, parameters, cause);
			}
		}
		// Returns the service.
		return nameComponent;
	}

	/**
	 * The relative JNDI path for email EJB service.
	 */
	private String relEmailComponentPath = "Component/AccessControl/Email";

	/**
	 * Gets the relative JNDI path for email EJB service.
	 * 
	 * @return The relative JNDI path for email EJB service.
	 */
	public synchronized String getRelEmailComponentPath() {
		return relEmailComponentPath;
	}

	/**
	 * Sets the relative JNDI path for email EJB service.
	 * 
	 * @param relEmailComponentPath
	 *            New relative JNDI path for email EJB service.
	 */
	public synchronized void setRelEmailComponentPath(final String relEmailComponentPath) {
		this.relEmailComponentPath = relEmailComponentPath;
	}

	/**
	 * The email EJB service.
	 */
	@Inject
	@AccessControlCtx
	private EmailComponent emailComponent;

	/**
	 * Gets the email EJB service.
	 * 
	 * @return The email EJB service.
	 */
	public EmailComponent getEmailComponent() {
		// If the service is null.
		if (emailComponent == null) {
			// Tries to get the object via JNDI.
			try {
				emailComponent = InitialContext.doLookup(APP_PRE_PATH + getAppModulePath() + "/"
						+ getRelEmailComponentPath());
			}
			// If the path is not found.
			catch (final NamingException exception) {
				// Throws an exception.
				// TODO throw new InvalidParameterException(bundleInfo, messageKey, parameters, cause);
			}
		}
		// Returns the service.
		return emailComponent;
	}

	/**
	 * The relative JNDI path for password EJB service.
	 */
	private String relPasswordComponentPath = "Component/AccessControl/Password";

	/**
	 * Gets the relative JNDI path for password EJB service.
	 * 
	 * @return The relative JNDI path for password EJB service.
	 */
	public synchronized String getRelPasswordComponentPath() {
		return relPasswordComponentPath;
	}

	/**
	 * Sets the relative JNDI path for password EJB service.
	 * 
	 * @param relPasswordComponentPath
	 *            New relative JNDI path for password EJB service.
	 */
	public synchronized void setRelPasswordComponentPath(final String relPasswordComponentPath) {
		this.relPasswordComponentPath = relPasswordComponentPath;
	}

	/**
	 * The password EJB service.
	 */
	@Inject
	@AccessControlCtx
	private PasswordComponent passwordComponent;

	/**
	 * Gets the password EJB service.
	 * 
	 * @return The password EJB service.
	 */
	public PasswordComponent getPasswordComponent() {
		// If the service is null.
		if (passwordComponent == null) {
			// Tries to get the object via JNDI.
			try {
				passwordComponent = InitialContext.doLookup(APP_PRE_PATH + getAppModulePath() + "/"
						+ getRelPasswordComponentPath());
			}
			// If the path is not found.
			catch (final NamingException exception) {
				// Throws an exception.
				// TODO throw new InvalidParameterException(bundleInfo, messageKey, parameters, cause);
			}
		}
		// Returns the service.
		return passwordComponent;
	}

	/**
	 * Resolves some text permissions to actual permissions.
	 * 
	 * @param permissions
	 *            Text permissions.
	 * @return The resolved permissions.
	 */
	private Set<Permission> resolvePermissions(final Set<String> permissions) {
		// Creates a new set of permissions.
		final Set<Permission> resolvedPermissions = new HashSet<>();
		// If there are any given permissions.
		if (permissions != null) {
			// For each given permission.
			for (final String currentPermission : permissions) {
				// Resolves the current permission and adds it to the list.
				resolvedPermissions.add(getPermissionResolver().resolvePermission(currentPermission));
			}
		}
		// Returns the resolved permissions.
		return resolvedPermissions;
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
			userPrincipal = getNameComponent().getByAttribute("name", upToken.getUsername());
			// If there is no user for the name.
			if (userPrincipal == null) {
				// Tries to get a similar registered user email.
				userPrincipal = getEmailComponent().getByAttribute("email", upToken.getUsername());
			}
			// If there is an user for the given name/email.
			if (userPrincipal != null) {
				// Gets the user for the email.
				user = userPrincipal.getUser();
			}
			// If there is an user for the given principal.
			if (user != null) {
				// Creates a new principal collection.
				final PrincipalCollection principals = new SimplePrincipalCollection(user.getPrincipals(),
						getName());
				// Gets the password for the user.
				final Password password = getPasswordComponent().getByAttribute("user", user);
				// Creates a new authentication info.
				final AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principals,
						password, getName());
				// Returns the authentication information.
				return authenticationInfo;
			}
		}
		// If there are no users for the given principal, returns null.
		return null;

	}

	/**
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
		// Gets the user from the principals.
		final User user = ((Principal) principals.getPrimaryPrincipal()).getUser();
		// Creates a new authorization info.
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(user.getRoles());
		// Sets the permissions for the user.
		authorizationInfo.setObjectPermissions(resolvePermissions(user.getAllPermissions()));
		// Returns a the authorization info.
		return authorizationInfo;
	}
}
