package org.dejava.service.accesscontrol.model.credentials;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.util.MessageTypes;

/**
 * Credentials to be used in the authentication/authorization.
 */
@Entity
@Table(name = "credentials")
@Inheritance(strategy = InheritanceType.JOINED)
@MessageSources(sources = {
		@MessageSource(bundleBaseName = "org.dejava.service.accesscontrol.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(bundleBaseName = "org.dejava.service.accesscontrol.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public abstract class Credentials extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -6292235989890051360L;

	/**
	 * The user for this credentials.
	 */
	private User user;

	/**
	 * Gets the user for this credentials.
	 * 
	 * @return The user for this credentials.
	 */
	@JoinColumn(name = "u5er")
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull(payload = MessageTypes.Error.class, message = "credentials.user.notnull")
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user for this credentials.
	 * 
	 * @param user
	 *            New user for this credentials.
	 */
	public void setUser(final User user) {
		this.user = user;
	}

	/**
	 * Gets the value for the credentials.
	 * 
	 * @return The value for the credentials.
	 */
	@Transient
	public abstract Object getValue();
}
