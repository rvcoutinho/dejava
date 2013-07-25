package org.dejava.service.accesscontrol.controller;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.Path;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.faces.controller.AbstractGenericController;
import org.dejava.service.accesscontrol.component.UserComponent;
import org.dejava.service.accesscontrol.model.User;
import org.dejava.service.accesscontrol.util.AccessControlCtx;

/**
 * TODO
 */
@Model
@Path("/user")
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
