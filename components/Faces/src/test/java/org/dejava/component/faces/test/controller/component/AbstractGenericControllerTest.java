package org.dejava.component.faces.test.controller.component;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJBException;

import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.faces.controller.GenericController;
import org.dejava.component.faces.test.controller.util.FakeEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Abstract tests for the generic controller.
 */
public abstract class AbstractGenericControllerTest {

	/**
	 * Gets the controller to test.
	 * 
	 * @return The controller to test.
	 */
	protected abstract GenericController<FakeEntity, Integer> getController();

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
		final Collection<FakeEntity> allEntities = getController().getAll(null, null);
		// Assert that there are no entities persisted.
		Assert.assertTrue(allEntities.isEmpty());
	}

	/**
	 * Removes all test entities.
	 */
	@After
	public void removeTestEntities() {
		// Gets all entities.
		final Collection<FakeEntity> allEntities = getController().getAll(null, null);
		// Removes all persisted entities.
		getController().remove(allEntities);
	}

	/**
	 * Tests a successful approach for the method addOrUpdate (entity).
	 */
	@Test
	public void testAddOrUpdateSuccess() {
		// Tries to add a fake entity.
		final FakeEntity fakeEntity = getController().addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to get the same entity by its new id.
		final FakeEntity sameFakeEntity = getController().getById(fakeEntity.getIdentifier());
		// Assert that the two entities are the sOame.
		Assert.assertEquals(fakeEntity, sameFakeEntity);
	}

	/**
	 * Tests the method addOrUpdate (entity) with null entity.
	 * 
	 * @throws Throwable
	 *             Expected exception.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testAddOrUpdateNullEntity() throws Throwable {
		// Tries to add a fake entity.
		try {
			getController().addOrUpdate((FakeEntity) null);
		}
		// Expects an Faces exception.
		catch (final EJBException exception) {
			// Throws the root of the exception.
			throw exception.getCause();
		}
	}

	/**
	 * Tests a successful approach for the method addOrUpdate (collection).
	 */
	@Test
	public void testAddOrUpdateCollectionSuccess() {
		// Tries to add fake entities (there are no persistent entities yet).
		final Collection<FakeEntity> entities = getController().addOrUpdate(getTestEntities());
		// Tries to get all persisted entities.
		final Collection<FakeEntity> sameFakeEntities = getController().getAll(null, null);
		// Assert that the two collections have the same entities (there were no persistent entities before).
		Assert.assertEquals(entities, sameFakeEntities);
	}

	/**
	 * Tests the method addOrUpdate (collection) with null entity.
	 */
	@Test
	public void testAddOrUpdateCollectionNullEntity() {
		// Tries to add fake entities.
		getController().addOrUpdate((Collection<FakeEntity>) null);
		// Assert that there are no entities persisted.
		assertNoEntitiesPersisted();
	}

	/**
	 * Tests a successful approach for the method remove (entity).
	 */
	@Test
	public void testRemoveSuccess() {
		// Tries to add a fake entity.
		final FakeEntity fakeEntity = getController().addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to remove the entity.
		getController().remove(fakeEntity);
		// Gets all entities.
		final Collection<FakeEntity> allEntities = getController().getAll(null, null);
		// Assert that there are no entities persisted.
		Assert.assertTrue(allEntities.isEmpty());
	}

	/**
	 * Tests the method remove with null entity (entity).
	 * 
	 * @throws Throwable
	 *             Expected exception.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testRemoveNullEntity() throws Throwable {
		// Tries to remove a null entity.
		try {
			getController().remove((FakeEntity) null);
		}
		// Expects an Faces exception.
		catch (final EJBException exception) {
			// Throws the root of the exception.
			throw exception.getCause();
		}
	}

	/**
	 * Tests a successful approach for the method remove (collection).
	 */
	@Test
	public void testRemoveCollectionSuccess() {
		// Tries to add fake entities.
		final Collection<FakeEntity> fakeEntities = getController().addOrUpdate(getTestEntities());
		// Tries to remove the entities.
		getController().remove(fakeEntities);
		// Gets all entities.
		final Collection<FakeEntity> allEntities = getController().getAll(null, null);
		// Assert that there are no entities persisted.
		Assert.assertTrue(allEntities.isEmpty());
	}

	/**
	 * Tests the method remove (collection) with null entity.
	 */
	@Test
	public void testRemoveCollectionNullEntity() {
		// Tries to remove fake entities (null).
		getController().remove((Collection<FakeEntity>) null);
		// Assert that there are no entities persisted.
		assertNoEntitiesPersisted();
	}

	/**
	 * Tests a successful approach for the method getById.
	 */
	@Test
	public void testGetByIdSuccess() {
		// Tries to add a fake entity.
		final FakeEntity fakeEntity = getController().addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to get the same entity by its new id.
		final FakeEntity sameFakeEntity = getController().getById(fakeEntity.getIdentifier());
		// Assert that the two entities are the same.
		Assert.assertEquals(fakeEntity, sameFakeEntity);
	}

	/**
	 * Tests the method getById with null id.
	 * 
	 * @throws Throwable
	 *             Expected exception.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testGetByIdNullId() throws Throwable {
		// Tries to get a fake entity with null id.
		try {
			getController().getById(null);
		}
		// Expects an Faces exception.
		catch (final EJBException exception) {
			// Throws the root of the exception.
			throw exception.getCause();
		}
	}

	/**
	 * Tests a successful approach for the method getAll.
	 */
	@Test
	public void testGetAllSuccess() {
		// Tries to add fake entities (there are no persistent entities yet).
		final Collection<FakeEntity> entities = getController().addOrUpdate(getTestEntities());
		// Tries to get all persisted entities.
		final Collection<FakeEntity> sameFakeEntities = getController().getAll(null, null);
		// Assert that the two collections have the same entities (there were no persistent entities before).
		Assert.assertEquals(entities, sameFakeEntities);
	}

}
