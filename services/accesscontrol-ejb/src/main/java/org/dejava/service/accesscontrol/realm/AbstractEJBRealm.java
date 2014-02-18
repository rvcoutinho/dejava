package org.dejava.service.accesscontrol.realm;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.dejava.service.accesscontrol.component.UserAuthenticationComponent;
import org.dejava.service.accesscontrol.component.UserAuthorizationComponent;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.principal.Principal;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * Abstract authentication and authorization realm via EJB services.
 */
public abstract class AbstractEJBRealm extends AuthorizingRealm {

	/**
	 * The application JNDI path (start).
	 */
	private static final String APP_PRE_PATH = "java:app/";

	/**
	 * The application/module JNDI path.
	 */
	private final String appModulePath = "accesscontrol-ejb";

	/**
	 * Gets the application/module JNDI path.
	 * 
	 * @return The application/module JNDI path.
	 */
	public synchronized String getAppModulePath() {
		return appModulePath;
	}

	/**
	 * The relative JNDI path for user authentication EJB component.
	 */
	private final String relUserAuthenticationComponentPath = "Component/AccessControl/UserAuthentication";

	/**
	 * Gets the relative JNDI path for user authentication EJB component.
	 * 
	 * @return The relative JNDI path for user authentication EJB component.
	 */
	public String getRelUserAuthenticationComponentPath() {
		return relUserAuthenticationComponentPath;
	}

	/**
	 * The user EJB service.
	 */
	@Inject
	@AccessControlCtx
	private UserAuthenticationComponent userAuthenticationComponent;

	/**
	 * Gets the user EJB service.
	 * 
	 * @return The user EJB service.
	 */
	public UserAuthenticationComponent getUserAuthenticationComponent() {
		// If the service is null.
		if (userAuthenticationComponent == null) {
			// Tries to get the object via JNDI.
			try {
				userAuthenticationComponent = InitialContext.doLookup(APP_PRE_PATH + getAppModulePath() + "/"
						+ getRelUserAuthenticationComponentPath());
			}
			// If the path is not found.
			catch (final NamingException exception) {
				// Throws an exception.
				// TODO throw new InvalidParameterException(bundleInfo, messageKey, parameters, cause);
			}
		}
		// Returns the service.
		return userAuthenticationComponent;
	}

	/**
	 * The relative JNDI path for user authorization EJB component.
	 */
	private final String relUserAuthorizationComponentPath = "Component/AccessControl/UserAuthorization";

	/**
	 * Gets the relative JNDI path for user authorization EJB component.
	 * 
	 * @return The relative JNDI path for user authorization EJB component.
	 */
	public String getRelUserAuthorizationComponentPath() {
		return relUserAuthorizationComponentPath;
	}

	/**
	 * The user EJB service.
	 */
	@Inject
	@AccessControlCtx
	private UserAuthorizationComponent userAuthorizationComponent;

	/**
	 * Gets the user EJB service.
	 * 
	 * @return The user EJB service.
	 */
	public UserAuthorizationComponent getUserAuthorizationComponent() {
		// If the service is null.
		if (userAuthorizationComponent == null) {
			// Tries to get the object via JNDI.
			try {
				userAuthorizationComponent = InitialContext.doLookup(APP_PRE_PATH + getAppModulePath() + "/"
						+ getRelUserAuthorizationComponentPath());
			}
			// If the path is not found.
			catch (final NamingException exception) {
				// Throws an exception.
				// TODO throw new InvalidParameterException(bundleInfo, messageKey, parameters, cause);
			}
		}
		// Returns the service.
		return userAuthorizationComponent;
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
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
		// Gets the user from the principals.
		final User user = ((Principal) principals.getPrimaryPrincipal()).getUser();
		// Creates a new authorization info.
		final SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(
				userAuthorizationComponent.getUserRolesNames(user));
		// Sets the permissions for the user.
		authorizationInfo.setObjectPermissions(resolvePermissions(userAuthorizationComponent
				.getUserPermissionsNames(user)));
		// Returns a the authorization info.
		return authorizationInfo;
	}
}
