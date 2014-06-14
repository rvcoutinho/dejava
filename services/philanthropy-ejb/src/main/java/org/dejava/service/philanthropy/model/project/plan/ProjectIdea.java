package org.dejava.service.philanthropy.model.project.plan;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.philanthropy.model.project.Project;
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
	 * The project for the idea.
	 */
	private Project project;

	/**
	 * Gets the project for the idea.
	 * 
	 * @return The project for the idea.
	 */
	@OneToOne(mappedBy = "idea")
	public Project getProject() {
		return project;
	}

	/**
	 * Sets the project for the idea.
	 * 
	 * @param project
	 *            New project for the idea.
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * The authors (relationship) of the project idea.
	 */
	private Set<ProjectAuthor> authors;

	/**
	 * Gets the authors (relationship) of the project idea.
	 * 
	 * @return The authors (relationship) of the project idea.
	 */
	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<ProjectAuthor> getAuthors() {
		// If there are no authors.
		if (authors == null) {
			// Creates a new list for the authors.
			authors = new HashSet<>();
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
	public void setAuthors(final Set<ProjectAuthor> authors) {
		this.authors = authors;
	}

}
