package org.dejava.service.party.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.contact.model.EmailAddress;
import org.dejava.service.party.util.MessageTypes;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Represents a person.
 */
@Entity
@Table(name = "person")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.party.properties.model", entriesAffix = {
				"", ".description" }, processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.party.properties.error", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class Person extends Party {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4453810439350160800L;

	/**
	 * @see org.dejava.service.party.model.Party#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		// If the display name is null.
		if (super.getDisplayName() == null) {
			// The display name is the party name.
			setDisplayName(getCompleteName());
		}
		// Returns the display name.
		return super.getDisplayName();
	}

	/**
	 * First name of the person.
	 */
	private String firstName;

	/**
	 * Gets the first name of the person.
	 * 
	 * @return The first name of the person.
	 */
	@NotNull(payload = MessageTypes.Error.class, message = "person.firstname.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = "person.firstname.notempty")
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
	@NotNull(payload = MessageTypes.Error.class, message = "person.lastname.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = "person.lastname.notempty")
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
	 * Gets the complete name of the person.
	 * 
	 * @return The complete name of the person.
	 */
	@Transient
	public String getCompleteName() {
		// The complete name.
		final StringBuffer completeName = new StringBuffer();
		// If there is a first name.
		if ((getFirstName() != null) && (!getFirstName().isEmpty())) {
			// The complete name starts with the first name.
			completeName.append(getFirstName());
		}
		// If there is a middle name.
		if ((getMiddleName() != null) && (!getMiddleName().isEmpty())) {
			// Appends the middle name to the complete name.
			completeName.append(" " + getMiddleName());
		}
		// If there is a last name.
		if ((getLastName() != null) && (!getLastName().isEmpty())) {
			// The complete name ends with the last name.
			completeName.append(" " + getLastName());
		}
		// Returns the complete name.
		return completeName.toString();
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
	public void setGender(final Gender gender) {
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
	 */
	public Person(final String name, final String firstName, final String middleName, final String lastName,
			final Gender gender, final Date birthDate) {
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
	 * @param facebookUser
	 *            The facebook user.
	 */
	public Person(final com.restfb.types.User facebookUser) {
		super();
		// If the facebook user is not null.
		if (facebookUser != null) {
			// Sets the basic info for the person.
			this.firstName = facebookUser.getFirstName();
			this.middleName = facebookUser.getMiddleName();
			this.lastName = facebookUser.getLastName();
			this.birthDate = facebookUser.getBirthdayAsDate();
			// If there is a given email.
			if ((facebookUser.getEmail() != null) && (!facebookUser.getEmail().isEmpty())) {
				// Adds the email address as a contact.
				getContacts().add(new EmailAddress(facebookUser.getEmail(), getIdentifier()));
			}
			// If there is a given gender.
			if ((facebookUser.getGender() != null) && (!facebookUser.getGender().isEmpty())) {
				// Sets the gender.
				this.gender = Gender.valueOf(facebookUser.getGender().toUpperCase());
			}
		}
	}

}
