package org.dejava.component.javaee.test.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJBException;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.javaee.service.GenericService;
import org.dejava.component.javaee.test.util.FakeEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Abstract tests for the generic service.
 */
public abstract class AbstractGenericServiceTest {

	/**
	 * Gets the service to test.
	 * 
	 * @return The service to test.
	 */
	protected abstract GenericService<FakeEntity, Integer> getService();

	/**
	 * Default names for fake entities.
	 */
	private static final String[] ENTITIES_NAMES = { "Entity1", "Entity2", "Entity3", "Entity4", "Entity5" };

	/**
	 * Gets new instances for the test entities.
	 * 
	 * @return New instances for the test entities.
	 */
	private Collection<FakeEntity> getTestEntities() {
		// Creates a new list of entities.
		final Collection<FakeEntity> entities = new ArrayList<>();
		// For each default entity name.
		for (final String currentName : ENTITIES_NAMES) {
			// Creates an entity for the current name.
			entities.add(new FakeEntity(currentName));
		}
		// Returns the list.
		return entities;
	}

	/**
	 * Assert that there are no entities persisted.
	 */
	@Before
	public void assertNoEntitiesPersisted() {
		// Gets all entities.
		final Collection<FakeEntity> allEntities = getService().getAllEntities(null, null);
		// Assert that there are no entities persisted.
		Assert.assertTrue(allEntities.isEmpty());
	}

	/**
	 * Removes all test entities.
	 */
	@After
	public void removeTestEntities() {
		// Gets all entities.
		final Collection<FakeEntity> allEntities = getService().getAllEntities(null, null);
		// Removes all persisted entities.
		getService().removeAll(allEntities);
	}

	/**
	 * Tests a successful approach for the method addOrUpdate.
	 */
	@Test
	public void testAddOrUpdateSuccess() {
		// Tries to add a fake entity.
		final FakeEntity fakeEntity = getService().addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to get the same entity by its new id.
		final FakeEntity sameFakeEntity = getService().getEntityById(fakeEntity.getIdentifier());
		// Assert that the two entities are the sOame.
		Assert.assertEquals(fakeEntity, sameFakeEntity);
	}

	/**
	 * Tests the method addOrUpdate with null entity.
	 * 
	 * @throws Throwable
	 *             Expected exception.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testAddOrUpdateNullEntity() throws Throwable {
		// Tries to add a fake entity.
		try {
			getService().addOrUpdate(null);
		}
		// Expects an EJB exception.
		catch (final EJBException exception) {
			// Throws the root of the exception.
			throw exception.getCause();
		}
	}

	/**
	 * Tests a successful approach for the method addOrUpdateAll.
	 */
	@Test
	public void testAddOrUpdateAllSuccess() {
		// Tries to add fake entities (there are no persistent entities yet).
		final Collection<FakeEntity> entities = getService().addOrUpdateAll(getTestEntities());
		// Tries to get all persisted entities.
		final Collection<FakeEntity> sameFakeEntities = getService().getAllEntities(null, null);
		// Assert that the two collections have the same entities (there were no persistent entities before).
		Assert.assertEquals(entities, sameFakeEntities);
	}

	/**
	 * Tests the method addOrUpdateAll with null entity.
	 */
	@Test
	public void testAddOrUpdateAllNullEntity() {
		// Tries to add fake entities.
		getService().addOrUpdateAll(null);
		// Assert that there are no entities persisted.
		assertNoEntitiesPersisted();
	}

	/**
	 * Tests a successful approach for the method remove.
	 */
	@Test
	public void testRemoveSuccess() {
		// Tries to add a fake entity.
		final FakeEntity fakeEntity = getService().addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to remove the entity.
		getService().remove(fakeEntity);
		// Gets all entities.
		final Collection<FakeEntity> allEntities = getService().getAllEntities(null, null);
		// Assert that there are no entities persisted.
		Assert.assertTrue(allEntities.isEmpty());
	}

	/**
	 * Tests the method remove with null entity.
	 * 
	 * @throws Throwable
	 *             Expected exception.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testRemoveNullEntity() throws Throwable {
		// Tries to remove a null entity.
		try {
			getService().remove(null);
		}
		// Expects an EJB exception.
		catch (final EJBException exception) {
			// Throws the root of the exception.
			throw exception.getCause();
		}
	}

	/**
	 * Tests a successful approach for the method removeAll.
	 */
	@Test
	public void testRemoveAllSuccess() {
		// Tries to add fake entities.
		final Collection<FakeEntity> fakeEntities = getService().addOrUpdateAll(getTestEntities());
		// Tries to remove the entities.
		getService().removeAll(fakeEntities);
		// Gets all entities.
		final Collection<FakeEntity> allEntities = getService().getAllEntities(null, null);
		// Assert that there are no entities persisted.
		Assert.assertTrue(allEntities.isEmpty());
	}

