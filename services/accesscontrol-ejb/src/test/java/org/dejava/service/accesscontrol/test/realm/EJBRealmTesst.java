package org.dejava.service.accesscontrol.test.realm;

import javax.inject.Inject;

import org.dejava.service.accesscontrol.business.UserService;
import org.dejava.service.accesscontrol.util.AccessControl;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.runner.RunWith;

/**
 * Tests for the shiro EJB realm.
 */
@RunWith(Arquillian.class)
public class EJBRealmTesst {

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
		// Defines and returns the archive definition.
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, "org.dejava.service.accesscontrol")
				.addAsLibraries(
						dependencyResolver.artifacts("org.dejava.component:security",
								"org.dejava.component:javaee", "org.dejava.component:exception",
								"org.dejava.component:i18n", "org.dejava.component:reflection")
								.resolveAsFiles())
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	/**
	 * TODO
	 */
	@Inject
	@AccessControl
	private UserService userService;

}
