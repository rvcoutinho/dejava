package org.dejava.component.security.test.util;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Some fake authentication token.
 */
public class FakeAuthenticationToken implements AuthenticationToken {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -5766196008736437714L;

	/**
	 * Principal.
	 */
	private Object principal;

	/**
	 * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()
	 */
	@Override
	public Object getPrincipal() {
		return principal;
	}

	/**
	 * Sets the principal.
	 * 
	 * @param principal
	 *            New principal.
	 */
	public void setPrincipal(final Object principal) {
		this.principal = principal;
	}

	/**
	 * Credentials.
	 */
	private Object credentials;

	/**
	 * @see org.apache.shiro.authc.AuthenticationToken#getCredentials()
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
