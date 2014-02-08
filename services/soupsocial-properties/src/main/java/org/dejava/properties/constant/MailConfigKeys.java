package org.dejava.properties.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Defines the mail configuration keys (should exist in the properties file).
 */
@MessageSources(sources = { @MessageSource(availableLocales = { "" }, description = "Mail configuration properties", bundleBaseName = "org.dejava.properties.mail-config", processors = "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor") })
public class MailConfigKeys {

	/**
	 * Debug property key.
	 */
	public static final String DEBUG = "mail.debug";

	/**
	 * Debug property key.
	 */
	public static final String FROM = "mail.from";

	/**
	 * Mail host property key.
	 */
	public static final String HOST = "mail.host";

	/**
	 * Mail user property key.
	 */
	public static final String USER = "mail.user";

	/**
	 * Mail user password property key.
	 */
	public static final String PASSWORD = "mail.password";

	/**
	 * SMTP port property key.
	 */
	public static final String SMTP_PORT = "mail.smtp.port";

	/**
	 * SMTPS port property key.
	 */
	public static final String SMTPS_PORT = "mail.smtps.port";

	/**
	 * POP3 port property key.
	 */
	public static final String POP3_PORT = "mail.pop3.port";

	/**
	 * POP3S port property key.
	 */
	public static final String POP3S_PORT = "mail.pop3s.port";

}
