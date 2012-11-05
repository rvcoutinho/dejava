package org.dejava.component.security.crypt.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.security.crypt.CredentialHasher;
import org.dejava.component.test.annotation.ParametricTest;
import org.dejava.component.test.runner.JUnitParametricRunner;
import org.dejava.component.test.runner.dataset.impl.StaticMethodTestDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for the credential hasher.
 */
@RunWith(value = JUnitParametricRunner.class)
public class CredentialHasherTest {

	/**
	 * Some credentials to be tested.
	 */
	private static final String[] CREDENTIALS = { "pesd", "hfehrbv", "fiekod", "pgrefdbsd", "ogrfegndklfj",
			"gvrjdesnbdfjk", "s3cr3t", "password", "123456", "12345678", "abc123", "qwerty", "monkey",
			"letmein", "dragon", "111111", "baseball", "iloveyou", "trustno1", "1234567", "sunshine",
			"master", "123123", "welcome", "shadow", "ashley", "football", "jesus", "michael", "ninja",
			"mustang", "password1" };

	/**
	 * Gets the data for the successful hash tests.
	 * 
	 * @return The data for the successful hash tests.
	 */
	public static Collection<String> getTestHashSuccessData() {
		return new ArrayList<>(Arrays.asList(CREDENTIALS));
	}

	/**
	 * Tests the successful credential hash with a string credential.
	 * 
	 * @param credential
	 *            Credential to be hashed.
	 */
	@ParametricTest(dataProvider = StaticMethodTestDataProvider.class, paramsValues = { "getTestHashSuccessData" })
	public void testHashStringSuccess(final String credential) {
		// Gets a new hasher instance.
		final CredentialHasher credentialHasher = new CredentialHasher();
		// Calculates the hash twice.
		final byte[] hash1 = credentialHasher.hash(credential, String.valueOf(credential.hashCode()));
		final byte[] hash2 = credentialHasher.hash(credential, String.valueOf(credential.hashCode()));
		// Assert that the hashes are equal.
		Assert.assertArrayEquals(hash1, hash2);
	}

	/**
	 * Any string.
	 */
	private static final String ANYTHING = "anything";

	/**
	 * Tests the credential hash with a null string credential.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testHashStringNullCredential() {
		// Gets a new hasher instance.
		final CredentialHasher credentialHasher = new CredentialHasher();
		// Calculates the hash twice.
		credentialHasher.hash((String) null, ANYTHING);
	}

	/**
	 * Tests the credential hash with a null salt.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testHashStringNullSalt() {
		// Gets a new hasher instance.
		final CredentialHasher credentialHasher = new CredentialHasher();
		// Calculates the hash twice.
		credentialHasher.hash(ANYTHING, null);
	}

	/**
	 * Tests the successful credential hash with a byte array credential.
	 * 
	 * @param credential
	 *            Credential to be hashed.
	 */
	@ParametricTest(dataProvider = StaticMethodTestDataProvider.class, paramsValues = { "getTestHashSuccessData" })
	public void testHashBytesSuccess(final String credential) {
		// Gets a new hasher instance.
		final CredentialHasher credentialHasher = new CredentialHasher();
		// Calculates the hash twice.
		final byte[] hash1 = credentialHasher.hash(credential.getBytes(),
				String.valueOf(credential.hashCode()));
		final byte[] hash2 = credentialHasher.hash(credential.getBytes(),
				String.valueOf(credential.hashCode()));
		// Assert that the hashes are equal.
		Assert.assertArrayEquals(hash1, hash2);
	}

