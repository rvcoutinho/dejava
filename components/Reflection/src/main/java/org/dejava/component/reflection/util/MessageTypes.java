package org.dejava.component.reflection.util;

import org.dejava.component.i18n.message.annotation.MessageBundle;

/**
 * Types of messages.
 */
public interface MessageTypes {

	/**
	 * Error message type.
	 */
	@MessageBundle(baseName = "org.dejava.component.reflection.properties.error")
	interface Error {
	}

}
