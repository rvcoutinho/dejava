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
 * Facebook facebookIdentifier of the user (as principal).
 */
@Entity
@Table(name = "facebook")
@MessageSources(sources = {
		@MessageSource(bundleBaseName = "org.dejava.properties.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(bundleBaseName = "org.dejava.properties.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class Facebook extends Principal {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -7876818384414658522L;

	/**
	 * Facebook facebookIdentifier of the user.
	 */
	private String facebookIdentifier;

	/**
	 * Gets the facebook facebookIdentifier of the user.
	 * 
	 * @return The facebook facebookIdentifier of the user.
	 */
	@Column(name = "facebookIdentifier")
	@NotNull(payload = MessageTypes.Error.class, message = "facebook.facebookidentifier.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = "facebook.facebookidentifier.notempty")
	public String getFacebookIdentifier() {
		return facebookIdentifier;
	}

	/**
	 * Sets the facebook facebookIdentifier of the user.
	 * 
	 * @param facebookIdentifier
	 *            New facebook facebookIdentifier of the user.
	 */
	public void setFacebookIdentifier(final String facebookIdentifier) {
		this.facebookIdentifier = facebookIdentifier;
	}

	/**
	 * @see org.dejava.service.accesscontrol.model.principal.Principal#getValue()
	 */
	@Override
	@Transient
	public Object getValue() {
		return getFacebookIdentifier();
	}

	/**
	 * Public constructor.
	 */
	public Facebook() {
		super();
	}

	/**
	 * Public constructor.
	 * 
	 * @param facebookIdentifier
	 *            Facebook facebookIdentifier of the user.
	 */
	public Facebook(final String facebookIdentifier) {
		super();
		this.facebookIdentifier = facebookIdentifier;
	}

}
