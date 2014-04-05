package org.dejava.service.philanthropy.model.party;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Philanthropy sponsor.
 */
@Entity
@Table(name = "sponsor")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.model", entriesAffix = {
				"", ".description" }, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class Sponsor extends Party {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = -5747449041899010760L;

	/**
	 * Default constructor.
	 */
	protected Sponsor() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param identifier
	 *            The party identifier.
	 */
	public Sponsor(final Integer identifier) {
		super(identifier);
	}

	/**
	 * Default constructor.
	 * 
	 * @param party
	 *            The original party.
	 */
	public Sponsor(final org.dejava.service.party.model.Party party) {
		super();
		// Sets the party id.
		setParty(party);
	}

}
