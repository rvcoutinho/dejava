package org.dejava.service.philanthropy.model;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Category of the philanthropy idea.
 */
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.service.philanthropy.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.EnumTypesEntryProcessor" }) })
public enum Category {

	/**
	 * Environment category.
	 */
	ENVIRONMENT,

	/**
	 * Citizenship category.
	 */
	CITIZENSHIP,

	/**
	 * Art category.
	 */
	ART,

	/**
	 * Education category.
	 */
	EDUCATION,

	/**
	 * Sport category.
	 */
	SPORT,

	/**
	 * Health category.
	 */
	HEALTH;

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return (this.getClass().getSimpleName() + "." + name()).toLowerCase();
	}

}
