package org.dejava.properties.util;

import java.util.Properties;

/**
 * Google places application properties.
 */
public final class GooglePlacesAPIProps extends AbstractProps {

	/**
	 * The path for the google place properties file.
	 */
	private static final String API_PROPERTIES_PATH = "org/dejava/properties/google-places-api_.properties";

	/**
	 * The google place properties file.
	 */
	public static final Properties API_PROPERTIES = getProperties(API_PROPERTIES_PATH);

}
