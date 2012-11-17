package org.dejava.component.security.authc.credential;

import org.dejava.component.security.crypt.hasher.CredentialsHasher;

/**
 * Interface that defines what is necessary for a credential to be hashed with {@link CredentialsHasher}.
 */
public interface HashedCredentials {

	/**
	 * Gets the algorithms names to be used in the hash calculation.
	 * 
	 * @return The algorithms names to be used in the hash calculation.
	 */
	String[] getAlgorithmsNames();

	/**
	 * Gets the minimum number of cycles to be used in the hash calculation.
	 * 
	 * @return The minimum number of cycles to be used in the hash calculation.
	 */
	Integer getMinCycles();

	/**
	 * Gets the minimum number of cycles to be used in the hash calculation.
	 * 
	 * @return The minimum number of cycles to be used in the hash calculation.
	 */
	Integer getMaxCycles();

	/**
	 * Gets the salt to be used in the hash calculation.
	 * 
	 * @return The salt to be used in the hash calculation.
	 */
	String getSalt();

	/**
	 * Gets the hashed credentials.
	 * 
	 * @return The hashed credentials.
	 */
	byte[] getHashedCredentials();

}
