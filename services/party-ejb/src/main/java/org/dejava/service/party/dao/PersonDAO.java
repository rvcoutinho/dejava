package org.dejava.service.party.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.party.model.Person;
import org.dejava.service.party.util.Party;

/**
 * TODO
 */
@Party
public class PersonDAO extends AbstractGenericDAO<Person, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@Party
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
