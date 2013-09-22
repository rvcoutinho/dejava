package org.dejava.service.message.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Application message.
 */
@Entity
@Table(name = "app_message")
@Inheritance(strategy = InheritanceType.JOINED)
public class AppMessage extends Message {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4461770040372376096L;

	/**
	 * The message sender.
	 */
	private String sender;

	/**
	 * @see org.dejava.service.message.model.Message#getSender()
	 */
	@Override
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
	public Object getRecipient() {
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
