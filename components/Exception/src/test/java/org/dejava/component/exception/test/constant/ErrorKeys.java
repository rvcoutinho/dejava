package org.dejava.component.exception.test.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(sourcePath = "src/test/resources", bundleBaseName = "org.dejava.component.exception.test.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class ErrorKeys {

	/**
	 * Generic error key.
	 */
	public static final String GENERIC = "generic";

	/**
	 * Invalid state error key.
	 */
	public static final String INVALID_STATE = "state.invalid";

	/**
	 * Invalid parameter error key.
	 */
	public static final String INVALID_PARAM = "parameter.invalid";

	/**
	 * Empty parameter error key.
	 */
	public static final String EMPTY_PARAM = "parameter.empty";

	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
