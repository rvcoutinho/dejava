package org.dejava.component.ejb.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.component.ejb.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class ErrorKeys {

	/**
	 * Invalid JNDI retrieve object name error key.
	 */
	public static final String INVALID_JNDI_RETRIEVE_OBJ_NAME = "external.entity.retrieve.object.name.invalid";

	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
