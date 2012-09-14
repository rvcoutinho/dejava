package org.dejava.component.ejb.test.service;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.dejava.component.ejb.test.auxiliary.FakeEntityService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * TODO
 */
@RunWith(Arquillian.class)
public class EJBServiceTest {

	/**
	 * Defines what should be done during deployment (before tests).
	 * 
	 * @return The configuration for the archive.
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackages(true, "org.dejava.component.ejb")
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	/**
	 * Fake entity service.
	 */
	@Inject
	protected FakeEntityService fakeEntityService;

	/**
	 * Logger.
	 */
	@Inject
	protected Logger log;

	/**
	 * TODO
	 */
	@Test
	public void testRegister() {

	}

}
