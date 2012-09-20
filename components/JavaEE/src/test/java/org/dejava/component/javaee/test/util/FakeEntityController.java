package org.dejava.component.javaee.test.util;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.dejava.component.javaee.controller.AbstractGenericController;
import org.dejava.component.javaee.service.AbstractGenericService;

/**
 * Fake entity JSF Controller.
 */
@Model
public class FakeEntityController extends AbstractGenericController<FakeEntity> {

	/**
	 * Fake entity EJB service.
	 */
	@Inject
	private FakeEntityService fakeEntityService;

	/**
	 * @see org.dejava.component.javaee.controller.AbstractGenericController#getBusinessService()
	 */
	@Override
	protected AbstractGenericService<FakeEntity> getBusinessService() {
		return fakeEntityService;
	}

}