	/**
	 * Tests the credential hash with a null byte array credential.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testHashBytesNullCredential() {
		// Gets a new hasher instance.
		final CredentialHasher credentialHasher = new CredentialHasher();
		// Calculates the hash twice.
		credentialHasher.hash((byte[]) null, ANYTHING);
	}

	/**
	 * Tests the credential hash with a null salt.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testHashBytesNullSalt() {
		// Gets a new hasher instance.
		final CredentialHasher credentialHasher = new CredentialHasher();
		// Calculates the hash twice.
		credentialHasher.hash(ANYTHING.getBytes(), null);
	}

	/**
	 * Tests the credential hasher for hash collisions.
	 */
	@Test
	public void testHashCollision() {
		// Gets a new hasher instance.
		final CredentialHasher credentialHasher = new CredentialHasher();
		// Creates a new list with the credentials hashes.
		final List<byte[]> credentialsHashes = new ArrayList<>();
		// For each available credential.
		for (final String currentCredential : getTestHashSuccessData()) {
			// Adds the current hash to the list.
			credentialsHashes.add(credentialHasher.hash(currentCredential,
					String.valueOf(currentCredential.hashCode())));
		}
		// For each credential hash.
		for (Integer currentHashIdx = 0; currentHashIdx < credentialsHashes.size(); currentHashIdx++) {
			// For each credential hash again.
			for (Integer currentTestHashIdx = 0; currentTestHashIdx < credentialsHashes.size(); currentTestHashIdx++) {
				// If the hashed are not from the same credential.
				if (currentHashIdx != currentTestHashIdx) {
					// Assert that are no hash collisions.
					Assert.assertFalse(Arrays.equals(credentialsHashes.get(currentHashIdx),
							credentialsHashes.get(currentTestHashIdx)));
				}
			}
		}
	}

	/**
	 * Some invalid cycles to be used in the tests.
	 */
	private static final Integer[] SOME_INVALID_CYCLES = { null, -10, -34, -57, -89, -97, -154, 13884,
			239321, 32842034 };

	/**
	 * Gets some invalid cycles to be used in the tests.
	 * 
	 * @return Some invalid cycles to be used in the tests.
	 */
	public static Collection<Integer> getSomeInvalidCycles() {
		// Returns some cycles to be used in the tests.
		return new ArrayList<>(Arrays.asList(SOME_INVALID_CYCLES));
	}

	/**
	 * Some valid cycles to be used in the tests.
	 */
	private static final Integer[] SOME_VALID_CYCLES = { 5, 17, 32, 46, 59, 89, 120, 168, 190 };

	/**
	 * Gets some valid cycles to be used in the tests.
	 * 
	 * @return Some valid cycles to be used in the tests.
	 */
	public static Collection<Integer> getSomeValidCycles() {
		// Returns some cycles to be used in the tests.
		return new ArrayList<>(Arrays.asList(SOME_VALID_CYCLES));
	}

	/**
	 * Some number for the tries (in setting max/min cycles).
	 */
	private static final Integer CYCLES_TRIES = 50;

	/**
	 * Tests setting valid minimum hash cycles to the credential hasher.
	 * 
	 * @param maxCycles
	 *            A valid number for the maximum hash cycles.
	 */
	@ParametricTest(dataProvider = StaticMethodTestDataProvider.class, paramsValues = { "getSomeValidCycles" })
	public void testSetMinCyclesValid(final Integer maxCycles) {
		// Creates a new credential hasher.
		final CredentialHasher credentialHasher = new CredentialHasher();
		// Sets the minimum cycles to 1.
		credentialHasher.setMinCycles(CredentialHasher.MIN_MIN_CYCLES);
		// Sets the maximum cycles to the given value.
		credentialHasher.setMaxCycles(maxCycles);
		// For some numbers over the minimum cycles.
		for (Integer currentValidMinCycles = maxCycles - 1; (currentValidMinCycles >= (maxCycles - CYCLES_TRIES)); currentValidMinCycles--) {
			// Tries to set the minimum cycles to the current valid minimum.
			credentialHasher.setMinCycles(currentValidMinCycles);
		}
	}

