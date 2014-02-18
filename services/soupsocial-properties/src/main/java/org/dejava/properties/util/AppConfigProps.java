package org.dejava.properties.util;

import java.util.Properties;

/**
 * Application configuration properties.
 */
public final class AppConfigProps extends AbstractProps {

	/**
	 * The path for the application properties file.
	 */
	private static final String APP_PROPERTIES_PATH = "org/dejava/properties/app-config_.properties";

	/**
	 * The application properties file.
	 */
	public static final Properties APP_CONFIG_PROPERTIES = getProperties(APP_PROPERTIES_PATH);

}
