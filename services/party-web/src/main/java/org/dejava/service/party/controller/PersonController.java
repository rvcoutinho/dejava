package org.dejava.service.party.controller;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.Path;

import org.dejava.component.javaee.controller.AbstractGenericController;
import org.dejava.component.javaee.service.AbstractGenericService;
import org.dejava.service.party.business.PersonService;
import org.dejava.service.party.model.Person;

/**
 * TODO
 */
@Model
@Path("/person")
public class PersonController extends AbstractGenericController<Person, Integer> {

	/**
	 * The person EJB service.
	 */
	@Inject
	private PersonService personService;

	/**
	 * @see org.dejava.component.javaee.controller.AbstractGenericController#getBusinessService()
	 */
	@Override
	protected AbstractGenericService<Person, Integer> getBusinessService() {
		return personService;
	}
}
