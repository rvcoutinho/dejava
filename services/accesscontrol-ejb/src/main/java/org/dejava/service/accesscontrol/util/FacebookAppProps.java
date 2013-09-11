package org.dejava.service.accesscontrol.util;

import java.security.InvalidParameterException;
import java.util.Properties;

/**
 * Facebook application properties.
 */
public final class FacebookAppProps {

	/**
	 * The path for the application properties file.
	 */
	private static final String APP_PROPERTIES_PATH = "org/dejava/service/accesscontrol/properties/facebook-app_.properties";

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
			appProperties.load(contextClassLoader.getResourceAsStream(APP_PROPERTIES_PATH));
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
	 * The application properties file.
	 */
	public static final Properties APP_PROPERTIES = getAppProperties();

}
