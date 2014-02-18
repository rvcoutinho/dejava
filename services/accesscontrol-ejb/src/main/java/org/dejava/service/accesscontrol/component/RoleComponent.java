package org.dejava.service.accesscontrol.component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.service.accesscontrol.businessrule.RoleBusinessRuleSet;
import org.dejava.service.accesscontrol.dao.permission.RoleDAO;
import org.dejava.service.accesscontrol.dao.permission.RolePermissionDAO;
import org.dejava.service.accesscontrol.model.permission.Permission;
import org.dejava.service.accesscontrol.model.permission.Role;
import org.dejava.service.accesscontrol.model.permission.RolePermission;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * EJB service for role.
 */
@AccessControlCtx
@Stateless(name = "Component/AccessControl/Role")
public class RoleComponent {

	/**
	 * The role DAO.
	 */
	@Inject
	@AccessControlCtx
	private RoleDAO roleDAO;

	/**
	 * The role business rule set.
	 */
	@Inject
	@AccessControlCtx
	private RoleBusinessRuleSet roleBizRuleSet;

	/**
	 * Role permission DAO.
	 */
	@Inject
	@AccessControlCtx
	private RolePermissionDAO rolePermissionDAO;

	/**
	 * Gets the permissions for a role.
	 * 
	 * @param role
	 *            The role to get the permissions from.
	 * @return The permissions for a role.
	 */
	public Set<Permission> getRolePermissions(final Role role) {
		// Creates a new set for the permissions of the role.
		final Set<Permission> permissions = new HashSet<>();
		// If there is a given role.
		if (role != null) {
			// Gets the role permissions.
			final Collection<RolePermission> rolePermissions = rolePermissionDAO.getByAttribute(
					"role.identifier", role.getIdentifier(), null, null);
			// For each role permission.
			for (final RolePermission currentRolePermission : rolePermissions) {
				// Adds the current permission to the set.
				permissions.add(currentRolePermission.getPermission());
			}
		}
		// Returns the permissions for the role.
		return permissions;
	}

	/**
	 * Gets the names of permissions for a role.
	 * 
	 * @param role
	 *            The role to get the permissions from.
	 * @return The names of permissions for a role.
	 */
	public Set<String> getRolePermissionsNames(final Role role) {
		// Creates a new set for the permissions names of the role.
		final Set<String> permissionsNames = new HashSet<>();
		// For each permission for the role.
		for (final Permission currentPermission : getRolePermissions(role)) {
			// Adds the name of the permission to the set.
			permissionsNames.add(currentPermission.getName());
		}
		// Returns the permissions names for the role.
		return permissionsNames;
	}
}
