package org.dejava.component.security.authc.util;

import org.dejava.component.i18n.message.annotation.MessageBundle;

/**
 * Severity of messages.
 */
public interface Severity {

	/**
	 * Error severity.
	 */
	@MessageBundle(baseName = "org.dejava.component.security.authc.properties.error")
	interface Error {
	}

}