package org.dejava.service.place.dao;

import static org.dejava.properties.util.GooglePlacesAPIProps.API_PROPERTIES;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.constant.DAOParamKeys;
import org.dejava.component.ejb.dao.AbstractGenericDAO;
import org.dejava.component.validation.method.PreConditions;
import org.dejava.properties.constant.GooglePlacesAPIKeys;
import org.dejava.service.place.model.Place;
import org.dejava.service.place.model.google.GooglePlaceResult;
import org.dejava.service.place.util.PlaceCtx;

import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * DAO for place.
 */
@PlaceCtx
public class PlaceDAO extends AbstractGenericDAO<Place, Integer> {

	/**
	 * Entity manager being used.
	 */
	@Inject
	@PlaceCtx
	private EntityManager entityManager;

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @see org.dejava.component.ejb.dao.AbstractGenericDAO#merge(java.lang.Object)
	 */
	@Override
	public Place merge(final Place entity) {
		// Asserts that the entity is not null.
		PreConditions.assertParamNotNull(DAOParamKeys.ENTITY, entity);
		// If the entity has no id (not previously persisted).
		if (entity.getIdentifier() == null) {
			// Tries to get a place with the same name.
			final Collection<Place> similarPlaces = getByAttribute("name", entity.getName(), null, null);
			// If the place has a parent place.
			if (entity.getParentPlace() != null) {
				// Merges the parent place and resets it.
				entity.setParentPlace(merge(entity.getParentPlace()));
			}
			// For each place with the same name. TODO Improve performance.
			for (final Place similarPlace : similarPlaces) {
				// If the place has the same name and parent place.
				if ((entity.getName().equals(similarPlace.getName()))
						&& (((entity.getParentPlace() == null) && (similarPlace.getParentPlace() == null)) || ((entity
								.getParentPlace() != null) && (entity.getParentPlace().equals(similarPlace
								.getParentPlace()))))) {
					// Copies the similar place id (as they are the same).
					entity.setIdentifier(similarPlace.getIdentifier());
					// If the similar place has a reference.
					if (similarPlace.getReference() != null) {
						// Copies the similar place reference (as they are the same).
						entity.setReference(similarPlace.getReference());
					}
					// If the similar place has a short name.
					if (similarPlace.getShortName() != null) {
						// Copies the similar place short name (as they are the same).
						entity.setShortName(similarPlace.getShortName());
					}
					// Stops searching.
					break;
				}
			}

		}
		// Merges and returns the given entity with the persistence context.
		return getEntityManager().merge(entity);
	}

	/**
	 * Gets the place search customized URL.
	 * 
	 * @param addressReference
	 *            The address reference to get the place information.
	 * @return The place search customized URL.
	 * @throws UnsupportedEncodingException
	 *             If the named encoding is not supported .
	 */
	public String getPlaceSearchURL(final String addressReference) throws UnsupportedEncodingException {
		// Gets the initial URL.
		final StringBuffer url = new StringBuffer(
				API_PROPERTIES.getProperty(GooglePlacesAPIKeys.PLACE_DETAILS_URL));
		// Appends the key to URL.
		url.append("key=" + API_PROPERTIES.getProperty(GooglePlacesAPIKeys.API_KEY));
		// Appends the place reference.
		url.append("&reference=" + addressReference);
		// Appends the sensor value.
		url.append("&sensor=false");
		// Returns the URL.
		return url.toString();
	}

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
		// Gets the URL google place detail.
		final URL placeURL = new URL(getPlaceSearchURL(placeReference));
		// Parses the result into a google place result.
		final GooglePlaceResult placeResult = new JsonObjectParser(new JacksonFactory()).parseAndClose(
				new InputStreamReader(placeURL.openStream()), GooglePlaceResult.class);
		// Returns the place for the google place.
		return placeResult.getPlace();
	}

}
