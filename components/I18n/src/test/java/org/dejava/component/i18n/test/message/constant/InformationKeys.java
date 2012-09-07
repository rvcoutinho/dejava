package org.dejava.component.i18n.test.message.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to information message keys of the package.
 */
@MessageSources(sources = { @MessageSource(sourcePath = "src/test/resources", bundleBaseName = "org.dejava.component.i18n.test.message.properties.information", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class InformationKeys {
	
	/**
	 * Success information message key.
	 */
	public static final String SUCCESS1 = "message.sample.success1";
	
	/**
	 * Success information message key.
	 */
	public static final String SUCCESS2 = "message.sample.success2";
	
	/**
	 * Success information message key.
	 */
	public static final String SUCCESS3 = "message.sample.success3";
	
	/**
	 * Success information message key.
	 */
	public static final String SUCCESS4 = "message.sample.success4";
	
	/**
	 * Success information message key.
	 */
	public static final String SUCCESS5 = "message.sample.success5";
	
	/**
	 * Success information message key.
	 */
	public static final String SUCCESS6 = "message.sample.success6";
	
	/**
	 * Private constructor.
	 */
	private InformationKeys() {
	}
}
