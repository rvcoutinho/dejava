package org.dejava.component.jsf.test.controller;

import javax.inject.Inject;

import org.dejava.component.ejb.component.GenericComponent;
import org.dejava.component.jsf.test.component.AbstractGenericComponentTest;
import org.dejava.component.jsf.test.util.FakeEntity;
import org.dejava.component.jsf.test.util.FakeEntityController;
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
public class GenericControllerTest extends AbstractGenericComponentTest {

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
				.addPackages(true, "org.dejava.component.jsf")
				.addAsLibraries(
						dependencyResolver.artifacts("org.dejava.component:ejb",
								"org.dejava.component:exception", "org.dejava.component:i18n",
								"org.dejava.component:reflection").resolveAsFiles())
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
	 * @see org.dejava.component.jsf.test.component.AbstractGenericComponentTest#getComponent()
	 */
	@Override
	protected GenericComponent<FakeEntity, Integer> getComponent() {
		return fakeEntityController;
	}

}
