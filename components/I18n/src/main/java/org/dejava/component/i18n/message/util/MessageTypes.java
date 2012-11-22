package org.dejava.component.i18n.message.util;

import org.dejava.component.i18n.message.annotation.MessageBundle;

/**
 * Types of messages.
 */
public interface MessageTypes {

	/**
	 * Error message type.
	 */
	@MessageBundle(baseName = "org.dejava.component.i18n.message.properties.error")
	interface Error {
	}

}