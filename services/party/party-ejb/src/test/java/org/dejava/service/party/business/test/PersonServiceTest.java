package org.dejava.service.party.business.test;

import java.lang.reflect.Member;
import java.util.logging.Logger;

import javax.annotation.Resources;
import javax.inject.Inject;

import org.dejava.service.party.business.PersonService;
import org.dejava.service.party.dao.AbstractGenericDAO;
import org.dejava.service.party.dao.PersonDAO;
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
public class PersonServiceTest {

	/**
	 * TODO
	 * 
	 * @return TODO
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackages(true, "org.dejava.service.party")
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Deploy our test datasource
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	/**
	 * TODO
	 */
	@Inject
	PersonService personService;

	/**
	 * TODO
	 */
	@Inject
	Logger log;

	/**
	 * TODO
	 */
	@Test
	public void testRegister() {

	}

}
