package org.dejava.service.philanthropy.model.share;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.philanthropy.model.party.Supporter;
import org.dejava.service.philanthropy.model.project.Project;

/**
 * A project share action.
 */
@Entity
@Table(name = "project_share")
@NamedQueries(value = {
		@NamedQuery(name = "countShareByProjectAndSupporter", query = "SELECT count(share) FROM ProjectShare share WHERE share.project.identifier = :projectId AND share.supporter.identifier = :supporterId"),
		@NamedQuery(name = "countSharesByProject", query = "SELECT count(share) FROM ProjectShare share WHERE share.project.identifier = :projectId"),
		@NamedQuery(name = "countSharesBySupporter", query = "SELECT count(share) FROM ProjectShare share WHERE share.supporter.identifier = :supporterId") })
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.model", entriesAffix = { "", ".description" }, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class ProjectShare extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1046831114572773930L;

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
	 * The date when the project was shared.
	 */
	private Date date;

	/**
	 * Gets the date when the project was shared.
	 * 
	 * @return The date when the project was shared.
	 */
	@Column(name = "date")
	public Date getDate() {
		// If the date is null.
		if (date == null) {
			// The date is now.
			date = new Date();
		}
		// Returns the date.
		return date;
	}

	/**
	 * Sets the date when the project was shared.
	 * 
	 * @param date
	 *            New date when the project was shared.
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Default constructor.
	 */
	public ProjectShare() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param projectId
	 *            The identifier of the project that been shared.
	 * @param supporterId
	 *            The identifier of the supporter who has shared the project.
	 */
	public ProjectShare(final Integer projectId, final Integer supporterId) {
		super();
		this.project = new Project(projectId);
		this.supporter = new Supporter(supporterId);
	}

}
