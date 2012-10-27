package org.dejava.component.security.crypt;

import java.security.MessageDigest;

/**
 * Handles the hash calculation for user credentials.
 */
public class CredentialHasher {

	/**
	 * Names of the algorithms to be used in the hash calculation. The first algorithm is also used in other
	 * calculations.
	 */
	private String[] possibleAlgorithmsNames = { "MD5", "SHA-1", "SHA-256", "SHA-384" };

	/**
	 * Gets the names of the algorithms to be used in the hash calculation.
	 * 
	 * @return The names of the algorithms to be used in the hash calculation.
	 */
	public String[] getPossibleAlgorithmsNames() {
		return possibleAlgorithmsNames;
	}

	/**
	 * Sets the names of the algorithms to be used in the hash calculation.
	 * 
	 * @param possibleAlgorithms
	 *            New names of the algorithms to be used in the hash calculation. The first algorithm is also
	 *            used in other calculations.
	 */
	public void setPossibleAlgorithms(final String[] possibleAlgorithms) {
		this.possibleAlgorithmsNames = possibleAlgorithms;
	}

	/**
	 * The algorithms to be used in the hash calculation.
	 */
	private MessageDigest[] possibleAlgorithms;

	/**
	 * Gets the algorithms to be used in the hash calculation.
	 * 
	 * @return The algorithms to be used in the hash calculation.
	 */
	public MessageDigest[] getPossibleAlgorithms() {
		return possibleAlgorithms;
	}

	/**
	 * The fixed salt to be used in the hash calculation.
	 */
	private String fixedSalt = "Super_Dej4v4_s41t_crypt_10ng";

	/**
	 * Gets the fixed salt to be used in the hash calculation.
	 * 
	 * @return The fixed salt to be used in the hash calculation.
	 */
	public String getFixedSalt() {
		return fixedSalt;
	}

	/**
	 * Sets the fixed salt to be used in the hash calculation.
	 * 
	 * @param fixedSalt
	 *            New fixed salt to be used in the hash calculation.
	 */
	public void setFixedSalt(final String fixedSalt) {
		this.fixedSalt = fixedSalt;
	}

	/**
	 * The unique (by user) salt to be used in the hash calculation.
	 */
	private String principalSalt;

	/**
	 * Gets the unique (by user) salt to be used in the hash calculation.
	 * 
	 * @return The unique (by user) salt to be used in the hash calculation.
	 */
	public String getPrincipalSalt() {
		return principalSalt;
	}

	/**
	 * Sets the unique (by user) salt to be used in the hash calculation.
	 * 
	 * @param principalSalt
	 *            New unique (by user) salt to be used in the hash calculation.
	 */
	public void setPrincipalSalt(final String principalSalt) {
		this.principalSalt = principalSalt;
	}

	/**
	 * Gets the complete salt to be used in the has calculation.
	 * 
	 * @return The complete salt to be used in the has calculation.
	 */
	public String getSalt() {
		return getFixedSalt() + getPrincipalSalt();
	}

	/**
	 * Gets an arbitrary number from a given string (always the same for an equal string).
	 * 
	 * @param text
	 *            The text to get the number from.
	 * @return An arbitrary number from a given string (always the same for an equal string)
	 */
	private Integer getArbitraryNumber(final String text) {
		// Gets the first hash algorithm.
		final MessageDigest digest = getPossibleAlgorithms()[0];
		// Makes sure the message digest is empty.
		digest.reset();
		// Digests the text.
		final String hash = new String(digest.digest(text.getBytes()));
		// Replaces all non-digit characters by an empty string.
		final String hashNumbers = hash.replaceAll("[^0-9]", "");
		// Returns the numbers in the hash.
		return Integer.valueOf(hashNumbers);
	}

	/**
	 * The minimum number of hash cycles to be used in the hash calculation.
	 */
	private Integer minCycles = 100;

	/**
	 * Gets the minimum number of hash cycles to be used in the hash calculation.
	 * 
	 * @return The minimum number of hash cycles to be used in the hash calculation.
	 */
	public Integer getMinCycles() {
		return minCycles;
	}

	/**
	 * Sets the minimum number of hash cycles to be used in the hash calculation.
	 * 
	 * @param minCycles
	 *            New minimum number of hash cycles to be used in the hash calculation.
	 */
	public void setMinCycles(final Integer minCycles) {
		this.minCycles = minCycles;
	}

	/**
	 * The maximum number of hash cycles to be used in the hash calculation.
	 */
	private Integer maxCycles = 110;

	/**
	 * Gets the maximum number of hash cycles to be used in the hash calculation.
	 * 
	 * @return The maximum number of hash cycles to be used in the hash calculation.
	 */
	public Integer getMaxCycles() {
		return maxCycles;
	}

