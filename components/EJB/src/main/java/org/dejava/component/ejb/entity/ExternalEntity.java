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
	 * The direct retrieve method name (if the external entity is mapped directly).
	 */
	public static final String DIR_RET_METHOD = "getById";

	/**
	 * The reverse retrieve method name (if the external entity maps the relationship).
	 */
	public static final String REV_RET_METHOD = "getByAttribute";

	/**
	 * The name of the method to be used in order to retrieve the external entity. The method signature must
	 * be compatible with the parameters returned by the {@link #paramsValuesMethod()}. The default values are
	 * "getById" (if the external entity is mapped directly) or "getByAttribute" (if the external entity maps
	 * the relationship).
	 */
	String retrieveMethod() default "";

	/**
	 * The direct retrieve method parameters classes (if {@link #mappedBy()} is empty).
	 */
	public static final Class<?>[] DIR_RET_METHOD_PARAMS_CLASSES = { Integer.class };

	/**
	 * The reverse retrieve method parameters classes (if {@link #mappedBy()} is not empty) for a single
	 * external entity.
	 */
	public static final Class<?>[] REV_SINGLE_RET_METHOD_PARAMS_CLASSES = { String.class, Object.class };

	/**
	 * The reverse retrieve method parameters classes (if {@link #mappedBy()} is not empty) for multiple
	 * external entities.
	 */
	public static final Class<?>[] REV_MULTI_RET_METHOD_PARAMS_CLASSES = { String.class, Object.class,
			Integer.class, Integer.class };

	/**
	 * The parameters classes for the method to used in order to retrieve the external entity. The default
	 * values are [Integer.class] (if {@link #mappedBy()} is empty), [String.class, Object.class,
	 * Integer.class, Integer.class] (if {@link #mappedBy()} is not empty and {@link #singleEntity()} is
	 * false) or [String.class, Object.class, Integer.class, Integer.class] (if {@link #mappedBy()} is not
	 * empty and {@link #singleEntity()} is true).
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
