package org.dejava.component.util.exception.constant;

import org.dejava.component.util.message.annotation.MessageSource;
import org.dejava.component.util.message.annotation.MessageSources;
import org.dejava.component.util.message.model.MessageSourceClassType;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.component.util.exception.properties.errors", types = { MessageSourceClassType.PUBLIC_CONSTANTS_VALUES }) })
public final class ErrorKeys {
	
	/**
	 * Missing key error key.
	 */
	public static final String EMPTY_PARAM = "exception.parameter.empty";
	
	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
