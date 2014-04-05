package org.dejava.service.soupsocial.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.soupsocial.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class ErrorKeys {

	/**
	 * Generic error key.
	 */
	public static final String GENERIC = "generic";

	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
