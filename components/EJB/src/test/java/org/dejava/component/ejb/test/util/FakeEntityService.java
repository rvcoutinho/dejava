package org.dejava.component.ejb.test.util;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.component.ejb.service.AbstractGenericService;
import org.dejava.component.ejb.test.util.FakeEntity;

/**
 * Fake entity stateless service.
 */
@Stateless
@EJB
public class FakeEntityService extends AbstractGenericService<FakeEntity, Integer> {

	/**
	 * The fake entity DAO.
	 */
	@Inject
	@EJB
	private FakeEntityDAO entityDAO;

	/**
	 * @see org.dejava.component.ejb.service.AbstractGenericService#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<FakeEntity, Integer> getEntityDAO() {
		return entityDAO;
	}

}
