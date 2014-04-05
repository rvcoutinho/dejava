package org.dejava.service.accesscontrol.model.permission;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.util.MessageTypes;

/**
 * A system user permission.
 */
@Entity
@Table(name = "user_permission")
@NamedQueries(value = { @NamedQuery(name = "getByName", query = "SELECT userPerm FROM UserPermission userPerm WHERE userPerm.user.id = :userId AND userPerm.permission.name LIKE :name") })
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.model", entriesAffix = { "", ".description" }, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class UserPermission extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -5814734344288219550L;

	/**
	 * The user.
	 */
	@NotNull(payload = MessageTypes.Error.class, message = "userpermission.user.notnull")
	private User user;

	/**
	 * Gets the user.
	 * 
	 * @return The user.
	 */
	@ManyToOne
	@JoinColumn(name = "u5er")
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 * 
	 * @param user
	 *            New user.
	 */
	public void setUser(final User user) {
		this.user = user;
	}

	/**
	 * The permission for the user.
	 */
	@NotNull(payload = MessageTypes.Error.class, message = "userpermission.permission.notnull")
	private Permission permission;

	/**
	 * Gets the permission for the user.
	 * 
	 * @return The permission for the user.
	 */
	@ManyToOne
	@JoinColumn(name = "permission")
	public Permission getPermission() {
		return permission;
	}

	/**
	 * Sets the permission for the user.
	 * 
	 * @param permission
	 *            New permission for the user.
	 */
	public void setPermission(final Permission permission) {
		this.permission = permission;
	}

	/**
	 * Default constructor.
	 */
	public UserPermission() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param user
	 *            The user.
	 * @param permission
	 *            The user permission.
	 */
	public UserPermission(final User user, final Permission permission) {
		super();
		this.user = user;
		this.permission = permission;
	}

	/**
	 * Default constructor.
	 * 
	 * @param userId
	 *            The user identifier.
	 * @param permissionName
	 *            The user permission name.
	 */
	public UserPermission(final Integer userId, final String permissionName) {
		this(new User(userId), new Permission(permissionName));
	}

}
