package org.dejava.service.accesscontrol.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.dejava.service.accesscontrol.business.crendential.PasswordService;
import org.dejava.service.accesscontrol.business.principal.EmailService;
import org.dejava.service.accesscontrol.business.principal.NameService;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.credential.Password;

/**
 * TODO
 */
public class DBRealm extends JdbcRealm {

	/**
	 * The email EJB service.
	 */
	private EmailService emailService;

	/**
	 * Gets the email EJB service.
	 * 
	 * @return The email EJB service.
	 */
	public EmailService getEmailService() {
		return emailService;
	}

	/**
	 * The name EJB service.
	 */
	private NameService nameService;

	/**
	 * Gets the name EJB service.
	 * 
	 * @return The name EJB service.
	 */
	public NameService getNameService() {
		return nameService;
	}

	/**
	 * The password EJB service.
	 */
	private PasswordService passwordService;

	/**
	 * Gets the password EJB service.
	 * 
	 * @return The password EJB service.
	 */
	public PasswordService getPasswordService() {
		return passwordService;
	}

	/**
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token)
			throws AuthenticationException {
		// Casts the token into an username/password token.
		final UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		// Tries to get the user by its name.
		User user = getNameService().getEntityByAttribute("name", upToken.getUsername()).getUser();
		// If no user is found for the name.
		if (user == null) {
			// Tries to get the user its email.
			user = getEmailService().getEntityByAttribute("email", upToken.getUsername()).getUser();
		}
		// Creates a new principal collection.
		final PrincipalCollection principals = new SimplePrincipalCollection(user.getId(), getName());
		// Gets the password for the user.
		final Password password = getPasswordService().getEntityByAttribute("user", user);
		// Creates a new simple authentication (with the user principals and credentials).
		final SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principals, password
				.getPassword().toCharArray());
		// Sets the salt to be used by the credential the hashed credential matcher.
		authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(password.getSalt()));
		// Returns the authentication information.
		return authenticationInfo;
	}

	/**
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

}
