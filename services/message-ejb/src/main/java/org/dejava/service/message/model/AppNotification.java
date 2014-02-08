package org.dejava.service.message.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dejava.component.i18n.message.handler.MessageCommand;

/**
 * Application notification.
 */
@Entity
@Table(name = "app_notification")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries(value = { @NamedQuery(name = "countUnreadAppNotificationsByRecipient", query = "SELECT count(notification) FROM AppNotification notification WHERE notification.recipient = :recipient AND notification.read IS NULL") })
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
	 * The notification recipient (identifier).
	 */
	private Integer recipient;

	/**
	 * Returns the notification recipient (identifier).
	 * 
	 * @return The notification recipient (identifier).
	 */
	@Column(name = "recipient")
	public Integer getRecipient() {
		return recipient;
	}

	/**
	 * Sets the notification recipient (identifier).
	 * 
	 * @param recipient
	 *            New notification recipient (identifier).
	 */
	protected void setRecipient(final Integer recipient) {
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
	public void setEventURL(final MessageCommand eventURL) {
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
	public void setImageURL(final MessageCommand imageURL) {
		this.imageURL = imageURL;
	}

	/**
	 * The content of the notification.
	 */
	private MessageCommand content;

	/**
	 * @see org.dejava.service.message.model.Message#getContent()
	 */
	@Override
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
	public void setContent(final MessageCommand content) {
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
	 *            The notification recipient (identifier).
	 * @param eventURL
	 *            The URL that the notification is about.
	 * @param imageURL
	 *            The image URL for the notification.
	 * @param content
	 *            The content of the notification.
	 */
	public AppNotification(final Integer recipient, final MessageCommand eventURL,
			final MessageCommand imageURL, final MessageCommand content) {
		super();
		this.recipient = recipient;
		this.eventURL = eventURL;
		this.imageURL = imageURL;
		this.content = content;
	}

}
