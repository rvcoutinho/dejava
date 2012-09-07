package org.dejava.component.i18n.test.sample.constant;

import org.dejava.component.i18n.test.source.annotation.MessageSource;
import org.dejava.component.i18n.test.source.annotation.MessageSources;

/**
 * Constants related to error message keys of the package.
 */
@MessageSources(sources = { @MessageSource(availableLocales = { "en_US", "pt_BR" }, sourcePath = "src/sample/resources", bundleBaseName = "org.dejava.component.i18n.test.test.message.properties.error", processors = { "org.dejava.component.i18n.test.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class ErrorKeys {
	
	/**
	 * Failure error message key.
	 */
	public static final String FAILURE = "message.sample.failure";
	
	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
