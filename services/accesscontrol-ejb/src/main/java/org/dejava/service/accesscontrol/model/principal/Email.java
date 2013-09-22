package org.dejava.service.accesscontrol.model.principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.accesscontrol.util.MessageTypes;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Email of the user (as principal).
 */
@Entity
@Table(name = "email")
@MessageSources(sources = {
		@MessageSource(bundleBaseName = "org.dejava.properties.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(bundleBaseName = "org.dejava.properties.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class Email extends Principal {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4373856994515306923L;

	/**
	 * Email of the user.
	 */
	private String email;

	/**
	 * Gets the email of the user.
	 * 
	 * @return The email of the user.
	 */
	@Column(name = "email")
	@NotNull(payload = MessageTypes.Error.class, message = "email.email.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = "email.email.notempty")
	@org.hibernate.validator.constraints.Email(payload = MessageTypes.Error.class, message = "email.email.email")
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the user.
	 * 
	 * @param email
	 *            New email of the user.
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @see org.dejava.service.accesscontrol.model.principal.Principal#getValue()
	 */
	@Override
	@Transient
	public Object getValue() {
		return getEmail();
	}

	/**
	 * Public constructor.
	 */
	public Email() {
		super();
	}

	/**
	 * Public constructor.
	 * 
	 * @param email
	 *            Email of the user.
	 */
	public Email(final String email) {
		super();
		this.email = email;
	}

}
