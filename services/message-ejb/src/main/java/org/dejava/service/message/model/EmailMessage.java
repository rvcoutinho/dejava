package org.dejava.service.message.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Email message.
 */
@Entity
@Table(name = "email_message")
@Inheritance(strategy = InheritanceType.JOINED)
public class EmailMessage implements Message {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 2867581639813845489L;

	/**
	 * Id for the email address.
	 */
	private Integer id;

	/**
	 * Gets the id for the email address.
	 * 
	 * @return The id for the email address.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id for the email address.
	 * 
	 * @param id
	 *            New id for the email address.
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

}
