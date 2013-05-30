package org.dejava.component.ejb.test.util;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Resources to be injected via CDI.
 */
public class EJBResources {

	/**
	 * Entity manager.
	 */
	@Produces
	@PersistenceContext(unitName = "TestDS")
	private EntityManager entityManager;

	/**
	 * Gets a logger instance.
	 * 
	 * @param injectionPoint
	 *            The injection point (CDI).
	 * @return A logger instance.
	 */
	@Produces
	public Logger produceLog(final InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
}
