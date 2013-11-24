package org.dejava.properties.util;

import java.util.Properties;

/**
 * Mail configuration properties.
 */
public final class MailConfigProps extends AbstractProps {

	/**
	 * The path for the application properties file.
	 */
	private static final String MAIL_PROPERTIES_PATH = "org/dejava/properties/mail-config_.properties";

	/**
	 * The application properties file.
	 */
	public static final Properties MAIL_CONFIG_PROPERTIES = getProperties(MAIL_PROPERTIES_PATH);

}
