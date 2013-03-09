package org.dejava.service.accesscontrol.test.realm;

import java.io.File;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.dejava.service.accesscontrol.business.UserService;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.credentials.Password;
import org.dejava.service.accesscontrol.model.principal.Email;
import org.dejava.service.accesscontrol.model.principal.Name;
import org.dejava.service.accesscontrol.util.AccessControl;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for the shiro EJB realm.
 */
@RunWith(Arquillian.class)
public class EJBRealmTest {

	/**
	 * The web application source path.
	 */
	public static final String WEBAPP_SRC = "src/main/webapp";

	/**
	 * Defines what should be done during deployment (before tests).
	 * 
	 * @return The configuration for the archive.
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		// Gets the maven dependency resolver (for the regular libs).
		final MavenDependencyResolver dependencyResolver = DependencyResolvers.use(
				MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
		// Gets the maven dependency resolver (for the ejb module).
		final MavenDependencyResolver dependencyResolver2 = DependencyResolvers.use(
				MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
		// Gets the EJB jar.
		final JavaArchive ejbJar = dependencyResolver2.artifact("org.dejava.service:accesscontrol-ejb")
				.resolveAs(JavaArchive.class).iterator().next();
		// Removes the persistence.xml from the jar.
		ejbJar.delete("META-INF/persistence.xml");
		// Defines and returns the archive definition.
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.merge(ejbJar, "WEB-INF/classes")
				.addPackages(true, "org.dejava.service.accesscontrol")
				.addAsLibraries(
						dependencyResolver.artifacts("org.dejava.component:security",
								"org.dejava.component:javaee", "org.apache.shiro:shiro-core",
								"org.apache.shiro:shiro-web", "com.restfb:restfb").resolveAsFiles())
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml")
				.addAsWebInfResource("shiro.ini", "shiro.ini")
				.addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/faces-config.xml"))
				.addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/web.xml"));
	}

	/**
	 * User service.
	 */
	@Inject
	@AccessControl
	private UserService userService;

	/**
	 * Some users to be used in the tests.
	 */
	private static final User[] SOME_USERS = { new User("rvcoutinho", "rvcoutinho@gmail.com", "12345") };

	/**
	 * Some other users to be used in the tests.
	 */
	private static final User[] SOME_OTHER_USERS = { new User("rvcoutinho", "rvcoutinho@gmail.com", "123456") };

	/**
	 * Adds some users (database) to be used in the tests.
	 */
	@Before
	public void addSomeUsers() {
		// For each user.
		for (final User currentUser : SOME_USERS) {
			// Tries to add the current user via the EJB user service.
			userService.addOrUpdate(currentUser);
		}
	}

	/**
	 * Removes the users (database) used in the tests.
	 */
	@After
	public void removeSomeUsers() {
		// Tries to remove all users.
		userService.removeAll(userService.getAllEntities(null, null));
	}

	/**
	 * Logs the current user out.
	 */
	@After
	public void logout() {
		// Gets the current logged user.
		final Subject loggedUser = SecurityUtils.getSubject();
		// Tries to logout the user.
		loggedUser.logout();
	}

	/**
	 * Gets a username/password token for the given user.
	 * 
	 * @param user
	 *            User to get the token for.
	 * @param emailAsUserName
	 *            If the email should be used as user name.
	 * @return A username/password token for the given user.
	 */
	private UsernamePasswordToken getUserToken(final User user, final Boolean emailAsUserName) {
		// The user name to be used in the login.
		final String userName;
		// If the email should be used as the user name.
		if (emailAsUserName) {
			// Gets the user name.
			userName = user.getPrincipal(Email.class).getEmail();
		}
		// Otherwise.
		else {
			// Gets the user name.
			userName = user.getPrincipal(Name.class).getName();
		}
		// Gets the user password.
		final String userPassword = user.getCredentials(Password.class).getRawPassword();
		// Returns a new username/password token.
		return new UsernamePasswordToken(userName, userPassword);
	}

	/**
	 * Tests a regular login (using the user name) attempt.
	 */
	@Test
	public void testLoginNameSuccess() {
		// Gets the current logged user.
		final Subject loggedUser = SecurityUtils.getSubject();
		// Tries to login the user.
		loggedUser.login(getUserToken(SOME_USERS[0], false));
	}

	/**
	 * Tests a regular login (using the user email) attempt.
	 */
	@Test
	public void testLoginEmailSuccess() {
		// Gets the current logged user.
		final Subject loggedUser = SecurityUtils.getSubject();
		// Tries to login the user.
		loggedUser.login(getUserToken(SOME_USERS[0], true));
	}

	/**
	 * Tests a login (using the user name) attempt with wrong credentials.
	 */
	@Test(expected = AuthenticationException.class)
	public void testLoginNameWrongCredentials() {
		// Gets the current logged user.
		final Subject loggedUser = SecurityUtils.getSubject();
		// Tries to login the user.
		loggedUser.login(getUserToken(SOME_OTHER_USERS[0], false));
	}

	/**
	 * Tests a login (using the user email) attempt with wrong credentials.
	 */
	@Test(expected = AuthenticationException.class)
	public void testLoginEmailWrongCredentials() {
		// Gets the current logged user.
		final Subject loggedUser = SecurityUtils.getSubject();
		// Tries to login the user.
		loggedUser.login(getUserToken(SOME_OTHER_USERS[0], true));
	}

	/**
	 * Tests a login attempt with a null token.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testLoginNullToken() {
		// Gets the current logged user.
		final Subject loggedUser = SecurityUtils.getSubject();
		// Tries to login the user.
		loggedUser.login(null);
	}

}
