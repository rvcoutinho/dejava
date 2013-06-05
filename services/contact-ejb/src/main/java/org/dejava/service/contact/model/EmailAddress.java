package org.dejava.service.contact.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Email address.
 */
@Entity
@Table(name = "email_address")
@Inheritance(strategy = InheritanceType.JOINED)
public class EmailAddress implements Contact {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1227930531632819560L;

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

	/**
	 * Address of the email address.
	 */
	private String address;

	/**
	 * Gets the address of the email address.
	 * 
	 * @return The address of the email address.
	 */
	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address of the email address.
	 * 
	 * @param address
	 *            New address of the email address.
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

}
