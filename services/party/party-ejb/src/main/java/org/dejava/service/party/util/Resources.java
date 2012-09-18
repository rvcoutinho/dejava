package org.dejava.service.party.util;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * TODO
 */
public class Resources {

	/**
	 * TODO
	 */
	@Produces
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * TODO
	 * 
	 * @param injectionPoint
	 *            TODO
	 * @return TODO
	 */
	@Produces
	public Logger produceLog(final InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
}
