package org.dejava.service.party.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dejava.component.i18n.source.annotation.MessageSource;
import org.dejava.component.i18n.source.annotation.MessageSources;
import org.dejava.component.validation.method.ArgFormatter;
import org.dejava.service.party.util.MessageTypes;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Represents an organization.
 */
@Entity
@Table(name = "organization")
@MessageSources(sources = {
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.party.properties.model", entriesAffix = {
				"", ".description" }, processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.PublicGettersEntryProcessor" }),
		@MessageSource(sourcePath = "../service-properties/src/main/resources", bundleBaseName = "org.dejava.service.party.properties.error", processSuperclasses = true, processors = { "org.dejava.component.i18n.source.processor.impl.GetterConstraintEntryProcessor" }) })
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
	@NotNull(payload = MessageTypes.Error.class, message = "organization.federalcode.notnull")
	@NotEmpty(payload = MessageTypes.Error.class, message = "organization.federalcode.notempty")
	@Length(payload = MessageTypes.Error.class, message = "organization.federalcode.length", min = 14, max = 14)
	@Column(name = "federal_code")
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
