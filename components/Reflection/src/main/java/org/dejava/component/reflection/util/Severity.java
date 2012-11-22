package org.dejava.component.reflection.util;

import org.dejava.component.i18n.message.annotation.MessageBundle;

/**
 * Severity of messages.
 */
public interface Severity {

	/**
	 * Error severity.
	 */
	@MessageBundle(baseName = "org.dejava.component.reflection.properties.error")
	interface Error {
	}

}
