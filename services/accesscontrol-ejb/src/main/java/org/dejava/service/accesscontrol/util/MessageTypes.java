package org.dejava.service.accesscontrol.util;

import javax.validation.Payload;

import org.dejava.component.i18n.message.annotation.MessageBundle;

/**
 * Types of messages.
 */
public interface MessageTypes {

	/**
	 * Info message type.
	 */
	@MessageBundle(baseName = "org.dejava.properties.properties.info")
	interface Info extends Payload {
	}

	/**
	 * Warn message type.
	 */
	@MessageBundle(baseName = "org.dejava.properties.properties.warn")
	interface Warn extends Payload {
	}

	/**
	 * Error message type.
	 */
	@MessageBundle(baseName = "org.dejava.properties.properties.error")
	interface Error extends Payload {
	}

	/**
	 * Fatal message type.
	 */
	@MessageBundle(baseName = "org.dejava.properties.properties.fatal")
	interface Fatal extends Payload {
	}

}