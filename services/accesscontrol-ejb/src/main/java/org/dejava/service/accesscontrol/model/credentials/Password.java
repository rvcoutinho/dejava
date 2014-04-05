package org.dejava.service.accesscontrol.model.credentials;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.component.security.authc.credential.HashedCredentials;
import org.dejava.component.security.crypt.hasher.CredentialsHasher;
import org.dejava.service.accesscontrol.util.MessageTypes;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Password of the user (as credential).
 */
@Entity
@Table(name = "password")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.model", entriesAffix = { "", ".description" }, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class Password extends Credentials implements HashedCredentials {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -9129686752976388996L;

	/**
	 * Algorithms to be used in the credential hash.
	 */
	private static final transient String[] ALGS_NAMES = { "SHA-256", "SHA-384", "SHA-512" };

	/**
	 * @see org.dejava.component.security.authc.credential.HashedCredentials#getAlgorithmsNames()
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
	 * @see org.dejava.component.security.authc.credential.HashedCredentials#getMinCycles()
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
	 * @see org.dejava.component.security.authc.credential.HashedCredentials#getMaxCycles()
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
	 * @see org.dejava.component.security.authc.credential.HashedCredentials#getSalt()
	 */
	@Override
	@Transient
	public String getSalt() {
		// Return the default salt plus the user id (hex).
		return DEFAULT_SALT + Integer.toHexString(getUser().getIdentifier().hashCode());
	}

	/**
	 * Raw password for the user.
	 */
	private String rawPassword;

	/**
	 * Gets the raw password for the user.
	 * 
	 * @return The raw password for the user.
	 */
	@Transient
	@Length(payload = MessageTypes.Error.class, message = "password.rawpassword.length", min = 8, max = 10)
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
	private byte[] hashedPassword;

	/**
	 * Gets the password (salted and hashed) for the user.
	 * 
	 * @return The password (salted and hashed) for the user.
	 */
	@NotEmpty(payload = MessageTypes.Error.class, message = "password.hashedpassword.notempty")
	@Column(name = "password")
	public byte[] getHashedPassword() {
		// If there is a raw password.
		if (getRawPassword() != null) {
			// Hashes the raw password.
			hashedPassword = new CredentialsHasher(this).hash(getRawPassword(), getSalt());
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
	public void setHashedPassword(final byte[] hashedPassword) {
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
	 * @see org.dejava.component.security.authc.credential.HashedCredentials#getHashedCredentials()
	 */
	@Override
	@Transient
	public byte[] getHashedCredentials() {
		return getHashedPassword();
	}

	/**
	 * Public constructor
	 */
	public Password() {
		super();
	}

	/**
	 * Public constructor
	 * 
	 * @param rawPassword
	 *            Raw password for the user.
	 */
	public Password(final String rawPassword) {
		super();
		this.rawPassword = rawPassword;
	}

}
