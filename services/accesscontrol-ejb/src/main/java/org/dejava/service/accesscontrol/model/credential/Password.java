package org.dejava.service.accesscontrol.model.credential;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Password of the user (as credential).
 */
@Table(name = "password")
@Entity
public class Password extends Credential {

	/**
	 * Password (salted) for the user.
	 */
	private String password;

	/**
	 * Gets the password (salted) for the user.
	 * 
	 * @return The password (salted) for the user.
	 */
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	/**
	 * Initial salt value.
	 */
	private static final String DEFAULT_SALT = "Break DéjàVa";

	/**
	 * Gets the custom salt for the password.
	 * 
	 * @return The custom salt for the password.
	 */
	@Transient
	public String getSalt() {
		// Return the default salt plus the user id (hex).
		return DEFAULT_SALT + Integer.toHexString(getUser().getId());
	}

	/**
	 * Minimum number of cycles when getting the hash.
	 */
	private static final Integer MIN_HASH_CYCLES = 2;

	/**
	 * Maximum number of cycles when getting the hash.
	 */
	private static final Integer MAX_HASH_CYCLES = 6;

	/**
	 * Sets the password (salted) for the user.
	 * 
	 * @param password
	 *            New password (salted) for the user.
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * @see org.dejava.service.accesscontrol.model.credential.Credential#getValue()
	 */
	@Override
	@Transient
	public Object getValue() {
		return getPassword();
	}

}
