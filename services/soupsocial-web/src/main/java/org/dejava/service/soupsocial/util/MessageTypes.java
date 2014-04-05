package org.dejava.service.soupsocial.util;

import javax.validation.Payload;

import org.dejava.component.faces.message.annotation.MessageType;
import org.dejava.component.faces.message.annotation.MessageType.Severity;
import org.dejava.component.i18n.message.annotation.MessageBundle;

/**
 * Types of messages.
 */
public interface MessageTypes {

	/**
	 * Model message type.
	 */
	@MessageBundle(baseName = "org.dejava.service.soupsocial.properties.model")
	interface Model extends Payload {
	}

	/**
	 * Info message type.
	 */
	@MessageType(severity = Severity.INFO)
	@MessageBundle(baseName = "org.dejava.service.soupsocial.properties.info")
	interface Info extends Payload {
	}

	/**
	 * Warn message type.
	 */
	@MessageType(severity = Severity.WARN)
	@MessageBundle(baseName = "org.dejava.service.soupsocial.properties.warn")
	interface Warn extends Payload {
	}

	/**
	 * Error message type.
	 */
	@MessageType(severity = Severity.ERROR)
	@MessageBundle(baseName = "org.dejava.service.soupsocial.properties.error")
	interface Error extends Payload {
	}

	/**
	 * Fatal message type.
	 */
	@MessageType(severity = Severity.FATAL)
	@MessageBundle(baseName = "org.dejava.service.soupsocial.properties.fatal")
	interface Fatal extends Payload {
	}

}