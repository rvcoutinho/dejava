package org.dejava.component.util.i18n.message.constant;

import org.dejava.component.util.i18n.source.annotation.MessageSource;
import org.dejava.component.util.i18n.source.annotation.MessageSources;
import org.dejava.component.util.i18n.source.processor.impl.ConstantValuesEntryProcessor;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.component.util.i18n.message.properties.errors", processors = { ConstantValuesEntryProcessor.class }) })
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
