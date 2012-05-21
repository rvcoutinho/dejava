package org.dejava.component.util.message.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define a message bundle to be used when getting messages from within a class. The annotations
 * are used to define the available bundles in for the message handler.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.ANNOTATION_TYPE })
public @interface MessageBundle {
	
	/**
	 * Type for the bundle.
	 */
	String type();
	
	/**
	 * Base name for the message bundle.
	 */
	String baseName();
}
