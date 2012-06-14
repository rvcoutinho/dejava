package org.dejava.component.util.i18n.sample.constant;

import org.dejava.component.util.i18n.source.annotation.MessageSource;
import org.dejava.component.util.i18n.source.annotation.MessageSources;
import org.dejava.component.util.i18n.source.processor.impl.ConstantValuesEntryProcessor;

/**
 * Constants related to information message keys of the package.
 */
@MessageSources(sources = { @MessageSource(sourcePath = "sample", bundleBaseName = "org.dejava.component.util.i18n.test.message.properties.information", processors = { "org.dejava.component.util.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class InformationKeys {
	
	/**
	 * Success information message key.
	 */
	public static final String SUCCESS = "message.sample.success";
	
	/**
	 * Private constructor.
	 */
	private InformationKeys() {
	}
}
