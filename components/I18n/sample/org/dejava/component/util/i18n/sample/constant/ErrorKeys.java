package org.dejava.component.util.i18n.sample.constant;

import org.dejava.component.util.i18n.source.annotation.MessageSource;
import org.dejava.component.util.i18n.source.annotation.MessageSources;
import org.dejava.component.util.i18n.source.model.MessageSourceClassType;

/**
 * Constants related to error message keys of the package.
 */
@MessageSources(sources = { @MessageSource(sourcePath = "sample", bundleBaseName = "org.dejava.component.util.i18n.message.test.properties.error", types = { MessageSourceClassType.PUBLIC_CONSTANTS_VALUES }) })
public final class ErrorKeys {
	
	/**
	 * Failure error message key.
	 */
	public static final String FAILURE = "message.sample.failure";
	
	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
