package org.dejava.component.exception.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.component.exception.properties.errors", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class ErrorKeys {
	
	/**
	 * Generic error key.
	 */
	public static final String GENERIC = "exception.generic";
	
	/**
	 * Missing key error key.
	 */
	public static final String EMPTY_PARAM = "exception.parameter.empty";
	
	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
