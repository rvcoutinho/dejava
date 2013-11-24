package org.dejava.service.philanthropy.model.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
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
import javax.persistence.Lob;
import javax.persistence.OrderColumn;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.ejb.entity.ExternalEntity;
import org.dejava.service.party.util.MessageTypes;
import org.dejava.service.philanthropy.component.share.ProjectShareComponent;
import org.dejava.service.philanthropy.model.Category;
import org.dejava.service.place.model.Place;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Philanthropy project.
 */
@Entity
@Table(name = "project")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractProject extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4851354607337382534L;

	/**
	 * Name of the idea.
	 */
	private String name;

	/**
	 * Gets the name of the idea.
	 * 
	 * @return The name of the idea.
	 */
	@NotNull(payload = MessageTypes.Error.class, message = "project.name.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = "project.name.notempty")
	@Length(payload = MessageTypes.Error.class, message = "project.name.length", min = 7, max = 25)
	@Column(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the idea.
	 * 
	 * @param name
	 *            New name of the idea.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Short description of the idea.
	 */
	private String shortDescription;

	/**
	 * Gets the short description of the idea.
	 * 
	 * @return The short description of the idea.
	 */
	@NotNull(payload = MessageTypes.Error.class, message = "project.shortdescription.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = "project.shortdescription.notempty")
	@Length(payload = MessageTypes.Error.class, message = "project.shortdescription.length", min = 30, max = 130)
	@Column(name = "short_description")
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * Sets the short description of the idea.
	 * 
	 * @param shortDescription
	 *            New short description of the idea.
	 */
	public void setShortDescription(final String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * Category of the idea.
	 */
	private Category category;

	/**
	 * Gets the category of the idea.
	 * 
	 * @return The category of the idea.
	 */
	@NotNull(payload = MessageTypes.Error.class, message = "project.category.notnull")
	@Column(name = "category")
	@Enumerated(value = EnumType.ORDINAL)
	public Category getCategory() {
		return category;
	}

	/**
	 * Sets the category of the idea.
	 * 
	 * @param category
	 *            New category of the idea.
	 */
	public void setCategory(final Category category) {
		this.category = category;
	}

	/**
	 * Description of the idea.
	 */
	private String description;

	/**
	 * Gets the description of the idea.
	 * 
	 * @return The description of the idea.
	 */
	@NotNull(payload = MessageTypes.Error.class, message = "project.description.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = "project.description.notempty")
	@Length(payload = MessageTypes.Error.class, message = "project.description.length", min = 100, max = 1000)
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the idea.
	 * 
	 * @param description
	 *            New description of the idea.
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Motivation for the idea.
	 */
	private String motivation;

	/**
	 * Gets the motivation for the idea.
	 * 
	 * @return The motivation for the idea.
	 */
	@Column(name = "motivation")
	public String getMotivation() {
		return motivation;
	}

	/**
	 * Sets the motivation for the idea.
	 * 
	 * @param motivation
	 *            New motivation for the idea.
	 */
	public void setMotivation(final String motivation) {
		this.motivation = motivation;
	}

	/**
	 * The target audience for the project.
	 */
	private String targetAudience;

	/**
	 * Gets the target audience for the project.
	 * 
	 * @return The target audience for the project.
	 */
	@Column(name = "target_audience")
	public String getTargetAudience() {
		return targetAudience;
	}

	/**
	 * Sets the target audience for the project.
	 * 
	 * @param targetAudience
	 *            New target audience for the project.
	 */
	public void setTargetAudience(final String targetAudience) {
		this.targetAudience = targetAudience;
	}

	/**
	 * The target area id for the idea.
	 */
	private Integer targetAreaId;

	/**
	 * Gets the target area id for the idea.
	 * 
	 * @return The target area id for the idea.
	 */
	@Column(name = "target_area")
	protected Integer getTargetAreaId() {
		return targetAreaId;
	}

	/**
	 * Sets the target area id for the idea.
	 * 
	 * @param targetAreaId
	 *            New target area id for the idea.
	 */
	protected void setTargetAreaId(final Integer targetAreaId) {
		this.targetAreaId = targetAreaId;
	}

	/**
	 * The target area for the idea.
	 */
	@ExternalEntity(retrieveObj = "java:app/targetArea-ejb/Component/Place/Place", paramsValuesMethod = "getTargetAreaId")
	private Place targetArea;

	/**
	 * Gets the target area for the idea.
	 * 
	 * @return The target area for the idea.
	 */
	@Transient
	public Place getTargetArea() {
		return targetArea;
	}

	/**
	 * Sets the target area for the idea.
	 * 
	 * @param targetArea
	 *            New target area for the idea.
	 */
	public void setTargetArea(final Place targetArea) {
		this.targetArea = targetArea;
	}

	/**
	 * The goals for the project.
	 */
	private List<String> goals;

	/**
	 * Gets the goals for the project.
	 * 
	 * @return The goals for the project.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "project_goal", joinColumns = { @JoinColumn(name = "project_id") })
	@OrderColumn(name = "priority")
	@Column(name = "goal")
	public List<String> getGoals() {
		// If goals is null.
		if (goals == null) {
			// Creates a new list for the goals.
			goals = new ArrayList<>();
		}
		// Returns the goals.
		return goals;
	}

	/**
	 * Sets the goals for the project.
	 * 
	 * @param goals
	 *            New goals for the project.
	 */
	public void setGoals(List<String> goals) {
		this.goals = goals;
	}

	/**
	 * The expected investment for the project.
	 */
	private BigDecimal investment;

	/**
	 * Gets the expected investment for the project.
	 * 
	 * @return The expected investment for the project.
	 */
	@Column(name = "investment")
	public BigDecimal getInvestment() {
		return investment;
	}

	/**
	 * Sets the expected investment for the project.
	 * 
	 * @param investment
	 *            New expected investment for the project.
	 */
	public void setInvestment(final BigDecimal investment) {
		this.investment = investment;
	}

	/**
	 * The estimated project time (in days).
	 */
	private Long time;

	/**
	 * Gets the estimated project time (in days).
	 * 
	 * @return The estimated project time (in days).
	 */
	@Column(name = "time")
	public Long getTime() {
		return time;
	}

	/**
	 * Sets the estimated project time (in days).
	 * 
	 * @param time
	 *            New estimated project time (in days).
	 */
	public void setTime(final Long time) {
		this.time = time;
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
	 * Sets the tags for the project (in a single string, and split by ",").
	 * 
	 * @param tags
	 *            New tags for the project (in a single string, and split by ",").
	 */
	public void setAllTags(final String tags) {
		// If the given tags are not null.
		if (tags != null) {
			// The final tags to be set.
			final Set<String> finalTags = new HashSet<>();
			// If there are tags.
			if (tags != null) {
				// For each tag.
				for (String currentTag : tags.split(",")) {
					// If the tag exists.
					if (currentTag != null) {
						// At first, trim the tag.
						currentTag = currentTag.trim();
						// If the tag is not empty.
						if (!currentTag.isEmpty()) {
							// Adds the current tag to the final tags to be set.
							finalTags.add(currentTag);
						}
					}
				}
			}
			// Sets the new tags.
			setTags(finalTags);
		}
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
	 * Counts and updates the number of project shares.
	 * 
	 * @throws NamingException
	 *             If the project share component could not be located.
	 */
	@PostLoad
	@PostUpdate
	@PostPersist
	@Transient
	private void countProjectShares() throws NamingException {
		// Gets the project share component.
		final ProjectShareComponent projectShareComponent = InitialContext
				.doLookup("java:app/philanthropy-ejb/Component/Philanthropy/ProjectShare");
		// Sets the shares for the project.
		setShares(projectShareComponent.countProjectShares(getIdentifier()));
	}

	// Temporary

	/**
	 * The display image for the idea.
	 */
	private byte[] displayImage;

	/**
	 * Gets the display image for the idea.
	 * 
	 * @return The display image for the idea.
	 */
	@Lob
	@Column(name = "display_image")
	public byte[] getDisplayImage() {
		return displayImage;
	}

	/**
	 * Sets the display image for the idea.
	 * 
	 * @param displayImage
	 *            New display image for the idea.
	 */
	public void setDisplayImage(final byte[] displayImage) {
		this.displayImage = displayImage;
	}

	/**
	 * The video URL for the project.
	 */
	private String video;

	/**
	 * Gets the video URL for the project.
	 * 
	 * @return The video URL for the project.
	 */
	@Column(name = "video")
	public String getVideo() {
		return video;
	}

	/**
	 * Sets the video URL for the project.
	 * 
	 * @param video
	 *            New video URL for the project.
	 */
	public void setVideo(final String video) {
		this.video = video;
	}

}
