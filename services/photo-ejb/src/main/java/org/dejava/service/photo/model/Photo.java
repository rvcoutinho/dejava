package org.dejava.service.photo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;
import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Represents a photo.
 */
@Entity
@Table(name = "photo")
@Inheritance(strategy = InheritanceType.JOINED)
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
	 * The album for the photo.
	 */
	private Album album;

	/**
	 * Gets the album for the photo.
	 * 
	 * @return The the album for the photo.
	 */
	@ManyToOne
	@JoinColumn(name = "album")
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
		this.album = album;
	}

}
