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
 * SMS message.
 */
@Entity
@Table(name = "sms_message")
@Inheritance(strategy = InheritanceType.JOINED)
public class SMSMessage implements Message {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4461770040372376096L;

	/**
	 * Id for the phone number.
	 */
	private Integer id;

	/**
	 * Gets the id for the phone number.
	 * 
	 * @return The id for the phone number.
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id for the phone number.
	 * 
	 * @param id
	 *            New id for the phone number.
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

}
