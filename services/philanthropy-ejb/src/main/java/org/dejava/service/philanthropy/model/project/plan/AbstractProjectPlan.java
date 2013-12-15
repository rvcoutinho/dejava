package org.dejava.service.philanthropy.model.project.plan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.ejb.entity.ExternalEntity;
import org.dejava.component.validation.constant.MessageTemplateWildCards;
import org.dejava.service.philanthropy.util.MessageTypes;
import org.dejava.service.place.model.Place;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Philanthropy planned project.
 */
@Entity
@Table(name = "abstract_project_plan")
public abstract class AbstractProjectPlan extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1052347648412720881L;

	/**
	 * The name of the project.
	 */
	private String name;

	/**
	 * Gets the name of the project.
	 * 
	 * @return The name of the project.
	 */
	@Column(name = "name")
	@NotNull(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".name.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".name.notempty")
	@Length(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".name.length", min = 7, max = 35)
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the project.
	 * 
	 * @param name
	 *            New name of the project.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * The short description of the idea.
	 */
	private String shortDesc;

	/**
	 * Gets the short description of the idea.
	 * 
	 * @return The short description of the idea.
	 */
	@Column(name = "short_description")
	@NotNull(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".shortdesc.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".shortdesc.notempty")
	@Length(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".shortdesc.length", min = 30, max = 130)
	public String getShortDesc() {
		return shortDesc;
	}

	/**
	 * Sets the short description of the idea.
	 * 
	 * @param shortDesc
	 *            New short description of the idea.
	 */
	public void setShortDesc(final String shortDesc) {
		this.shortDesc = shortDesc;
	}

	/**
	 * The target area id for the project.
	 */
	private Integer targetAreaId;

	/**
	 * Gets the target area id for the project.
	 * 
	 * @return The target area id for the project.
	 */
	@Column(name = "target_area")
	protected Integer getTargetAreaId() {
		return targetAreaId;
	}

	/**
	 * Sets the target area id for the project.
	 * 
	 * @param targetAreaId
	 *            New target area id for the project.
	 */
	protected void setTargetAreaId(final Integer targetAreaId) {
		this.targetAreaId = targetAreaId;
	}

	/**
	 * The target area for the project.
	 */
	@ExternalEntity(retrieveObj = "java:app/targetArea-ejb/Component/Place/Place", paramsValuesMethod = "getTargetAreaId")
	private Place targetArea;

	/**
	 * Gets the target area for the project.
	 * 
	 * @return The target area for the project.
	 */
	@Transient
	public Place getTargetArea() {
		return targetArea;
	}

	/**
	 * Sets the target area for the project.
	 * 
	 * @param targetArea
	 *            New target area for the project.
	 */
	public void setTargetArea(final Place targetArea) {
		this.targetArea = targetArea;
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
	@NotNull(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".targetaudience.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".targetaudience.notempty")
	@Length(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".targetaudience.length", min = 10, max = 100)
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
	 * The motivation for the project.
	 */
	private String motivation;

	/**
	 * Gets the motivation for the project.
	 * 
	 * @return The motivation for the project.
	 */
	@Column(name = "motivation")
	@NotNull(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".motivation.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".motivation.notempty")
	@Length(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".motivation.length", min = 100, max = 500)
	public String getMotivation() {
		return motivation;
	}

	/**
	 * Sets the motivation for the project.
	 * 
	 * @param motivation
	 *            New motivation for the project.
	 */
	public void setMotivation(final String motivation) {
		this.motivation = motivation;
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
	@NotEmpty(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".goals.notempty")
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
	 * The description of the idea.
	 */
	private String desc;

	/**
	 * Gets the description of the idea.
	 * 
	 * @return The description of the idea.
	 */
	@Column(name = "description")
	@NotNull(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".desc.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".desc.notempty")
	@Length(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".desc.length", min = 100, max = 1000)
	public String getDesc() {
		return desc;
	}

	/**
	 * Sets the description of the idea.
	 * 
	 * @param desc
	 *            New description of the idea.
	 */
	public void setDesc(final String desc) {
		this.desc = desc;
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
	@NotNull(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".investment.notnull")
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
	@NotNull(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".time.notnull")
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
	 * The display image for the project.
	 */
	private byte[] displayImage;

	/**
	 * Gets the display image for the project.
	 * 
	 * @return The display image for the project.
	 */
	@Lob
	@Column(name = "display_image")
	public byte[] getDisplayImage() {
		return displayImage;
	}

	/**
	 * Sets the display image for the project.
	 * 
	 * @param displayImage
	 *            New display image for the project.
	 */
	public void setDisplayImage(final byte[] displayImage) {
		this.displayImage = displayImage;
	}

	/**
	 * The video URL for the project.
	 */
	private String videoURL;

	/**
	 * Gets the video URL for the project.
	 * 
	 * @return The video URL for the project.
	 */
	@Column(name = "video_url")
	public String getVideoURL() {
		return videoURL;
	}

	/**
	 * Sets the video URL for the project.
	 * 
	 * @param videoURL
	 *            New video URL for the project.
	 */
	public void setVideoURL(final String videoURL) {
		this.videoURL = videoURL;
	}

}
