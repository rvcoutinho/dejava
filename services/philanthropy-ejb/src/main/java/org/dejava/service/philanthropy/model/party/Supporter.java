package org.dejava.service.philanthropy.model.party;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Philanthropy supporter.
 */
@Entity
@Table(name = "supporter")
@MessageSources(sources = {
		@MessageSource(bundleBaseName = "org.dejava.service.philanthropy.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(bundleBaseName = "org.dejava.service.philanthropy.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class Supporter extends PhilanthropyParty {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1190219162283077498L;

}
