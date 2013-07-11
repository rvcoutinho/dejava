package org.dejava.component.faces.controller.test.util;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.ejb.dao.AbstractGenericDAO;

/**
 * Fake entity stateless component.
 */
@Faces
@Stateless(name = "Component/Test/FakeEntityComponent")
public class FakeEntityComponent extends AbstractGenericComponent<FakeEntity, Integer> {

	/**
	 * The fake entity DAO.
	 */
	@Faces
	@Inject
	private FakeEntityDAO entityDAO;

	/**
	 * @see org.dejava.component.ejb.component.AbstractGenericComponent#getEntityDAO()
	 */
	@Override
	public AbstractGenericDAO<FakeEntity, Integer> getEntityDAO() {
		return entityDAO;
	}

}
