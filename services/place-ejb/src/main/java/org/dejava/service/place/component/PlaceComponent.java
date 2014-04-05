package org.dejava.service.place.component;

import java.io.IOException;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.dejava.service.place.businessrule.PlaceBusinessRuleSet;
import org.dejava.service.place.dao.PlaceDAO;
import org.dejava.service.place.model.Place;
import org.dejava.service.place.util.PlaceCtx;

/**
 * EJB service for place.
 */
@PlaceCtx
@Stateless(name = "Component/Place/Place")
public class PlaceComponent {

	/**
	 * The place DAO.
	 */
	@Inject
	@PlaceCtx
	private PlaceDAO placeDAO;

	/**
	 * The place business rule set.
	 */
	@Inject
	@PlaceCtx
	private PlaceBusinessRuleSet placeBusinessRuleSet;

	/**
	 * Gets the place from the google reference.
	 * 
	 * @param placeReference
	 *            The place reference.
	 * @return The place from the google reference.
	 * @throws IOException
	 *             If the google place URL cannot be processed. FIXME
	 */
	public Place getContactByGoogleReference(final String placeReference) throws IOException {
		return placeDAO.getContactByGoogleReference(placeReference);
	}

	/**
	 * Creates a new place.
	 * 
	 * @param place
	 *            The new place.
	 * @return The created place.
	 */
	public Place createPlace(final Place place) {
		// Validates the place to be added.
		placeBusinessRuleSet.validate(place);
		// Adds the new place.
		return placeDAO.merge(place);
	}

	/**
	 * Creates new places.
	 * 
	 * @param places
	 *            The new places.
	 * @return The created places.
	 */
	public Collection<Place> createPlaces(final Collection<Place> places) {
		// Validates the places to be added.
		placeBusinessRuleSet.validate(places);
		// Adds the new places.
		return placeDAO.merge(places);
	}

	/**
	 * Gets a place by its identifier.
	 * 
	 * @param identifier
	 *            The identifier of the for place.
	 * @return A person by its identifier.
	 */
	public Place getPlaceById(final Integer identifier) {
		return placeDAO.getById(identifier);
	}

}
