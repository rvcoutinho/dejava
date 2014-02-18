package org.dejava.service.accesscontrol.component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.service.accesscontrol.dao.permission.PermissionDAO;
import org.dejava.service.accesscontrol.dao.permission.RoleDAO;
import org.dejava.service.accesscontrol.dao.permission.UserPermissionDAO;
import org.dejava.service.accesscontrol.dao.permission.UserRoleDAO;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.permission.Permission;
import org.dejava.service.accesscontrol.model.permission.Role;
import org.dejava.service.accesscontrol.model.permission.UserPermission;
import org.dejava.service.accesscontrol.model.permission.UserRole;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * User authorization EJB component.
 */
@AccessControlCtx
@Stateless(name = "Component/AccessControl/UserAuthorization")
public class UserAuthorizationComponent {

	/**
	 * Role DAO.
	 */
	@Inject
	@AccessControlCtx
	private RoleDAO roleDAO;

	/**
	 * User role DAO.
	 */
	@Inject
	@AccessControlCtx
	private UserRoleDAO userRoleDAO;

	/**
	 * Adds a role to a user.
	 * 
	 * @param userId
	 *            User identifier.
	 * @param roleName
	 *            Role name.
	 */
	public void addUserRole(final Integer userId, final String roleName) {
		// Creates a new user role.
		final UserRole userRole = new UserRole(userId, roleName);
		// Tries to get a role with the same name. TODO Think about
		Role actualRole = roleDAO.getByAttribute("name", userRole.getRole().getName());
		// If the role does not exist yet.
		if (actualRole == null) {
			// Creates the role. TODO Think about
			actualRole = roleDAO.merge(userRole.getRole());
		}
		// Validates TODO
		// Adds the role to the user.
		userRoleDAO.merge(userRole);
	}

	/**
	 * TODO
	 * 
	 * @param userId
	 *            User identifier.
	 * @param roleName
	 *            Role name.
	 */
	public void removeUserRole(final Integer userId, final String roleName) {

	}

	/**
	 * Gets the roles for a user.
	 * 
	 * @param user
	 *            The user to get the roles from.
	 * @return The roles for a user.
	 */
	public Set<Role> getUserRoles(final User user) {
		// Creates a new set for the roles of the user.
		final Set<Role> roles = new HashSet<>();
		// If there is a given user.
		if (user != null) {
			// Gets the user roles.
			final Collection<UserRole> userRoles = userRoleDAO.getByAttribute("user.identifier",
					user.getIdentifier(), null, null);
			// For each user role.
			for (final UserRole currentUserRole : userRoles) {
				// Adds the current role to the set.
				roles.add(currentUserRole.getRole());
			}
		}
		// Returns the roles for the user.
		return roles;
	}

	/**
	 * Gets the names of roles for a user.
	 * 
	 * @param user
	 *            The user to get the roles from.
	 * @return The names of roles for a user.
	 */
	public Set<String> getUserRolesNames(final User user) {
		// Creates a new set for the roles names of the user.
		final Set<String> rolesNames = new HashSet<>();
		// For each role for the user.
		for (final Role currentRole : getUserRoles(user)) {
			// Adds the name of the role to the set.
			rolesNames.add(currentRole.getName());
		}
		// Returns the roles names for the user.
		return rolesNames;
	}

	/**
	 * Permission DAO.
	 */
	@Inject
	@AccessControlCtx
	private PermissionDAO permissionDAO;

	/**
	 * User permission DAO.
	 */
	@Inject
	@AccessControlCtx
	private UserPermissionDAO userPermissionDAO;

	/**
	 * Role component. TODO Think about
	 */
	@Inject
	@AccessControlCtx
	private RoleComponent roleComponent;

	/**
	 * Adds a permission to a user.
	 * 
	 * @param userId
	 *            User identifier.
	 * @param permissionName
	 *            Permission name.
	 */
	public void addUserPermission(final Integer userId, final String permissionName) {
		// Creates a new user permission.
		final UserPermission userPermission = new UserPermission(userId, permissionName);
		// Tries to get a permission with the same name. TODO Think about
		Permission actualPermission = permissionDAO.getByAttribute("name", userPermission.getPermission()
				.getName());
		// If the permission does not exist yet.
		if (actualPermission == null) {
			// Creates the permission. TODO Think about
			actualPermission = permissionDAO.merge(userPermission.getPermission());
		}
		// Validates TODO
		// Adds the permission to the user.
		userPermissionDAO.merge(userPermission);
	}

	/**
	 * Gets the permissions for a user.
	 * 
	 * @param user
	 *            The user to get the permissions from.
	 * @return The permissions for a user.
	 */
	public Set<Permission> getPermissions(final User user) {
		// Creates a new set for the permissions of the user.
		final Set<Permission> permissions = new HashSet<>();
		// If there is a given user.
		if (user != null) {
			// Gets the user permissions.
			final Collection<UserPermission> userPermissions = userPermissionDAO.getByAttribute(
					"user.identifier", user.getIdentifier(), null, null);
			// For each user permission.
			for (final UserPermission currentUserPermission : userPermissions) {
				// Adds the current permission to the set.
				permissions.add(currentUserPermission.getPermission());
			}
			// Gets the user roles.
			final Collection<UserRole> userRoles = userRoleDAO.getByAttribute("user.identifier",
					user.getIdentifier(), null, null);
			// For each user role.
			for (final UserRole currentUserRole : userRoles) {
				// Adds the current roles permissions.
				permissions.addAll(roleComponent.getRolePermissions(currentUserRole.getRole()));
			}
		}
		// Returns the permissions for the user.
		return permissions;
	}

	/**
	 * Gets the names of permissions for a user.
	 * 
	 * @param user
	 *            The user to get the permissions from.
	 * @return The names of permissions for a user.
	 */
	public Set<String> getUserPermissionsNames(final User user) {
		// Creates a new set for the permissions names of the user.
		final Set<String> permissionsNames = new HashSet<>();
		// For each permission for the user.
		for (final Permission currentPermission : getPermissions(user)) {
			// Adds the name of the permission to the set.
			permissionsNames.add(currentPermission.getName());
		}
		// Returns the permissions names for the user.
		return permissionsNames;
	}

	/**
	 * Gets the user permissions by user and name.
	 * 
	 * @param userId
	 *            The user identifier.
	 * @param permissionName
	 *            The permission name (or name expression).
	 * @param firstResult
	 *            The first result that should be considered by the query.
	 * @param maxResults
	 *            The maximum numbers of results to be considered by the query.
	 * @return The user permissions by name.
	 */
	public Collection<UserPermission> getUserPermissionsByUserAndName(final Integer userId,
			final String permissionName, final Integer firstResult, final Integer maxResults) {
		return userPermissionDAO.getByName(userId, permissionName, firstResult, maxResults);
	}

}
