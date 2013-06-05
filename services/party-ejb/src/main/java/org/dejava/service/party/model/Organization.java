package org.dejava.service.party.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;

/**
 * Represents an organization.
 */
@Entity
@Table(name = "organization")
@MessageSources(sources = { @MessageSource(bundleBaseName = "org.dejava.service.party.properties.model", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }) })
public class Organization extends Party {

	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 4239070392385682564L;

	/**
	 * Federal code for the organization.
	 */
	private Integer federalCode;

	/**
	 * Gets the federal code for the organization..
	 * 
	 * @return The federal code for the organization.
	 */
	public Integer getFederalCode() {
		return federalCode;
	}

	/**
	 * Sets the federal code for the organization.
	 * 
	 * @param federalCode
	 *            New federal code for the organization.
	 */
	public void setFederalCode(Integer federalCode) {
		this.federalCode = federalCode;
	}

	/**
	 * Default constructor.
	 */
	public Organization() {
		super();
	}

}
