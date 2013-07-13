package org.dejava.component.exception.test.util;

import org.dejava.component.i18n.message.annotation.MessageBundle;

/**
 * Types of messages.
 */
public interface MessageTypes {

	/**
	 * Error message type.
	 */
	@MessageBundle(baseName = "org.dejava.component.exception.test.properties.error")
	interface Error {
	}

}
