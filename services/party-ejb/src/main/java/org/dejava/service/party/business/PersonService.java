package org.dejava.service.party.business;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.service.party.dao.PersonDAO;
import org.dejava.service.party.model.Person;

/**
 * TODO
 */
@Remote
@Stateless
public class PersonService extends AbstractGenericService<Person, Integer> {

	/**
	 * The person DAO.
	 */
	@Inject
	private PersonDAO personDAO;

	/**
	 * @see org.dejava.component.javaee.service.AbstractGenericService#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Person, Integer> getEntityDAO() {
		return personDAO;
	}

}
