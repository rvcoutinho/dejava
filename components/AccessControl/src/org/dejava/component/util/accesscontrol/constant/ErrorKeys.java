package org.dejava.component.util.accesscontrol.constant;

import org.dejava.component.util.i18n.source.annotation.MessageSource;
import org.dejava.component.util.i18n.source.annotation.MessageSources;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.component.util.accesscontrol.properties.errors", processors = { "org.dejava.component.util.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class ErrorKeys {

	/**
	 * Authentication error key.
	 */
	public static final String AUTHENTICATION = "accesscontrol.validation";

	/**
	 * Permission error key.
	 */
	public static final String PERMISSION = "accesscontrol.permission";

	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
