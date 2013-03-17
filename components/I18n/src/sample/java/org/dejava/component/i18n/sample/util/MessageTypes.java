package org.dejava.component.i18n.sample.util;

import org.dejava.component.i18n.message.annotation.MessageBundle;

/**
 * Types of messages.
 */
public interface MessageTypes {

	/**
	 * Error message type.
	 */
	@MessageBundle(baseName = "org.dejava.component.i18n.test.sample.properties.error")
	interface Error {
	}

	/**
	 * Information message type.
	 */
	@MessageBundle(baseName = "org.dejava.component.i18n.test.sample.properties.information")
	interface Information {
	}

}