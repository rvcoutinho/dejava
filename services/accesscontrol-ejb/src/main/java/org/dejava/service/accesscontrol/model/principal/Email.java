package org.dejava.service.accesscontrol.model.principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Email of the user (as principal).
 */
@Table(name = "email")
@Entity
public class Email extends Principal {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4373856994515306923L;

	/**
	 * Email of the user.
	 */
	private String email;

	/**
	 * Gets the email of the user.
	 * 
	 * @return The email of the user.
	 */
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the user.
	 * 
	 * @param email
	 *            New email of the user.
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @see org.dejava.service.accesscontrol.model.principal.Principal#getValue()
	 */
	@Override
	@Transient
	public Object getValue() {
		return getEmail();
	}

	/**
	 * Public constructor.
	 */
	public Email() {
		super();
	}

	/**
	 * Public constructor.
	 * 
	 * @param email
	 *            Email of the user.
	 */
	public Email(final String email) {
		super();
		this.email = email;
	}

}
