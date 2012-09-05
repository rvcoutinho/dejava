package org.dejava.component.i18n.test.sample.constant;

import org.dejava.component.i18n.test.source.annotation.MessageSource;
import org.dejava.component.i18n.test.source.annotation.MessageSources;

/**
 * Constants related to information message keys of the package.
 */
@MessageSources(sources = { @MessageSource(sourcePath = "sample", bundleBaseName = "org.dejava.component.i18n.test.test.message.properties.information", processors = { "org.dejava.component.i18n.test.source.processor.impl.ConstantValuesEntryProcessor" }) })
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
