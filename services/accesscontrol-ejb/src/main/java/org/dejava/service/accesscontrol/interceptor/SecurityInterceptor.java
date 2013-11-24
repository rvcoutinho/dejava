package org.dejava.service.accesscontrol.interceptor;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;

/**
 * The security enforcer interceptor.
 */
@Secured
@Interceptor
public class SecurityInterceptor implements Serializable {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 3000099623439084773L;

	/**
	 * Gets the application subject.
	 * 
	 * @return The application subject.
	 */
	private Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * Enforces that the user is authenticated.
	 * 
	 * @param contextClass
	 *            The class in the invocation context.
	 * @param contextMethod
	 *            The method in the invocation context.
	 */
	private void enforceAuthentication(final Class<?> contextClass, final Method contextMethod) {
		// If the method (or the class) requires authentication.
		if ((contextMethod.getAnnotation(RequiresAuthentication.class) != null)
				|| (contextClass.getAnnotation(RequiresAuthentication.class) != null)) {
			// If the user is not authenticated.
			if (!getSubject().isAuthenticated()) {
				// Throws an authorization exception. FIXME
				throw new AuthorizationException();
			}
		}
		// If the method (or the class) requires an user.
		if ((contextMethod.getAnnotation(RequiresUser.class) != null)
				|| (contextClass.getAnnotation(RequiresUser.class) != null)) {
			// If the user is not authenticated and not remembered.
			if ((!getSubject().isAuthenticated()) && (!getSubject().isRemembered())) {
				// Throws an authorization exception. FIXME
				throw new AuthorizationException();
			}
		}
		// If the method (or the class) requires a guest.
		if ((contextMethod.getAnnotation(RequiresGuest.class) != null)
				|| (contextClass.getAnnotation(RequiresGuest.class) != null)) {
			// If the user is authenticated or remembered.
			if ((getSubject().isAuthenticated()) || (getSubject().isRemembered())) {
				// Throws an authorization exception. FIXME
				throw new AuthorizationException();
			}
		}

	}

	/**
	 * Enforces that the user has at least one of the given roles.
	 * 
	 * @param roles
	 *            The expected roles.
	 */
	private void enforceOneRole(final Collection<String> roles) {
		// The user is not authorized by default.
		Boolean authorized = false;
		// For each role.
		for (final String currentRole : roles) {
			// If the subject has the current role.
			if (getSubject().hasRole(currentRole)) {
				// Sets the the user is authorized.
				authorized = true;
			}
		}
		// If the subject has none of the given roles.
		if (!authorized) {
			// Throws an authorization exception. FIXME
			throw new AuthorizationException();
		}
	}

	/**
	 * Enforces that the user has all of the given roles.
	 * 
	 * @param roles
	 *            The expected roles.
	 */
	private void enforceAllRoles(final Collection<String> roles) {
		// If the subject does not have all of the given roles.
		if (!getSubject().hasAllRoles(roles)) {
			// Throws an authorization exception. FIXME
			throw new AuthorizationException();
		}
	}

	/**
	 * Enforces that the user is authorized (has role).
	 * 
	 * @param contextClass
	 *            The class in the invocation context.
	 * @param contextMethod
	 *            The method in the invocation context.
	 */
	private void enforceRoleAuthorization(final Class<?> contextClass, final Method contextMethod) {
		// Tries to get the roles expected for the method.
		final RequiresRoles rolesRequiredMethod = contextMethod.getAnnotation(RequiresRoles.class);
		// Tries to get the roles expected for the method.
		final RequiresRoles rolesRequiredClass = contextClass.getAnnotation(RequiresRoles.class);
		// If the method requires any roles.
		if ((rolesRequiredMethod != null) && (rolesRequiredMethod.value().length > 0)) {
			// If the method requires one of the given roles.
			if (rolesRequiredMethod.logical() == Logical.OR) {
				// Enforces that the subject has one of the expected roles.
				enforceOneRole(Arrays.asList(rolesRequiredMethod.value()));
			}
			// If the method requires all roles.
			else {
				// Enforces that the subject has all of the expected roles.
				enforceAllRoles(Arrays.asList(rolesRequiredMethod.value()));
			}
		}
		// If the class requires any roles.
		if ((rolesRequiredClass != null) && (rolesRequiredClass.value().length > 0)) {
			// If the method requires one of the given roles.
			if (rolesRequiredClass.logical() == Logical.OR) {
				// Enforces that the subject has one of the expected roles.
				enforceOneRole(Arrays.asList(rolesRequiredClass.value()));
			}
			// If the method requires all roles.
			else {
				// Enforces that the subject has all of the expected roles.
				enforceAllRoles(Arrays.asList(rolesRequiredClass.value()));
			}
		}
	}

