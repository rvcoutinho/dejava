package org.dejava.component.i18n.message.util;

import org.dejava.component.i18n.message.annotation.MessageBundle;

/**
 * Severity of messages.
 */
public interface Severity {

	/**
	 * Error severity.
	 */
	@MessageBundle(baseName = "org.dejava.component.i18n.message.properties.error")
	interface Error {
	}

}