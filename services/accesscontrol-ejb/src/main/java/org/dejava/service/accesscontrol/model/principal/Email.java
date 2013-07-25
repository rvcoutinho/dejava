package org.dejava.service.accesscontrol.model.principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Email of the user (as principal).
 */
@Entity
@Table(name = "email")
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.service.accesscontrol.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }) })
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
