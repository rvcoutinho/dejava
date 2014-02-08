package org.dejava.service.philanthropy.model.project;

import java.util.HashSet;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.component.validation.constant.MessageTemplateWildCards;
import org.dejava.component.validation.method.ArgFormatter;
import org.dejava.service.philanthropy.component.PhilanthropyProjectComponent;
import org.dejava.service.philanthropy.model.Category;
import org.dejava.service.philanthropy.model.party.Sponsor;
import org.dejava.service.philanthropy.model.project.plan.ProjectIdea;
import org.dejava.service.philanthropy.model.project.plan.ProjectPlan;
import org.dejava.service.philanthropy.util.MessageTypes;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Philanthropy project.
 */
@Entity
@Table(name = "project")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries(value = {
		@NamedQuery(name = "countCreatedBySupporter", query = "SELECT count(author) FROM ProjectAuthor author WHERE author.supporter.identifier = :supporterId"),
		@NamedQuery(name = "getCreatedBySupporter", query = "SELECT author.project FROM ProjectAuthor author WHERE author.supporter.identifier = :supporterId"),
		@NamedQuery(name = "getSharedBySupporter", query = "SELECT share.project FROM ProjectShare share WHERE share.supporter.identifier = :supporterId") })
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.model", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.error", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class PhilanthropyProject extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4851354607337382534L;

	/**
	 * Gets the name of the project.
	 * 
	 * @return The name of the project.
	 */
	@Transient
	public String getName() {
		// If there is a plan.
		if (getPlan() != null) {
			// Returns the plan name.
			return getPlan().getName();
		}
		// If there is not.
		else {
			// Returns the idea name.
			return getIdea().getName();
		}
	}

	/**
	 * Gets the description of the project.
	 * 
	 * @return The description of the project.
	 */
	@Transient
	public String getShortDesc() {
		// If there is a plan.
		if (getPlan() != null) {
			// Returns the plan short description.
			return getPlan().getShortDesc();
		}
		// If there is not.
		else {
			// Returns the idea short description.
			return getIdea().getShortDesc();
		}
	}

	/**
	 * Category of the project.
	 */
	private Category category;

	/**
	 * Gets the category of the project.
	 * 
	 * @return The category of the project.
	 */
	@Column(name = "category")
	@Enumerated(value = EnumType.ORDINAL)
	@NotNull(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".category.notnull")
	public Category getCategory() {
		return category;
	}

	/**
	 * Sets the category of the project.
	 * 
	 * @param category
	 *            New category of the project.
	 */
	public void setCategory(final Category category) {
		this.category = category;
	}

	/**
	 * The project initial idea.
	 */
	private ProjectIdea idea;

	/**
	 * Gets the project initial idea.
	 * 
	 * @return The project initial idea.
	 */
	@Valid
	@Null(groups = ProjectPlan.class, payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".idea.null")
	@NotNull(groups = ProjectIdea.class, payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".idea.notnull")
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "idea")
	public ProjectIdea getIdea() {
		return idea;
	}

	/**
	 * Sets the project initial idea.
	 * 
	 * @param idea
	 *            New project initial idea.
	 */
	public void setIdea(final ProjectIdea idea) {
		this.idea = idea;
	}

	/**
	 * The project initial plan.
	 */
	private ProjectPlan plan;

	/**
	 * Gets the project initial plan.
	 * 
	 * @return The project initial plan.
	 */
	@Valid
	@Null(groups = ProjectIdea.class, payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".plan.null")
	@NotNull(groups = ProjectPlan.class, payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".plan.notnull")
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "plan")
	public ProjectPlan getPlan() {
		return plan;
	}

	/**
	 * Sets the project initial plan.
	 * 
	 * @param plan
	 *            New project initial plan.
	 */
	public void setPlan(final ProjectPlan plan) {
		this.plan = plan;
	}

	/**
	 * The sponsor of the project.
	 */
	private Sponsor sponsor;

	/**
	 * Gets the sponsor of the project.
	 * 
	 * @return The sponsor of the project.
	 */
	@ManyToOne
	@JoinColumn(name = "sponsor")
	public Sponsor getSponsor() {
		return sponsor;
	}

	/**
	 * Sets the sponsor of the project.
	 * 
	 * @param sponsor
	 *            New sponsor of the project.
	 */
	public void setSponsor(final Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	/**
	 * The tags for the project.
	 */
	private Set<String> tags;

	/**
	 * Gets the tags for the project.
	 * 
	 * @return The tags for the project.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "project_tag")
	@Column(name = "tag")
	@NotNull(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".tags.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".tags.notempty")
	public Set<String> getTags() {
		// If tags is null.
		if (tags == null) {
			// Creates a new list for the tags.
			tags = new HashSet<>();
		}
		// Returns the tags.
		return tags;
	}

	/**
	 * Sets the tags for the project.
	 * 
	 * @param tags
	 *            New tags for the project.
	 */
	public void setTags(final Set<String> tags) {
		this.tags = tags;
	}

	/**
	 * Gets the tags for the project (in a single string, and split by ",").
	 * 
	 * @return The tags for the project (in a single string, and split by ",").
	 */
	@Transient
	public String getAllTags() {
		// The tags are initially empty.
		final StringBuffer allTags = new StringBuffer();
		// If there are any tags.
		if (getTags() != null) {
			// For each tag.
			for (Integer currentTagIdx = 0; currentTagIdx < getTags().size(); currentTagIdx++) {
				// If any tags have been added.
				if (allTags.length() != 0) {
					// Appends a "," to the tags string.
					allTags.append(", ");
				}
				// Appends the current tag to the string.
				allTags.append(getTags().iterator().next());
			}
		}
		// Returns all tags in a single string.
		return allTags.toString();
	}

	/**
	 * Sets the tags for the project.
	 * 
	 * @param tags
	 *            New tags for the project (in a single string, and split by ",").
	 */
	public void setAllTags(final String tags) {
		// Sets the new tags.
		setTags(new HashSet<>(ArgFormatter.split(tags)));
	}

	/**
	 * The number of shares for the project.
	 */
	private Long shares;

	/**
	 * Gets the number of shares for the project.
	 * 
	 * @return The number of shares for the project.
	 */
	@Transient
	public Long getShares() {
		return shares;
	}

	/**
	 * Sets the number of shares for the project.
	 * 
	 * @param shares
	 *            New number of shares for the project.
	 */
	public void setShares(final Long shares) {
		this.shares = shares;
	}

	/**
	 * Default constructor.
	 */
	public PhilanthropyProject() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param identifier
	 *            The project identifier.
	 */
	public PhilanthropyProject(final Integer identifier) {
		super();
		// Sets the project identifier.
		setIdentifier(identifier);
	}

	/**
	 * Counts and updates the number of project shares.
	 * 
	 * @throws NamingException
	 *             If the project share component could not be located.
	 */
	@PostLoad
	@PostUpdate
	@PostPersist
	@Transient
	private void countSharesByProject() throws NamingException {
		// Gets the project component.
		final PhilanthropyProjectComponent projectComponent = InitialContext
				.doLookup("java:app/philanthropy-ejb/Component/Philanthropy/PhilanthropyProject");
		// Sets the shares for the project.
		setShares(projectComponent.countProjectSharesByProject(getIdentifier()));
	}

}
