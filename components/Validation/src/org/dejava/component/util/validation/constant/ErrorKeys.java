package org.dejava.component.util.validation.constant;

import org.dejava.component.util.i18n.source.annotation.MessageSource;
import org.dejava.component.util.i18n.source.annotation.MessageSources;
import org.dejava.component.util.i18n.source.processor.impl.ConstantValuesEntryProcessor;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.component.util.validation.properties.errors", processors = { ConstantValuesEntryProcessor.class }) })
public final class ErrorKeys {
	
	/**
	 * Unaccessible field error key.
	 */
	public static final String UNACCESSIBLE_FIELD = "reflection.field.unaccessible";
	
	/**
	 * Empty object error key.
	 */
	public static final String EMPTY_OBJECT = "validation.object.empty";
	
	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
