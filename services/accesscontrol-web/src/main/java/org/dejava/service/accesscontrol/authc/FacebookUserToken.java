package org.dejava.service.accesscontrol.authc;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * The facebook authentication token.
 */
public class FacebookUserToken implements AuthenticationToken {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -378171926579174125L;

	/**
	 * Facebook identifier for the user.
	 */
	private String identifier;

	/**
	 * Gets the facebook identifier for the user.
	 * 
	 * @return The facebook identifier for the user.
	 */
	public synchronized String getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the facebook identifier for the user.
	 * 
	 * @param identifier
	 *            New facebook identifier for the user.
	 */
	public synchronized void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()
	 */
	@Override
	public Object getPrincipal() {
		return getIdentifier();
	}

	/**
	 * @see org.apache.shiro.authc.AuthenticationToken#getCredentials()
	 */
	@Override
	public Object getCredentials() {
		// Returns null (there are no credentials as authentication is made through facebook).
		return null;
	}

	/**
	 * Public constructor.
	 */
	public FacebookUserToken() {
		super();
	}

	/**
	 * Public constructor.
	 * 
	 * @param identifier
	 *            Facebook identifier for the user.
	 */
	public FacebookUserToken(final String identifier) {
		super();
		this.identifier = identifier;
	}

}
