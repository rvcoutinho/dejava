package org.dejava.service.message.util;

import javax.validation.Payload;

import org.dejava.component.i18n.message.annotation.MessageBundle;

/**
 * Types of messages.
 */
public interface MessageTypes {

	/**
	 * Info message type.
	 */
	@MessageBundle(baseName = "org.dejava.service.message.properties.info")
	interface Info extends Payload {
	}

	/**
	 * Warn message type.
	 */
	@MessageBundle(baseName = "org.dejava.service.message.properties.warn")
	interface Warn extends Payload {
	}

	/**
	 * Error message type.
	 */
	@MessageBundle(baseName = "org.dejava.service.message.properties.error")
	interface Error extends Payload {
	}

	/**
	 * Fatal message type.
	 */
	@MessageBundle(baseName = "org.dejava.service.message.properties.fatal")
	interface Fatal extends Payload {
	}

}