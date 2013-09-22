package org.dejava.service.message.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Email message.
 */
@Entity
@Table(name = "email_message")
@Inheritance(strategy = InheritanceType.JOINED)
public class EmailMessage extends Message {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 2867581639813845489L;

	/**
	 * The message sender.
	 */
	private String sender;

	/**
	 * @see org.dejava.service.message.model.Message#getSender()
	 */
	@Override
	@Column(name = "sender")
	public String getSender() {
		return sender;
	}

	/**
	 * Sets the message sender.
	 * 
	 * @param sender
	 *            New message sender.
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * The message recipient.
	 */
	private String recipient;

	/**
	 * @see org.dejava.service.message.model.Message#getRecipient()
	 */
	@Override
	@Column(name = "recipient")
	public String getRecipient() {
		return recipient;
	}

	/**
	 * Sets the message recipient.
	 * 
	 * @param recipient
	 *            New message recipient.
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	/**
	 * The subject of the message.
	 */
	public String subject;

	/**
	 * Gets the subject of the message.
	 * 
	 * @return The subject of the message.
	 */
	@Column(name = "subject")
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject of the message.
	 * 
	 * @param subject
	 *            New subject of the message.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * The content of the message.
	 */
	private String content;

	/**
	 * @see org.dejava.service.message.model.Message#getContent()
	 */
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
	public void setContent(String content) {
		this.content = content;
	}

}
