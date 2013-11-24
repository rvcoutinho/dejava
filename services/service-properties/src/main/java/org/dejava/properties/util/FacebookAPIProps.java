package org.dejava.properties.util;

import java.util.Properties;

/**
 * Facebook application properties.
 */
public final class FacebookAPIProps extends AbstractProps {

	/**
	 * The path for the application properties file.
	 */
	private static final String API_PROPERTIES_PATH = "org/dejava/properties/facebook-api_.properties";

	/**
	 * The application properties file.
	 */
	public static final Properties API_PROPERTIES = getProperties(API_PROPERTIES_PATH);

}
