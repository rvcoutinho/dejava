package org.dejava.service.accesscontrol.model.permission;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.accesscontrol.model.User;

/**
 * A system user permission.
 */
@Entity
@Table(name = "user_permission")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class UserPermission extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -5814734344288219550L;

	/**
	 * The user.
	 */
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

}
