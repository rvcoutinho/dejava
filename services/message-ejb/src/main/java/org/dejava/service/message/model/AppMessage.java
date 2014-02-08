package org.dejava.service.message.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Application message.
 */
@Entity
@Table(name = "app_message")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries(value = { @NamedQuery(name = "getAppMessagesByParty", query = "SELECT message FROM AppMessage message WHERE message.sender = :partyId OR message.recipient = :partyId ORDER BY message.date DESC") })
public class AppMessage extends Message {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4461770040372376096L;

	/**
	 * The message sender (identifier).
	 */
	private Integer sender;

	/**
	 * Gets the message sender (identifier).
	 * 
	 * @return The message sender (identifier).
	 */
	@Override
	@Column(name = "sender")
	public Integer getSender() {
		return sender;
	}

	/**
	 * Sets the message sender (identifier).
	 * 
	 * @param sender
	 *            New message sender (identifier).
	 */
	protected void setSender(final Integer sender) {
		this.sender = sender;
	}

	/**
	 * The message recipient (identifier).
	 */
	private Integer recipient;

	/**
	 * Returns the message recipient (identifier).
	 * 
	 * @return The message recipient (identifier).
	 */
	@Override
	@Column(name = "recipient")
	public Integer getRecipient() {
		return recipient;
	}

	/**
	 * Sets the message recipient (identifier).
	 * 
	 * @param recipient
	 *            New message recipient (identifier).
	 */
	protected void setRecipient(final Integer recipient) {
		this.recipient = recipient;
	}

	/**
	 * The content of the message.
	 */
	private String content;

	/**
	 * @see org.dejava.service.message.model.Message#getContent()
	 */
	@Override
	@Column(name = "content")
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content of the message.
	 * 
	 * @param content
	 *            New content of the message.
	 */
	public void setContent(final String content) {
		this.content = content;
	}

}
