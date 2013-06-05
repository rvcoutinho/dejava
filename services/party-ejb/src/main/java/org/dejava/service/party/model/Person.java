package org.dejava.service.party.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.accesscontrol.model.User;

/**
 * Represents a person.
 */
@Entity
@Table(name = "person")
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.service.party.properties.model", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }) })
public class Person extends Party {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4453810439350160800L;

	/**
	 * First name of the person.
	 */
	private String firstName;

	/**
	 * Gets the first name of the person.
	 * 
	 * @return The first name of the person.
	 */
	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the person.
	 * 
	 * @param firstName
	 *            New first name of the person.
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Middle name of the person.
	 */
	private String middleName;

	/**
	 * Gets the middle name of the person.
	 * 
	 * @return The middle name of the person.
	 */
	@Column(name = "middle_name")
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Sets the middle name of the person.
	 * 
	 * @param middleName
	 *            New middle name of the person.
	 */
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Last name of the person.
	 */
	private String lastName;

	/**
	 * Gets the last name of the person.
	 * 
	 * @return The last name of the person.
	 */
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the person.
	 * 
	 * @param lastName
	 *            New last name of the person.
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gender of the person.
	 */
	private Gender gender;

	/**
	 * Gets the gender of the person.
	 * 
	 * @return The gender of the person.
	 */
	@Column(name = "gender")
	@Enumerated(value = EnumType.ORDINAL)
	public Gender getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the person.
	 * 
	 * @param gender
	 *            New gender of the person.
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * Birth date of the person.
	 */
	private Date birthDate;

	/**
	 * Gets the birth date of the person.
	 * 
	 * @return The birth date of the person.
	 */
	@Column(name = "birth_date")
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * Sets the birth date of the person.
	 * 
	 * @param birthDate
	 *            New birth date of the person.
	 */
	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Default constructor.
	 */
	public Person() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            Name of the person.
	 * @param firstName
	 *            The first name of the person.
	 * @param middleName
	 *            The middle name of the person.
	 * @param lastName
	 *            The last name of the person.
	 * @param gender
	 *            The gender of the person.
	 * @param birthDate
	 *            The birth date of the person.
	 * @param user
	 *            The user for the person.
	 */
	public Person(String name, String firstName, String middleName, String lastName, Gender gender,
			Date birthDate, User user) {
		super(name, user);
		// Sets the basic info for the person.
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
	}

	/**
	 * Facebook user constructor.
	 * 
	 * @param fbUser
	 *            The facebook user.
	 */
	public Person(com.restfb.types.User fbUser) {
		super();
		// If the facebook user is not null.
		if (fbUser != null) {
			// Sets the basic info for the person.
			setName(fbUser.getName());
			this.firstName = fbUser.getFirstName();
			this.middleName = fbUser.getMiddleName();
			this.lastName = fbUser.getLastName();
			this.birthDate = fbUser.getBirthdayAsDate();
			// If the gender is not null.
			if (fbUser.getGender() != null) {
				// Sets the gender.
				this.gender = Gender.valueOf(fbUser.getGender().toUpperCase());
			}
		}
	}
}
