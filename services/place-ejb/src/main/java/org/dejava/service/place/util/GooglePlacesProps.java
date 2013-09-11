package org.dejava.service.place.util;

import java.security.InvalidParameterException;
import java.util.Properties;

/**
 * Google places application properties.
 */
public final class GooglePlacesProps {

	/**
	 * The path for the google place properties file.
	 */
	private static final String PLACE_PROPERTIES_PATH = "org/dejava/service/place/properties/google-places-api_.properties";

	/**
	 * Gets the application properties object (from file).
	 * 
	 * @return The application properties object (from file).
	 */
	private static Properties getAppProperties() {
		// Gets the current thread class loader.
		final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		// Creates a new properties object.
		final Properties appProperties = new Properties();
		// Tries to load the properties content.
		try {
			appProperties.load(contextClassLoader.getResourceAsStream(PLACE_PROPERTIES_PATH));
		}
		// If an exception is raised.
		catch (final Exception exception) {
			// Throws an exception. TODO
			throw new InvalidParameterException();
		}
		// Returns the new properties object.
		return appProperties;
	}

	/**
	 * The google place properties file.
	 */
	public static final Properties PLACE_PROPERTIES = getAppProperties();

}
