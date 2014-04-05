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
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.model", entriesAffix = {
				"", ".description" }, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class Supporter extends Party {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1190219162283077498L;

	/**
	 * Default constructor.
	 */
	public Supporter() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param identifier
	 *            The party identifier.
	 */
	public Supporter(final Integer identifier) {
		super(identifier);
	}

	/**
	 * Default constructor.
	 * 
	 * @param party
	 *            The original party.
	 */
	public Supporter(final org.dejava.service.party.model.Party party) {
		super(party);
	}

}
