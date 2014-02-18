package org.dejava.service.soupsocial.constant;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Constants related to info keys of the package.
 */
@MessageSources(sources = { @MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.soupsocial.properties.info", processors = { "org.dejava.component.i18n.source.processor.impl.ConstantValuesEntryProcessor" }) })
public final class InfoKeys {

	/**
	 * Create idea info key.
	 */
	public static final String CREATE_IDEA = "idea.create";

	/**
	 * Create project info key.
	 */
	public static final String CREATE_PROJECT = "project.create";

	/**
	 * Private constructor.
	 */
	private InfoKeys() {
	}
}
