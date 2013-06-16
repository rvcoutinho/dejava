package org.dejava.component.ejb.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Information to load an external entity.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD })
public @interface ExternalEntity {

	/**
	 * If the external entity should be lazy loaded (only loaded when forced to). Be careful when persisting
	 * entities that have lazy loaded external entities; external entities should be explicitly loaded before
	 * persisted again. Default value is false.
	 */
	boolean lazyLoading() default false;

	/**
	 * The name of the annotated entity method that returns the identifiers used by the
	 * {@link #retrieveMethod()} to retrieve the external entities. The method might return a single
	 * identifier or a Collection of identifiers. The method must take no parameters.
	 */
	String idsMethod();

	/**
	 * The name of the method to be used in order to retrieve the external entity. The default value is
	 * "getById". The method signature must be compatible with the parameters returned by the
	 * {@link #idsMethod()}.
	 */
	String retrieveMethod() default "getById";

	/**
	 * The parameters classes for the method to used in order to retrieve the external entity. The default
	 * value is Integer.
	 */
	Class<?>[] retrieveMethodParamsClasses() default { Integer.class };

	/**
	 * The JNDI name of the object to be used in order to retrieve the external entity.
	 */
	String retrieveObj();

	/**
	 * If the field value must be set directly (field access) or via setter method. The default value is
	 * false.
	 */
	boolean fieldAccess() default false;
}
