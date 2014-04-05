package org.dejava.service.philanthropy.model.project.author;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.philanthropy.model.party.Supporter;
import org.dejava.service.philanthropy.model.project.Project;

/**
 * A project author relationship.
 */
@Entity
@Table(name = "ProjectAuthor")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.model", entriesAffix = { "", ".description" }, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class ProjectAuthor extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 6407671084382636313L;

	/**
	 * The project that has been shared.
	 */
	private Project project;

	/**
	 * Gets the project that has been shared.
	 * 
	 * @return The project that has been shared.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project")
	public Project getProject() {
		return project;
	}

	/**
	 * Sets the project that has been shared.
	 * 
	 * @param project
	 *            New project that has been shared.
	 */
	public void setProject(final Project project) {
		this.project = project;
	}

	/**
	 * The supporter who has shared the project.
	 */
	private Supporter supporter;

	/**
	 * Gets the supporter who has shared the project.
	 * 
	 * @return The supporter who has shared the project.
	 */
	@ManyToOne
	@JoinColumn(name = "supporter")
	public Supporter getSupporter() {
		return supporter;
	}

	/**
	 * Sets the supporter who has shared the project.
	 * 
	 * @param supporter
	 *            New supporter who has shared the project.
	 */
	public void setSupporter(final Supporter supporter) {
		this.supporter = supporter;
	}

	/**
	 * Default constructor.
	 */
	public ProjectAuthor() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param project
	 *            The project that has been authored.
	 * @param supporter
	 *            The supporter who has authored the project.
	 */
	public ProjectAuthor(final Project project, final Supporter supporter) {
		super();
		this.supporter = supporter;
		this.project = project;
	}

}
