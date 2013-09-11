package org.dejava.service.place.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Defines the google places API keys (should exist in the properties file).
 */
@MessageSources(sources = { @MessageSource(availableLocales = { "" }, description = "Google Place API properties", bundleBaseName = "org.dejava.service.place.properties.google-places-api", processors = "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor") })
public class GooglePlacesAPIKeys {

	/**
	 * The key for the google places API key.
	 */
	public static final String API_KEY = "google.places.key";

	/**
	 * The key for the google places details URL.
	 */
	public static final String PLACE_DETAILS_URL = "google.places.details.url";

}
