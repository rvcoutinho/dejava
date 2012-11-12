package org.dejava.service.accesscontrol.model.credential;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dejava.component.security.crypt.CredentialHasher;

/**
 * Password of the user (as credential).
 */
@Table(name = "password")
@Entity
public class Password extends Credential implements Hashed {

	/**
	 * Algorithms to be used in the credential hash.
	 */
	private static final transient String[] ALGS_NAMES = { "SHA-256", "SHA-384", "SHA-512" };

	/**
	 * Minimum number of cycles when getting the hash.
	 */
	private static final transient Integer MIN_HASH_CYCLES = 38;

	/**
	 * Maximum number of cycles when getting the hash.
	 */
	private static final transient Integer MAX_HASH_CYCLES = 97;

	/**
	 * Initial salt value.
	 */
	private static final transient String DEFAULT_SALT = "DéJ4V4-ment41ist_p0ney=Smith+4nn0nym0u5.1082348317";

	/**
	 * The credential hasher.
	 */
	public static final transient CredentialHasher CREDENTIAL_HASHER = new CredentialHasher(ALGS_NAMES,
			MIN_HASH_CYCLES, MAX_HASH_CYCLES);

	/**
	 * Gets the custom salt for the password.
	 * 
	 * @return The custom salt for the password.
	 */
	@Transient
	public String getSalt() {
		// Return the default salt plus the user id (hex).
		return DEFAULT_SALT + Integer.toHexString(getUser().getId().hashCode());
	}

	/**
	 * Password (salted and hashed) for the user.
	 */
	private String password;

	/**
	 * Gets the password (salted and hashed) for the user.
	 * 
	 * @return The password (salted and hashed) for the user.
	 */
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password (salted and hashed) for the user.
	 * 
	 * @param password
	 *            New password (plain) for the user.
	 */
	public void setPassword(final String password) {
		// The hashed password is, at first, the password itself.
		final String hashedPassword = password;
		// If the password is not null.
		if (password != null) {
			// Gets the hash for the password.
			CREDENTIAL_HASHER.hash(password, getSalt());
		}
		// Sets the password.
		this.password = hashedPassword;
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
