package org.dejava.service.message.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

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
	public abstract Object getSender();

	/**
	 * Gets the message recipient.
	 * 
	 * @return The message recipient.
	 */
	public abstract Object getRecipient();

	/**
	 * Gets the content of the message.
	 * 
	 * @return The content of the message.
	 */
	public abstract Serializable getContent();

}
