package org.dejava.service.accesscontrol.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to error keys of the package.
 */
@MessageSources(sources = { @MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.accesscontrol.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class ErrorKeys {

	/**
	 * User not authenticated error key.
	 */
	public static final String NOT_AUTHENTICATED = "user.not.authenticated";

	/**
	 * User not remembered error key.
	 */
	public static final String NOT_REMEMBERED = "user.not.remembered";

	/**
	 * User not guest error key.
	 */
	public static final String NOT_GUEST = "user.not.guest";

	/**
	 * User not authorized error key.
	 */
	public static final String NOT_AUTHORIZED = "user.not.authorized";

	/**
	 * Private constructor.
	 */
	private ErrorKeys() {
	}
}
