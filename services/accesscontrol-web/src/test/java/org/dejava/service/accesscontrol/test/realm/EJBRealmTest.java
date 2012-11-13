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
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
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
		// Gets the maven dependency resolver.
		final MavenDependencyResolver dependencyResolver = DependencyResolvers.use(
				MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
		// Defines and returns the archive definition.
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, "org.dejava.component.accesscontrol")
				.addAsLibraries(dependencyResolver.artifact("org.dejava.component:security").resolveAsFiles())
				.addAsLibraries(dependencyResolver.artifact("org.dejava.component:javaee").resolveAsFiles())
				.addAsLibraries(
						dependencyResolver.artifact("org.dejava.component:exception").resolveAsFiles())
				.addAsLibraries(dependencyResolver.artifact("org.dejava.component:i18n").resolveAsFiles())
				.addAsLibraries(
						dependencyResolver.artifact("org.dejava.component:reflection").resolveAsFiles())
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
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
		Name name = new Name();
		// Sets the user name fields.
		name.setName("rvcoutinho");
		name.setUser(user);
		// Creates a new password.
		Password password = new Password();
		// Sets the password fields.
		password.setRawPassword("1234");
		password.setUser(user);
		// Tries to persist the new user.
		user = userService.addOrUpdate(user);
		// Prints the user id.
		System.out.println(user.getId());
	}

}
