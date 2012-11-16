package org.dejava.component.javaee.test.util;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.javaee.dao.AbstractGenericDAO;
import org.dejava.component.javaee.service.AbstractGenericService;

/**
 * Fake entity stateless service.
 */
@Stateless
@JavaEE
public class FakeEntityService extends AbstractGenericService<FakeEntity, Integer> {

	/**
	 * The fake entity DAO.
	 */
	@Inject
	@JavaEE
	private FakeEntityDAO entityDAO;

	/**
	 * @see org.dejava.component.javaee.service.AbstractGenericService#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<FakeEntity, Integer> getEntityDAO() {
		return entityDAO;
	}

}
