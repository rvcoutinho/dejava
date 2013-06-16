package org.dejava.service.party.component;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.party.dao.PersonDAO;
import org.dejava.service.party.model.Person;
import org.dejava.service.party.util.PartyCtx;

/**
 * TODO
 */
@PartyCtx
@Stateless(name = "Component/Party/Person")
public class PersonComponent extends AbstractGenericComponent<Person, Integer> {

	/**
	 * The person DAO.
	 */
	@Inject
	@PartyCtx
	private PersonDAO personDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<Person, Integer> getEntityDAO() {
		return personDAO;
	}

}
