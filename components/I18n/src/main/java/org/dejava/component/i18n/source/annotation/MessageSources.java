package org.dejava.component.i18n.source.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.dejava.component.i18n.source.MessageSourceCreator;

/**
 * Defines message sources for an annotated class (will be processed with the {@link MessageSourceCreator}
 * annotation processor).
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface MessageSources {
	
	/**
	 * Sources of messages.
	 */
	MessageSource[] sources();
}
