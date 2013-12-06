package org.dejava.component.validation.test.object.util;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.component.validation.constant.MessageTemplateWildCards;
import org.hibernate.validator.constraints.Email;

/**
 * Fake entity.
 */
@MessageSources(sources = { @MessageSource(sourcePath = "src/test/resources", bundleBaseName = "org.dejava.component.validation.test.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.FieldAnnotationEntryProcessor" }) })
public class FakeEntity {

	/**
	 * Email address.
	 */
	@Email(payload = MessageTypes.Error.class, message = "fakeentity.email.email")
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
	@DecimalMin(payload = MessageTypes.Error.class, message = "fakeentity.age.decimalmin", value = "0")
	@DecimalMax(payload = MessageTypes.Error.class, message = MessageTemplateWildCards.ACTUAL_CLASS
			+ ".age.decimalmax", value = "100")
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
