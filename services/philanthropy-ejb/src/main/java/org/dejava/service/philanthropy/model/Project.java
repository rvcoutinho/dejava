package org.dejava.service.philanthropy.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.party.model.Organization;

/**
 * Philanthropy project.
 */
@Entity
@Table(name = "project")
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.service.philanthropy.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }) })
public class Project extends Idea {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4851354607337382534L;

	/**
	 * Foundation supporting the philanthropy project.
	 */
	private Organization foundation;

	/**
	 * Gets the foundation supporting the philanthropy project.
	 * 
	 * @return The foundation supporting the philanthropy project.
	 */
	public Organization getFoundation() {
		return foundation;
	}

	/**
	 * Sets the foundation supporting the philanthropy project.
	 * 
	 * @param foundation
	 *            New foundation supporting the philanthropy project.
	 */
	public void setFoundation(final Organization foundation) {
		this.foundation = foundation;
	}

}
