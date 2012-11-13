package org.dejava.service.accesscontrol.test.realm;

import javax.inject.Inject;

import org.dejava.service.accesscontrol.business.UserService;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.model.credentials.Password;
import org.dejava.service.accesscontrol.model.principal.Name;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for the shiro EJB realm.
 */
@RunWith(Arquillian.class)
public class EJBRealmTest {

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
				.addPackages(true, "org.dejava.component.accesscontrol")
				.addAsLibraries(
						dependencyResolver.artifacts("org.dejava.component:security",
								"org.dejava.component:javaee", "org.dejava.component:exception",
								"org.dejava.component:i18n", "org.dejava.component:reflection").resolveAs(
								JavaArchive.class))
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	/**
	 * TODO
	 */
	@Inject
	private UserService userService;

	/**
	 * TODO
	 */
	@Test
	public void test() {
		System.out.println("test");
		// Creates a new user.
		User user = new User();
		// Creates a new user name.
		final Name name = new Name();
		// Sets the user name fields.
		name.setName("rvcoutinho");
		name.setUser(user);
		// Creates a new password.
		final Password password = new Password();
		// Sets the password fields.
		password.setRawPassword("1234");
		password.setUser(user);
		// Tries to persist the new user.
		user = userService.addOrUpdate(user);
		// Prints the user id.
		System.out.println(user.getId());
	}

}
