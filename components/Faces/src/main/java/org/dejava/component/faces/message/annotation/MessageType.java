package org.dejava.component.faces.message.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a JSF message.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE })
public @interface MessageType {

	/**
	 * The severity for the message type.
	 */
	Severity severity() default Severity.ERROR;

	/**
	 * Faces message severity.
	 */
	public enum Severity {

		/**
		 * Info severity.
		 */
		INFO,

		/**
		 * Warn severity.
		 */
		WARN,

		/**
		 * Error severity.
		 */
		ERROR,

		/**
		 * Fatal severity.
		 */
		FATAL;
	}
}
