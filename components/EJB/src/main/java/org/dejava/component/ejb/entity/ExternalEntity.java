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
	 * identifier or a Collection of identifiers (in this case, the {@link #singleEntity()} must be false).
	 * The method must take no parameters.
	 */
	String paramsValuesMethod() default "";

	/**
	 * If the relationship represents a *-to-one relationship.
	 */
	boolean singleEntity() default true;

	/**
	 * The external entity field that maps the relationship (reversed mapped relationship). By default, the
	 * annotated entity maps the relationship.
	 */
	String mappedBy() default "";

	/**
	 * The name of the method to be used in order to retrieve the external entity. The method signature must
	 * be compatible with the parameters returned by the {@link #paramsValuesMethod()}. The default values are
	 * {@link ExternalEntityLoader#DIR_RET_METHOD} (if the external entity is mapped directly) or
	 * {@link ExternalEntityLoader#REV_RET_METHOD} (if the external entity maps the relationship).
	 */
	String retrieveMethod() default "";

	/**
	 * The parameters classes for the method to used in order to retrieve the external entity. The default
	 * values are {@link ExternalEntityLoader#DIR_RET_METHOD_PARAMS_CLASSES} (if {@link #mappedBy()} is
	 * empty), {@link ExternalEntityLoader#REV_SINGLE_RET_METHOD_PARAMS_CLASSES} (if {@link #mappedBy()} is
	 * not empty and {@link #singleEntity()} is false) or
	 * {@link ExternalEntityLoader#REV_MULTI_RET_METHOD_PARAMS_CLASSES} (if {@link #mappedBy()} is not empty
	 * and {@link #singleEntity()} is true).
	 */
	Class<?>[] retrieveMethodParamsClasses() default {};

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
