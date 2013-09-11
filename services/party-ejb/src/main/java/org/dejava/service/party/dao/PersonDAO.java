package org.dejava.service.party.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.service.party.model.Person;
import org.dejava.service.party.util.PartyCtx;
import org.dejava.service.place.component.PlaceComponent;
import org.dejava.service.place.util.PlaceCtx;

/**
 * Person DAO.
 */
@PartyCtx
public class PersonDAO extends AbstractGenericDAO<Person, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@PartyCtx
	private EntityManager entityManager;

	/**
	 * Place EJB component.
	 */
	@Inject
	@PlaceCtx
	private PlaceComponent placeComponent;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
