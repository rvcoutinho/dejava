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
 * A system user permission.
 */
@Entity
@Table(name = "permission")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class Permission extends AbstractIdentifiedEntity {

	/**
	 * Generated serial
	 */
	private static final long serialVersionUID = -3553749104745641309L;

	/**
	 * The name of the permission.
	 */
	private String name;

	/**
	 * Gets the name of the permission.
	 * 
	 * @return The name of the permission.
	 */
	@Column(name = "name")
	@NotNull(payload = MessageTypes.Error.class, message = "permission.name.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = "permission.name.notnempty")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the permission.
	 * 
	 * @param name
	 *            New name of the permission.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Default constructor.
	 */
	public Permission() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            New name of the permission.
	 */
	public Permission(final String name) {
		super();
		this.name = name;
	}

}
