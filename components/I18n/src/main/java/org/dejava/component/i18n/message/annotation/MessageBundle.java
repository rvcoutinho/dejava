package org.dejava.component.i18n.message.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define a message bundle to be used when getting messages from a class.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE })
public @interface MessageBundle {

	/**
	 * Base name for the message bundle.
	 */
	String baseName();
}
