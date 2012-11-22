package org.dejava.component.security.authc.credential.matcher;

import java.util.Arrays;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.dejava.component.security.authc.credential.HashedCredentials;
import org.dejava.component.security.crypt.hasher.CredentialsHasher;

/**
 * Handles matching with hashed credentials.
 */
public class HashedCredentialsMatcher implements CredentialsMatcher {

	/**
	 * @see org.apache.shiro.authc.credential.CredentialsMatcher#doCredentialsMatch(org.apache.shiro.authc.AuthenticationToken,
	 *      org.apache.shiro.authc.AuthenticationInfo)
	 */
	@Override
	public boolean doCredentialsMatch(final AuthenticationToken token, final AuthenticationInfo info) {
		// If both token and authentication info are not null.
		if ((token != null) & (info != null)) {
			// If the credentials are hashed credentials.
			if (info.getCredentials() instanceof HashedCredentials) {
				// Converts them into hashed credentials.
				final HashedCredentials hashedCredentials = (HashedCredentials) info.getCredentials();
				// Creates a new credentials hasher with the hashed credentials information.
				final CredentialsHasher credentialsHasher = new CredentialsHasher(hashedCredentials);
				// Gets the submitted credentials.
				Object submittedCredentials = token.getCredentials();
				// If the submitted credentials are not null.
				if (submittedCredentials != null) {
					// If the submitted credentials are represented by a char array.
					if (submittedCredentials instanceof char[]) {
						// The submitted credentials are converted to string.
						submittedCredentials = new String((char[]) submittedCredentials);
					}
					// If the submitted credentials are represented by a byte array.
					if (submittedCredentials instanceof byte[]) {
						// The submitted credentials are converted to string.
						submittedCredentials = new String((byte[]) submittedCredentials);
					}
					// Uses the credentials hasher to hash the submitted credentials.
					final byte[] hashedSubmittedCredentials = credentialsHasher.hash(
							submittedCredentials.toString(), hashedCredentials.getSalt());
					// Returns if the credentials are equals.
					return Arrays
							.equals(hashedCredentials.getHashedCredentials(), hashedSubmittedCredentials);
				}
			}
		}
		// If the credentials could be matched to the one in the token, returns false.
		return false;
	}
}
