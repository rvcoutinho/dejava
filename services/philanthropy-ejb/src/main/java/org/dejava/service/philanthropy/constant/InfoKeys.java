package org.dejava.service.philanthropy.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to info keys of the package.
 */
@MessageSources(sources = { @MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.info", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class InfoKeys {

	/**
	 * Project already shared.
	 */
	public static final String PROJECT_ALREADY_SHARED = "project.shared.already";

	/**
	 * Private constructor.
	 */
	private InfoKeys() {
	}
}
