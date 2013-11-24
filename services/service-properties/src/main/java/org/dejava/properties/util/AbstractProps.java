package org.dejava.properties.util;

import java.security.InvalidParameterException;
import java.util.Properties;

/**
 * Some abstract properties recovery tools.
 */
public class AbstractProps {

	/**
	 * Gets the application properties object (from file).
	 * 
	 * @param propertiesPath
	 *            The properties file path.
	 * @return The application properties object (from file).
	 */
	protected static Properties getProperties(String propertiesPath) {
		// Gets the current thread class loader.
		final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		// Creates a new properties object.
		final Properties properties = new Properties();
		// Tries to load the properties content.
		try {
			properties.load(contextClassLoader.getResourceAsStream(propertiesPath));
		}
		// If an exception is raised.
		catch (final Exception exception) {
			// Throws an exception. TODO
			throw new InvalidParameterException();
		}
		// Returns the new properties object.
		return properties;
	}

}
