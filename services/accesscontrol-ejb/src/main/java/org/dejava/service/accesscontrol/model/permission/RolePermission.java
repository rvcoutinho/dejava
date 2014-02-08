package org.dejava.service.accesscontrol.model.permission;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.accesscontrol.util.MessageTypes;

/**
 * A system role permission.
 */
@Entity
@Table(name = "role_permission")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class RolePermission extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -237058121250685396L;

	/**
	 * The role.
	 */
	@NotNull(payload = MessageTypes.Error.class, message = "rolepermission.role.notnull")
	private Role role;

	/**
	 * Gets the role.
	 * 
	 * @return The role.
	 */
	@ManyToOne
	@JoinColumn(name = "u5er")
	public Role getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 * 
	 * @param role
	 *            New role.
	 */
	public void setRole(final Role role) {
		this.role = role;
	}

	/**
	 * The permission for the role.
	 */
	@NotNull(payload = MessageTypes.Error.class, message = "rolepermission.permission.notnull")
	private Permission permission;

	/**
	 * Gets the permission for the role.
	 * 
	 * @return The permission for the role.
	 */
	@ManyToOne
	@JoinColumn(name = "permission")
	public Permission getPermission() {
		return permission;
	}

	/**
	 * Sets the permission for the role.
	 * 
	 * @param permission
	 *            New permission for the role.
	 */
	public void setPermission(final Permission permission) {
		this.permission = permission;
	}

	/**
	 * Default constructor.
	 */
	public RolePermission() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param role
	 *            The role.
	 * @param permission
	 *            The role permission.
	 */
	public RolePermission(final Role role, final Permission permission) {
		super();
		this.role = role;
		this.permission = permission;
	}

}