	/**
	 * Sets the maximum number of hash cycles to be used in the hash calculation.
	 * 
	 * @param maxCycles
	 *            New maximum number of hash cycles to be used in the hash calculation.
	 */
	public void setMaxCycles(final Integer maxCycles) {
		this.maxCycles = maxCycles;
	}

	/**
	 * A prime to be used in the algorithm selection.
	 */
	private Integer algorithmPrime = 7;

	/**
	 * Gets the prime to be used in the algorithm selection.
	 * 
	 * @return The prime to be used in the algorithm selection.
	 */
	public Integer getAlgorithmPrime() {
		return algorithmPrime;
	}

	/**
	 * Sets the prime to be used in the algorithm selection.
	 * 
	 * @param algorithmPrime
	 *            New prime to be used in the algorithm selection.
	 */
	public void setAlgorithmPrime(final Integer algorithmPrime) {
		this.algorithmPrime = algorithmPrime;
	}

	/**
	 * A prime to be used in the cycle selection.
	 */
	private Integer cyclePrime = 13;

	/**
	 * Gets the prime to be used in the cycle selection.
	 * 
	 * @return The prime to be used in the cycle selection.
	 */
	public Integer getCyclePrime() {
		return cyclePrime;
	}

	/**
	 * Sets the prime to be used in the cycle selection.
	 * 
	 * @param cyclePrime
	 *            New prime to be used in the cycle selection.
	 */
	public void setCyclePrime(final Integer cyclePrime) {
		this.cyclePrime = cyclePrime;
	}

	/**
	 * TODO
	 * 
	 * @param algorithms
	 *            Algorithms to be used in the hash calculation. The first algorithm is also used in other
	 *            calculations.
	 * @param fixedSalt
	 *            The fixed salt to be used in the hash calculation.
	 * @param principalSalt
	 *            The unique (by user) salt to be used in the hash calculation.
	 * @param minCycles
	 *            The minimum number of hash cycles to be used in the hash calculation.
	 * @param maxCycles
	 *            The maximum number of hash cycles to be used in the hash calculation.
	 */
	public CredentialHasher(final String[] algorithms, final String fixedSalt, final String principalSalt,
			final Integer minCycles, final Integer maxCycles) {
		super();
		this.possibleAlgorithmsNames = algorithms;
		this.fixedSalt = fixedSalt;
		this.principalSalt = principalSalt;
		this.minCycles = minCycles;
		this.maxCycles = maxCycles;
	}

	/**
	 * Gets the number of cycles to be used when getting the hash for the credential.
	 * 
	 * @return The number of cycles to be used when getting the hash for the credential.
	 */
	private Integer getHashCycles() {
		// Gets the number of possible cycles.
		final Integer possibleCycles = (1 + getMaxCycles()) - getMinCycles();
		// Returns the modulo of the salt (and prime) by the number of possible cycles (plus the minimum).
		return ((getArbitraryNumber(getSalt()) + getCyclePrime()) % possibleCycles)) + getMinCycles();
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	private String[] get() {
		// Gets the number of hash cycles.
		final Integer hashCycles = getHashCycles();
		// Creates a new array with the same size of the cycles.
		final String[] algorithms = new String[hashCycles];
		// Gets the number of possible algorithms.
		final Integer possibleAlgorithmsNumber = getPossibleAlgorithmsNames().length;
		// For each hash cycle.
		for (Integer currentCycle = 0; currentCycle < hashCycles; currentCycle++) {
			// TODO
			final Integer algorithmIndex = ((getArbitraryNumber(getSalt()) + getAlgorithmPrime()) % possibleAlgorithmsNumber)
					+ getMinCycles();
			// Sets the algorithm for the current cycle.
			algorithms[currentCycle] = getPossibleAlgorithmsNames()[algorithmIndex];
		}
	}

	/**
	 * Gets the hash from a given credential.
	 * 
	 * @return The hash from a given credential.
	 */
	public String hash() {
		// Gets the digest.
		final MessageDigest digest = MessageDigest.getInstance(algorithm);
		// Makes sure the digest is reset.
		digest.reset();
		// Adds the salt to the digest.
		digest.update(salt.getBytes());
		// The hashed credential is initially the credential itself.
		byte[] hashedCredential = credential.getBytes();
		// For the number of given cycles.
		for (Integer currentCycle = 0; currentCycle < hashCycles; currentCycle++) {
			// Generates the hash for the current cycle.
			hashedCredential = digest.digest(hashedCredential);
		}
		// Returns the hashed cycle.
		return new String(hashedCredential);
	}

}
