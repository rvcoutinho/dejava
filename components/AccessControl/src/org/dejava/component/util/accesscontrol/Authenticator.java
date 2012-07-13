package org.dejava.component.util.accesscontrol;

import org.dejava.component.util.accesscontrol.model.User;

/**
 * Defines an user authentication method.
 */
public interface Authenticator {

	/**
	 * Authenticates an user in the system.
	 * 
	 * @param user
	 *            The user to be authenticated.
	 * @param authInfo
	 *            Authentication information.
	 */
	void authenticate(final User user, Object... authInfo);
}
