package org.dejava.component.util.reflection.test.annotation.auxiliary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Auxiliary annotation.
 */
@Target(value = { ElementType.TYPE })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AnnotationK {
	
	/**
	 * Anything.
	 */
	String anything() default "nothing";
}
