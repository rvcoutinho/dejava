package org.dejava.component.security.crypt.hasher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.security.authc.credential.HashedCredentials;
import org.dejava.component.security.crypt.constant.CredentialHasherParamKeys;
import org.dejava.component.security.crypt.constant.ErrorKeys;
import org.dejava.component.security.crypt.util.MessageTypes;
import org.dejava.component.validation.method.PreConditions;

/**
 * Handles the hash calculation for user credentials.
 */
public final class CredentialsHasher {

	/**
	 * Names of the algorithms used by default.
	 */
	public static final String[] ALGS_NAMES = { "SHA-256", "SHA-384", "SHA-512" };

	/**
	 * The algorithms to be used in the hash calculation. The first algorithm is also used in other
	 * calculations.
	 */
	private MessageDigest[] algorithms;

	/**
	 * Gets the algorithms to be used in the hash calculation.
	 * 
	 * @return The algorithms to be used in the hash calculation.
	 */
	public MessageDigest[] getAlgorithms() {
		return algorithms;
	}

	/**
	 * Sets the algorithms (with the given names) to be used in the hash calculation.
	 * 
	 * @param algorithmsNames
	 *            Names of the algorithms to be used in the hash calculation. The first algorithm is also used
	 *            in other calculations.
	 */
	public void setAlgorithms(final String[] algorithmsNames) {
		// Creates a new array for the algorithms.
		algorithms = new MessageDigest[algorithmsNames.length];
		// For each algorithm name.
		for (Integer currentAlgorithmIdx = 0; currentAlgorithmIdx < algorithmsNames.length; currentAlgorithmIdx++) {
			// Tries to get the current algorithm by its name.
			try {
				algorithms[currentAlgorithmIdx] = MessageDigest
						.getInstance(algorithmsNames[currentAlgorithmIdx]);
			}
			// If the algorithm for the name cannot be found.
			catch (final NoSuchAlgorithmException exception) {
				// Throws an exception.
				throw new InvalidParameterException(MessageTypes.Error.class, ErrorKeys.INVALID_ALG_NAME,
						new Object[] { algorithmsNames[currentAlgorithmIdx] }, exception);
			}
		}
	}

