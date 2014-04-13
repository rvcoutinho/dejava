package org.dejava.service.photo.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.photo.util.MessageTypes;

/**
 * Represents a photo.
 */
@Entity
@Table(name = "photo")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries(value = { @NamedQuery(name = "getPhotosByAlbum", query = "SELECT DISTINCT photo FROM Photo photo JOIN photo.album album WHERE album.name = :albumName AND album.owner = :albumOwner") })
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.photo.properties.model", entriesAffix = {
				"", ".description" }, processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.photo.properties.error", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class Photo extends AbstractIdentifiedEntity {

	/**
	 * Generated serial
	 */
	private static final long serialVersionUID = 260461274770755292L;

	/**
	 * The photo content.
	 */
	private byte[] content;

	/**
	 * Gets the photo content.
	 * 
	 * @return The photo content.
	 */
	@Lob
	@Column(name = "content")
	@Basic(fetch = FetchType.LAZY)
	@NotNull(payload = MessageTypes.Error.class, message = "photo.content.notnull")
	public byte[] getContent() {
		return content;
	}

	/**
	 * Sets the photo content.
	 * 
	 * @param content
	 *            New photo content.
	 */
	public void setContent(final byte[] content) {
		this.content = content;
	}

	/**
	 * The description of the photo.
	 */
	private String description;

	/**
	 * Gets the description of the photo.
	 * 
	 * @return The description of the photo.
	 */
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the photo.
	 * 
	 * @param description
	 *            New description of the photo.
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * The album for the photo.
	 */
	private Album album;

	/**
	 * Gets the album for the photo.
	 * 
	 * @return The the album for the photo.
	 */
	@JoinColumn(name = "album")
	@ManyToOne(cascade = CascadeType.PERSIST)
	@NotNull(payload = MessageTypes.Error.class, message = "photo.album.notnull")
	public Album getAlbum() {
		return album;
	}

	/**
	 * Sets the the album for the photo.
	 * 
	 * @param album
	 *            New the album for the photo.
	 */
	public void setAlbum(final Album album) {
		// Sets the album.
		this.album = album;
		// If the album is not null.
		if (getAlbum() != null) {
			// Adds the photo to the album.
			getAlbum().getPhotos().add(this);
		}
	}

	/**
	 * Default constructor.
	 */
	public Photo() {
	}

	/**
	 * Default constructor.
	 * 
	 * @param content
	 *            The photo content.
	 * @param album
	 *            The album for the photo.
	 */
	public Photo(final byte[] content, final Album album) {
		this.content = content;
		this.album = album;
	}

}
