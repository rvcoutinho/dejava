package org.dejava.component.faces.test.message.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.component.faces.test.message.properties.fatal", sourcePath = "src/test/resources", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class FatalKeys {

	/**
	 * Generic error key.
	 */
	public static final String TEST = "test";

	/**
	 * Private constructor.
	 */
	private FatalKeys() {
	}
}
