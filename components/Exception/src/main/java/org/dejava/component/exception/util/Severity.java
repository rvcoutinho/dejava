package org.dejava.component.exception.util;

import org.dejava.component.i18n.message.annotation.MessageBundle;

/**
 * Severity of messages.
 */
public interface Severity {

	/**
	 * Error severity.
	 */
	@MessageBundle(baseName = "org.dejava.component.exception.properties.error")
	interface Error {
	}

}
