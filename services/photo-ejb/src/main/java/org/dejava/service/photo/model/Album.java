package org.dejava.service.photo.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

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
	 * The photos in the album.
	 */
	private Collection<Photo> photos;

	/**
	 * Gets the photos in the album.
	 * 
	 * @return The photos in the album.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
	public Collection<Photo> getPhotos() {
		return photos;
	}

	/**
	 * Sets the photos in the album.
	 * 
	 * @param photos
	 *            New photos in the album.
	 */
	public void setPhotos(final Collection<Photo> photos) {
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

}
