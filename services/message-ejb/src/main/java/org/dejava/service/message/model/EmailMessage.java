package org.dejava.service.message.model;

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

}
