package org.dejava.component.util.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Define validations for an object field.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD })
public @interface Validations {
	
	/**
	 * Validations for the field.
	 */
	ValidationMethod[] validationMethods() default {};
	
	/**
	 * Se the validation must be invoked recursively for the object(s) in the annotated field.
	 */
	boolean recursiveValidation() default false;
}