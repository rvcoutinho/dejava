package org.dejava.service.accesscontrol.model.permission;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.accesscontrol.util.MessageTypes;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * A system user role.
 */
@Entity
@Table(name = "role")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class Role extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -8910104895961543802L;

	/**
	 * The name of the role.
	 */
	private String name;

	/**
	 * Gets the name of the role.
	 * 
	 * @return The name of the role.
	 */
	@Column(name = "name")
	@NotNull(payload = MessageTypes.Error.class, message = "role.name.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = "role.name.notnempty")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the role.
	 * 
	 * @param name
	 *            New name of the role.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Default constructor.
	 */
	public Role() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            New name of the role.
	 */
	public Role(final String name) {
		super();
		this.name = name;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Role other = (Role) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}
