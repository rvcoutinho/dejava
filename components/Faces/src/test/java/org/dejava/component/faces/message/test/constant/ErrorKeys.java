package org.dejava.component.faces.message.test.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.component.faces.message.test.properties.error", sourcePath = "src/test/resources", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class ErrorKeys {

	/**
	 * Generic error key.
	 */
	public static final String TEST = "test";

	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
