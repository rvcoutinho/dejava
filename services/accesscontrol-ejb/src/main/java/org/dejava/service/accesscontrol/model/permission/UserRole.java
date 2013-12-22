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
 * A system user role.
 */
@Entity
@Table(name = "user_role")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class UserRole extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -4599931770800115638L;

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
	 * The role for the user.
	 */
	private Role role;

	/**
	 * Gets the role for the user.
	 * 
	 * @return The role for the user.
	 */
	@ManyToOne
	@JoinColumn(name = "role")
	public Role getRole() {
		return role;
	}

	/**
	 * Sets the role for the user.
	 * 
	 * @param role
	 *            New role for the user.
	 */
	public void setRole(final Role role) {
		this.role = role;
	}

	/**
	 * Default constructor.
	 */
	public UserRole() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param user
	 *            The user.
	 * @param role
	 *            The user role.
	 */
	public UserRole(User user, Role role) {
		super();
		this.user = user;
		this.role = role;
	}

}
