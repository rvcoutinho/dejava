package org.dejava.component.security.crypto;

/**
 * Handles the hash calculation for user credentials.
 */
public class CredentialHasher {

	/**
	 * Algorithms to be used in the hash calculation.'
	 */
	private String[] algorithms;

	/**
	 * Gets the algorithms.
	 * 
	 * @return The algorithms.
	 */
	public String[] getAlgorithms() {
		return algorithms;
	}

	/**
	 * Sets the algorithms.
	 * 
	 * @param algorithms
	 *            New algorithms.
	 */
	public void setAlgorithms(final String[] algorithms) {
		this.algorithms = algorithms;
	}

	/**
	 * The fixed salt to be used in the hash calculation.
	 */
	private String fixedSalt;

	/**
	 * Gets the fixedSalt.
	 * 
	 * @return The fixedSalt.
	 */
	public String getFixedSalt() {
		return fixedSalt;
	}

	/**
	 * Sets the fixedSalt.
	 * 
	 * @param fixedSalt
	 *            New fixedSalt.
	 */
	public void setFixedSalt(final String fixedSalt) {
		this.fixedSalt = fixedSalt;
	}

	/**
	 * The unique (by user) salt to be used in the hash calculation.
	 */
	private Integer principalSalt;

	/**
	 * Gets the principalSalt.
	 * 
	 * @return The principalSalt.
	 */
	public Integer getPrincipalSalt() {
		return principalSalt;
	}

	/**
	 * Sets the principalSalt.
	 * 
	 * @param principalSalt
	 *            New principalSalt.
	 */
	public void setPrincipalSalt(final Integer principalSalt) {
		this.principalSalt = principalSalt;
	}

	/**
	 * The minimum number of hash cycles to be used in the hash calculation.
	 */
	private Integer minCycles;

	/**
	 * Gets the minCycles.
	 * 
	 * @return The minCycles.
	 */
	public Integer getMinCycles() {
		return minCycles;
	}

	/**
	 * Sets the minCycles.
	 * 
	 * @param minCycles
	 *            New minCycles.
	 */
	public void setMinCycles(final Integer minCycles) {
		this.minCycles = minCycles;
	}

	/**
	 * The maximum number of hash cycles to be used in the hash calculation.
	 */
	private Integer maxCycles;

	/**
	 * Gets the maxCycles.
	 * 
	 * @return The maxCycles.
	 */
	public Integer getMaxCycles() {
		return maxCycles;
	}

	/**
	 * Sets the maxCycles.
	 * 
	 * @param maxCycles
	 *            New maxCycles.
	 */
	public void setMaxCycles(final Integer maxCycles) {
		this.maxCycles = maxCycles;
	}

	/**
	 * A prime to be used in the algorithm selection.
	 */
	private Integer algorithmPrime = 7;

	/**
	 * Gets the algorithmPrime.
	 * 
	 * @return The algorithmPrime.
	 */
	public Integer getAlgorithmPrime() {
		return algorithmPrime;
	}

	/**
	 * Sets the algorithmPrime.
	 * 
	 * @param algorithmPrime
	 *            New algorithmPrime.
	 */
	public void setAlgorithmPrime(final Integer algorithmPrime) {
		this.algorithmPrime = algorithmPrime;
	}

	/**
	 * A prime to be used in the cycle selection.
	 */
	private Integer cyclePrime = 13;

	/**
	 * Gets the cyclePrime.
	 * 
	 * @return The cyclePrime.
	 */
	public Integer getCyclePrime() {
		return cyclePrime;
	}

	/**
	 * Sets the cyclePrime.
	 * 
	 * @param cyclePrime
	 *            New cyclePrime.
	 */
	public void setCyclePrime(final Integer cyclePrime) {
		this.cyclePrime = cyclePrime;
	}

}
