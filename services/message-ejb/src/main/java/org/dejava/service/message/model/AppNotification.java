package org.dejava.service.message.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dejava.component.ejb.entity.ExternalEntity;
import org.dejava.component.i18n.message.handler.MessageCommand;
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
	 * @see org.dejava.service.message.model.Message#getSender()
	 */
	@Override
	@Transient
	public Object getSender() {
		return null;
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
	 * The URL that the notification is about.
	 */
	private MessageCommand eventURL;

	/**
	 * Gets the URL that the notification is about.
	 * 
	 * @return The URL that the notification is about.
	 */
	@Lob
	@Column(name = "event_url")
	public MessageCommand getEventURL() {
		return eventURL;
	}

	/**
	 * Sets the URL that the notification is about.
	 * 
	 * @param eventURL
	 *            New URL that the notification is about.
	 */
	public void setEventURL(MessageCommand eventURL) {
		this.eventURL = eventURL;
	}

	/**
	 * The image URL for the notification.
	 */
	private MessageCommand imageURL;

	/**
	 * Gets the image URL for the notification.
	 * 
	 * @return The image URL for the notification.
	 */
	@Lob
	@Column(name = "image_url")
	public MessageCommand getImageURL() {
		return imageURL;
	}

	/**
	 * Sets the image URL for the notification.
	 * 
	 * @param imageURL
	 *            New image URL for the notification.
	 */
	public void setImageURL(MessageCommand imageURL) {
		this.imageURL = imageURL;
	}

	/**
	 * The content of the notification.
	 */
	private MessageCommand content;

	/**
	 * @see org.dejava.service.message.model.Message#getContent()
	 */
	@Lob
	@Column(name = "content")
	public MessageCommand getContent() {
		return content;
	}

	/**
	 * Sets the content of the notification.
	 * 
	 * @param content
	 *            New content of the notification.
	 */
	public void setContent(MessageCommand content) {
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
	 * @param recipient
	 *            The notification recipient (party).
	 * @param eventURL
	 *            The URL that the notification is about.
	 * @param imageURL
	 *            The image URL for the notification.
	 * @param content
	 *            The content of the notification.
	 */
	public AppNotification(Party recipient, MessageCommand eventURL, MessageCommand imageURL,
			MessageCommand content) {
		super();
		setRecipient(recipient);
		this.eventURL = eventURL;
		this.imageURL = imageURL;
		this.content = content;
	}

}
