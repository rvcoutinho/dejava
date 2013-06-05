package org.dejava.service.party.controller;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.Path;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.jsf.controller.AbstractGenericController;
import org.dejava.service.party.component.PersonComponent;
import org.dejava.service.party.model.Person;
import org.dejava.service.party.util.Party;

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
	@Party
	private PersonComponent personComponent;

	/**
	 * @see org.dejava.component.jsf.controller.AbstractGenericController#getBusinessComponent()
	 */
	@Override
	protected AbstractGenericComponent<Person, Integer> getBusinessComponent() {
		return personComponent;
	}
}
