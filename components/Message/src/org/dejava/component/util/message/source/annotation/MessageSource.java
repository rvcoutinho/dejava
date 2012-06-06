package org.dejava.component.util.message.source.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.dejava.component.util.message.source.model.MessageSourceClassType;

/**
 * Defines a source of messages.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.ANNOTATION_TYPE)
public @interface MessageSource {
	
	/**
	 * Base name of the bundle that the class provides messages to.
	 */
	String bundleBaseName();
	
	/**
	 * Description for the message source file.
	 */
	String description() default "";
	
	/**
	 * Available locales that this source must provide messages to.
	 */
	String[] availableLocales() default { "en_US_", "pt_BR_" };
	
	/**
	 * Types of message source that the annotated class represents.
	 */
	MessageSourceClassType[] types();
}
