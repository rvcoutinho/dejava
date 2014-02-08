package org.dejava.service.philanthropy.model.project.plan;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.philanthropy.model.party.NonProfitOrg;

/**
 * Philanthropy planned project.
 */
@Entity
@Table(name = "project_plan")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.model", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.error", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class ProjectPlan extends ProjectIdea {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1192525905793804431L;

	/**
	 * The organization for the project.
	 */
	private NonProfitOrg organization;

	/**
	 * Gets the organization for the project.
	 * 
	 * @return The organization for the project.
	 */
	@ManyToOne
	@JoinColumn(name = "organization")
	public NonProfitOrg getOrganization() {
		return organization;
	}

	/**
	 * Sets the organization for the project.
	 * 
	 * @param organization
	 *            New organization for the project.
	 */
	public void setOrganization(final NonProfitOrg organization) {
		this.organization = organization;
	}

}
