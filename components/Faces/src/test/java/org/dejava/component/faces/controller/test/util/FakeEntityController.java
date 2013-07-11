package org.dejava.component.faces.controller.test.util;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.faces.controller.AbstractGenericController;
import org.dejava.component.faces.message.test.constant.ErrorKeys;
import org.dejava.component.faces.message.test.constant.FatalKeys;
import org.dejava.component.faces.message.test.constant.InfoKeys;
import org.dejava.component.faces.message.test.constant.WarnKeys;
import org.dejava.component.i18n.message.handler.ApplicationMessageHandler;

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
