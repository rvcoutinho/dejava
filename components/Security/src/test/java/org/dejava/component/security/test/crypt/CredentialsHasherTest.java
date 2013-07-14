package org.dejava.component.security.test.crypt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.security.crypt.hasher.CredentialsHasher;
import org.dejava.component.security.test.util.FakeHashedCredentials;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the credential hasher.
 */
public class CredentialsHasherTest {

	/**
	 * Some credentials to be tested.
	 */
	public static final String[] SOME_RAW_CREDENTIALS = { "pesd", "hfehrbv", "fiekod", "pgrefdbsd",
			"ogrfegndklfj", "gvrjdesnbdfjk", "s3cr3t", "password", "123456", "12345678", "abc123", "qwerty",
			"monkey", "letmein", "dragon", "111111", "baseball", "iloveyou", "trustno1", "1234567",
			"sunshine", "master", "123123", "welcome", "shadow", "ashley", "football", "jesus", "michael",
			"ninja", "mustang", "password1" };

	/**
	 * Gets some credentials to be used in the tests.
	 * 
	 * @return Some credentials to be used in the tests.
	 */
	public static Collection<String> getSomeCredentials() {
		return new ArrayList<>(Arrays.asList(SOME_RAW_CREDENTIALS));
	}

	/**
	 * Tests the successful credential hash with a string credential.
	 */
	@Test
	public void testHashStringSuccess() {
		// For each credentials to be hashed.
		for (final String credentials : getSomeCredentials()) {
			// Gets a new hasher instance.
			final CredentialsHasher credentialsHasher = new CredentialsHasher();
			// Calculates the hash twice.
			final byte[] hash1 = credentialsHasher.hash(credentials, String.valueOf(credentials.hashCode()));
			final byte[] hash2 = credentialsHasher.hash(credentials, String.valueOf(credentials.hashCode()));
			// Assert that the hashes are equal.
			Assert.assertArrayEquals(hash1, hash2);
		}
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
		final CredentialsHasher credentialsHasher = new CredentialsHasher();
		// Calculates the hash twice.
		credentialsHasher.hash((String) null, ANYTHING);
	}

	/**
	 * Tests the credential hash with a null salt.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testHashStringNullSalt() {
		// Gets a new hasher instance.
		final CredentialsHasher credentialsHasher = new CredentialsHasher();
		// Calculates the hash twice.
		credentialsHasher.hash(ANYTHING, null);
	}

	/**
	 * Tests the successful credential hash with a byte array credential.
	 */
	@Test
	public void testHashBytesSuccess() {
		// For each credentials to be hashed.
		for (final String credentials : getSomeCredentials()) {
			// Gets a new hasher instance.
			final CredentialsHasher credentialsHasher = new CredentialsHasher();
			// Calculates the hash twice.
			final byte[] hash1 = credentialsHasher.hash(credentials.getBytes(),
					String.valueOf(credentials.hashCode()));
			final byte[] hash2 = credentialsHasher.hash(credentials.getBytes(),
					String.valueOf(credentials.hashCode()));
			// Assert that the hashes are equal.
			Assert.assertArrayEquals(hash1, hash2);
		}
	}

