package org.dejava.service.message.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dejava.component.ejb.entity.ExternalEntity;
import org.dejava.service.party.model.Party;

/**
 * Application notification.
 */
@Entity
@Table(name = "app_notification")
@Inheritance(strategy = InheritanceType.JOINED)
public class AppNotification extends Message {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -2431557934384868984L;

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
	 * The message recipient identifier.
	 */
	private Integer recipientId;

	/**
	 * Returns the message recipient identifier.
	 * 
	 * @return The message recipient identifier.
	 */
	@Column(name = "recipient")
	public Integer getRecipientId() {
		return recipientId;
	}

	/**
	 * Sets the message recipient identifier.
	 * 
	 * @param recipientId
	 *            New message recipient identifier.
	 */
	public void setRecipientId(Integer recipientId) {
		this.recipientId = recipientId;
	}

	/**
	 * The message recipient.
	 */
	@ExternalEntity(retrieveObj = "java:app/place-ejb/Component/Party/Party")
	private Party recipient;

	/**
	 * @see org.dejava.service.message.model.Message#getRecipient()
	 */
	@Override
	@Transient
	public Party getRecipient() {
		return recipient;
	}

	/**
	 * Sets the message recipient.
	 * 
	 * @param recipient
	 *            New message recipient.
	 */
	public void setRecipient(Party recipient) {
		// If the recipient is null.
		if (recipient == null) {
			// Sets the recipient id to null.
			setRecipientId(null);
		}
		// If the recipient is not null.
		else {
			// Sets the new recipient id.
			setRecipientId(recipient.getIdentifier());
		}
		// Updates the recipient.
		this.recipient = recipient;
	}

	/**
	 * The content of the notification.
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
	 * Sets the content of the notification.
	 * 
	 * @param content
	 *            New content of the notification.
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Default constructor.
	 */
	public AppNotification() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param sender
	 *            The notification sender.
	 * @param recipientId
	 *            The notification recipient (party) identifier.
	 * @param content
	 *            The content of the notification.
	 */
	public AppNotification(String sender, Integer recipientId, String content) {
		super();
		this.sender = sender;
		this.recipientId = recipientId;
		this.content = content;
	}

}
