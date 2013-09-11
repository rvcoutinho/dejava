package org.dejava.service.accesscontrol.controller;

import javax.inject.Inject;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.faces.controller.AbstractGenericController;
import org.dejava.service.accesscontrol.component.UserComponent;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * TODO
 */
@AccessControlCtx
public class UserController extends AbstractGenericController<User, Integer> {

	/**
	 * The person EJB service.
	 */
	@Inject
	@AccessControlCtx
	private UserComponent userComponent;

	/**
	 * @see org.dejava.component.faces.controller.AbstractGenericController#getBusinessComponent()
	 */
	@Override
	protected AbstractGenericComponent<User, Integer> getBusinessComponent() {
		return userComponent;
	}
}
