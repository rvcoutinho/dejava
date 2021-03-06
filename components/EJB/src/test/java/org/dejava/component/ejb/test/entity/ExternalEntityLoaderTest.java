package org.dejava.component.ejb.test.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.inject.Inject;

import org.dejava.component.ejb.entity.ExternalEntityLoader;
import org.dejava.component.ejb.test.util.FakeEntity;
import org.dejava.component.ejb.test.util.FakeEntityComponent;
import org.dejava.component.ejb.test.util.SomeOtherFakeEntity;
import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
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
		invalidParams.add(new Object[] { null, null, null, null });
		invalidParams.add(new Object[] { null, "invalid", null, null });
		invalidParams.add(new Object[] { "invalid", "invalid", null, null });
		invalidParams.add(new Object[] { "invalid", null, new Class[] { Integer.class }, null });
		invalidParams.add(new Object[] { "invalid", null, new Class[] { Integer.class },
				new Object[] { "invalid" } });
		invalidParams.add(new Object[] { "invalid", null, null, new Object[] { "invalid" } });
		invalidParams.add(new Object[] { null, "invalid", new Class[] { Integer.class }, null });
		invalidParams.add(new Object[] { null, "invalid", null, new Object[] { "invalid" } });
		invalidParams.add(new Object[] { null, null, new Class[] { Integer.class },
				new Object[] { "invalid" } });
		invalidParams.add(new Object[] { null, "invalid", new Class[] { Integer.class },
				new Object[] { "invalid" } });
		invalidParams.add(new Object[] { "invalid", "invalid", new Class[] { Integer.class },
				new Object[] { "invalid" } });
		invalidParams.add(new Object[] { "Component/Test/FakeEntityComponent", null, null, null });
		invalidParams.add(new Object[] { "Component/Test/FakeEntityComponent", null,
				new Class[] { Integer.class }, null });
		invalidParams.add(new Object[] { "Component/Test/FakeEntityComponent", "getById", null, null });
		invalidParams.add(new Object[] { "Component/Test/FakeEntityComponent", "getById",
				new Class[] { Integer.class }, null });
		invalidParams.add(new Object[] { "Component/Test/FakeEntityComponent", "getById", null,
				new Object[] { 1 } });
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
						(Class[]) curParamsSet[2], (Object[]) curParamsSet[3]);
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
		final FakeEntity externalEntity = (FakeEntity) ExternalEntityLoader.getExternalEntity(
				"java:/global/test/Component/Test/FakeEntityComponent", "getById",
				new Class[] { Integer.class }, new Object[] { testEntity.getIdentifier() });
		// Asserts that the entities are similar.
		Assert.assertEquals(testEntity, externalEntity);
	}

	/**
	 * Tests the loadAllExternalEntities with no external entities (ignoring lazy loading).
	 */
	@Test
	public void testLoadAllExternalEntitiesNoEntity() {
		// Creates an empty test entity.
		final SomeOtherFakeEntity testEntity = new SomeOtherFakeEntity(null, null, null, null, null, null,
				null);
		// Tries to load all external entities for the test entity (no exceptions should be thrown).
		ExternalEntityLoader.loadAllExternalEntities(testEntity, true);
	}

	/**
	 * Tests the loadAllExternalEntities with external entity in a regular field (ignoring lazy loading).
	 */
	@Test
	public void testLoadAllExternalEntitiesNonCollection() {
		// Persists a new external entity.
		final FakeEntity externalEntity = fakeEntityComponent.addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Creates a test entity that refers to the external entity identifier.
		final SomeOtherFakeEntity testEntity = new SomeOtherFakeEntity(null, externalEntity.getIdentifier(),
				null, null, null, null, null);
		// Asserts that the test entity does not refer to the external entity.
		Assert.assertNull(testEntity.getExtEntity());
		// Tries to load all external entities for the test entity.
		ExternalEntityLoader.loadAllExternalEntities(testEntity, true);
		// Asserts that the external entity is the same previously persisted.
		Assert.assertEquals(externalEntity, testEntity.getExtEntity());
	}

	/**
	 * Tests the loadAllExternalEntities with external entities in a collection (ignoring lazy loading).
	 */
	@Test
	public void testLoadAllExternalEntitiesCollection() {
		// Persists some new external entities.
		final Collection<FakeEntity> externalEntities = new ArrayList<>(
				fakeEntityComponent.addOrUpdate(Arrays.asList(new FakeEntity[] {
						new FakeEntity(ENTITIES_NAMES[0]), new FakeEntity(ENTITIES_NAMES[1]),
						new FakeEntity(ENTITIES_NAMES[2]), new FakeEntity(ENTITIES_NAMES[3]),
						new FakeEntity(ENTITIES_NAMES[4]) })));
		// Creates a new list with the external entities ids.
		final ArrayList<Integer> extEntitiesIds = new ArrayList<>();
		// For each external entity.
		for (final FakeEntity curFakeEntity : externalEntities) {
			// Adds the id for the current entity to the list.
			extEntitiesIds.add(curFakeEntity.getIdentifier());
		}
		// Creates a test entity that refers to the external entities identifiers.
		final SomeOtherFakeEntity testEntity = new SomeOtherFakeEntity(SOME_ENTITY_IDS[1], null,
				extEntitiesIds.toArray(new Integer[0]), null, null, null, null);
		// Tries to load all external entities for the test entity.
		ExternalEntityLoader.loadAllExternalEntities(testEntity, true);
		// Asserts that the retrieved external entities are the same previously persisted.
		Assert.assertEquals(externalEntities, testEntity.getExtEntities());
	}

	/**
	 * Tests the loadAllExternalEntities with external entities in a collection and in regular field (ignoring
	 * lazy loading).
	 */
	@Test
	public void testLoadAllExternalEntitiesCollectionNonCollection() {
		// Persists some new external entities.
		final Collection<FakeEntity> externalEntities = new ArrayList<>(
				fakeEntityComponent.addOrUpdate(Arrays.asList(new FakeEntity[] {
						new FakeEntity(ENTITIES_NAMES[0]), new FakeEntity(ENTITIES_NAMES[1]),
						new FakeEntity(ENTITIES_NAMES[2]), new FakeEntity(ENTITIES_NAMES[3]) })));
		// Persists a new external entity.
		final FakeEntity externalEntity = fakeEntityComponent.addOrUpdate(new FakeEntity(ENTITIES_NAMES[4]));
		// Creates a new list with the external entities ids.
		final ArrayList<Integer> extEntitiesIds = new ArrayList<>();
		// For each external entity.
		for (final FakeEntity curFakeEntity : externalEntities) {
			// Adds the id for the current entity to the list.
			extEntitiesIds.add(curFakeEntity.getIdentifier());
		}
		// Creates a test entity that refers to the external entities identifiers.
		final SomeOtherFakeEntity testEntity = new SomeOtherFakeEntity(SOME_ENTITY_IDS[1], externalEntity.getIdentifier(),
				extEntitiesIds.toArray(new Integer[0]), null, null, null, null);
		// Tries to load all external entities for the test entity.
		ExternalEntityLoader.loadAllExternalEntities(testEntity, true);
		// Asserts that the retrieved external entities are the same previously persisted.
		Assert.assertEquals(externalEntities, testEntity.getExtEntities());
		// Asserts that the external entity is the same previously persisted.
		Assert.assertEquals(externalEntity, testEntity.getExtEntity());
	}

	/**
	 * Tests the loadAllExternalEntities with external entities in a collection and in regular field.
	 */
	@Test
	public void testLoadAllExternalEntitiesCollectionNonCollectionLazyLoading() {
		// Persists some new external entities.
		final Collection<FakeEntity> externalEntities = new ArrayList<>(
				fakeEntityComponent.addOrUpdate(Arrays.asList(new FakeEntity[] {
						new FakeEntity(ENTITIES_NAMES[0]), new FakeEntity(ENTITIES_NAMES[1]),
						new FakeEntity(ENTITIES_NAMES[2]), new FakeEntity(ENTITIES_NAMES[3]) })));
		// Persists a new external entity.
		final FakeEntity externalEntity = fakeEntityComponent.addOrUpdate(new FakeEntity(ENTITIES_NAMES[4]));
		// Creates a new list with the external entities ids.
		final ArrayList<Integer> extEntitiesIds = new ArrayList<>();
		// For each external entity.
		for (final FakeEntity curFakeEntity : externalEntities) {
			// Adds the id for the current entity to the list.
			extEntitiesIds.add(curFakeEntity.getIdentifier());
		}
		// Creates a test entity that refers to the external entities identifiers.
		final SomeOtherFakeEntity testEntity = new SomeOtherFakeEntity(SOME_ENTITY_IDS[1], externalEntity.getIdentifier(),
				extEntitiesIds.toArray(new Integer[0]), null, null, null, null);
		// Tries to load all external entities for the test entity.
		ExternalEntityLoader.loadAllExternalEntities(testEntity, false);
		// Asserts that the retrieved external entities set is an empty one.
		Assert.assertEquals(new ArrayList<>(), testEntity.getExtEntities());
		// Asserts that the external entity is the same previously persisted.
		Assert.assertEquals(externalEntity, testEntity.getExtEntity());
	}

	/**
	 * Tests the loadAllExternalEntities with external entity in a regular field (ignoring lazy loading), and
	 * with no parameters.
	 */
	@Test
	public void testLoadAllExternalEntitiesNonCollectionNoParams() {
		// Persists a new external entity.
		final FakeEntity externalEntity = fakeEntityComponent.addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Creates a test entity that refers to the external entity identifier.
		final SomeOtherFakeEntity testEntity = new SomeOtherFakeEntity(null, null, null,
				externalEntity.getIdentifier(), null, null, null);
		// Asserts that the test entity does not refer to the external entity.
		Assert.assertNull(testEntity.getExtEntity2());
		// Tries to load all external entities for the test entity.
		ExternalEntityLoader.loadAllExternalEntities(testEntity, true);
		// Asserts that the external entity is the same previously persisted.
		Assert.assertEquals(externalEntity, testEntity.getExtEntity2());
	}

	/**
	 * Tests the loadAllExternalEntities with external entities in a collection (ignoring lazy loading), and
	 * with no parameters.
	 */
	@Test
	public void testLoadAllExternalEntitiesCollectionNoParams() {
		// Persists some new external entities.
		final Collection<FakeEntity> externalEntities = new ArrayList<>(
				fakeEntityComponent.addOrUpdate(Arrays.asList(new FakeEntity[] {
						new FakeEntity(ENTITIES_NAMES[0]), new FakeEntity(ENTITIES_NAMES[1]),
						new FakeEntity(ENTITIES_NAMES[2]), new FakeEntity(ENTITIES_NAMES[3]),
						new FakeEntity(ENTITIES_NAMES[4]) })));
		// Creates a new list with the external entities ids.
		final ArrayList<Integer> extEntities2Ids = new ArrayList<>();
		// For each external entity.
		for (final FakeEntity curFakeEntity : externalEntities) {
			// Adds the id for the current entity to the list.
			extEntities2Ids.add(curFakeEntity.getIdentifier());
		}
		// Creates a test entity that refers to the external entities identifiers.
		final SomeOtherFakeEntity testEntity = new SomeOtherFakeEntity(SOME_ENTITY_IDS[1], null, null, null,
				extEntities2Ids.toArray(new Integer[0]), null, null);
		// Tries to load all external entities for the test entity.
		ExternalEntityLoader.loadAllExternalEntities(testEntity, true);
		// Asserts that the retrieved external entities are the same previously persisted.
		Assert.assertEquals(externalEntities, testEntity.getExtEntities2());
	}

	/**
	 * Some entity ids.
	 */
	private static final Integer[] SOME_ENTITY_IDS = { 13, 42, 54, 75, 23, 652, 35 };

	/**
	 * Tests the loadAllExternalEntities with external entity in a regular field (ignoring lazy loading), with
	 * no parameters, and with reverse mapping.
	 */
	@Test
	public void testLoadAllExternalEntitiesNonCollectionNoParamsReverse() {
		// Persists a new external entity.
		final FakeEntity externalEntity = fakeEntityComponent.addOrUpdate(new FakeEntity(ENTITIES_NAMES[0],
				SOME_ENTITY_IDS[1], null));
		// Creates a test entity with no references.
		final SomeOtherFakeEntity testEntity = new SomeOtherFakeEntity(SOME_ENTITY_IDS[1], null, null, null,
				null, null, null);
		// Asserts that the test entity does not refer to the external entity.
		Assert.assertNull(testEntity.getExtMappedEntity());
		// Tries to load all external entities for the test entity.
		ExternalEntityLoader.loadAllExternalEntities(testEntity, true);
		// Asserts that the external entity is the same previously persisted.
		Assert.assertEquals(externalEntity, testEntity.getExtMappedEntity());
	}

	/**
	 * Tests the loadAllExternalEntities with external entities in a collection (ignoring lazy loading), with
	 * no parameters, and with reverse mapping.
	 */
	@Test
	public void testLoadAllExternalEntitiesCollectionNoParamReverse() {
		// Persists some new external entities.
		final Collection<FakeEntity> externalEntities = new ArrayList<>(
				fakeEntityComponent.addOrUpdate(Arrays.asList(new FakeEntity[] {
						new FakeEntity(ENTITIES_NAMES[0], null, SOME_ENTITY_IDS[2]),
						new FakeEntity(ENTITIES_NAMES[1], null, SOME_ENTITY_IDS[2]),
						new FakeEntity(ENTITIES_NAMES[2], null, SOME_ENTITY_IDS[2]),
						new FakeEntity(ENTITIES_NAMES[3], null, SOME_ENTITY_IDS[2]),
						new FakeEntity(ENTITIES_NAMES[4], null, SOME_ENTITY_IDS[2]) })));
		// Creates a test entity with no references.
		final SomeOtherFakeEntity testEntity = new SomeOtherFakeEntity(SOME_ENTITY_IDS[2], null, null, null,
				null, null, null);
		// Tries to load all external entities for the test entity.
		ExternalEntityLoader.loadAllExternalEntities(testEntity, true);
		// Asserts that the retrieved external entities are the same previously persisted.
		Assert.assertEquals(externalEntities, testEntity.getExtMappedEntities());
	}

	/**
	 * Tests the updateReverseMappedEntities with invalid parameters (null entity).
	 */
	@Test(expected = EmptyParameterException.class)
	public void testUpdateReverseMappedEntitiesInvalidParams() {
		ExternalEntityLoader.updateReverseMappedEntities(null);
	}

	/**
	 * Tests the updateReverseMappedEntities with a single external entity (reverse mapped).
	 */
	@Test
	public void testUpdateReverseMappedEntitiesSingleEntity() {
		// Creates a new external entity.
		final FakeEntity externalEntity = new FakeEntity(ENTITIES_NAMES[0]);
		// Creates a test entity that refers to the created entity.
		final SomeOtherFakeEntity testEntity = new SomeOtherFakeEntity(null, null, null, null, null,
				externalEntity, null);
		// Sets the id of the test entity.
		testEntity.setIdentifier(SOME_ENTITY_IDS[0]);
		// Asserts that the external entity has no prior relationship.
		Assert.assertNull(externalEntity.getSomeOtherEntityId());
		// Tries to update the reverser mapped entities.
		ExternalEntityLoader.updateReverseMappedEntities(testEntity);
		// Asserts that the mapped fields values are now up to date.
		Assert.assertEquals(testEntity.getIdentifier(), externalEntity.getSomeOtherEntityId());
	}

	/**
	 * Tests the updateReverseMappedEntities with multiple external entities (reverse mapped).
	 */
	@Test
	public void testUpdateReverseMappedEntitiesMultiEntities() {
		// Creates some new external entities.
		final Collection<FakeEntity> externalEntities = new ArrayList<>(Arrays.asList(new FakeEntity[] {
				new FakeEntity(ENTITIES_NAMES[0]), new FakeEntity(ENTITIES_NAMES[1]),
				new FakeEntity(ENTITIES_NAMES[2]), new FakeEntity(ENTITIES_NAMES[3]),
				new FakeEntity(ENTITIES_NAMES[4]) }));
		// Creates a test entity that refers to the created entities.
		final SomeOtherFakeEntity testEntity = new SomeOtherFakeEntity(null, null, null, null, null, null,
				externalEntities);
		// Sets the id of the test entity.
		testEntity.setIdentifier(SOME_ENTITY_IDS[0]);
		// For each external entity.
		for (final FakeEntity currentFakeEntity : externalEntities) {
			// Asserts that the external entity has no prior relationship.
			Assert.assertNull(currentFakeEntity.getSomeOtherEntityId2());
		}
		// Tries to update the reverser mapped entities.
		ExternalEntityLoader.updateReverseMappedEntities(testEntity);
		// For each external entity.
		for (final FakeEntity currentFakeEntity : externalEntities) {
			// Asserts that the mapped fields values are now up to date.
			Assert.assertEquals(testEntity.getIdentifier(), currentFakeEntity.getSomeOtherEntityId2());
		}
	}
}
