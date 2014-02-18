package org.dejava.service.party.util;

import javax.validation.Payload;

import org.dejava.component.i18n.message.annotation.MessageBundle;

/**
 * Types of messages.
 */
public interface MessageTypes {

	/**
	 * Model message type.
	 */
	@MessageBundle(baseName = "org.dejava.service.party.properties.model")
	interface Model extends Payload {
	}

	/**
	 * Info message type.
	 */
	@MessageBundle(baseName = "org.dejava.service.party.properties.info")
	interface Info extends Payload {
	}

	/**
	 * Warn message type.
	 */
	@MessageBundle(baseName = "org.dejava.service.party.properties.warn")
	interface Warn extends Payload {
	}

	/**
	 * Error message type.
	 */
	@MessageBundle(baseName = "org.dejava.service.party.properties.error")
	interface Error extends Payload {
	}

	/**
	 * Fatal message type.
	 */
	@MessageBundle(baseName = "org.dejava.service.party.properties.fatal")
	interface Fatal extends Payload {
	}

}