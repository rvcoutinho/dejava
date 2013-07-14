package org.dejava.component.validation.object.test.util;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.Email;

/**
 * Fake entity.
 */
public class FakeEntity {

	/**
	 * Email address.
	 */
	@Email
	private String email;

	/**
	 * Gets the email.
	 * 
	 * @return The email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 * 
	 * @param email
	 *            New email.
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * Age.
	 */
	@DecimalMin(value = "0")
	@DecimalMax(value = "100")
	private Integer age;

	/**
	 * Gets the age.
	 * 
	 * @return The age.
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 * 
	 * @param age
	 *            New age.
	 */
	public void setAge(final Integer age) {
		this.age = age;
	}

	/**
	 * Default constructor.
	 * 
	 * @param email
	 *            Email address.
	 * @param age
	 *            Age.
	 */
	public FakeEntity(final String email, final Integer age) {
		this.email = email;
		this.age = age;
	}
}
