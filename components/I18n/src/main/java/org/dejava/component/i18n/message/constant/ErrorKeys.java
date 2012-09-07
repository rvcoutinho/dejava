package org.dejava.component.i18n.message.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.component.i18n.message.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class ErrorKeys {
	
	/**
	 * Missing key error key.
	 */
	public static final String MISSING_KEY = "message.key.missing";
	
	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
