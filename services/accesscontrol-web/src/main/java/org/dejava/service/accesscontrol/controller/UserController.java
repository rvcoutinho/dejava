package org.dejava.service.accesscontrol.controller;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.Path;

import org.dejava.component.javaee.controller.AbstractGenericController;
import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.service.accesscontrol.business.UserService;
import org.dejava.service.accesscontrol.model.User;

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
	private UserService userService;

	/**
	 * @see org.dejava.component.javaee.controller.AbstractGenericController#getBusinessService()
	 */
	@Override
	protected AbstractGenericService<User, Integer> getBusinessService() {
		return userService;
	}
}
