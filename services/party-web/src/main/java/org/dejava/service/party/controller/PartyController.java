package org.dejava.service.party.controller;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.Path;

import org.dejava.component.ejb.component.AbstractGenericComponent;
import org.dejava.component.jsf.controller.AbstractGenericController;
import org.dejava.service.party.component.PartyComponent;
import org.dejava.service.party.model.Party;
import org.dejava.service.party.util.PartyCtx;

/**
 * Party web controller.
 */
@Model
@Path("/party")
public class PartyController extends AbstractGenericController<Party, Integer> {

	/**
	 * The party EJB service.
	 */
	@Inject
	@PartyCtx
	private PartyComponent partyComponent;

	/**
	 * @see org.dejava.component.jsf.controller.AbstractGenericController#getBusinessComponent()
	 */
	@Override
	protected AbstractGenericComponent<Party, Integer> getBusinessComponent() {
		return partyComponent;
	}
}
