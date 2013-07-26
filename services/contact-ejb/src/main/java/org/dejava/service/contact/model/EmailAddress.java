package org.dejava.service.contact.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.contact.util.MessageTypes;
import org.hibernate.validator.constraints.Email;

/**
 * Email address.
 */
@Entity
@Table(name = "email_address")
@Inheritance(strategy = InheritanceType.JOINED)
@MessageSources(sources = {
		@MessageSource(bundleBaseName = "org.dejava.service.contact.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(bundleBaseName = "org.dejava.service.contact.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.FieldAnnotationEntryProcessor" }) })
public class EmailAddress extends Contact {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1227930531632819560L;

	/**
	 * Address of the email address.
	 */
	@Email(payload = MessageTypes.Error.class, message = "emailaddress.address.email")
	private String address;

	/**
	 * Gets the address of the email address.
	 * 
	 * @return The address of the email address.
	 */
	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address of the email address.
	 * 
	 * @param address
	 *            New address of the email address.
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

}
