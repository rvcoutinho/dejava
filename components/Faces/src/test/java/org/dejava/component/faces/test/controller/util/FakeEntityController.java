package org.dejava.component.faces.test.controller.util;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.faces.controller.AbstractGenericController;

/**
 * Fake entity JSF Controller.
 */
@Faces
@ManagedBean
public class FakeEntityController extends AbstractGenericController<FakeEntity, Integer> {

	/**
	 * Fake entity Faces component.
	 */
	@Inject
	@Faces
	private FakeEntityComponent fakeEntityComponent;

	/**
	 * @see org.dejava.component.faces.controller.AbstractGenericController#getBusinessComponent()
	 */
	@Override
	protected AbstractGenericComponent<FakeEntity, Integer> getBusinessComponent() {
		return fakeEntityComponent;
	}

}