	/**
	 * Tests the credential hash with a null byte array credential.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testHashBytesNullCredential() {
		// Gets a new hasher instance.
		final CredentialsHasher credentialsHasher = new CredentialsHasher();
		// Calculates the hash twice.
		credentialsHasher.hash((byte[]) null, ANYTHING);
	}

	/**
	 * Tests the credential hash with a null salt.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testHashBytesNullSalt() {
		// Gets a new hasher instance.
		final CredentialsHasher credentialsHasher = new CredentialsHasher();
		// Calculates the hash twice.
		credentialsHasher.hash(ANYTHING.getBytes(), null);
	}

	/**
	 * Tests the credential hasher for hash collisions.
	 */
	@Test
	public void testHashCollision() {
		// Gets a new hasher instance.
		final CredentialsHasher credentialsHasher = new CredentialsHasher();
		// Creates a new list with the credentials hashes.
		final List<byte[]> credentialsHashes = new ArrayList<>();
		// For each available credential.
		for (final String currentCredential : getSomeCredentials()) {
			// Adds the current hash to the list.
			credentialsHashes.add(credentialsHasher.hash(currentCredential,
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
	 */
	@Test
	public void testSetMinCyclesValid() {
		// For each valid cycles.
		for (final Integer maxCycles : getSomeValidCycles()) {
			// Creates a new credential hasher.
			final CredentialsHasher credentialsHasher = new CredentialsHasher();
			// Sets the minimum cycles to 1.
			credentialsHasher.setMinCycles(CredentialsHasher.MIN_MIN_CYCLES);
			// Sets the maximum cycles to the given value.
			credentialsHasher.setMaxCycles(maxCycles);
			// For some numbers over the minimum cycles.
			for (Integer currentValidMinCycles = maxCycles; (currentValidMinCycles >= (maxCycles - CYCLES_TRIES))
					&& (currentValidMinCycles >= CredentialsHasher.MIN_MIN_CYCLES); currentValidMinCycles--) {
				// Tries to set the minimum cycles to the current valid minimum.
				credentialsHasher.setMinCycles(currentValidMinCycles);
			}
		}
	}

	/**
	 * Tests setting valid maximum hash cycles to the credential hasher.
	 */
	@Test
	public void testSetMaxCyclesValid() {
		// For each valid cycles.
		for (final Integer minCycles : getSomeValidCycles()) {
			// Creates a new credential hasher.
			final CredentialsHasher credentialsHasher = new CredentialsHasher();
			// Sets the maximum cycles to 1.
			credentialsHasher.setMaxCycles(CredentialsHasher.MAX_MAX_CYCLES);
			// Sets the minimum cycles to the given value.
			credentialsHasher.setMinCycles(minCycles);
			// For some numbers over the maximum cycles.
			for (Integer currentValidMaxCycles = minCycles; (currentValidMaxCycles <= (minCycles + CYCLES_TRIES))
					&& (currentValidMaxCycles <= CredentialsHasher.MAX_MAX_CYCLES); currentValidMaxCycles++) {
				// Tries to set the maximum cycles to the current valid maximum.
				credentialsHasher.setMaxCycles(currentValidMaxCycles);
			}
		}
	}

	/**
	 * Tests setting invalid (always) minimum hash cycles to the credential hasher.
	 */
	@Test
	public void testSetMinCyclesAlwaysInvalid() {
		// For each invalid cycles.
		for (final Integer invalidCycle : getSomeInvalidCycles()) {
			// Creates a new credential hasher.
			final CredentialsHasher credentialsHasher = new CredentialsHasher();
			// Tries to set the minimum cycles with an invalid value.
			try {
				credentialsHasher.setMinCycles(invalidCycle);
				// If no exception is thrown, the test fails.
				Assert.fail();
			}
			// If an invalid parameter exception is thrown.
			catch (final InvalidParameterException exception) {
				// Nothing happens (as that is expected).
			}
		}
	}

	/**
	 * Tests setting invalid (always) maximum hash cycles to the credential hasher.
	 */
	@Test
	public void testSetMaxCyclesAlwaysInvalid() {
		// For each invalid cycles.
		for (final Integer invalidCycle : getSomeInvalidCycles()) {
			// Creates a new credential hasher.
			final CredentialsHasher credentialsHasher = new CredentialsHasher();
			// Tries to set the maximum cycles with an invalid value.
			try {
				credentialsHasher.setMaxCycles(invalidCycle);
				// If no exception is thrown, the test fails.
				Assert.fail();
			}
			// If an invalid parameter exception is thrown.
			catch (final InvalidParameterException exception) {
				// Nothing happens (as that is expected).
			}
		}
	}

	/**
	 * Tests setting invalid (greater than the maximum) minimum hash cycles to the credential hasher.
	 */
	@Test
	public void testSetMinCyclesGreater() {
		// For each valid cycles.
		for (final Integer maxCycles : getSomeValidCycles()) {
			// Creates a new credential hasher.
			final CredentialsHasher credentialsHasher = new CredentialsHasher();
			// Sets the minimum cycles to 1.
			credentialsHasher.setMinCycles(CredentialsHasher.MIN_MIN_CYCLES);
			// Sets the maximum cycles to the given value.
			credentialsHasher.setMaxCycles(maxCycles);
			// For some numbers over the maximum cycles.
			for (Integer currentInvalidMinCycles = maxCycles + 1; currentInvalidMinCycles <= (maxCycles + CYCLES_TRIES); currentInvalidMinCycles++) {
				// Tries to set the minimum cycles to the current invalid minimum.
				try {
					credentialsHasher.setMinCycles(currentInvalidMinCycles);
					// If no exception was raised, the test fails.
					Assert.fail("An invalid parameter exception should have been raised.");
				}
				// If an invalid parameter exception is thrown.
				catch (final InvalidParameterException exception) {
					// Nothing happens (as that is expected).
				}
			}
		}
	}

	/**
	 * Tests setting invalid (lesser than the minimum) maximum hash cycles to the credential hasher.
	 */
	@Test
	public void testSetMaxCyclesLesser() {
		// For each valid cycles.
		for (final Integer minCycles : getSomeValidCycles()) {
			// Creates a new credential hasher.
			final CredentialsHasher credentialsHasher = new CredentialsHasher();
			// Sets the maximum cycles to 10000.
			credentialsHasher.setMaxCycles(CredentialsHasher.MAX_MAX_CYCLES);
			// Sets the minimum cycles to the given value.
			credentialsHasher.setMinCycles(minCycles);
			// For some numbers over the maximum cycles.
			for (Integer currentInvalidMaxCycles = minCycles - 1; currentInvalidMaxCycles >= (minCycles - CYCLES_TRIES); currentInvalidMaxCycles--) {
				// Tries to set the maximum cycles to the current invalid maximum.
				try {
					credentialsHasher.setMaxCycles(currentInvalidMaxCycles);
					// If no exception was raised, the test fails.
					Assert.fail("An invalid parameter exception should have been raised.");
				}
				// If an invalid parameter exception is thrown.
				catch (final InvalidParameterException exception) {
					// Nothing happens (as that is expected).
				}
			}
		}
	}

	/**
	 * Some valid algorithms names.
	 */
	private static final String[] SOME_VALID_ALGS_NAMES = { "MD5", "SHA-1", "SHA-256", "SHA-512" };

	/**
	 * Gets some valid algorithms names.
	 * 
	 * @return Some valid algorithms names.
	 */
	public static Collection<String> getSomeValidAlgorithmsNames() {
		// Returns some valid algorithms names to be used in the tests.
		return new ArrayList<>(Arrays.asList(SOME_VALID_ALGS_NAMES));
	}

	/**
	 * Tests setting the algorithms to be used in the hash calculation with valid algorithms names.
	 */
	@Test
	public void testSetAlgorithmsValid() {
		// For each valid algorithm name.
		for (final String validAlgName : getSomeValidAlgorithmsNames()) {
			// Creates a new credential hasher.
			final CredentialsHasher credentialsHasher = new CredentialsHasher();
			// Tries to set the current valid algorithm.
			credentialsHasher.setAlgorithms(new String[] { validAlgName, validAlgName });
		}
	}

	/**
	 * Some invalid algorithms names.
	 */
	private static final String[] SOME_INVALID_ALGS_NAMES = { "MD532", "SHA-342432", "sdrfgdsf", "fsdfds" };

	/**
	 * Gets some invalid algorithms names.
	 * 
	 * @return Some invalid algorithms names.
	 */
	public static Collection<String> getSomeInvalidAlgorithmsNames() {
		// Returns some invalid algorithms names to be used in the tests.
		return new ArrayList<>(Arrays.asList(SOME_INVALID_ALGS_NAMES));
	}

	/**
	 * Tests setting the algorithms to be used in the hash calculation with invalid algorithms names.
	 */
	@Test
	public void testSetAlgorithmsInvalid() {
		// For each invalid algorithm name.
		for (final String invalidAlgName : getSomeInvalidAlgorithmsNames()) {
			// Creates a new credential hasher.
			final CredentialsHasher credentialsHasher = new CredentialsHasher();
			// Tries to set the current invalid algorithm.
			try {
				credentialsHasher.setAlgorithms(new String[] { invalidAlgName, invalidAlgName });
				// If no exception was raised, the test fails.
				Assert.fail("An invalid parameter exception should have been raised.");
			}
			// If an invalid parameter exception is thrown.
			catch (final InvalidParameterException exception) {
				// Nothing happens (as that is expected).
			}
		}
	}

	/**
	 * Gets some valid parameters to be used in the credential hasher creation.
	 * 
	 * @return Some valid parameters to be used in the credential hasher creation.
	 */
	public static Collection<Object[]> getSomeValidParameters() {
		// Creates a new list of parameters.
		final Collection<Object[]> parameters = new ArrayList<>();
		// For each valid algorithm name.
		for (final String currentValidAlg : getSomeValidAlgorithmsNames()) {
			// For each valid cycle number.
			for (final Integer currentValidCycle : getSomeValidCycles()) {
				// Adds the current combination (and some other incrementing the number of max cycles) to the
				// parameters list.
				parameters.add(new Object[] { new String[] { currentValidAlg }, currentValidCycle,
						currentValidCycle });
				parameters.add(new Object[] { new String[] { currentValidAlg }, currentValidCycle,
						currentValidCycle + 1 });
				parameters.add(new Object[] { new String[] { currentValidAlg }, currentValidCycle,
						currentValidCycle + 2 });
			}
		}
		// Returns the parameters.
		return parameters;
	}

	/**
	 * Gets some invalid parameters to be used in the credential hasher creation.
	 * 
	 * @return Some invalid parameters to be used in the credential hasher creation.
	 */
	public static Collection<Object[]> getSomeInvalidParameters() {
		// Creates a new list of parameters.
		final Collection<Object[]> parameters = new ArrayList<>();
		// For each invalid algorithm name.
		for (final String currentInvalidAlg : getSomeInvalidAlgorithmsNames()) {
			// For each invalid cycle number.
			for (final Integer currentInvalidCycle : getSomeInvalidCycles()) {
				// Adds the current combination to the parameters list.
				parameters.add(new Object[] { new String[] { currentInvalidAlg }, currentInvalidCycle,
						currentInvalidCycle });
			}
			// For each valid cycle number.
			for (final Integer currentInvalidCycle : getSomeValidCycles()) {
				// Adds the current combination (and some similar others) to the parameters list.
				parameters.add(new Object[] { new String[] { currentInvalidAlg }, currentInvalidCycle,
						currentInvalidCycle });
				parameters.add(new Object[] { new String[] { currentInvalidAlg }, currentInvalidCycle,
						currentInvalidCycle + 1 });
				parameters.add(new Object[] { new String[] { currentInvalidAlg }, currentInvalidCycle,
						currentInvalidCycle - 1 });
			}
		}
		// For each valid algorithm name.
		for (final String currentValidAlg : getSomeValidAlgorithmsNames()) {
			// For each invalid cycle number.
			for (final Integer currentInvalidCycle : getSomeInvalidCycles()) {
				// Adds the current combination to the parameters list.
				parameters.add(new Object[] { new String[] { currentValidAlg }, currentInvalidCycle,
						currentInvalidCycle });
			}
			// For each valid cycle number.
			for (final Integer currentInvalidCycle : getSomeValidCycles()) {
				// Adds the current combination (with invalid min/max cycles pair) to the parameters list.
				parameters.add(new Object[] { new String[] { currentValidAlg }, currentInvalidCycle,
						currentInvalidCycle - 1 });
				parameters.add(new Object[] { new String[] { currentValidAlg }, currentInvalidCycle,
						currentInvalidCycle - 2 });
			}
		}
		// Returns the parameters.
		return parameters;
	}

	/**
	 * Tests setting the hasher with some valid parameters.
	 */
	@Test
	public void testSetLongHasherValid() {
		// For each valid parameters.
		for (final Object[] validParameters : getSomeValidParameters()) {
			// Creates a new credential hasher.
			final CredentialsHasher credentialsHasher = new CredentialsHasher();
			// Tries to set the the main parameters.
			credentialsHasher.setCredentialHasher((String[]) validParameters[0],
					(Integer) validParameters[1], (Integer) validParameters[2]);
		}
	}

	/**
	 * Tests setting the hasher with some invalid parameters.
	 */
	@Test
	public void testSetLongHasherInvalid() {
		// For each invalid parameters.
		for (final Object[] invalidParameters : getSomeInvalidParameters()) {
			// Creates a new credential hasher.
			final CredentialsHasher credentialsHasher = new CredentialsHasher();
			try {
				// Tries to set the the main parameters.
				credentialsHasher.setCredentialHasher((String[]) invalidParameters[0],
						(Integer) invalidParameters[1], (Integer) invalidParameters[2]);
				// If no exception was raised, the test fails.
				Assert.fail("An invalid parameter exception should have been raised.");
			}
			// If an invalid parameter exception is thrown.
			catch (final InvalidParameterException exception) {
				// Nothing happens (as that is expected).
			}
		}
	}

	/**
	 * Tests setting the hasher with some valid parameters.
	 */
	@Test
	public void testSetCredentialsHasherValid() {
		// For each valid parameters.
		for (final Object[] validParameters : getSomeValidParameters()) {
			// Creates a new credential hasher.
			final CredentialsHasher credentialsHasher = new CredentialsHasher();
			// Creates new hashed credentials.
			final FakeHashedCredentials hashedCredentials = new FakeHashedCredentials(
					(String[]) validParameters[0], (Integer) validParameters[1],
					(Integer) validParameters[2], null, null);
			// Tries to set the the main parameters.
			credentialsHasher.setCredentialHasher(hashedCredentials);
		}
	}

	/**
	 * Tests setting the hasher with some invalid parameters.
	 */
	@Test
	public void testSetCredentialsHasherInvalid() {
		// For each invalid parameters.
		for (final Object[] invalidParameters : getSomeInvalidParameters()) {
			// Creates a new credential hasher.
			final CredentialsHasher credentialsHasher = new CredentialsHasher();
			// Creates new hashed credentials.
			final FakeHashedCredentials hashedCredentials = new FakeHashedCredentials(
					(String[]) invalidParameters[0], (Integer) invalidParameters[1],
					(Integer) invalidParameters[2], null, null);
			// Tries to set the the main parameters.
			try {
				credentialsHasher.setCredentialHasher(hashedCredentials);
				// If no exception was raised, the test fails.
				Assert.fail("An invalid parameter exception should have been raised.");
			}
			// If an invalid parameter exception is thrown.
			catch (final InvalidParameterException exception) {
				// Nothing happens (as that is expected).
			}

		}
	}

	/**
	 * Tests the long constructor with valid parameters.
	 */
	@Test
	public void testLongConstructorValid() {
		// For each valid parameters.
		for (final Object[] validParameters : getSomeValidParameters()) {
			// Tries to create a new credential hasher with valid parameters.
			new CredentialsHasher((String[]) validParameters[0], (Integer) validParameters[1],
					(Integer) validParameters[2]);
		}
	}

	/**
	 * Tests the long constructor with invalid parameters.
	 */
	@Test
	public void testLongConstructorInvalid() {
		// For each invalid parameters.
		for (final Object[] invalidParameters : getSomeInvalidParameters()) {
			// Tries to create a new credential hasher with invalid parameters.
			try {
				new CredentialsHasher((String[]) invalidParameters[0], (Integer) invalidParameters[1],
						(Integer) invalidParameters[2]);
				// If no exception was raised, the test fails.
				Assert.fail("An invalid parameter exception should have been raised.");
			}
			// If an invalid parameter exception is thrown.
			catch (final InvalidParameterException exception) {
				// Nothing happens (as that is expected).
			}

		}
	}

	/**
	 * Tests the credential constructor with valid parameters.
	 */
	@Test
	public void testCredentialConstructorValid() {
		// For each valid parameters.
		for (final Object[] validParameters : getSomeValidParameters()) {
			// Tries to create a new credential hasher with valid parameters.
			new CredentialsHasher(new FakeHashedCredentials((String[]) validParameters[0],
					(Integer) validParameters[1], (Integer) validParameters[2], null, null));
		}
	}

	/**
	 * Tests the credential constructor with invalid parameters.
	 */
	@Test
	public void testCredentialConstructorInvalid() {
		// For each invalid parameters.
		for (final Object[] invalidParameters : getSomeInvalidParameters()) {
			// Tries to create a new credential hasher with invalid parameters.
			try {
				new CredentialsHasher(new FakeHashedCredentials((String[]) invalidParameters[0],
						(Integer) invalidParameters[1], (Integer) invalidParameters[2], null, null));
				// If no exception was raised, the test fails.
				Assert.fail("An invalid parameter exception should have been raised.");
			}
			// If an invalid parameter exception is thrown.
			catch (final InvalidParameterException exception) {
				// Nothing happens (as that is expected).
			}

		}
	}

}
