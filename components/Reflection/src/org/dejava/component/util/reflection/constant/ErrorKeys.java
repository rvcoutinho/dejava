package org.dejava.component.util.reflection.constant;

import org.dejava.component.util.i18n.source.annotation.MessageSource;
import org.dejava.component.util.i18n.source.annotation.MessageSources;
import org.dejava.component.util.i18n.source.model.MessageSourceClassType;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.component.util.reflection.properties.errors", types = { MessageSourceClassType.PUBLIC_CONSTANTS_VALUES }) })
public final class ErrorKeys {
	
	/**
	 * Class not found error key.
	 */
	public static final String CLASS_NOT_FOUND = "reflection.class.notfound";
	
	/**
	 * Unexpected class error key.
	 */
	public static final String UNEXPECTED_CLASS = "reflection.class.unexpected";
	
	/**
	 * Field not found error key.
	 */
	public static final String FIELD_NOT_FOUND = "reflection.class.field.notfound";
	
	/**
	 * Method not found error key.
	 */
	public static final String METHOD_NOT_FOUND = "reflection.class.method.notfound";
	
	/**
	 * Empty parameter class error key.
	 */
	public static final String EMPTY_PARAM_CLASS = "reflection.class.method.parameter.class.empty";
	
	/**
	 * Empty parameter value error key.
	 */
	public static final String EMPTY_PARAM_VALUE = "reflection.class.method.parameter.value.empty";
	
	
	
	/**
	 * Wrong target object error key.
	 */
	public static final String WRONG_TARGET_OBJ = "reflection.field.target.object.wrong";
	
	/**
	 * Unaccessible field error key.
	 */
	public static final String UNACCESSIBLE_FIELD = "reflection.field.unaccessible";

	
	
	
	
	
	/**
	 * Not getter/setter method error key.
	 */
	public static final String NOT_GETTER_SETTER = "reflection.method.notgettersetter";
	
	/**
	 * Abstract class error key.
	 */
	public static final String ABSTRACT_CLASS = "reflection.class.abstract";
	
	/**
	 * Constructor exception error key.
	 */
	public static final String CONSTRUCTOR_EXCEPTION = "reflection.constructor.exception";
	
	/**
	 * Empty annotation class error key.
	 */
	public static final String EMPTY_ANNOTATION_CLASS = "reflection.annotation.class.empty";
	
	/**
	 * Empty class error key.
	 */
	public static final String EMPTY_CLASS = "reflection.class.empty";
	
	/**
	 * Empty class name error key.
	 */
	public static final String EMPTY_CLASS_NAME = "reflection.class.name.empty";
	
	/**
	 * Empty field name error key.
	 */
	public static final String EMPTY_FIELD_NAME = "reflection.field.name.empty";
	
	/**
	 * Empty method name error key.
	 */
	public static final String EMPTY_METHOD_NAME = "reflection.method.name.empty";
	
	/**
	 * Empty object error key.
	 */
	public static final String EMPTY_OBJECT = "reflection.object.empty";
	
	/**
	 * Illegal parameters values error key.
	 */
	public static final String ILLEGAL_PARAMS_VALUES = "reflection.parameter.values.illegal";
	
	/**
	 * Method exception error key.
	 */
	public static final String METHOD_EXCEPTION = "reflection.method.exception";
	
	/**
	 * Missing method error key.
	 */
	public static final String MISSING_METHOD = "reflection.method.missing";
	
	/**
	 * Missing constructor error key.
	 */
	public static final String MISSING_CONSTRUCTOR = "reflection.constructor.missing";
	
	/**
	 * Unaccessible constructor error key.
	 */
	public static final String UNACCESSIBLE_CONSTRUCTOR = "reflection.constructor.unaccessible";
	
	/**
	 * Unaccessible field path error key.
	 */
	public static final String UNACCESSIBLE_FIELD_PATH = "reflection.field.path.unaccessible";
	
	
	/**
	 * Unaccessible method error key.
	 */
	public static final String UNACCESSIBLE_METHOD = "reflection.method.unaccessible";
	
	/**
	 * Invalid JNDI path error key.
	 */
	public static final String INVALID_JNDI_PATH = "reflection.jndi.path.invalid";
	
	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
