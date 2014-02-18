package org.dejava.service.philanthropy.model.party;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.service.party.model.Party;

/**
 * Non-profit organization.
 */
@Entity
@Table(name = "non_profit_org")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.philanthropy.properties.error", processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
public class NonProfitOrg extends PhilanthropyParty {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 3284129300978748094L;

	/**
	 * Default constructor.
	 */
	protected NonProfitOrg() {
		super();
	}

	/**
	 * Default constructor.
	 * 
	 * @param identifier
	 *            The party identifier.
	 */
	public NonProfitOrg(final Integer identifier) {
		super(identifier);
	}

	/**
	 * Default constructor.
	 * 
	 * @param party
	 *            The original party.
	 */
	public NonProfitOrg(final Party party) {
		super();
		// Sets the party id.
		setParty(party);
	}
}
