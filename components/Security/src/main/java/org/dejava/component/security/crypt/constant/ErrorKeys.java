package org.dejava.component.security.crypt.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.component.security.crypt.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class ErrorKeys {

	/**
	 * Invalid algorithm name error key.
	 */
	public static final String INVALID_ALG_NAME = "algorithm.name.invalid";

	/**
	 * Invalid minimum hash cycles error key.
	 */
	public static final String INVALID_MIN_CYCLES = "hash.cycles.min.invalid";

	/**
	 * Invalid maximum hash cycles error key.
	 */
	public static final String INVALID_MAX_CYCLES = "hash.cycles.max.invalid";

	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
