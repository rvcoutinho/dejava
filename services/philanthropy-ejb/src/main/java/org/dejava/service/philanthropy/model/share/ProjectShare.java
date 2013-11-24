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
import org.dejava.service.philanthropy.model.project.AbstractProject;

/**
 * An abstractProject share action.
 */
@Entity
@Table(name = "idea_share")
@NamedQueries(value = { @NamedQuery(name = "countProjectShares", query = "SELECT count(share) FROM ProjectShare share WHERE share.project.identifier = :projectId") })
@MessageSources(sources = {
		@MessageSource(bundleBaseName = "org.dejava.service.philanthropy.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(bundleBaseName = "org.dejava.service.philanthropy.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class ProjectShare extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1046831114572773930L;

	/**
	 * The abstractProject that has been shared.
	 */
	private AbstractProject abstractProject;

	/**
	 * Gets the abstractProject that has been shared.
	 * 
	 * @return The abstractProject that has been shared.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "abstractProject")
	public AbstractProject getProject() {
		return abstractProject;
	}

	/**
	 * Sets the abstractProject that has been shared.
	 * 
	 * @param abstractProject
	 *            New abstractProject that has been shared.
	 */
	public void setProject(final AbstractProject abstractProject) {
		this.abstractProject = abstractProject;
	}

	/**
	 * The supporter who has shared the abstractProject.
	 */
	private Supporter supporter;

	/**
	 * Gets the supporter who has shared the abstractProject.
	 * 
	 * @return The supporter who has shared the abstractProject.
	 */
	@ManyToOne
	@JoinColumn(name = "supporter")
	public Supporter getSupporter() {
		return supporter;
	}

	/**
	 * Sets the supporter who has shared the abstractProject.
	 * 
	 * @param supporter
	 *            New supporter who has shared the abstractProject.
	 */
	public void setSupporter(final Supporter supporter) {
		this.supporter = supporter;
	}

	/**
	 * The date when the abstractProject was shared.
	 */
	private Date date;

	/**
	 * Gets the date when the abstractProject was shared.
	 * 
	 * @return The date when the abstractProject was shared.
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
	 * Sets the date when the abstractProject was shared.
	 * 
	 * @param date
	 *            New date when the abstractProject was shared.
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
	 * @param abstractProject
	 *            The abstractProject that has been shared.
	 * @param supporter
	 *            The supporter who has shared the abstractProject.
	 */
	public ProjectShare(final AbstractProject abstractProject, final Supporter supporter) {
		super();
		this.supporter = supporter;
		this.abstractProject = abstractProject;
	}

}
