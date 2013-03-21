package org.dejava.component.jsf.test.util;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.dejava.component.ejb.service.AbstractGenericService;
import org.dejava.component.jsf.controller.AbstractGenericController;

/**
 * Fake entity JSF Controller.
 */
@ManagedBean
public class FakeEntityController extends AbstractGenericController<FakeEntity, Integer> {

	/**
	 * Fake entity EJB service.
	 */
	@Inject
	@EJB
	private FakeEntityService fakeEntityService;

	/**
	 * @see org.dejava.component.jsf.controller.AbstractGenericController#getBusinessService()
	 */
	@Override
	protected AbstractGenericService<FakeEntity, Integer> getBusinessService() {
		return fakeEntityService;
	}

}
