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
 * Name of the user (as principal).
 */
@Entity
@Table(name = "name")
@MessageSources(sources = {
		@MessageSource(bundleBaseName = "org.dejava.service.accesscontrol.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(bundleBaseName = "org.dejava.service.accesscontrol.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class Name extends Principal {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -5457903376671909886L;

	/**
	 * Name of the user.
	 */
	private String name;

	/**
	 * Gets the name of the user.
	 * 
	 * @return The name of the user.
	 */
	@Column(name = "name")
	@NotNull(payload = MessageTypes.Error.class, message = "name.name.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = "name.name.notempty")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the user.
	 * 
	 * @param name
	 *            New name of the user.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @see org.dejava.service.accesscontrol.model.principal.Principal#getValue()
	 */
	@Override
	@Transient
	public Object getValue() {
		return getName();
	}

	/**
	 * Public constructor.
	 */
	public Name() {
		super();
	}

	/**
	 * Public constructor.
	 * 
	 * @param name
	 *            Name of the user.
	 */
	public Name(final String name) {
		super();
		this.name = name;
	}

}
