package org.dejava.service.contact.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.dejava.component.validation.method.ArgFormatter;

/**
 * Phone number.
 */
@Entity
@Table(name = "phone_number")
@Inheritance(strategy = InheritanceType.JOINED)
public class PhoneNumber extends Contact {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 7787723866238121969L;

	/**
	 * The country code for the phone number.
	 */
	private String countryCode = "55";

	/**
	 * Gets the country code for the phone number.
	 * 
	 * @return The country code for the phone number.
	 */
	@Column(name = "contry_code")
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Sets the country code for the phone number.
	 * 
	 * @param countryCode
	 *            New country code for the phone number.
	 */
	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Phone number.
	 */
	private String number;

	/**
	 * Gets the phone number.
	 * 
	 * @return The phone number.
	 */
	@Column(name = "number")
	public String getNumber() {
		return number;
	}

	/**
	 * Sets the phone number.
	 * 
	 * @param number
	 *            New phone number.
	 */
	public void setNumber(final String number) {
		// Sets the new field value (making sure that just numbers are recorded).
		this.number = ArgFormatter.removeInvalidRegex(number, "[^\\d]");
	}
}
