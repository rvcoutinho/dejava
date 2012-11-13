package org.dejava.service.accesscontrol.model.credentials;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dejava.component.security.auth.credential.HashedCredentials;
import org.dejava.component.security.crypt.hasher.CredentialsHasher;

/**
 * Password of the user (as credential).
 */
@Table(name = "password")
@Entity
public class Password extends Credentials implements HashedCredentials {

	/**
	 * Algorithms to be used in the credential hash.
	 */
	private static final transient String[] ALGS_NAMES = { "SHA-256", "SHA-384", "SHA-512" };

	/**
	 * @see org.dejava.component.security.auth.credential.HashedCredentials#getAlgorithmsNames()
	 */
	@Override
	@Transient
	public String[] getAlgorithmsNames() {
		return ALGS_NAMES;
	}

	/**
	 * Minimum number of cycles when getting the hash.
	 */
	private static final transient Integer MIN_HASH_CYCLES = 38;

	/**
	 * @see org.dejava.component.security.auth.credential.HashedCredentials#getMinCycles()
	 */
	@Override
	@Transient
	public Integer getMinCycles() {
		return MIN_HASH_CYCLES;
	}

	/**
	 * Maximum number of cycles when getting the hash.
	 */
	private static final transient Integer MAX_HASH_CYCLES = 97;

	/**
	 * @see org.dejava.component.security.auth.credential.HashedCredentials#getMaxCycles()
	 */
	@Override
	@Transient
	public Integer getMaxCycles() {
		return MAX_HASH_CYCLES;
	}

	/**
	 * Initial salt value.
	 */
	private static final transient String DEFAULT_SALT = "DéJ4V4-ment41ist_p0ney=Smith+4nn0nym0u5.1082348317";

	/**
	 * @see org.dejava.component.security.auth.credential.HashedCredentials#getSalt()
	 */
	@Override
	@Transient
	public String getSalt() {
		// Return the default salt plus the user id (hex).
		return DEFAULT_SALT + Integer.toHexString(getUser().getId().hashCode());
	}

	/**
	 * The raw password for the user.
	 */
	private String rawPassword;

	/**
	 * Gets the raw password for the user.
	 * 
	 * @return The raw password for the user.
	 */
	@Transient
	public String getRawPassword() {
		return rawPassword;
	}

	/**
	 * Sets the raw password for the user.
	 * 
	 * @param rawPassword
	 *            New raw password for the user.
	 */
	public void setRawPassword(final String rawPassword) {
		this.rawPassword = rawPassword;
	}

	/**
	 * Password (salted and hashed) for the user.
	 */
	private String hashedPassword;

	/**
	 * Gets the password (salted and hashed) for the user.
	 * 
	 * @return The password (salted and hashed) for the user.
	 */
	@Column(name = "password")
	public String getHashedPassword() {
		// If the hashed password is null (and there is a raw password).
		if ((hashedPassword == null) && (getRawPassword() != null)) {
			// Hashes the raw password.
			hashedPassword = new String(new CredentialsHasher(this).hash(getRawPassword(), getSalt()));
		}
		// Returns the hashed password.
		return hashedPassword;
	}

	/**
	 * Sets the hashedPassword.
	 * 
	 * @param hashedPassword
	 *            New password (salted and hashed) for the user.
	 */
	public void setHashedPassword(final String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	/**
	 * @see org.dejava.service.accesscontrol.model.credentials.Credentials#getValue()
	 */
	@Override
	@Transient
	public Object getValue() {
		return getHashedPassword();
	}

	/**
	 * @see org.dejava.component.security.auth.credential.HashedCredentials#getHashedCredentials()
	 */
	@Override
	@Transient
	public String getHashedCredentials() {
		return getHashedPassword();
	}

}
