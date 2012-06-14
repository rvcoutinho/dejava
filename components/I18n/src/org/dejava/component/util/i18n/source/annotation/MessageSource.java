package org.dejava.component.util.i18n.source.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.dejava.component.util.i18n.source.processor.MessageSourceEntryProcessor;

/**
 * Defines a source of messages.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.ANNOTATION_TYPE)
public @interface MessageSource {
	
	/**
	 * The source path where the source files should be created.
	 * 
	 * "src" is used by default.
	 */
	String sourcePath() default "src";
	
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
	 * 
	 * By default, "en_US" and "pt_BR" are used.
	 */
	String[] availableLocales() default { "en_US", "pt_BR" };
	
	/**
	 * Types of message source that the annotated class represents.
	 */
	Class<MessageSourceEntryProcessor>[] processors();
}
