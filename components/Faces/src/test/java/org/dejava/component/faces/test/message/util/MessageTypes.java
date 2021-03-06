package org.dejava.component.faces.test.message.util;

import org.dejava.component.faces.message.annotation.MessageType;
import org.dejava.component.faces.message.annotation.MessageType.Severity;
import org.dejava.component.i18n.message.annotation.MessageBundle;

/**
 * Types of messages.
 */
public interface MessageTypes {

	/**
	 * Info message type.
	 */
	@MessageType(severity = Severity.INFO)
	@MessageBundle(baseName = "org.dejava.component.faces.test.message.properties.info")
	interface Info {
	}

	/**
	 * Warn message type.
	 */
	@MessageType(severity = Severity.WARN)
	@MessageBundle(baseName = "org.dejava.component.faces.test.message.properties.warn")
	interface Warn {
	}

	/**
	 * Error message type.
	 */
	@MessageType(severity = Severity.ERROR)
	@MessageBundle(baseName = "org.dejava.component.faces.test.message.properties.error")
	interface Error {
	}

	/**
	 * Error message type (2).
	 */
	@MessageBundle(baseName = "org.dejava.component.faces.test.message.properties.error")
	interface Error2 {
	}

	/**
	 * Fatal message type.
	 */
	@MessageType(severity = Severity.FATAL)
	@MessageBundle(baseName = "org.dejava.component.faces.test.message.properties.fatal")
	interface Fatal {
	}

}
