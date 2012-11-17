package org.dejava.component.security.test.util;

import org.dejava.component.security.authc.credential.HashedCredentials;

/**
 * Some fake hashed credentials.
 */
public class FakeHashedCredentials implements HashedCredentials {

	/**
	 * Algorithms names.
	 */
	private String[] algorithmsNames;

	/**
	 * @see org.dejava.component.security.authc.credential.HashedCredentials#getAlgorithmsNames()
	 */
	@Override
	public String[] getAlgorithmsNames() {
		return algorithmsNames;
	}

	/**
	 * Sets the algorithms names.
	 * 
	 * @param algorithmsNames
	 *            New algorithms names.
	 */
	public void setAlgorithmsNames(final String[] algorithmsNames) {
		this.algorithmsNames = algorithmsNames;
	}

	/**
	 * Minimum number of cycles.
	 */
	private Integer minCycles;

	/**
	 * @see org.dejava.component.security.authc.credential.HashedCredentials#getMinCycles()
	 */
	@Override
	public Integer getMinCycles() {
		return minCycles;
	}

	/**
	 * Sets the minimum number of cycles.
	 * 
	 * @param minCycles
	 *            New minimum number of cycles.
	 */
	public void setMinCycles(final Integer minCycles) {
		this.minCycles = minCycles;
	}

	/**
	 * Maximum number of cycles.
	 */
	private Integer maxCycles;

	/**
	 * @see org.dejava.component.security.authc.credential.HashedCredentials#getMaxCycles()
	 */
	@Override
	public Integer getMaxCycles() {
		return maxCycles;
	}

	/**
	 * Sets the maximum number of cycles.
	 * 
	 * @param maxCycles
	 *            New maximum number of cycles.
	 */
	public void setMaxCycles(final Integer maxCycles) {
		this.maxCycles = maxCycles;
	}

	/**
	 * Salt for the credentials.
	 */
	private String salt;

	/**
	 * @see org.dejava.component.security.authc.credential.HashedCredentials#getSalt()
	 */
	@Override
	public String getSalt() {
		return salt;
	}

	/**
	 * Sets the salt for the credentials.
	 * 
	 * @param salt
	 *            New salt for the credentials.
	 */
	public void setSalt(final String salt) {
		this.salt = salt;
	}

	/**
	 * Hashed credentials.
	 */
	private byte[] hashedCredentials;

	/**
	 * @see org.dejava.component.security.authc.credential.HashedCredentials#getHashedCredentials()
	 */
	@Override
	public byte[] getHashedCredentials() {
		return hashedCredentials;
	}

	/**
	 * Sets the hashed credentials.
	 * 
	 * @param hashedCredentials
	 *            New hashed credentials.
	 */
	public void setHashedCredentials(final byte[] hashedCredentials) {
		this.hashedCredentials = hashedCredentials;
	}

	/**
	 * Constructor.
	 * 
	 * @param algorithmsNames
	 *            Algorithms names.
	 * @param minCycles
	 *            Minimum number of cycles.
	 * @param maxCycles
	 *            Maximum number of cycles.
	 * @param salt
	 *            Salt for the credentials.
	 * @param hashedCredentials
	 *            Hashed credentials.
	 */
	public FakeHashedCredentials(final String[] algorithmsNames, final Integer minCycles,
			final Integer maxCycles, final String salt, final byte[] hashedCredentials) {
		super();
		this.algorithmsNames = algorithmsNames;
		this.minCycles = minCycles;
		this.maxCycles = maxCycles;
		this.salt = salt;
		this.hashedCredentials = hashedCredentials;
	}

}
