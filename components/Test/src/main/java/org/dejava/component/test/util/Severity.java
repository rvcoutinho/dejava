package org.dejava.component.test.util;

import org.dejava.component.i18n.message.annotation.MessageBundle;

/**
 * Severity of messages.
 */
public interface Severity {

	/**
	 * Error severity.
	 */
	@MessageBundle(baseName = "org.dejava.component.test.properties.error")
	interface Error {
	}

}
