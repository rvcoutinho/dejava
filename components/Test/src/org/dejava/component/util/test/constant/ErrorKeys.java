package org.dejava.component.util.test.constant;

import org.dejava.component.util.message.annotation.MessageSource;
import org.dejava.component.util.message.annotation.MessageSources;
import org.dejava.component.util.message.model.MessageSourceClassType;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.component.util.test.properties.errors", types = { MessageSourceClassType.PUBLIC_CONSTANTS_VALUES }) })
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
