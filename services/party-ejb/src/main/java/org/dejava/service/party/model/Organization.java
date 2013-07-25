package org.dejava.service.party.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.component.validation.method.ArgFormatter;

/**
 * Represents an organization.
 */
@Entity
@Table(name = "organization")
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.service.party.properties.model", processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }) })
public class Organization extends Party {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4239070392385682564L;

	/**
	 * Federal code for the organization.
	 */
	private String federalCode;

	/**
	 * Gets the federal code for the organization..
	 * 
	 * @return The federal code for the organization.
	 */
	public String getFederalCode() {
		return federalCode;
	}

	/**
	 * Sets the federal code for the organization.
	 * 
	 * @param federalCode
	 *            New federal code for the organization.
	 */
	public void setFederalCode(final String federalCode) {
		// Sets the new field value (making sure that just numbers are recorded).
		this.federalCode = ArgFormatter.removeInvalidRegex(federalCode, "[^\\d]");
	}

	/**
	 * Default constructor.
	 */
	public Organization() {
		super();
	}

}
