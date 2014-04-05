package org.dejava.service.philanthropy.model.project.plan;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.philanthropy.model.project.author.ProjectAuthor;

/**
 * Philanthropy project idea (pre-plan).
 */
@Entity
@Table(name = "project_idea")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.model", entriesAffix = {
				"", ".description" }, processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.error", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class ProjectIdea extends AbstractProjectPlan {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1192525905793804431L;

	/**
	 * The authors (relationship) of the project idea.
	 */
	private Collection<ProjectAuthor> authors;

	/**
	 * Gets the authors (relationship) of the project idea.
	 * 
	 * @return The authors (relationship) of the project idea.
	 */
	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	public Collection<ProjectAuthor> getAuthors() {
		// If there are no authors.
		if (authors == null) {
			// Creates a new list for the authors.
			authors = new ArrayList<>();
		}
		// Returns the authors.
		return authors;
	}

	/**
	 * Sets the authors (relationship) of the project idea.
	 * 
	 * @param authors
	 *            New authors (relationship) of the project idea.
	 */
	public void setAuthors(final Collection<ProjectAuthor> authors) {
		this.authors = authors;
	}

}
