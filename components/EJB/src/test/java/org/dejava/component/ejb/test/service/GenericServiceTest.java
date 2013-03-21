package org.dejava.component.ejb.test.service;

import javax.inject.Inject;

import org.dejava.component.ejb.service.GenericService;
import org.dejava.component.ejb.test.util.EJB;
import org.dejava.component.ejb.test.util.FakeEntity;
import org.dejava.component.ejb.test.util.FakeEntityService;
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
 * Tests for the generic EJB service.
 */
@RunWith(Arquillian.class)
public class GenericServiceTest extends AbstractGenericServiceTest {

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
				.addPackages(true, "org.dejava.component.ejb")
				.addAsLibraries(
						dependencyResolver.artifacts("org.dejava.component:exception",
								"org.dejava.component:i18n", "org.dejava.component:exception",
								"org.dejava.component:reflection", "org.dejava.component:validation")
								.resolveAsFiles())
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	/**
	 * Fake entity service.
	 */
	@Inject
	@EJB
	private FakeEntityService fakeEntityService;

	/**
	 * @see org.dejava.component.ejb.test.service.AbstractGenericServiceTest#getService()
	 */
	@Override
	protected GenericService<FakeEntity, Integer> getService() {
		return fakeEntityService;
	}

}
