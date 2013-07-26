package org.dejava.component.faces.test.controller.component;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJBException;

import org.dejava.component.ejb.component.GenericComponent;
import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.dejava.component.faces.test.controller.util.FakeEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Abstract tests for the generic component.
 */
public abstract class AbstractGenericComponentTest {

	/**
	 * Gets the component to test.
	 * 
	 * @return The component to test.
	 */
	protected abstract GenericComponent<FakeEntity, Integer> getComponent();

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
		final Collection<FakeEntity> allEntities = getComponent().getAll(null, null);
		// Assert that there are no entities persisted.
		Assert.assertTrue(allEntities.isEmpty());
	}

	/**
	 * Removes all test entities.
	 */
	@After
	public void removeTestEntities() {
		// Gets all entities.
		final Collection<FakeEntity> allEntities = getComponent().getAll(null, null);
		// Removes all persisted entities.
		getComponent().remove(allEntities);
	}

	/**
	 * Tests a successful approach for the method addOrUpdate (entity).
	 */
	@Test
	public void testAddOrUpdateSuccess() {
		// Tries to add a fake entity.
		final FakeEntity fakeEntity = getComponent().addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to get the same entity by its new id.
		final FakeEntity sameFakeEntity = getComponent().getById(fakeEntity.getIdentifier());
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
			getComponent().addOrUpdate((FakeEntity) null);
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
		final Collection<FakeEntity> entities = getComponent().addOrUpdate(getTestEntities());
		// Tries to get all persisted entities.
		final Collection<FakeEntity> sameFakeEntities = getComponent().getAll(null, null);
		// Assert that the two collections have the same entities (there were no persistent entities before).
		Assert.assertEquals(entities, sameFakeEntities);
	}

	/**
	 * Tests the method addOrUpdate (collection) with null entity.
	 */
	@Test
	public void testAddOrUpdateCollectionNullEntity() {
		// Tries to add fake entities.
		getComponent().addOrUpdate((Collection<FakeEntity>) null);
		// Assert that there are no entities persisted.
		assertNoEntitiesPersisted();
	}

	/**
	 * Tests a successful approach for the method remove (entity).
	 */
	@Test
	public void testRemoveSuccess() {
		// Tries to add a fake entity.
		final FakeEntity fakeEntity = getComponent().addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to remove the entity.
		getComponent().remove(fakeEntity);
		// Gets all entities.
		final Collection<FakeEntity> allEntities = getComponent().getAll(null, null);
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
			getComponent().remove((FakeEntity) null);
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
		final Collection<FakeEntity> fakeEntities = getComponent().addOrUpdate(getTestEntities());
		// Tries to remove the entities.
		getComponent().remove(fakeEntities);
		// Gets all entities.
		final Collection<FakeEntity> allEntities = getComponent().getAll(null, null);
		// Assert that there are no entities persisted.
		Assert.assertTrue(allEntities.isEmpty());
	}

	/**
	 * Tests the method remove (collection) with null entity.
	 */
	@Test
	public void testRemoveCollectionNullEntity() {
		// Tries to remove fake entities (null).
		getComponent().remove((Collection<FakeEntity>) null);
		// Assert that there are no entities persisted.
		assertNoEntitiesPersisted();
	}

	/**
	 * Tests a successful approach for the method getById.
	 */
	@Test
	public void testGetByIdSuccess() {
		// Tries to add a fake entity.
		final FakeEntity fakeEntity = getComponent().addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to get the same entity by its new id.
		final FakeEntity sameFakeEntity = getComponent().getById(fakeEntity.getIdentifier());
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
			getComponent().getById(null);
		}
		// Expects an Faces exception.
		catch (final EJBException exception) {
			// Throws the root of the exception.
			throw exception.getCause();
		}
	}

	/**
	 * Tests a successful approach for the method getByAttribute (collection).
	 */
	@Test
	public void testGetCollectionByAttributeSuccess() {
		// Tries to add fake entities.
		final Collection<FakeEntity> fakeEntities = getComponent().addOrUpdate(getTestEntities());
		// Gets the first entity.
		final FakeEntity firstEntity = fakeEntities.iterator().next();
		// Tries to get an entity by the name.
		final Collection<FakeEntity> sameFirstEntity = getComponent().getByAttribute("name",
				firstEntity.getName(), null, null);
		// Assert that only one element is retrieved.
		Assert.assertEquals(1, sameFirstEntity.size());
		// Assert that the two entities are the same.
		Assert.assertEquals(firstEntity, sameFirstEntity.iterator().next());
	}

	/**
	 * Tests the method getByAttribute (collection) with null attribute name.
	 * 
	 * @throws Throwable
	 *             Expected exception.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testGetCollectionByAttributeNullAttributeName() throws Throwable {
		// Tries to get a fake entity with null attribute name.
		try {
			getComponent().getByAttribute(null, null, null, null);
		}
		// Expects an Faces exception.
		catch (final EJBException exception) {
			// Throws the root of the exception.
			throw exception.getCause();
		}
	}

	/**
	 * Tests a successful approach for the method getByAttribute (entity).
	 */
	@Test
	public void testGetEntityByAttributeSuccess() {
		// Tries to add a fake entity.
		final FakeEntity fakeEntity = getComponent().addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to get an entity by the name.
		final FakeEntity sameFakeEntity = getComponent().getByAttribute("name", fakeEntity.getName());
		// Assert that the two entities are the same.
		Assert.assertEquals(fakeEntity, sameFakeEntity);
	}

	/**
	 * Tests the method getByAttribute (entity) with null attribute name.
	 * 
	 * @throws Throwable
	 *             Expected exception.
	 */
	@Test(expected = EmptyParameterException.class)
	public void testGetEntityByAttributeNullAttributeName() throws Throwable {
		// Tries to get a fake entity with null attribute name.
		try {
			getComponent().getByAttribute(null, null);
		}
		// Expects an Faces exception.
		catch (final EJBException exception) {
			// Throws the root of the exception.
			throw exception.getCause();
		}
	}

	/**
	 * Tests the method getByAttribute (entity) with a attribute being duplicated in two entities.
	 * 
	 * @throws Throwable
	 *             Expected exception.
	 */
	@Test(expected = InvalidParameterException.class)
	public void testGetEntityByAttributeDuplicateAttribute() throws Throwable {
		// Tries to add a fake entity.
		final FakeEntity fakeEntity = getComponent().addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to add a similar fake entity.
		getComponent().addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to get an entity by the name.
		try {
			getComponent().getByAttribute("name", fakeEntity.getName());
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
		final Collection<FakeEntity> entities = getComponent().addOrUpdate(getTestEntities());
		// Tries to get all persisted entities.
		final Collection<FakeEntity> sameFakeEntities = getComponent().getAll(null, null);
		// Assert that the two collections have the same entities (there were no persistent entities before).
		Assert.assertEquals(entities, sameFakeEntities);
	}

}
