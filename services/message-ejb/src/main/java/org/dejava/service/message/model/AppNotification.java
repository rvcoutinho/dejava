package org.dejava.service.message.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	@Transient
	@Override
	public String getSender() {
		return null;
	}

	/**
	 * The notification recipient.
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
	 * Sets the notification recipient.
	 * 
	 * @param recipient
	 *            New notification recipient.
	 */
	public void setRecipient(String recipient) {
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

}
