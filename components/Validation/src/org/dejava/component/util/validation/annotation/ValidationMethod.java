package org.dejava.component.util.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.dejava.component.util.validation.BasicValidations;

/**
 * Defines a new validation method for a field.
 * 
 * The validation must be performed by invoking the method with the given name.
 * 
 * It might be invoked in a static context if only the validation method class (clazz) is given (but not the
 * jndiObjectName). If the JNDI object name is given, the class will be ignored and the method will be invoked
 * from the JNDI object.
 * 
 * The first parameter of the method must always expect the value for the annotated field.
 * 
 * The next parameters values used are from the given complementary fields (complementaryFieldsPaths). The
 * expected types should be compatible with each field type and value.
 * 
 * In addition, other parameters must be used. Their classes and values should be defined in the proper place
 * (otherParametersClasses and otherParametersValues). The parameters values are converted from String via the
 * given class constructor (must take string as parameter). If more classes than values are given, the missing
 * values will be considered as null. A null value can also be specified by surrounding the NULL_VALUE with
 * "#{}".
 * 
 * If validateItems is true (default is false), the field value will not be validated directly. Instead, every
 * object in the array/Iterable represented by the field value will be validated.
 * 
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.ANNOTATION_TYPE)
public @interface ValidationMethod {
	
	/**
	 * Null value expression. To be used in otherParametersValues.
	 */
	public static final String NULL_EXPRESSION = "#{null}";
	
	/**
	 * Class from which to invoke the validation method. Will be ignored if an JNDI object is given.
	 */
	Class<?> clazz() default BasicValidations.class;
	
	/**
	 * JNDI object name to lookup. If given, the validation will be made by invoking the method from this
	 * object.
	 */
	String jndiObjectName() default "";
	
	/**
	 * Validation method name. A method with the given name will be invoked to perform validation.
	 */
	String methodName();
	
	/**
	 * If true (default is false), the field value will not be validated directly. Instead, every object in
	 * the array/Iterable represented by the field value will be validated. The validation processor does not
	 * support null values in the array/Iterable.
	 */
	boolean validateItems() default false;
	
	/**
	 * Complementary fields paths. Every given field will be used in the method call (in the given order). The
	 * expected types should be compatible with each field type and value.
	 */
	String[] complementaryFieldsPaths() default {};
	
	/**
	 * Other parameters classes for the method. In addition to the field value itself and the complementary
	 * fields, other parameters can be provided. Must be used together with otherParametersValues.
	 */
	Class<?>[] otherParametersClasses() default {};
	
	/**
	 * Other parameters values for the method. The parameters values are converted from String via the given
	 * class constructor (must take string as parameter). If more classes than values are given, the missing
	 * values will be considered as null. A null value can also be specified by using the
	 * NULL_VALUE_EXPRESSION defined here.
	 */
	String[] otherParametersValues() default {};
	
	/**
	 * If the validation should be ignored when the field value is null.
	 */
	boolean ignoreOnNull() default true;
}