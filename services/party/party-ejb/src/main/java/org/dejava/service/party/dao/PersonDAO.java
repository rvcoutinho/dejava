package org.dejava.service.party.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.service.party.model.Person;

/**
 * TODO
 */
public class PersonDAO extends AbstractGenericDAO<Person> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	private EntityManager entityManager;

	/**
	 * @see org.dejava.service.party.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
