package org.dejava.service.philanthropy.model.project;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.philanthropy.model.party.NGO;

/**
 * Philanthropy idea.
 */
@Entity
@Table(name = "planned_project")
@MessageSources(sources = {
		@MessageSource(bundleBaseName = "org.dejava.service.philanthropy.properties.model", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(bundleBaseName = "org.dejava.service.philanthropy.properties.error", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class PlannedProject extends Project {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1192525905793804431L;

	/**
	 * The original idea for the project.
	 */
	private ProjectIdea originalIdea;

	/**
	 * Gets the original idea for the project.
	 * 
	 * @return The original idea for the project.
	 */
	@OneToOne
	@JoinColumn(name = "original_idea")
	public ProjectIdea getOriginalIdea() {
		return originalIdea;
	}

	/**
	 * Sets the original idea for the project.
	 * 
	 * @param originalIdea
	 *            New original idea for the project.
	 */
	public void setOriginalIdea(final ProjectIdea originalIdea) {
		this.originalIdea = originalIdea;
	}

	/**
	 * The organization for the project.
	 */
	private NGO organization;

	/**
	 * Gets the organization for the project.
	 * 
	 * @return The organization for the project.
	 */
	@ManyToOne
	@JoinColumn(name = "organization")
	public NGO getOrganization() {
		return organization;
	}

	/**
	 * Sets the organization for the project.
	 * 
	 * @param organization
	 *            New organization for the project.
	 */
	public void setOrganization(final NGO organization) {
		this.organization = organization;
	}

}
