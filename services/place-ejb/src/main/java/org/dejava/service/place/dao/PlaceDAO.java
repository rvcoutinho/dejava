package org.dejava.service.place.dao;

import static org.dejava.properties.util.GooglePlacesAPIProps.API_PROPERTIES;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.dejava.component.ejb.dao.AbstractGenericDAO;
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
	 * Gets the place search customized URL.
	 * 
	 * @param addressReference
	 *            The address reference to get the place information.
	 * @return The place search customized URL.
	 * @throws UnsupportedEncodingException
	 *             If the named encoding is not supported .
	 */
	protected String getPlaceSearchURL(final String addressReference) throws UnsupportedEncodingException {
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
	 * Gets the place from the google API.
	 * 
	 * @param placeReference
	 *            The place reference.
	 * @return The place from the google API.
	 * @throws IOException
	 *             If the google place URL cannot be processed. FIXME
	 */
	public Place getPlaceByGoogleReference(final String placeReference) throws IOException {
		// Gets the URL google place detail.
		final URL placeURL = new URL(getPlaceSearchURL(placeReference));
		// Parses the result into a google place result.
		final GooglePlaceResult placeResult = new JsonObjectParser(new JacksonFactory()).parseAndClose(
				new InputStreamReader(placeURL.openStream()), GooglePlaceResult.class);
		// Returns the place for the google place.
		return placeResult.getPlace();
	}

}
