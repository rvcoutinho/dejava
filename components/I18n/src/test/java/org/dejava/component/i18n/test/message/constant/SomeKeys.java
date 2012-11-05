package org.dejava.component.i18n.test.message.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to different message keys of the package.
 */
@MessageSources(sources = {
		@MessageSource(sourcePath = "src/test/resources", bundleBaseName = "org.dejava.component.i18n.test.message.properties.stacktrace1", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }),
		@MessageSource(sourcePath = "src/test/resources", bundleBaseName = "org.dejava.component.i18n.test.message.properties.stacktrace2", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }),
		@MessageSource(sourcePath = "src/test/resources", bundleBaseName = "org.dejava.component.i18n.test.message.properties.class", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }),
		@MessageSource(sourcePath = "src/test/resources", bundleBaseName = "org.dejava.component.i18n.test.message.properties.superclass", processors = { "org.dejava.component.i18n.source.processor.impl.FieldsEntryProcessor" }),
		@MessageSource(sourcePath = "src/test/resources", bundleBaseName = "org.dejava.component.i18n.test.message.properties.interface", processors = { "org.dejava.component.i18n.source.processor.impl.NameEntryProcessor" }) })
public final class SomeKeys {

	/**
	 * Some message key.
	 */
	public static final String KEY1 = "message.sample.key1";

	/**
	 * Some message key.
	 */
	public static final String KEY2 = "message.sample.key2";

	/**
	 * Some message key.
	 */
	public static final String KEY3 = "message.sample.key3";
	
	/**
	 * Some key.
	 */
	protected Integer someKey;

	/**
	 * Private constructor.
	 */
	private SomeKeys() {
	}
}
