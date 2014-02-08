package org.dejava.service.accesscontrol.authc;

import org.apache.shiro.authc.RememberMeAuthenticationToken;

/**
 * The facebook authentication token.
 */
public class FacebookUserToken implements RememberMeAuthenticationToken {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -378171926579174125L;

	/**
	 * If the user identity should be remembered across sessions.
	 */
	private Boolean rememberMe = true;

	/**
	 * @see org.apache.shiro.authc.RememberMeAuthenticationToken#isRememberMe()
	 */
	@Override
	public boolean isRememberMe() {
		return rememberMe;
	}

	/**
	 * Sets if the user identity should be remembered across sessions.
	 * 
	 * @param rememberMe
	 *            If the user identity should be remembered across sessions.
	 */
	public void setRememberMe(final Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	/**
	 * Facebook identifier for the user.
	 */
	private String facebookIdentifier;

	/**
	 * Gets the facebook identifier for the user.
	 * 
	 * @return The facebook identifier for the user.
	 */
	public synchronized String getFacebookIdentifier() {
		return facebookIdentifier;
	}

	/**
	 * Sets the facebook identifier for the user.
	 * 
	 * @param facebookIdentifier
	 *            New facebook identifier for the user.
	 */
	public synchronized void setFacebookIdentifier(final String facebookIdentifier) {
		this.facebookIdentifier = facebookIdentifier;
	}

	/**
	 * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()
	 */
	@Override
	public Object getPrincipal() {
		return getFacebookIdentifier();
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
	 * @param facebookIdentifier
	 * 
	 *            Facebook identifier for the user.
	 */
	public FacebookUserToken(final String facebookIdentifier) {
		super();
		this.facebookIdentifier = facebookIdentifier;
	}

}
