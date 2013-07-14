package org.dejava.component.security.test.authc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import junit.framework.Assert;

import org.dejava.component.security.authc.credential.matcher.HashedCredentialsMatcher;
import org.dejava.component.security.crypt.hasher.CredentialsHasher;
import org.dejava.component.security.test.crypt.CredentialsHasherTest;
import org.dejava.component.security.test.util.FakeAuthenticationInfo;
import org.dejava.component.security.test.util.FakeAuthenticationToken;
import org.dejava.component.security.test.util.FakeHashedCredentials;
import org.junit.Test;

/**
 * Tests for the hashed credentials matcher.
 */
public class HashedCredentialsMatcherTest {

	/**
	 * Gets some credentials to be used in the tests.
	 * 
	 * @return Some credentials to be used in the tests.
	 */
	public static Collection<String> getSomeRawCredentials() {
		return new ArrayList<>(Arrays.asList(CredentialsHasherTest.SOME_RAW_CREDENTIALS));
	}

	/**
	 * Tests the credentials matcher with a valid match.
	 */
	@Test
	public void testMatcherSuccess() {
		// For each credential.
		for (final String rawCredentials : getSomeRawCredentials()) {
			// The salt is going to be the raw credentials hash code.
			final String salt = String.valueOf(rawCredentials.hashCode());
			// Creates a fake hashed credential.
			final FakeHashedCredentials hashedCredentials = new FakeHashedCredentials(
					CredentialsHasher.ALGS_NAMES, 10, 30, salt, null);
			// Creates the credentials hasher.
			final CredentialsHasher credentialsHasher = new CredentialsHasher(hashedCredentials);
			// Sets the hashed credentials.
			hashedCredentials.setHashedCredentials(credentialsHasher.hash(rawCredentials, salt));
			// Creates 3 tokens.
			final FakeAuthenticationToken token1 = new FakeAuthenticationToken();
			final FakeAuthenticationToken token2 = new FakeAuthenticationToken();
			final FakeAuthenticationToken token3 = new FakeAuthenticationToken();
			// Sets the raws credentials in the tokens (in different formats).
			token1.setCredentials(rawCredentials);
			token2.setCredentials(rawCredentials.toCharArray());
			token3.setCredentials(rawCredentials.getBytes());
			// Creates a new authentication info.
			final FakeAuthenticationInfo info = new FakeAuthenticationInfo();
			// Sets the hashed credentials in the authentication info.
			info.setCredentials(hashedCredentials);
			// Creates a new matcher.
			final HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
			// Asserts that tokens and authentication info match.
			Assert.assertTrue(hashedCredentialsMatcher.doCredentialsMatch(token1, info));
			Assert.assertTrue(hashedCredentialsMatcher.doCredentialsMatch(token2, info));
			Assert.assertTrue(hashedCredentialsMatcher.doCredentialsMatch(token3, info));
		}
	}

	/**
	 * Gets pairs of uneven credentials (raw/hashed).
	 * 
	 * @return Pairs of uneven credentials (raw/hashed).
	 */
	public static Collection<Object[]> getSomeUnevenCredentialsPairs() {
		// Creates a new list of credentials pairs.
		final Collection<Object[]> credentialsPairs = new ArrayList<>();
		// For some raw credentials.
		for (final String curRawCredentials : getSomeRawCredentials()) {
			// The salt is going to be the raw credentials hash code.
			final String salt = String.valueOf(curRawCredentials.hashCode());
			// Creates a fake hashed credential.
			final FakeHashedCredentials curHashedCredentials = new FakeHashedCredentials(
					CredentialsHasher.ALGS_NAMES, 10, 30, salt, null);
			// Creates the credentials hasher.
			final CredentialsHasher credentialsHasher = new CredentialsHasher(curHashedCredentials);
			// Sets the hashed credentials.
			curHashedCredentials.setHashedCredentials(credentialsHasher.hash(curRawCredentials, salt));
			// Adds the current pair (raw/hashed) but with some minor modification in the raw credentials (so
			// they do not match).
			credentialsPairs.add(new Object[] { curRawCredentials + "dif", curHashedCredentials });
			// Adds null raw credentials and the regular hashed ones.
			credentialsPairs.add(new Object[] { null, curHashedCredentials });
			// Adds null hashed credentials and the regular raw ones.
			credentialsPairs.add(new Object[] { curRawCredentials, null });
			// Adds a pair of raw credentials.
			credentialsPairs.add(new Object[] { curRawCredentials, curRawCredentials });
		}
		// Adds a null/null pair.
		credentialsPairs.add(new Object[] { null, null });
		// Returns the list of credentials pairs.
		return credentialsPairs;
	}

	/**
	 * Tests the credentials matcher with an uneven match.
	 */
	@Test
	public void testUnevenMatch() {
		// For each credentials pair.
		for (final Object[] credentialsPair : getSomeUnevenCredentialsPairs()) {
			// Creates a new authentication token.
			final FakeAuthenticationToken token = new FakeAuthenticationToken();
			// Sets the raws credentials in the tokens (in different formats).
			token.setCredentials(credentialsPair[0]);
			// Creates a new authentication info.
			final FakeAuthenticationInfo info = new FakeAuthenticationInfo();
			// Sets the hashed credentials in the authentication info.
			info.setCredentials(credentialsPair[1]);
			// Creates a new matcher.
			final HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
			// Asserts that tokens and authentication info do not match.
			Assert.assertTrue(!hashedCredentialsMatcher.doCredentialsMatch(token, info));
		}
	}

}
