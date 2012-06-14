package org.dejava.component.util.test.constant;

import org.dejava.component.util.i18n.source.annotation.MessageSource;
import org.dejava.component.util.i18n.source.annotation.MessageSources;
import org.dejava.component.util.i18n.source.processor.impl.ConstantValuesEntryProcessor;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.component.util.test.properties.errors", processors = { "org.dejava.component.util.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class ErrorKeys {
	
	/**
	 * Unavailable parametric test data.
	 */
	public static final String UNAVAILABLE_TEST_DATA = "test.test.data.unavailable";
	
	/**
	 * Invalid parametric test method.
	 */
	public static final String INVALID_PARAM_TEST_METHOD = "test.test.parametric.method.invalid";
	
	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
