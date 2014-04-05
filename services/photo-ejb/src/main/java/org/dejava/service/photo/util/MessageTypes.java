package org.dejava.service.photo.util;

import javax.validation.Payload;

import org.dejava.component.i18n.message.annotation.MessageBundle;

/**
 * Types of messages.
 */
public interface MessageTypes {

	/**
	 * Info message type.
	 */
	@MessageBundle(baseName = "org.dejava.service.photo.properties.info")
	interface Info extends Payload {
	}

	/**
	 * Warn message type.
	 */
	@MessageBundle(baseName = "org.dejava.service.photo.properties.warn")
	interface Warn extends Payload {
	}

	/**
	 * Error message type.
	 */
	@MessageBundle(baseName = "org.dejava.service.photo.properties.error")
	interface Error extends Payload {
	}

	/**
	 * Fatal message type.
	 */
	@MessageBundle(baseName = "org.dejava.service.photo.properties.fatal")
	interface Fatal extends Payload {
	}

}