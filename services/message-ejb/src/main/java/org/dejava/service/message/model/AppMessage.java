package org.dejava.service.message.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dejava.component.ejb.entity.ExternalEntity;
import org.dejava.service.party.model.Party;

/**
 * Application message.
 */
@Entity
@Table(name = "app_message")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries(value = { @NamedQuery(name = "getAppMessagesByParty", query = "SELECT message FROM AppMessage message WHERE message.senderId = :partyId OR message.recipientId = :partyId ORDER BY message.date DESC") })
public class AppMessage extends Message {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4461770040372376096L;

	/**
	 * The message sender identifier.
	 */
	private Integer senderId;

	/**
	 * Gets the message sender identifier.
	 * 
	 * @return The message sender identifier.
	 */
	@Column(name = "sender")
	protected Integer getSenderId() {
		return senderId;
	}

	/**
	 * Sets the message sender identifier.
	 * 
	 * @param senderId
	 *            New message sender identifier.
	 */
	protected void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	/**
	 * The message sender.
	 */
	@ExternalEntity(retrieveObj = "java:app/party-ejb/Component/Party/Party")
	private Party sender;

	/**
	 * @see org.dejava.service.message.model.Message#getSender()
	 */
	@Override
	@Transient
	public Party getSender() {
		return sender;
	}

	/**
	 * Sets the message sender.
	 * 
	 * @param sender
	 *            New message sender.
	 */
	public void setSender(Party sender) {
		// If the sender is null.
		if (sender == null) {
			// Sets the sender id to null.
			setSenderId(null);
		}
		// If the sender is not null.
		else {
			// Sets the new sender id.
			setSenderId(sender.getIdentifier());
		}
		// Updates the sender.
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
	protected Integer getRecipientId() {
		return recipientId;
	}

	/**
	 * Sets the message recipient identifier.
	 * 
	 * @param recipientId
	 *            New message recipient identifier.
	 */
	protected void setRecipientId(Integer recipientId) {
		this.recipientId = recipientId;
	}

	/**
	 * The message recipient.
	 */
	@ExternalEntity(retrieveObj = "java:app/party-ejb/Component/Party/Party")
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
