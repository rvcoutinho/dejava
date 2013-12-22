package org.dejava.service.accesscontrol.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to permission keys of the package.
 */
@MessageSources(sources = { @MessageSource(sourcePath = "../service-properties/src/main/resources", availableLocales = { "" }, bundleBaseName = "org.dejava.service.accesscontrol.properties.permission", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class PermissionKeys {

	/**
	 * New user roles key.
	 */
	public static final String NEW_USER_ROLES = "user.new.roles";

	/**
	 * New user roles key.
	 */
	public static final String NEW_USER_PERMISSIONS = "user.new.permissions";

	/**
	 * Private constructor.
	 */
	private PermissionKeys() {
	}
}
