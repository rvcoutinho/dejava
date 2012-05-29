package org.dejava.component.util.message.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines message sources for an annotated class.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface MessageSources {
	
	/**
	 * Sources of messages.
	 */
	MessageSource[] sources();
}