package org.dejava.component.jsf.test.util;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.jsf.controller.AbstractGenericController;

/**
 * Fake entity JSF Controller.
 */
@ManagedBean
public class FakeEntityController extends AbstractGenericController<FakeEntity, Integer> {

	/**
	 * Fake entity EJB component.
	 */
	@Inject
	@EJB
	private FakeEntityComponent fakeEntityComponent;

	/**
	 * @see org.dejava.component.jsf.controller.AbstractGenericController#getBusinessComponent()
	 */
	@Override
	protected AbstractGenericComponent<FakeEntity, Integer> getBusinessComponent() {
		return fakeEntityComponent;
	}

}
