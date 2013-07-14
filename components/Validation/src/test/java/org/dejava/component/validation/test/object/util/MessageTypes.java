package org.dejava.component.validation.test.object.util;

import javax.validation.Payload;

import org.dejava.component.i18n.message.annotation.MessageBundle;

/**
 * Types of messages.
 */
public interface MessageTypes {

	/**
	 * Error message type.
	 */
	@MessageBundle(baseName = "org.dejava.component.validation.test.properties.error")
	interface Error extends Payload {
	}

}
