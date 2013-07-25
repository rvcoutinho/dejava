package org.dejava.service.philanthropy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.place.model.Place;

/**
 * Philanthropy idea.
 */
@Entity
@Table(name = "idea")
@Inheritance(strategy = InheritanceType.JOINED)
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.service.philanthropy.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }) })
public class Idea extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1192525905793804431L;

	/**
	 * Name of the idea.
	 */
	private String name;

	/**
	 * Gets the name of the idea.
	 * 
	 * @return The name of the idea.
	 */
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
	 * Description of the idea.
	 */
	private String description;

	/**
	 * Gets the description of the idea.
	 * 
	 * @return The description of the idea.
	 */
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
	 * Category of the idea.
	 */
	private Category category;

	/**
	 * Gets the category of the idea.
	 * 
	 * @return The category of the idea.
	 */
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
	 * The place id for the idea.
	 */
	private Integer placeId;

	/**
	 * Gets the place id for the idea.
	 * 
	 * @return The place id for the idea.
	 */
	@Column(name = "place")
	protected Integer getPlaceId() {
		// If the place is null.
		if (place == null) {
			// Returns the stored id.
			return placeId;
		}
		// If the place is not null.
		else {
			// Returns the id of the place.
			return getPlace().getIdentifier();
		}
	}

	/**
	 * Sets the place id for the idea.
	 * 
	 * @param placeId
	 *            New place id for the idea.
	 */
	protected void setPlaceId(final Integer placeId) {
		this.placeId = placeId;
	}

	/**
	 * The place for the idea.
	 */
	private Place place;

	/**
	 * Gets the place for the idea.
	 * 
	 * @return The place for the idea.
	 */
	@Transient
	public Place getPlace() {
		return place;
	}

	/**
	 * Sets the place for the idea.
	 * 
	 * @param place
	 *            New place for the idea.
	 */
	public void setPlace(final Place place) {
		// Sets the place.
		this.place = place;
		// Resets the place id.
		setPlaceId(null);
	}

	/**
	 * The author id for the idea.
	 */
	private Integer authorId;

	/**
	 * Gets the author id for the idea.
	 * 
	 * @return The author id for the idea.
	 */
	@Column(name = "author")
	public Integer getAuthorId() {
		return authorId;
	}

	/**
	 * Sets the author id for the idea.
	 * 
	 * @param authorId
	 *            New author id for the idea.
	 */
	protected void setAuthorId(final Integer authorId) {
		this.authorId = authorId;
	}

}
