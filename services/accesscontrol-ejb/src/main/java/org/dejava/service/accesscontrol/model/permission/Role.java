package org.dejava.service.accesscontrol.model.permission;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.component.validation.method.ArgFormatter;
import org.dejava.service.accesscontrol.util.MessageTypes;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * A system user role.
 */
@Entity
@Table(name = "role")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class Role extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -8910104895961543802L;

	/**
	 * The name of the role.
	 */
	private String name;

	/**
	 * Gets the name of the role.
	 * 
	 * @return The name of the role.
	 */
	@Column(name = "name")
	@NotNull(payload = MessageTypes.Error.class, message = "role.name.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = "role.name.notnempty")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the role.
	 * 
	 * @param name
	 *            New name of the role.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Role permissions.
	 */
	private Set<RolePermission> rolePermissions;

	/**
	 * Gets the role permissions.
	 * 
	 * @return The role permissions.
	 */
	@OneToMany(mappedBy = "role", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<RolePermission> getRolePermissions() {
		// If the set is null.
		if (rolePermissions == null) {
			// Creates a new set for the set.
			rolePermissions = new HashSet<>();
		}
		// Returns the set.
		return rolePermissions;
	}

	/**
	 * Sets the rolePermissions.
	 * 
	 * @param rolePermissions
	 *            New rolePermissions.
	 */
	private void setRolePermissions(final Set<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	/**
	 * Gets the role permissions.
	 * 
	 * @return The role permissions.
	 */
	@Transient
	public Set<String> getPermissions() {
		// Creates a new empty set.
		final Set<String> permissions = new HashSet<>();
		// For each role permission.
		for (final RolePermission currentPermission : getRolePermissions()) {
			// Adds the current permission name to the set.
			permissions.add(currentPermission.getPermission().getName());
		}
		// Returns the set.
		return permissions;
	}

	/**
	 * Sets the role permissions.
	 * 
	 * @param permissions
	 *            New role permissions.
	 */
	public void setPermissions(final Set<String> permissions) {
		// If there are permissions.
		if (permissions != null) {
			// Creates a new role permission set.
			final Set<RolePermission> rolePermissions = new HashSet<>();
			// For each permission.
			for (final String currentPermission : permissions) {
				// Adds a new role permission for the current name.
				rolePermissions.add(new RolePermission(this, new Permission(currentPermission)));
			}
			// Sets the new role permissions.
			setRolePermissions(rolePermissions);
		}
	}

	/**
	 * Sets the user permissions.
	 * 
	 * @param permissions
	 *            New user permissions (in a single string, and split by ",").
	 */
	public void setPermissions(final String permissions) {
		// Sets the new permissions.
		setPermissions(new HashSet<>(ArgFormatter.split(permissions)));
	}

	/**
	 * Default constructor.
	 */
	public Role() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            New name of the role.
	 */
	public Role(final String name) {
		super();
		this.name = name;
	}

}
