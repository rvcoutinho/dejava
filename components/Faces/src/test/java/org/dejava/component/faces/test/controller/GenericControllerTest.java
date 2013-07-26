package org.dejava.component.faces.test.controller;

import javax.inject.Inject;

import org.dejava.component.ejb.component.GenericComponent;
import org.dejava.component.faces.test.controller.component.AbstractGenericComponentTest;
import org.dejava.component.faces.test.controller.util.Faces;
import org.dejava.component.faces.test.controller.util.FakeEntity;
import org.dejava.component.faces.test.controller.util.FakeEntityController;
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
				.addAsLibraries(
						dependencyResolver.artifacts("org.dejava.component:ejb",
								"org.dejava.component:validation", "org.dejava.component:exception",
								"org.dejava.component:i18n", "org.dejava.component:reflection")
								.resolveAsFiles()).addPackages(true, "org.dejava.component.faces.test.controller")
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	/**
	 * Fake entity controller.
	 */
	@Faces
	@Inject
	private FakeEntityController fakeEntityController;

	/**
	 * @see org.dejava.component.faces.test.controller.component.AbstractGenericComponentTest#getComponent()
	 */
	@Override
	protected GenericComponent<FakeEntity, Integer> getComponent() {
		return fakeEntityController;
	}

}