	/**
	 * Tests setting invalid (always) minimum hash cycles to the credential hasher.
	 * 
	 * @param invalidCycle
	 *            An invalid number for the minimum hash cycles.
	 */
	@ParametricTest(dataProvider = StaticMethodTestDataProvider.class, paramsValues = { "getSomeInvalidCycles" }, expectedExceptionClass = InvalidParameterException.class)
	public void testSetMinCyclesAlwaysInvalid(final Integer invalidCycle) {
		// Creates a new credential hasher.
		final CredentialHasher credentialHasher = new CredentialHasher();
		// Tries to set the minimum cycles with an invalid value.
		credentialHasher.setMinCycles(invalidCycle);
	}

	/**
	 * Tests setting invalid (greater than the maximum) minimum hash cycles to the credential hasher.
	 * 
	 * @param maxCycles
	 *            A valid number for the maximum hash cycles.
	 */
	@ParametricTest(dataProvider = StaticMethodTestDataProvider.class, paramsValues = { "getSomeValidCycles" })
	public void testSetMinCyclesGreater(final Integer maxCycles) {
		// Creates a new credential hasher.
		final CredentialHasher credentialHasher = new CredentialHasher();
		// Sets the minimum cycles to 1.
		credentialHasher.setMinCycles(CredentialHasher.MIN_MIN_CYCLES);
		// Sets the maximum cycles to the given value.
		credentialHasher.setMaxCycles(maxCycles);
		// For some numbers over the maximum cycles.
		for (Integer currentInvalidMinCycles = maxCycles + 1; currentInvalidMinCycles <= (maxCycles + CYCLES_TRIES); currentInvalidMinCycles++) {
			// Tries to set the minimum cycles to the current invalid minimum.
			try {
				credentialHasher.setMinCycles(currentInvalidMinCycles);
				// If no exception was raised, the test fails.
				Assert.fail("An invalid parameter exception should have been raised.");
			}
			// It is expected that an invalid parameter exception is raised.
			catch (final InvalidParameterException exception) {
			}
		}
	}

	/**
	 * Tests setting invalid (always) maximum hash cycles to the credential hasher.
	 * 
	 * @param invalidCycle
	 *            An invalid number for the maximum hash cycles.
	 */
	@ParametricTest(dataProvider = StaticMethodTestDataProvider.class, paramsValues = { "getSomeInvalidCycles" }, expectedExceptionClass = InvalidParameterException.class)
	public void testSetMaxCyclesAlwaysInvalid(final Integer invalidCycle) {
		// Creates a new credential hasher.
		final CredentialHasher credentialHasher = new CredentialHasher();
		// Tries to set the maximum cycles with an invalid value.
		credentialHasher.setMaxCycles(invalidCycle);
	}

	/**
	 * Tests setting invalid (lesser than the minimum) maximum hash cycles to the credential hasher.
	 * 
	 * @param minCycles
	 *            A valid number for the minimum hash cycles.
	 */
	@ParametricTest(dataProvider = StaticMethodTestDataProvider.class, paramsValues = { "getSomeValidCycles" })
	public void testSetMaxCyclesLesser(final Integer minCycles) {
		// Creates a new credential hasher.
		final CredentialHasher credentialHasher = new CredentialHasher();
		// Sets the maximum cycles to 10000.
		credentialHasher.setMaxCycles(CredentialHasher.MAX_MAX_CYCLES);
		// Sets the minimum cycles to the given value.
		credentialHasher.setMinCycles(minCycles);
		// For some numbers over the maximum cycles.
		for (Integer currentInvalidMaxCycles = minCycles - 1; currentInvalidMaxCycles >= (minCycles - CYCLES_TRIES); currentInvalidMaxCycles--) {
			// Tries to set the maximum cycles to the current invalid maximum.
			try {
				credentialHasher.setMaxCycles(currentInvalidMaxCycles);
				// If no exception was raised, the test fails.
				Assert.fail("An invalid parameter exception should have been raised.");
			}
			// It is expected that an invalid parameter exception is raised.
			catch (final InvalidParameterException exception) {
			}
		}
	}

}
