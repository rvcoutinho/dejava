package org.dejava.component.javaee.test.controller;

import javax.inject.Inject;

import org.dejava.component.javaee.service.GenericService;
import org.dejava.component.javaee.test.service.AbstractGenericServiceTest;
import org.dejava.component.javaee.test.util.FakeEntity;
import org.dejava.component.javaee.test.util.FakeEntityController;
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
 * Tests for the generic JSF controller.
 */
@RunWith(Arquillian.class)
public class GenericControllerTest extends AbstractGenericServiceTest {

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
				.addPackages(true, "org.dejava.component.javaee")
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
	 * Fake entity controller.
	 */
	@Inject
	private FakeEntityController fakeEntityController;

	/**
	 * @see org.dejava.component.javaee.test.service.AbstractGenericServiceTest#getService()
	 */
	@Override
	protected GenericService<FakeEntity, Integer> getService() {
		return fakeEntityController;
	}

}
