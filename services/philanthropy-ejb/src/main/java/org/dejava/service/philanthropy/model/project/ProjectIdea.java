package org.dejava.service.philanthropy.model.project;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.philanthropy.model.party.Supporter;

/**
 * Philanthropy idea.
 */
@Entity
@Table(name = "project_idea")
@MessageSources(sources = {
		@MessageSource(bundleBaseName = "org.dejava.service.philanthropy.properties.model", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(bundleBaseName = "org.dejava.service.philanthropy.properties.error", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class ProjectIdea extends AbstractProject {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1192525905793804431L;

	/**
	 * The author of the project idea.
	 */
	private Supporter author;

	/**
	 * Gets the author of the project idea.
	 * 
	 * @return The author of the project idea.
	 */
	@ManyToOne
	@JoinColumn(name = "author")
	public Supporter getAuthor() {
		return author;
	}

	/**
	 * Sets the author of the project idea.
	 * 
	 * @param author
	 *            New author of the project idea.
	 */
	public void setAuthor(final Supporter author) {
		this.author = author;
	}

}
