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
import org.dejava.component.ejb.entity.ExternalEntity;
import org.dejava.service.location.model.Location;

/**
 * Philanthropy idea.
 */
@Entity
@Table(name = "idea")
@Inheritance(strategy = InheritanceType.JOINED)
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
	 * The location id for the idea.
	 */
	private Integer locationId;

	/**
	 * Gets the location id for the idea.
	 * 
	 * @return The location id for the idea.
	 */
	@Column(name = "location")
	protected Integer getLocationId() {
		// If the location is null.
		if (location == null) {
			// Returns the stored id.
			return locationId;
		}
		// If the location is not null.
		else {
			// Returns the id of the location.
			return getLocation().getIdentifier();
		}
	}

	/**
	 * Sets the location id for the idea.
	 * 
	 * @param locationId
	 *            New location id for the idea.
	 */
	protected void setLocationId(final Integer locationId) {
		this.locationId = locationId;
	}

	/**
	 * The location for the idea.
	 */
	@ExternalEntity(retrieveObj = "", idsMethod = "")
	private Location location;

	/**
	 * Gets the location for the idea.
	 * 
	 * @return The location for the idea.
	 */
	@Transient
	public Location getLocation() {
		return location;
	}

	/**
	 * Sets the location for the idea.
	 * 
	 * @param location
	 *            New location for the idea.
	 */
	public void setLocation(final Location location) {
		// Sets the location.
		this.location = location;
		// Resets the location id.
		setLocationId(null);
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
