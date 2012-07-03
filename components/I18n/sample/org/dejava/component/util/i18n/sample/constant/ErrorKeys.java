package org.dejava.component.util.i18n.sample.constant;

import org.dejava.component.util.i18n.source.annotation.MessageSource;
import org.dejava.component.util.i18n.source.annotation.MessageSources;

/**
 * Constants related to error message keys of the package.
 */
@MessageSources(sources = { @MessageSource(availableLocales = { "en_US", "pt_BR" }, sourcePath = "sample", bundleBaseName = "org.dejava.component.util.i18n.test.message.properties.error", processors = { "org.dejava.component.util.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
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
