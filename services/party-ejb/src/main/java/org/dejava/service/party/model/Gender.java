package org.dejava.service.party.model;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Gender of a person.
 */
@MessageSources(sources = { @MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.party.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.EnumTypesEntryProcessor" }), })
public enum Gender {

	/**
	 * Male gender.
	 */
	MALE,

	/**
	 * Female gender.
	 */
	FEMALE;

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return (this.getClass().getSimpleName() + "." + name()).toLowerCase();
	}

}
