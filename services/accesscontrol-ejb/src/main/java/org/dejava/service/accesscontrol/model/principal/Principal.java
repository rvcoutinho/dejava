package org.dejava.service.accesscontrol.model.principal;

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
 * Principal (user identity) to be used in the authentication/authorization.
 */
@Entity
@Table(name = "principal")
@Inheritance(strategy = InheritanceType.JOINED)
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public abstract class Principal extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -8167482368597973777L;

	/**
	 * The user for this principal.
	 */
	private User user;

	/**
	 * Gets the user for this principal.
	 * 
	 * @return The user for this principal.
	 */
	@JoinColumn(name = "u5er")
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull(payload = MessageTypes.Error.class, message = "principal.user.notnull")
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user for this principal.
	 * 
	 * @param user
	 *            New user for this principal.
	 */
	public void setUser(final User user) {
		this.user = user;
	}

	/**
	 * Gets the value for the principal.
	 * 
	 * @return The value for the principal.
	 */
	@Transient
	public abstract Object getValue();
}