	/**
	 * The minimum for the minimum number of hash cycles.
	 */
	public static final Integer MIN_MIN_CYCLES = 1;

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
		// Asserts that the given minimum is lesser than the maximum (and greater than the basic minimum).
		PreConditions.assertParamValid((minCycles != null) && (minCycles <= getMaxCycles())
				&& (minCycles >= MIN_MIN_CYCLES), MessageTypes.Error.class, ErrorKeys.INVALID_MIN_CYCLES,
				new Object[] { getMaxCycles(), MIN_MIN_CYCLES });
		// Sets the new minimum.
		this.minCycles = minCycles;
	}

	/**
	 * The maximum for the maximum number of hash cycles.
	 */
	public static final Integer MAX_MAX_CYCLES = 10000;

	/**
	 * The maximum number of hash cycles to be used in the hash calculation.
	 */
	private Integer maxCycles = 150;

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
		// Asserts that the given maximum is greater than the minimum (and lesser than the basic maximum).
		PreConditions.assertParamValid((maxCycles != null) && (maxCycles >= getMinCycles())
				&& (maxCycles <= MAX_MAX_CYCLES), MessageTypes.Error.class, ErrorKeys.INVALID_MAX_CYCLES,
				new Object[] { getMinCycles(), MAX_MAX_CYCLES });
		// Sets the new maximum.
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
		// Asserts that the parameter is not null.
		PreConditions.assertParamNotNull(CredentialHasherParamKeys.ALG_PRIME, algorithmPrime);
		// Sets the new number.
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
		// Asserts that the parameter is not null.
		PreConditions.assertParamNotNull(CredentialHasherParamKeys.CYCLE_PRIME, cyclePrime);
		// Sets the new number.
		this.cyclePrime = cyclePrime;
	}

	/**
	 * Sets the credential hasher with basic information on hash calculation.
	 * 
	 * @param algorithmsNames
	 *            Names of the algorithms to be used in the hash calculation. The first algorithm is also used
	 *            in other calculations.
	 * @param minCycles
	 *            The minimum number of hash cycles to be used in the hash calculation.
	 * @param maxCycles
	 *            The maximum number of hash cycles to be used in the hash calculation.
	 */
	public void setCredentialHasher(final String[] algorithmsNames, final Integer minCycles,
			final Integer maxCycles) {
		// Sets the minimum cycles to 1 (so the maximum could not be lesser).
		setMinCycles(MIN_MIN_CYCLES);
		// Sets the maximum cycles to 10000 (so the minimum could not be greater).
		setMaxCycles(MAX_MAX_CYCLES);
		// Sets the fields.
		setAlgorithms(algorithmsNames);
		setMaxCycles(maxCycles);
		setMinCycles(minCycles);
	}

	/**
	 * Sets the credential hasher with basic information on hash calculation.
	 * 
	 * @param hashedCredentials
	 *            A credential containing information on how to be hashed.
	 */
	public void setCredentialHasher(final HashedCredentials hashedCredentials) {
		// Asserts that the parameter is not null.
		PreConditions.assertParamNotNull(CredentialHasherParamKeys.CREDENTIALS, hashedCredentials);
		// Sets the basic information for the credential hasher.
		setCredentialHasher(hashedCredentials.getAlgorithmsNames(), hashedCredentials.getMinCycles(),
				hashedCredentials.getMaxCycles());
	}

	/**
	 * Public constructor.
	 */
	public CredentialsHasher() {
		super();
		setAlgorithms(ALGS_NAMES);
	}

	/**
	 * Public constructor.
	 * 
	 * @param algorithmsNames
	 *            Names of the algorithms to be used in the hash calculation. The first algorithm is also used
	 *            in other calculations.
	 * @param minCycles
	 *            The minimum number of hash cycles to be used in the hash calculation.
	 * @param maxCycles
	 *            The maximum number of hash cycles to be used in the hash calculation.
	 */
	public CredentialsHasher(final String[] algorithmsNames, final Integer minCycles, final Integer maxCycles) {
		super();
		// Sets the basic information for the credential hasher.
		setCredentialHasher(algorithmsNames, minCycles, maxCycles);
	}

	/**
	 * Public constructor.
	 * 
	 * @param hashedCredentials
	 *            A credential containing information on how to be hashed.
	 * 
	 */
	public CredentialsHasher(final HashedCredentials hashedCredentials) {
		// Sets the basic information for the credential hasher.
		setCredentialHasher(hashedCredentials);
	}

	/**
	 * Gets the number of cycles to be used when getting the hash for the credential.
	 * 
	 * @param salt
	 *            Salt to be used in the hash calculation. It is a good idea to have an unique salt per user.
	 * @return The number of cycles to be used when getting the hash for the credential.
	 */
	private Integer getHashCycles(final String salt) {
		// Gets the number of possible cycles.
		final Integer possibleCycles = (1 + getMaxCycles()) - getMinCycles();
		// Gets an arbitrary (yet repeatable) number for the iteration (using the salt).
		final Integer arbitraryNumber = (salt + getCyclePrime()).hashCode() + getCyclePrime();
		// Calculates the modulo of the salt (and prime) by the number of possible cycles (plus the minimum).
		Integer hashCycles = (arbitraryNumber % possibleCycles) + getMinCycles();
		// Makes sure the number of cycles is not negative.
		hashCycles = hashCycles * Integer.signum(hashCycles);
		// Returns the number of hash cycles.
		return hashCycles;
	}

	/**
	 * Gets the algorithms (in order) to be used in the hash calculation (iteration).
	 * 
	 * @param salt
	 *            Salt to be used in the hash calculation. It is a good idea to have an unique salt per user.
	 * @return The algorithms (in order) to be used in the hash calculation (iteration).
	 */
	private MessageDigest[] getAlgorithmsOrder(final String salt) {
		// Gets the number of hash cycles.
		final Integer hashCycles = getHashCycles(salt);
		// Creates a new array with the same size of the cycles.
		final MessageDigest[] algorithmsOrder = new MessageDigest[hashCycles];
		// Gets the number of possible algorithms.
		final Integer algorithmsNumber = getAlgorithms().length;
		// For each hash cycle.
		for (Integer currentCycle = 0; currentCycle < hashCycles; currentCycle++) {
			// Gets an arbitrary (yet repeatable) number for the iteration (using the salt).
			final Integer arbitraryNumber = (salt + getAlgorithmPrime() + currentCycle).hashCode()
					+ getAlgorithmPrime();
			// Calculates the algorithm to be used (by index).
			Integer algorithmIndex = arbitraryNumber % algorithmsNumber;
			// Makes sure the index is not negative.
			algorithmIndex = algorithmIndex * Integer.signum(algorithmIndex);
			// Sets the algorithm for the current cycle.
			algorithmsOrder[currentCycle] = getAlgorithms()[algorithmIndex];
		}
		// Returns the algorithms order.
		return algorithmsOrder;
	}

	/**
	 * Gets the hash from a given credential.
	 * 
	 * @param credentials
	 *            The credential to be hashed.
	 * @param salt
	 *            Salt to be used in the hash calculation. It is a good idea to have an unique salt per user.
	 * @return The hash from a given credential.
	 */
	public byte[] hash(final byte[] credentials, final String salt) {
		// If the credential is null or empty.
		if ((credentials == null) || (credentials.length == 0)) {
			// Throws an exception.
			throw new EmptyParameterException(CredentialHasherParamKeys.CREDENTIALS);
		}
		// Asserts that the parameter is not empty.
		PreConditions.assertParamNotEmpty(CredentialHasherParamKeys.SALT, salt);
		// If the salt is null or empty.
		// The hashed credential is initially the credential itself.
		byte[] hashedCredential = credentials;
		// For each algorithm to be used.
		for (final MessageDigest currentDigest : getAlgorithmsOrder(salt)) {
			// Makes sure the digest is reset.
			currentDigest.reset();
			// Adds the salt to the digest.
			currentDigest.update(salt.getBytes());
			// Generates the hash for the current cycle.
			hashedCredential = currentDigest.digest(hashedCredential);
		}
		// Returns the hashed cycle.
		return hashedCredential;
	}

	/**
	 * Gets the hash from a given credentials.
	 * 
	 * @param credentials
	 *            The credential to be hashed.
	 * @param salt
	 *            Salt to be used in the hash calculation. It is a good idea to have an unique salt per user.
	 * @return The hash from a given credential.
	 */
	public byte[] hash(final String credentials, final String salt) {
		// Asserts that the parameter is not empty.
		PreConditions.assertParamNotEmpty(CredentialHasherParamKeys.CREDENTIALS, credentials);
		// Tries to hash the credentials.
		return hash(credentials.getBytes(), salt);
	}
}
