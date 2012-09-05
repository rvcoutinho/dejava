package org.dejava.component.i18n.source.model;

/**
 * Message source type.
 */
public enum MessageSourceClassType {
	
	/**
	 * Name of the class.
	 */
	NAME,
	/**
	 * Public constants values of the class.
	 */
	PUBLIC_CONSTANTS_VALUES,
	/**
	 * Non static fields of the class.
	 */
	OBJECT_FIELDS,
	/**
	 * Public getters of the class.
	 */
	PUBLIC_GETTERS,
	/**
	 * Public methods of the class.
	 */
	PUBLIC_METHODS,
	/**
	 * Enum types of the class (enumerator).
	 */
	ENUM_TYPES;
}
