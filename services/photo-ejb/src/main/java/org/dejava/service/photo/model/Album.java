package org.dejava.service.photo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.photo.util.MessageTypes;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Represents a photo album.
 */
@Entity
@Table(name = "album")
@Inheritance(strategy = InheritanceType.JOINED)
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.photo.properties.model", entriesAffix = {
				"", ".description" }, processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.photo.properties.error", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class Album extends AbstractIdentifiedEntity {

	/**
	 * Generated serial
	 */
	private static final long serialVersionUID = -218528996563536826L;

	/**
	 * The name of the album.
	 */
	private String name;

	/**
	 * Gets the name of the album.
	 * 
	 * @return The name of the album.
	 */
	@Column(name = "name")
	@NotNull(payload = MessageTypes.Error.class, message = "album.name.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = "album.name.notempty")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the album.
	 * 
	 * @param name
	 *            New name of the album.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * The description of the album.
	 */
	private String description;

	/**
	 * Gets the description of the album.
	 * 
	 * @return The description of the album.
	 */
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the album.
	 * 
	 * @param description
	 *            New description of the album.
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * The owner of the album.
	 */
	private Integer owner;

	/**
	 * Gets the owner of the album.
	 * 
	 * @return The owner of the album.
	 */
	@Column(name = "owner")
	@NotNull(payload = MessageTypes.Error.class, message = "album.owner.notnull")
	public Integer getOwner() {
		return owner;
	}

	/**
	 * Sets the owner of the album.
	 * 
	 * @param owner
	 *            New owner of the album.
	 */
	public void setOwner(final Integer owner) {
		this.owner = owner;
	}

	/**
	 * The photos in the album.
	 */
	private List<Photo> photos;

	/**
	 * Gets the photos in the album.
	 * 
	 * @return The photos in the album.
	 */
	// @OrderColumn(name = "album_index") FIXME
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
	public List<Photo> getPhotos() {
		// If there are no photos.
		if (photos == null) {
			// Creates a new list for the photos.
			photos = new ArrayList<>();
		}
		// Returns the photos.
		return photos;
	}

	/**
	 * Sets the photos in the album.
	 * 
	 * @param photos
	 *            New photos in the album.
	 */
	public void setPhotos(final List<Photo> photos) {
		this.photos = photos;
	}

	/**
	 * The album cover.
	 */
	private Photo cover;

	/**
	 * Gets the album cover.
	 * 
	 * @return The album cover.
	 */
	@OneToOne
	@JoinColumn(name = "cover")
	public Photo getCover() {
		// If the cover is null.
		if (cover == null) {
			// If there are any photos in the album.
			if ((getPhotos() != null) && (!getPhotos().isEmpty())) {
				// The cover is the first photo in the album.
				cover = getPhotos().get(0);
			}
		}
		// Returns the cover.
		return cover;
	}

	/**
	 * Sets the album cover.
	 * 
	 * @param cover
	 *            New album cover.
	 */
	public void setCover(final Photo cover) {
		this.cover = cover;
	}

	/**
	 * Default constructor.
	 */
	public Album() {
	}

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            The name of the album.
	 * @param owner
	 *            The owner of the album.
	 * @param photos
	 *            The photos in the album.
	 */
	public Album(final String name, final Integer owner, final List<Photo> photos) {
		this.name = name;
		this.photos = photos;
		this.owner = owner;
	}

}
