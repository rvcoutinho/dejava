package org.dejava.component.util.xml.constant;

import org.dejava.component.util.message.annotation.MessageSource;
import org.dejava.component.util.message.annotation.MessageSources;
import org.dejava.component.util.message.model.MessageSourceClassType;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.component.util.xml.properties.errors", types = { MessageSourceClassType.PUBLIC_CONSTANTS_VALUES }) })
public final class ErrorKeys {
	
	/**
	 * Impossible parse error key.
	 */
	public static final String IMPOSSIBLE_PARSE = "xml.parse.impossible";
	
	/**
	 * IO Exception error key.
	 */
	public static final String IO_EXCEPTION = "xml.io.exception";
	
	/**
	 * Invalid configuration error key.
	 */
	public static final String INVALID_CONFIG = "xml.invalid.configuration";
	
	/**
	 * Empty object error key.
	 */
	public static final String EMPTY_OBJECT = "xml.object.empty";
	
	/**
	 * Getter exception error key.
	 */
	public static final String GETTER_EXCEPTION = "xml.getter.exception";
	
	/**
	 * Invalid getter parameters error key.
	 */
	public static final String INVALID_GETTER_PARAMS = "xml.getter.parameters.invalid";
	
	/**
	 * Setter exception error key.
	 */
	public static final String SETTER_EXCEPTION = "xml.setter.exception";
	
	/**
	 * Invalid setter parameters error key.
	 */
	public static final String INVALID_SETTER_PARAMS = "xml.setter.parameters.invalid";
	
	/**
	 * Constructor exception error key.
	 */
	public static final String CONSTRUCTOR_EXCEPTION = "xml.constructor.exception";
	
	/**
	 * Invalid constructor parameters error key.
	 */
	public static final String INVALID_CONSTRUCTOR_PARAMS = "xml.constructor.parameters.invalid";
	
	/**
	 * Empty node list error key.
	 */
	public static final String EMPTY_NODE_LIST = "xml.node.list.empty";
	
	/**
	 * Empty node error key.
	 */
	public static final String EMPTY_NODE = "xml.node.empty";
	
	/**
	 * Unaccessible JNDI object error key.
	 */
	public static final String UNACCESSIBLE_JNDI_OBJ = "xml.jndi.object.unaccessible";
	
	/**
	 * Invalid JNDI method parameters error key.
	 */
	public static final String INVALID_JNDI_METHOD_PARAMS = "xml.jndi.method.parameters.invalid";
	
	/**
	 * JNDI method exception error key.
	 */
	public static final String JNDI_METHOD_EXCEPTION = "xml.jndi.method.exception";
	
	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
