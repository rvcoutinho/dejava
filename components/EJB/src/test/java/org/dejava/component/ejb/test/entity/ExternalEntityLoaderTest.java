package org.dejava.component.ejb.test.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import org.dejava.component.ejb.entity.ExternalEntityLoader;
import org.dejava.component.ejb.test.util.FakeEntity;
import org.dejava.component.ejb.test.util.FakeEntityComponent;
import org.dejava.component.ejb.test.util.SomeOtherFakeEntity;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for the external entity loader.
 */
@RunWith(Arquillian.class)
public class ExternalEntityLoaderTest {

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
	 * Fake entity component.
	 */
	@Inject
	private FakeEntityComponent fakeEntityComponent;

	/**
	 * Assert that there are no entities persisted.
	 */
	@Before
	public void assertNoEntitiesPersisted() {
		// Gets all entities.
		final Collection<FakeEntity> allEntities = fakeEntityComponent.getAll(null, null);
		// Assert that there are no entities persisted.
		Assert.assertTrue(allEntities.isEmpty());
	}

	/**
	 * Removes all test entities.
	 */
	@After
	public void removeTestEntities() {
		// Gets all entities.
		final Collection<FakeEntity> allEntities = fakeEntityComponent.getAll(null, null);
		// Removes all persisted entities.
		fakeEntityComponent.remove(allEntities);
	}

	/**
	 * Gets some invalid set of parameters for the getExternalEntity method.
	 * 
	 * @return Some invalid set of parameters for the getExternalEntity method.
	 */
	public static Collection<Object[]> getInvalidParamsGetExternalEntityMethod() {
		// Creates a new list of invalid parameters for the method.
		final ArrayList<Object[]> invalidParams = new ArrayList<>();
		// Adds some invalid set of parameters.
		invalidParams.add(new Object[] { null, null, null });
		invalidParams.add(new Object[] { null, "invalid", null });
		invalidParams.add(new Object[] { "invalid", "invalid", null });
		invalidParams.add(new Object[] { "invalid", null, "invalid" });
		invalidParams.add(new Object[] { null, "invalid", "invalid" });
		invalidParams.add(new Object[] { "invalid", "invalid", "invalid" });
		invalidParams.add(new Object[] { "Component/Test/FakeEntityComponent", null, null });
		invalidParams.add(new Object[] { "Component/Test/FakeEntityComponent", "getById", null });
		// Returns the invalid parameters.
		return invalidParams;
	}

	/**
	 * Tests the getExternalEntity method with invalid parameters.
	 */
	@Test
	public void testGetExternalEntityInvalidParams() {
		// For each set of invalid parameters.
		for (final Object[] curParamsSet : getInvalidParamsGetExternalEntityMethod()) {
			// Tries to get the external entity.
			try {
				ExternalEntityLoader.getExternalEntity((String) curParamsSet[0], (String) curParamsSet[1],
						curParamsSet[2]);
				// If no InvalidParameter exception is thrown, fails the test.
				Assert.fail();
			}
			// Expects a InvalidParameterException.
			catch (final InvalidParameterException exception) {

			}
		}
	}

	/**
	 * Default names for fake entities.
	 */
	private static final String[] ENTITIES_NAMES = { "Entity1", "Entity2", "Entity3", "Entity4", "Entity5" };

	/**
	 * Tests the getExternalEntity method with valid parameters.
	 */
	@Test
	public void testGetExternalEntityValidParams() {
		// Persists a new test entity.
		final FakeEntity testEntity = fakeEntityComponent.addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to get the entity as if it is an external one.
		final FakeEntity externalEntity = (FakeEntity) ExternalEntityLoader
				.getExternalEntity("java:/global/test/Component/Test/FakeEntityComponent", "getById",
						testEntity.getIdentifier());
		// Asserts that the entities are similar.
		Assert.assertEquals(testEntity, externalEntity);
	}

	/**
	 * Tests the loadAllExternalEntities with one external entity and no collection fields (for extenal
	 * entities).
	 */
	@Test
	public void testLoadAllExternalEntitiesOneEntityNonCollection() {
		// Persists a new "external" entity.
		final FakeEntity externalEntity = fakeEntityComponent.addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Creates a test entity that refers to the "external" entity identifier.
		final SomeOtherFakeEntity testEntity = new SomeOtherFakeEntity(externalEntity.getIdentifier());
		// Asserts that the test entity does not refer to the external entity.
		Assert.assertNull(testEntity.getExternalEntity());
		// Tries to load all external entities for the test entity.
		ExternalEntityLoader.loadAllExternalEntities(testEntity);
		// Asserts that the external entity is the same previously persisted.
		Assert.assertEquals(externalEntity, testEntity.getExternalEntity());
	}
}
