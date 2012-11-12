package org.dejava.component.security.test.util;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Some fake authentication token.
 */
public class FakeAuthenticationInfo implements AuthenticationInfo {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -401518223461015757L;

	/**
	 * @see org.apache.shiro.authc.AuthenticationInfo#getPrincipals()
	 */
	@Override
	public PrincipalCollection getPrincipals() {
		// Will not be used.
		return null;
	}

	/**
	 * Credentials.
	 */
	private Object credentials;

	/**
	 * @see org.apache.shiro.authc.AuthenticationInfo#getCredentials()
	 */
	@Override
	public Object getCredentials() {
		return credentials;
	}

	/**
	 * Sets the credentials.
	 * 
	 * @param credentials
	 *            New credentials.
	 */
	public void setCredentials(final Object credentials) {
		this.credentials = credentials;
	}

}
