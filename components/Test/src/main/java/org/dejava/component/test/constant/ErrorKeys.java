package org.dejava.component.test.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.component.test.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class ErrorKeys {

	/**
	 * Unavailable parametric test data error key.
	 */
	public static final String UNAVAILABLE_TEST_DATA = "test.data.unavailable";

	/**
	 * Invalid parametric test method error key.
	 */
	public static final String INVALID_PARAM_TEST_METHOD = "test.parametric.method.invalid";

	/**
	 * Failed parametric test error key.
	 */
	public static final String FAILED_PARAMETRIC_TEST = "test.parametric.failed";

	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
