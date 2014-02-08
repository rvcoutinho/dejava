package org.dejava.service.accesscontrol.model.permission;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.util.MessageTypes;

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
	@NotNull(payload = MessageTypes.Error.class, message = "userrole.user.notnull")
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
	@NotNull(payload = MessageTypes.Error.class, message = "userrole.role.notnull")
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
	public UserRole(final User user, final Role role) {
		super();
		this.user = user;
		this.role = role;
	}

	/**
	 * Default constructor.
	 * 
	 * @param userId
	 *            The user identifier.
	 * @param roleName
	 *            The user role name.
	 */
	public UserRole(final Integer userId, final String roleName) {
		super();
		this.user = new User(userId);
		this.role = new Role(roleName);
	}

}
