package org.dejava.component.security.crypt.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
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
			"gvrjdesnbdfjk", "s3cr3t" };

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
}
