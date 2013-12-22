package org.dejava.service.accesscontrol.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to info keys of the package.
 */
@MessageSources(sources = { @MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.info", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class InfoKeys {

	/**
	 * New user info key.
	 */
	public static final String NEW_USER = "user.new";

	/**
	 * Private constructor.
	 */
	private InfoKeys() {
	}
}