	/**
	 * Enforces that the user has at least one of the given permissions.
	 * 
	 * @param permissions
	 *            The expected permissions.
	 */
	private void enforceOnePermission(final Collection<String> permissions) {
		// The user is not authorized by default.
		Boolean authorized = false;
		// For each permission.
		for (final String currentPermission : permissions) {
			// If the subject has the current permission.
			if (getSubject().isPermitted(currentPermission)) {
				// Sets the the user is authorized.
				authorized = true;
			}
		}
		// If has none of the given permissions.
		if (!authorized) {
			// Throws an authorization exception. FIXME
			throw new AuthorizationException();
		}
	}

	/**
	 * Enforces that the user has all of the given permissions.
	 * 
	 * @param permissions
	 *            The expected permissions.
	 */
	private void enforceAllPermissions(final Collection<String> permissions) {
		// If the subject does not have all of the given permissions.
		if (!getSubject().isPermittedAll(permissions.toArray(new String[0]))) {
			// Throws an authorization exception. FIXME
			throw new AuthorizationException();
		}
	}

	/**
	 * Enforces that the user is authorized (has permission).
	 * 
	 * @param contextClass
	 *            The class in the invocation context.
	 * @param contextMethod
	 *            The method in the invocation context.
	 */
	private void enforcePermissionAuthorization(final Class<?> contextClass, final Method contextMethod) {
		// Tries to get the permissions expected for the method.
		final RequiresPermissions permissionsRequiredMethod = contextMethod
				.getAnnotation(RequiresPermissions.class);
		// Tries to get the permissions expected for the method.
		final RequiresPermissions permissionsRequiredClass = contextClass
				.getAnnotation(RequiresPermissions.class);
		// If the method requires any permissions.
		if ((permissionsRequiredMethod != null) && (permissionsRequiredMethod.value().length > 0)) {
			// If the method requires one of the given permissions.
			if (permissionsRequiredMethod.logical() == Logical.OR) {
				// Enforces that the subject has one of the expected permissions.
				enforceOnePermission(Arrays.asList(permissionsRequiredMethod.value()));
			}
			// If the method requires all permissions.
			else {
				// Enforces that the subject has all of the expected permissions.
				enforceAllPermissions(Arrays.asList(permissionsRequiredMethod.value()));
			}
		}
		// If the class requires any permissions.
		if ((permissionsRequiredClass != null) && (permissionsRequiredClass.value().length > 0)) {
			// If the method requires one of the given permissions.
			if (permissionsRequiredClass.logical() == Logical.OR) {
				// Enforces that the subject has one of the expected permissions.
				enforceOnePermission(Arrays.asList(permissionsRequiredClass.value()));
			}
			// If the method requires all permissions.
			else {
				// Enforces that the subject has all of the expected permissions.
				enforceAllPermissions(Arrays.asList(permissionsRequiredClass.value()));
			}
		}
	}

	/**
	 * Enforces that the user is authorized.
	 * 
	 * @param contextClass
	 *            The class in the invocation context.
	 * @param contextMethod
	 *            The method in the invocation context.
	 */
	private void enforceAuthorization(final Class<?> contextClass, final Method contextMethod) {
		// Enforces that the user is authorized by a role (if needed).
		enforceRoleAuthorization(contextClass, contextMethod);
		// Enforces that the user is authorized by a permission (if needed).
		enforcePermissionAuthorization(contextClass, contextMethod);
	}

	/**
	 * Enforces the authentication and authorization for the given invocation context.
	 * 
	 * @param invocationContext
	 *            The intercepted invocation context.
	 * @return The invocation return.
	 * @throws Exception
	 *             Any exception raised in the invocation.
	 */
	@AroundInvoke
	public Object enforceAuth(final InvocationContext invocationContext) throws Exception {
		// Enforces that the user is authenticated (if needed).
		enforceAuthentication(invocationContext.getTarget().getClass(), invocationContext.getMethod());
		// Enforces that the user is authorized (if needed).
		enforceAuthorization(invocationContext.getTarget().getClass(), invocationContext.getMethod());
		// Proceeds with the method invocation (if no authorization exception is thrown).
		return invocationContext.proceed();
	}
}