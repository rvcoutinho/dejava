package org.dejava.properties.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Defines the application configuration keys (should exist in the properties file).
 */
@MessageSources(sources = { @MessageSource(availableLocales = { "" }, description = "Application configuration properties", bundleBaseName = "org.dejava.properties.app-config", processors = "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor") })
public class AppConfigKeys {

	/**
	 * Debug property key.
	 */
	public static final String EAR_NAME = "ear.name";

}
