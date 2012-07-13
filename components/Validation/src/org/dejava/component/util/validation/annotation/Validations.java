package org.dejava.component.util.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Define validations to be executed in a object field. The validations are defined using a set of
 * {@link ValidationMethod} annotations.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD })
public @interface Validations {
	
	/**
	 * Validations for the annotated field.
	 */
	ValidationMethod[] validationMethods() default {};
	
	/**
	 * If the validation must be invoked recursively for the object(s) in the annotated field.
	 */
	boolean recursiveValidation() default false;
}