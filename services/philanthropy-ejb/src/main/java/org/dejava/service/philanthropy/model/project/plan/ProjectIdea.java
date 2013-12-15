package org.dejava.service.philanthropy.model.project.plan;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.philanthropy.model.party.Supporter;
import org.dejava.service.philanthropy.model.project.author.ProjectAuthor;

/**
 * Philanthropy project idea (pre plan).
 */
@Entity
@Table(name = "project_idea")
@NamedQueries(value = { @NamedQuery(name = "countIdeasBySupporter", query = "SELECT count(author) FROM ProjectAuthor author WHERE author.supporter.identifier = :supporterId") })
@MessageSources(sources = {
		@MessageSource(bundleBaseName = "org.dejava.service.philanthropy.properties.model", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(bundleBaseName = "org.dejava.service.philanthropy.properties.error", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class ProjectIdea extends AbstractProjectPlan {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1192525905793804431L;

	/**
	 * The authors (relationship) of the project idea.
	 */
	private Collection<ProjectAuthor> projectAuthors;

	/**
	 * Gets the authors (relationship) of the project idea.
	 * 
	 * @return The authors (relationship) of the project idea.
	 */
	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Collection<ProjectAuthor> getProjectAuthors() {
		// If there are no authors.
		if (projectAuthors == null) {
			// Creates a new list for the authors.
			projectAuthors = new ArrayList<>();
		}
		// Returns the authors.
		return projectAuthors;
	}

	/**
	 * Sets the authors (relationship) of the project idea.
	 * 
	 * @param projectAuthors
	 *            New authors (relationship) of the project idea.
	 */
	public void setProjectAuthors(final Collection<ProjectAuthor> projectAuthors) {
		this.projectAuthors = projectAuthors;
	}

	/**
	 * The authors of the project idea.
	 */
	private Collection<Supporter> authors;

	/**
	 * Gets the authors of the project idea.
	 * 
	 * @return The authors of the project idea.
	 */
	@Transient
	public Collection<Supporter> getAuthors() {
		// If there are no authors.
		if (authors == null) {
			// Creates a new list for the authors.
			authors = new ArrayList<>();
		}
		// Returns the authors.
		return authors;
	}

	/**
	 * Sets the authors of the project idea.
	 * 
	 * @param authors
	 *            New authors of the project idea.
	 */
	protected void setAuthors(final Collection<Supporter> authors) {
		this.authors = authors;
	}

	/**
	 * Retrieves the authors from the project author relationship.
	 */
	@PostLoad
	private void retrieveAuthors() {
		// If there are any authors.
		if (getProjectAuthors() != null) {
			// For each author.
			for (final ProjectAuthor currentAuthor : getProjectAuthors()) {
				// Adds the author to the authors list.
				getAuthors().add(currentAuthor.getSupporter());
			}
		}
	}

}
