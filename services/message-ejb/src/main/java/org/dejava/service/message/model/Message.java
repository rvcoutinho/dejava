package org.dejava.service.message.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.dejava.component.ejb.entity.AbstractIdentifiedEntity;

/**
 * Represents a message.
 */
@MappedSuperclass
public abstract class Message extends AbstractIdentifiedEntity {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 3981962718307403244L;

	/**
	 * Gets the message sender.
	 * 
	 * @return The message sender.
	 */
	@Transient
	public abstract Object getSender();

	/**
	 * Gets the message recipient.
	 * 
	 * @return The message recipient.
	 */
	@Transient
	public abstract Object getRecipient();

	/**
	 * Gets the content of the message.
	 * 
	 * @return The content of the message.
	 */
	@Transient
	public abstract Serializable getContent();

	/**
	 * Date when the message has been read.
	 */
	private Date read;

	/**
	 * Gets the date when the message has been read.
	 * 
	 * @return The date when the message has been read.
	 */
	@Column(name = "read")
	public Date getRead() {
		return read;
	}

	/**
	 * Sets the date when the message has been read.
	 * 
	 * @param read
	 *            The date when the message has been read.
	 */
	public void setRead(Date read) {
		this.read = read;
	}

	/**
	 * Date of the message.
	 */
	private Date date;

	/**
	 * Gets the date of the message.
	 * 
	 * @return The date of the message.
	 */
	@Column(name = "date")
	public Date getDate() {
		// If the date is null.
		if (date == null) {
			// The date is now.
			date = new Date();
		}
		// Returns the date.
		return date;
	}

	/**
	 * Sets the date of the message.
	 * 
	 * @param date
	 *            New date of the message.
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
