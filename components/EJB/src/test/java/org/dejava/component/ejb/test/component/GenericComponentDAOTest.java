package org.dejava.component.ejb.test.component;

import java.util.Collection;

import javax.ejb.EJBException;
import javax.inject.Inject;

import org.dejava.component.ejb.component.GenericComponent;
import org.dejava.component.ejb.test.util.FakeEntity;
import org.dejava.component.ejb.test.util.FakeEntityComponent;
import org.dejava.component.exception.localized.unchecked.EmptyParameterException;
import org.dejava.component.exception.localized.unchecked.InvalidParameterException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for the generic EJB component and DAO.
 */
@RunWith(Arquillian.class)
public class GenericComponentDAOTest extends AbstractGenericComponentDAOTest {

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
	 * @see org.dejava.component.ejb.test.component.AbstractGenericComponentDAOTest#getComponent()
	 */
	@Override
	protected GenericComponent<FakeEntity, Integer> getComponent() {
		return fakeEntityComponent;
	}

	/**
	 * Tests a successful approach for the method getByAttribute (collection).
	 */
	@Test
	public void testGetCollectionByAttributeSuccess() {
		// Tries to add fake entities.
		final Collection<FakeEntity> fakeEntities = fakeEntityComponent.addOrUpdate(getTestEntities());
		// Gets the first entity.
		final FakeEntity firstEntity = fakeEntities.iterator().next();
		// Tries to get an entity by the name.
		final Collection<FakeEntity> sameFirstEntity = fakeEntityComponent.getByAttribute("name",
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
			fakeEntityComponent.getByAttribute(null, null, null, null);
		}
		// Expects an EJB exception.
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
		final FakeEntity fakeEntity = fakeEntityComponent.addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to get an entity by the name.
		final FakeEntity sameFakeEntity = fakeEntityComponent.getByAttribute("name", fakeEntity.getName());
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
			fakeEntityComponent.getByAttribute(null, null);
		}
		// Expects an EJB exception.
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
		final FakeEntity fakeEntity = fakeEntityComponent.addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to add a similar fake entity.
		fakeEntityComponent.addOrUpdate(new FakeEntity(ENTITIES_NAMES[0]));
		// Tries to get an entity by the name.
		try {
			fakeEntityComponent.getByAttribute("name", fakeEntity.getName());
		}
		// Expects an EJB exception.
		catch (final EJBException exception) {
			// Throws the root of the exception.
			throw exception.getCause();
		}
	}

}
