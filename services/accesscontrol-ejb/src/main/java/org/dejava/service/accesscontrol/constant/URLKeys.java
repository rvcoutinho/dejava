package org.dejava.service.accesscontrol.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to other keys of the package.
 */
@MessageSources(sources = { @MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.url", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class URLKeys {

	/**
	 * New user URL key.
	 */
	public static final String NEW_USER = "user.new.url";

	/**
	 * New user image URL key.
	 */
	public static final String NEW_USER_IMAGE = "user.new.image.url";

	/**
	 * Private constructor.
	 */
	private URLKeys() {
	}
}