	/**
	 * Tests the method removelAll with null entity.
	 */
	@Test
	public void testRemoveAllNullEntity() {
		// Tries to remove fake entities (null).
		getService().removeAll(null);
		// Assert that there are no entities persisted.
		assertNoEntitiesPersisted();
	}

	/**
	 * Tests a successful approach for the method getEntityById.
	 */
	@Test
	public void testGetEntityByIdSuccess() {
		// Tries to add a fake entity.
		final FakeEntity fakeEntity = getService().addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to get the same entity by its new id.
		final FakeEntity sameFakeEntity = getService().getEntityById(fakeEntity.getIdentifier());
		// Assert that the two entities are the same.
		Assert.assertEquals(fakeEntity, sameFakeEntity);
	}

	/**
	 * Tests the method getEntityById with null id.
	 * 
	 * @throws Throwable
	 *             Expected exception.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testGetEntityByIdNullId() throws Throwable {
		// Tries to get a fake entity with null id.
		try {
			getService().getEntityById(null);
		}
		// Expects an EJB exception.
		catch (final EJBException exception) {
			// Throws the root of the exception.
			throw exception.getCause();
		}
	}

	/**
	 * Tests a successful approach for the method getEntitiesByAttribute.
	 */
	@Test
	public void testGetEntitiesByAttributeSuccess() {
		// Tries to add fake entities.
		final Collection<FakeEntity> fakeEntities = getService().addOrUpdateAll(getTestEntities());
		// Gets the first entity.
		final FakeEntity firstEntity = fakeEntities.iterator().next();
		// Tries to get an entity by the name.
		final Collection<FakeEntity> sameFirstEntity = getService().getEntitiesByAttribute("name",
				firstEntity.getName(), null, null);
		// Assert that only one element is retrieved.
		Assert.assertEquals(1, sameFirstEntity.size());
		// Assert that the two entities are the same.
		Assert.assertEquals(firstEntity, sameFirstEntity.iterator().next());
	}

	/**
	 * Tests the method getEntitiesByAttribute with null attribute name.
	 * 
	 * @throws Throwable
	 *             Expected exception.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testGetEntitiesByAttributeNullAttributeName() throws Throwable {
		// Tries to get a fake entity with null attribute name.
		try {
			getService().getEntitiesByAttribute(null, null, null, null);
		}
		// Expects an EJB exception.
		catch (final EJBException exception) {
			// Throws the root of the exception.
			throw exception.getCause();
		}
	}

	/**
	 * Tests a successful approach for the method getEntityByAttribute.
	 */
	@Test
	public void testGetEntityByAttributeSuccess() {
		// Tries to add a fake entity.
		final FakeEntity fakeEntity = getService().addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to get an entity by the name.
		final FakeEntity sameFakeEntity = getService().getEntityByAttribute("name", fakeEntity.getName());
		// Assert that the two entities are the same.
		Assert.assertEquals(fakeEntity, sameFakeEntity);
	}

	/**
	 * Tests the method getEntityByAttribute with null attribute name.
	 * 
	 * @throws Throwable
	 *             Expected exception.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testGetEntityByAttributeNullAttributeName() throws Throwable {
		// Tries to get a fake entity with null attribute name.
		try {
			getService().getEntityByAttribute(null, null);
		}
		// Expects an EJB exception.
		catch (final EJBException exception) {
			// Throws the root of the exception.
			throw exception.getCause();
		}
	}

	/**
	 * Tests the method getEntityByAttribute with a attribute being duplicated in two entities.
	 * 
	 * @throws Throwable
	 *             Expected exception.
	 */
	@Test(expected = InvalidParameterException.class)
	public void testGetEntityByAttributeDuplicateAttribute() throws Throwable {
		// Tries to add a fake entity.
		final FakeEntity fakeEntity = getService().addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to add a similar fake entity.
		final FakeEntity similarFakeEntity = getService().addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to get an entity by the name.
		try {
			getService().getEntityByAttribute("name", fakeEntity.getName());
		}
		// Expects an EJB exception.
		catch (final EJBException exception) {
			// Throws the root of the exception.
			throw exception.getCause();
		}
	}

	/**
	 * Tests a successful approach for the method getAllEntities.
	 */
	@Test
	public void testGetAllEntitiesSuccess() {
		// Tries to add fake entities (there are no persistent entities yet).
		final Collection<FakeEntity> entities = getService().addOrUpdateAll(getTestEntities());
		// Tries to get all persisted entities.
		final Collection<FakeEntity> sameFakeEntities = getService().getAllEntities(null, null);
		// Assert that the two collections have the same entities (there were no persistent entities before).
		Assert.assertEquals(entities, sameFakeEntities);
	}

}
