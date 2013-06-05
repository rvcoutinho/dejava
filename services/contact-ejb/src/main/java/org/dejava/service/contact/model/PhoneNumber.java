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
 * Phone number.
 */
@Entity
@Table(name = "phone_number")
@Inheritance(strategy = InheritanceType.JOINED)
public class PhoneNumber implements Contact {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 7787723866238121969L;

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

	/**
	 * The country code for the phone number.
	 */
	private Integer countryCode;

	/**
	 * Gets the country code for the phone number.
	 * 
	 * @return The country code for the phone number.
	 */
	@Column(name = "contry_code")
	public Integer getCountryCode() {
		return countryCode;
	}

	/**
	 * Sets the country code for the phone number.
	 * 
	 * @param countryCode
	 *            New country code for the phone number.
	 */
	public void setCountryCode(final Integer countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Phone number.
	 */
	private Integer number;

	/**
	 * Gets the phone number.
	 * 
	 * @return The phone number.
	 */
	@Column(name = "number")
	public Integer getNumber() {
		return number;
	}

	/**
	 * Sets the phone number.
	 * 
	 * @param number
	 *            New phone number.
	 */
	public void setNumber(final Integer number) {
		this.number = number;
	}

}