package org.dejava.component.ejb.test.component;

import javax.inject.Inject;

import org.dejava.component.ejb.component.GenericComponent;
import org.dejava.component.ejb.test.util.FakeEntity;
import org.dejava.component.ejb.test.util.FakeEntityComponent;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;

/**
 * Tests for the generic EJB component.
 */
@RunWith(Arquillian.class)
public class GenericComponentTest extends AbstractGenericComponentTest {

	/**
	 * Defines what should be done during deployment (before tests).
	 * 
	 * @return The configuration for the archive.
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		// Defines and returns the archive definition.
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, "org.dejava.component.ejb")
				.addAsLibraries(
						Maven.resolver()
								.loadPomFromFile("pom.xml")
								.resolve("org.dejava.component:exception", "org.dejava.component:i18n",
										"org.dejava.component:exception", "org.dejava.component:reflection",
										"org.dejava.component:validation").withoutTransitivity().asFile())
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	/**
	 * Fake entity component.
	 */
	@Inject
	private FakeEntityComponent fakeEntityComponent;

	/**
	 * @see org.dejava.component.ejb.test.component.AbstractGenericComponentTest#getComponent()
	 */
	@Override
	protected GenericComponent<FakeEntity, Integer> getComponent() {
		return fakeEntityComponent;
	}

}
